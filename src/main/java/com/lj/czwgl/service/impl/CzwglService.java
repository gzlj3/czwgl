package com.lj.czwgl.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lj.czwgl.common.Constants;
import com.lj.czwgl.domain.House;
import com.lj.czwgl.domain.HouseDto;
import com.lj.czwgl.domain.Housefy;
import com.lj.czwgl.domain.Userb;
import com.lj.czwgl.repository.HouseRepository;
import com.lj.czwgl.repository.HousefyRepository;
import com.lj.czwgl.service.ICzwglService;
import com.lj.czwgl.utils.Utils;

@SuppressWarnings("unchecked")
@Service
@Transactional(rollbackFor = Exception.class)
public class CzwglService implements ICzwglService {

	@Autowired
	private HouseRepository houseRepository;
	@Autowired
	private HousefyRepository housefyRepository;

	public House saveFy(House house) throws Exception {
		if (Utils.empty(house.getHousefyid()) && !Utils.empty(house.getZhxm())) {
			// 如果为新签约，则自动生成合同帐单
			Housefy newHousefy = this.makeHousefy(house, null,
					Constants.ZDLX_HTZD);
			// 结转房屋数据
			housefyRepository.save(newHousefy);
		}
		return houseRepository.save(house);
	}

	@Override
	public void deleteFy(House house) throws Exception {
		houseRepository.delete(house);
	}

	@Override
	public List<House> queryFyList(String yzhid) throws Exception {
		Iterable<House> iter = houseRepository.findByYzhidOrderByFwmc(yzhid);
		return Utils.convertIterToList(iter);
	}

	@Override
	public List<House> querySdbList(String yzhid) throws Exception {
		Date date = Utils.relativeDate(new Date(), Calendar.DAY_OF_MONTH, 4);
		Iterable<House> iter = houseRepository.findSdbList(yzhid, date);
		return Utils.convertIterToList(iter);
	}

	@Override
	public void updateSdbList(HouseDto houseDto) throws Exception {
		List<House> rows = houseDto.getRows();
		rows.forEach(house -> {
			houseRepository.updateHouseSdb(house.getDbcds(), house.getSbcds(),
					house.getHouseid());
		});
	}

	@Transactional(readOnly = true)
	public HouseDto queryZdList(String yzhid) throws Exception {
		Iterable<House> iter = houseRepository.queryZdList(yzhid);
		List<String> SelectedRowKeys = new ArrayList<String>();
		iter.forEach(house -> {
			house.setBz(null);
			house.setQtf(null);
			try {
				house.setQtf(jsFwfy(house));
				SelectedRowKeys.add(house.getHouseid());
			} catch (Exception e) {
				house.setBz(e.getMessage());
			}
		});
		HouseDto houseDto = new HouseDto();
		houseDto.setRows(Utils.convertIterToList(iter));
		houseDto.setSelectedRowKeys(SelectedRowKeys);
		return houseDto;
	}

