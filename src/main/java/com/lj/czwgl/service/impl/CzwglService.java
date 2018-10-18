package com.lj.czwgl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lj.czwgl.domain.House;
import com.lj.czwgl.repository.HouseRepository;
import com.lj.czwgl.service.ICzwglService;
import com.lj.czwgl.utils.Utils;

@Service
@Transactional
public class CzwglService implements ICzwglService {

	@Autowired
	private HouseRepository houseRepository;

	public House saveFy(House house) throws Exception {
		return houseRepository.save(house);
	}

	@Override
	public void deleteFy(House house) throws Exception {
		houseRepository.delete(house);
	}

	@Override
	public List<House> queryFyList() throws Exception {
		Iterable<House> iter = houseRepository.findAll();
		return Utils.convertIterToList(iter);
	}

	@Override
	public List<House> querySdbList(String yyhid) throws Exception {
		Iterable<House> iter = houseRepository.findByYyhidAndZhxmNotNull(yyhid);
		return Utils.convertIterToList(iter);
	}
}
