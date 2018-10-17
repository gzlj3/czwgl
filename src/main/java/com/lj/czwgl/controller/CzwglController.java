package com.lj.czwgl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.czwgl.common.Results;
import com.lj.czwgl.domain.House;
import com.lj.czwgl.repository.HouseRepository;

@Controller
@ResponseBody
@RequestMapping(path = "/fygl")
public class CzwglController {

	@Autowired
	private HouseRepository houseRepository;

	@GetMapping(path = "/fygl_list")
	public Iterable<House> getFyglList() {
		Iterable<House> result = houseRepository.findAll();
		return result;
	}

	@PostMapping(path = "/fygl_list")
	public Results<House> postFyglList(@RequestBody String s) {
		return new Results<House>(0, "", null);
	}

	@GetMapping(path = "/add")
	public String addNewUser() {
		House n = new House();
		n.setHouseid("1");
		houseRepository.save(n);
		return "Saved";
	}
}
