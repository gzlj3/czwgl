package com.lj.czwgl.service.impl;

import java.util.ArrayList;
import java.util.List;

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
			if(housefy == null){
				Housefy newHousefy = new Housefy();
				newHousefy.setHousefyid(Utils.getUUID32());
			}
		});
	}

	private Double jsFwfy(House house) throws Exception {
		if (house.getSbcds() == null || house.getDbcds() == null)
			throw new Exception("未抄水电表");

		// 计算电费
		Integer dsyds = Utils.getInteger(house.getDbcds())
				- Utils.getInteger(house.getDscds())
				+ Utils.getInteger(house.getDgtds());
		Double df = dsyds * Utils.getDouble(house.getDdj());
		if (df < 0)
			throw new Exception("电费计算小于0");
		// 计算水费
		Integer ssyds = Utils.getInteger(house.getSbcds())
				- Utils.getInteger(house.getSscds())
				+ Utils.getInteger(house.getSgtds());
		Double sf = ssyds * Utils.getDouble(house.getSdj());
		if (sf < 0)
			throw new Exception("水费计算小于0");
		// 计算合计费
		Double fwfy = df + sf + Utils.getInteger(house.getCzje())
				+ Utils.getInteger(house.getWlf())
				+ Utils.getInteger(house.getGlf())
				+ Utils.getInteger(house.getLjf())
				+ Utils.getDouble(house.getQtf())
				+ Utils.getDouble(house.getSyjzf());
		if (fwfy <= 0)
			throw new Exception("无帐单费用");

		return fwfy;
	}

}
