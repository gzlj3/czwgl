package com.lj.czwgl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.czwgl.common.Results;
import com.lj.czwgl.domain.House;
import com.lj.czwgl.service.ICzwglService;
import com.lj.czwgl.utils.Utils;

@Controller
@ResponseBody
@RequestMapping(path = "/fygl")
public class CzwglController {

	private final String yyhid = "1";

	@Autowired
	private ICzwglService czwglService;

	@GetMapping(path = "/fygl_list")
	public Results<House> getFyglList() {
		Results<House> results = new Results<House>();
		try {
			List<House> result = czwglService.getFyglList();
			results.setData(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	@PostMapping(path = "/fygl_list/{action}")
	public Results<House> postFyglList(@PathVariable short action,
			@RequestBody House house) {
		try {
			String id = Utils.getUUID32();
			house.setHouseid(id);
			house.setYyhid(yyhid);
			czwglService.addFy(house);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Results<House>();
	}

	// @GetMapping(path = "/add")
	// public String addNewUser() {
	// House n = new House();
	// n.setHouseid("1");
	// houseRepository.save(n);
	// return "Saved";
	// }
}
