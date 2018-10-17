package com.lj.czwgl.common;

import java.util.List;

public class Results<T> {
	int status = Constants.REMOTE_SUCCESS;
	String msg = "";
	List<T> data = null;

	public Results() {
	}

	public Results(int status, String msg, List<T> data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
