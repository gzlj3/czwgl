package com.lj.czwgl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.czwgl.common.Constants;
import com.lj.czwgl.common.Results;
import com.lj.czwgl.domain.House;
import com.lj.czwgl.domain.HouseDto;
import com.lj.czwgl.domain.Housefy;
import com.lj.czwgl.service.ICzwglService;
import com.lj.czwgl.utils.Utils;

@Controller
@ResponseBody
@RequestMapping(path = "/fygl")
@SuppressWarnings("unchecked")
public class CzwglController {

	private final String yzhid = "1";

	@Autowired
	private ICzwglService czwglService;

	@GetMapping(path = "/fygl_list")
	public Results<House> getFyglList() {
		try {
			List<House> result = czwglService.queryFyList(yzhid);
			Results<House> successResults = (Results<House>) Results
					.getSuccessResults(result);
			return successResults;
		} catch (Exception e) {
			e.printStackTrace();
			Results<House> errorResults = (Results<House>) Results
					.getErrorResults(e.getMessage());
			return errorResults;
		}
	}

	@PostMapping(path = "/fygl_list/{action}")
	public Results<House> postFyglList(@PathVariable short action,
			@RequestBody House house) {
		try {
			if (action == Constants.BUTTON_ADDFY) {
				String id = Utils.getUUID32();
				house.setHouseid(id);
				house.setYzhid(yzhid);
				house.setSfsz("1"); // 初始签约设置为已收租状态
				czwglService.saveFy(house);
			} else if (action == Constants.BUTTON_EDITFY) {
				czwglService.saveFy(house);
			} else if (action == Constants.BUTTON_DELETEFY) {
				czwglService.deleteFy(house);
			}
			Results<House> successResults = (Results<House>) Results
					.getSuccessResults(house);
			return successResults;
		} catch (Exception e) {
			e.printStackTrace();
			Results<House> errorResults = (Results<House>) Results
					.getErrorResults(e.getMessage());
			return errorResults;
		}
	}

	@GetMapping(path = "/sdb_list")
	public Results<House> getSdbList() {
		try {
			List<House> result = czwglService.querySdbList(yzhid);
			Results<House> successResults = (Results<House>) Results
					.getSuccessResults(result);
			return successResults;
		} catch (Exception e) {
			e.printStackTrace();
			Results<House> errorResults = (Results<House>) Results
					.getErrorResults(e.getMessage());
			return errorResults;
		}
	}

	@PostMapping(path = "/sdb_list")
	public Results<House> postSdbList(@RequestBody HouseDto houseDto) {
		try {
			czwglService.updateSdbList(houseDto);
			return this.getFyglList();
		} catch (Exception e) {
			e.printStackTrace();
			Results<House> errorResults = (Results<House>) Results
					.getErrorResults(e.getMessage());
			return errorResults;
		}
	}

	@GetMapping(path = "/zd_list")
	public Results<House> getZdList() {
		try {
			HouseDto result = czwglService.queryZdList(yzhid);
			Results<House> successResults = (Results<House>) Results
					.getSuccessResults(result);
			return successResults;
		} catch (Exception e) {
			e.printStackTrace();
			Results<House> errorResults = (Results<House>) Results
					.getErrorResults(e.getMessage());
			return errorResults;
		}
	}

	@PostMapping(path = "/zd_list")
	public Results<House> postZdList(@RequestBody HouseDto houseDto) {
		try {
			czwglService.updateZdList(houseDto);
			return this.getFyglList();
		} catch (Exception e) {
			e.printStackTrace();
			Results<House> errorResults = (Results<House>) Results
					.getErrorResults(e.getMessage());
			return errorResults;
		}
	}

	@GetMapping(path = "/lastzd")
	public Results<Housefy> getLastZdList(@RequestParam String houseid) {
		try {
			List<Housefy> result = czwglService.queryLastZdList(houseid);
			Results<Housefy> successResults = (Results<Housefy>) Results
					.getSuccessResults(result);
			return successResults;
		} catch (Exception e) {
			e.printStackTrace();
			Results<Housefy> errorResults = (Results<Housefy>) Results
					.getErrorResults(e.getMessage());
			return errorResults;
		}
	}

	@GetMapping(path = "/handlezd")
	public Results<House> handleZd(@RequestParam String housefyid) {
		try {
			czwglService.processQrsz(housefyid);
			return this.getFyglList();
		} catch (Exception e) {
			e.printStackTrace();
			Results<House> errorResults = (Results<House>) Results
					.getErrorResults(e.getMessage());
			return errorResults;
		}
	}

}
