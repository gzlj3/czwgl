package com.lj.czwgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lj.czwgl.domain.House;
import com.lj.czwgl.repository.HouseRepository;
import com.lj.czwgl.service.ICzwglService;

@Service
@Transactional
public class CzwglService implements ICzwglService {

	@Autowired
	private HouseRepository houseRepository;

	public List<House> getFyglList() {
		List<House> result = new ArrayList<House>();
		Iterable<House> iterObj = houseRepository.findAll();
		iterObj.forEach(obj -> {
			result.add(obj);
		});
		return result;
	}

	public void addFy(House house) throws Exception {
		houseRepository.save(house);
	}

	public String addNewUser() {
		House n = new House();
		n.setHouseid("1");
		houseRepository.save(n);
		return "Saved";
	}
}