	public void updateZdList(HouseDto houseDto) throws Exception {
		List<String> ids = houseDto.getSelectedRowKeys();
		Iterable<House> iter = houseRepository.findAllById(ids);
		iter.forEach(house -> {
			Housefy housefy = housefyRepository
					.findFirstByHouseidOrderByRq1Desc(house.getHouseid());
			if (housefy != null && "0".equals(housefy.getSfsz()))
				throw new RuntimeException("有帐单未结清(" + housefy.getFwmc()
						+ ")，生成新帐单失败！");
			try {
				// 生成新帐单
				Housefy newHousefy = this.makeHousefy(house, null,
						Constants.ZDLX_YJZD);
				housefyRepository.save(newHousefy);
				houseRepository.save(house);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public List<Housefy> queryLastZdList(String houseid) throws Exception {
		return housefyRepository.findTop6ByHouseidOrderBySzrqDesc(houseid);
	}

	@Override
	public void processQrsz(String housefyid, String flag, Userb curUser)
			throws Exception {
		Optional<Housefy> housefyOpt = housefyRepository.findById(housefyid);
		if (!housefyOpt.isPresent())
			throw new Exception("房源费用ID不存在：" + housefyid);
		Housefy housefy = housefyOpt.get();
		House house = houseRepository.findById(housefy.getHouseid()).get();

		if ("sxzd".equals(flag) && !housefyid.equals(house.getHousefyid()))
			throw new Exception("帐单数据与主房屋不匹配，刷新帐单失败！");

		if ("sxzd".equals(flag)) {
			// 刷新帐单
			this.makeHousefy(house, housefy, null);
		} else {
			jzHouse(house, housefy, flag);
		}

		house.setZhxgr(curUser.getUserid());
		house.setZhxgsj(Utils.getCurrentTimestamp());
		housefy.setZhxgr(curUser.getUserid());
		housefy.setZhxgsj(Utils.getCurrentTimestamp());
		housefyRepository.save(housefy);
		houseRepository.save(house);
	}

	private void jzHouse(House house, Housefy housefy, String flag)
			throws Exception {
		// 更新房源上次收租日期
		house.setSzrq(housefy.getSzrq());
		// 更新房源结转数据
		house.setDscds(housefy.getDbcds());
		house.setSscds(housefy.getSbcds());
		house.setDbcds(null);
		house.setSbcds(null);
		if ("jzzd".equals(flag)) {
			// 结转帐单
			house.setSyjzf(housefy.getFyhj());
			house.setSfsz("2");
			housefy.setSfsz("2");
		} else if ("qrsz".equals(flag)) {
			// 确认收租
			house.setSyjzf(null);
			house.setSfsz("1");
			housefy.setSfsz("1");
		} else {
			throw new Exception("未知的帐单处理动作，帐单处理失败！");
		}
	}

	/**
	 * 生成新帐单,同时更新房屋主数据
	 * 
	 * @param house
	 * @param housefy
	 *            如果传入有housefy，则表示刷新当前帐单
	 * @param zdlx
	 *            帐单类型（0：合同帐单，1：月结帐单，2：退房帐单）
	 * @return 新生成的帐单
	 * @throws Exception
	 */
	private Housefy makeHousefy(House house, Housefy housefy, String zdlx)
			throws Exception {
		// 计算收租范围
		Date szrq = house.getSzrq();
		if (szrq == null)
			throw new Exception("收租日期不能为空！");

		if (housefy == null) {
			housefy = new Housefy();
			housefy.setHousefyid(Utils.getUUID32());
			housefy.setLrr(house.getLrr());
			housefy.setLrsj(house.getLrsj());
		} else {
			zdlx = housefy.getZdlx();
		}

		Date xcszrq, rq1, rq2;
		if (Constants.ZDLX_HTZD.equals(zdlx)) {
			// 合同帐单的下次收租日期为当前录入的时间
			xcszrq = szrq;
			rq1 = house.getHtrqq(); // 收租范围起始时间为合同日期起
			rq2 = Utils.relativeDate(szrq, Calendar.DAY_OF_MONTH, -1);
			int days = Utils.daysBetween(rq1, rq2) + 1;
			if (days < 0)
				throw new Exception("下次收租日期不能小于合同起始日期！");
			int yzj;
			if (days == 30 || days == 31)
				yzj = house.getCzje();
			else
				yzj = (int) Math.round((new Double(house.getCzje()) / 30)
						* days); // 计算合同签约时月租金
			housefy.setCzje(yzj); // 月租金
			housefy.setYj(house.getYj()); // 押金
			housefy.setQtf(house.getQtf());
			housefy.setDbcds(house.getDscds());
			housefy.setSbcds(house.getSscds());
			// 房屋合计费
			housefy.setFyhj(Utils.getInteger(housefy.getCzje())
					+ Utils.getInteger(housefy.getYj())
					+ Utils.getDouble(housefy.getQtf()));
		} else {
			xcszrq = Utils.relativeDate(szrq, Calendar.MONTH, 1);
			rq1 = Utils.relativeDate(szrq, Calendar.MONTH, -1);
			rq2 = Utils.relativeDate(szrq, Calendar.DAY_OF_MONTH, -1);

			housefy.setCzje(house.getCzje());
			// 电费数据
			housefy.setDbcds(house.getDbcds());
			housefy.setDscds(house.getDscds());
			housefy.setDsyds(housefy.getDbcds() - housefy.getDscds());
			housefy.setDgtds(house.getDgtds());
			housefy.setDdj(house.getDdj());
			housefy.setDfhj(jsdf(house));

			// 水费数据
			housefy.setSbcds(house.getSbcds());
			housefy.setSscds(house.getSscds());
			housefy.setSsyds(housefy.getSbcds() - housefy.getSscds());
			housefy.setSgtds(house.getSgtds());
			housefy.setSdj(house.getSdj());
			housefy.setSfhj(jssf(house));
			// 房屋其它费用
			housefy.setGlf(house.getGlf());
			housefy.setWlf(house.getWlf());
			housefy.setLjf(house.getLjf());
			housefy.setSyjzf(house.getSyjzf());
			housefy.setQtf(house.getQtf());

			// 房屋合计费
			housefy.setFyhj(Utils.getDouble(housefy.getDfhj())
					+ Utils.getDouble(housefy.getSfhj()) + jsqtfhj(house));
		}

		// 日期范围
		housefy.setSzrq(xcszrq);
		housefy.setRq1(rq1);
		housefy.setRq2(rq2);

		// 房屋信息
		housefy.setHouseid(house.getHouseid());
		housefy.setFwmc(house.getFwmc());
		housefy.setZhxm(house.getZhxm());

		// 是否收租
		housefy.setSfsz("0");
		housefy.setZdlx(zdlx);

		// 生成更新人和时间
		housefy.setYzhid(house.getYzhid());
		housefy.setZhxgr(house.getZhxgr());
		housefy.setZhxgsj(house.getZhxgsj());

		// 更新房屋数据
		house.setSfsz("0"); // 设置房屋为未收租
		house.setZdlx(zdlx);
		house.setRq1(housefy.getRq1());
		house.setRq2(housefy.getRq2());
		house.setFyhj(housefy.getFyhj());
		house.setHousefyid(housefy.getHousefyid());
		return housefy;
	}

	private Double jsFwfy(House house) throws Exception {
		if (house.getSbcds() == null || house.getDbcds() == null)
			throw new Exception("未抄水电表");

		// 计算电费
		Double df = jsdf(house);
		if (df < 0)
			throw new Exception("电费计算小于0");
		// 计算水费
		Double sf = jssf(house);
		if (sf < 0)
			throw new Exception("水费计算小于0");
		// 计算合计费
		Double fwfy = df + sf + jsqtfhj(house);
		if (fwfy <= 0)
			throw new Exception("无帐单费用");

		return fwfy;
	}

	private Double jsdf(House house) {
		Integer dsyds = Utils.getInteger(house.getDbcds())
				- Utils.getInteger(house.getDscds())
				+ Utils.getInteger(house.getDgtds());
		Double df = dsyds * Utils.getDouble(house.getDdj());
		return df;
	}

	private Double jssf(House house) {
		Integer ssyds = Utils.getInteger(house.getSbcds())
				- Utils.getInteger(house.getSscds())
				+ Utils.getInteger(house.getSgtds());
		Double sf = ssyds * Utils.getDouble(house.getSdj());
		return sf;
	}

	private Double jsqtfhj(House house) {
		Double qtfy = Utils.getInteger(house.getCzje())
				+ Utils.getInteger(house.getWlf())
				+ Utils.getInteger(house.getGlf())
				+ Utils.getInteger(house.getLjf())
				+ Utils.getDouble(house.getQtf())
				+ Utils.getDouble(house.getSyjzf());
		return qtfy;
	}
}
