package com.lj.czwgl.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lj.czwgl.domain.House;
import com.lj.czwgl.domain.HouseDto;
import com.lj.czwgl.domain.Housefy;
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
		Iterable<House> iter = houseRepository
				.findByYzhidAndZhxmNotNullOrderByFwmc(yzhid);
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
			if (housefy != null && !"1".equals(housefy.getSfsz()))
				throw new RuntimeException("有帐单未结清(" + housefy.getFwmc()
						+ ")，生成新帐单失败！");
			try {
				// 生成新帐单
				Housefy newHousefy = this.createhousefy(house);
				// 结转房屋数据
				housefyRepository.save(newHousefy);
				house.setSfsz("0"); // 设置房屋为未收租
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
	public void processQrsz(String housefyid) throws Exception {
		Optional<Housefy> housefyOpt = housefyRepository.findById(housefyid);
		if (!housefyOpt.isPresent())
			throw new Exception("房源费用ID不存在：" + housefyid);
		Housefy housefy = housefyOpt.get();
		House house = houseRepository.findById(housefy.getHouseid()).get();

		jzHouse(house, housefy);

		housefyRepository.save(housefy);
		houseRepository.save(house);
	}

	private void jzHouse(House house, Housefy housefy) throws Exception {
		// 更新房源上次收租日期
		house.setSzrq(housefy.getSzrq());
		// 更新房源结转数据
		house.setDscds(housefy.getDbcds());
		house.setSscds(housefy.getSbcds());
		house.setDbcds(null);
		house.setSbcds(null);
		// 是否收租
		house.setSfsz("1");
		housefy.setSfsz("1");
	}

	private Housefy createhousefy(House house) throws Exception {
		// 计算收租范围
		Date szrq = house.getSzrq();
		if (szrq == null)
			throw new Exception("收租日期不能为空！");

		Date xcszrq = Utils.relativeDate(szrq, Calendar.MONTH, 1);
		Date rq1 = Utils.relativeDate(szrq, Calendar.MONTH, -1);
		Date rq2 = Utils.relativeDate(szrq, Calendar.DAY_OF_MONTH, -1);

		Housefy housefy = new Housefy();
		// 主键
		housefy.setHousefyid(Utils.getUUID32());
		// 日期范围
		housefy.setSzrq(xcszrq);
		housefy.setRq1(rq1);
		housefy.setRq2(rq2);

		// 房屋信息
		housefy.setHouseid(house.getHouseid());
		housefy.setFwmc(house.getFwmc());
		housefy.setZhxm(house.getZhxm());
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
		housefy.setQtf(house.getQtf());
		housefy.setSyjzf(house.getSyjzf());

		// 房屋合计费
		housefy.setFyhj(housefy.getDfhj() + housefy.getSfhj() + jsqtfhj(house));

		// 是否收租
		housefy.setSfsz("0");

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
