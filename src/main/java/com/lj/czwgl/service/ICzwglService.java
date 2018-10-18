package com.lj.czwgl.service;

import java.util.List;

import com.lj.czwgl.domain.House;
import com.lj.czwgl.domain.HouseDto;

public interface ICzwglService {

	public List<House> queryFyList() throws Exception;
	
	public List<House> querySdbList(String yyhid) throws Exception;

	public House saveFy(House house) throws Exception;

	public void deleteFy(House house) throws Exception;

	public void updateSdbList(HouseDto houseDto) throws Exception;
}