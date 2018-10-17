package com.lj.czwgl.service;


import java.util.List;
import com.lj.czwgl.domain.House;


public interface ICzwglService {

	public List<House> getFyglList() throws Exception;

	public void addFy(House house)  throws Exception;

}
