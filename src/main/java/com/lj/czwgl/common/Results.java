package com.lj.czwgl.common;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Results<T> {
	int status = Constants.REMOTE_SUCCESS;
	String msg = "";
	List<T> data = null;

	public static Results<?> getErrorResults(String msg) {
		return new Results<>(Constants.REMOTE_ERROR, msg);
	}

	public static Results<?> getSuccessResults() {
		return new Results<>();
	}

	public static Results<?> getSuccessResults(Object data) {
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		list.add(data);
		return new Results<>(Constants.REMOTE_SUCCESS, "", list);
	}

	public static Results<?> getSuccessResults(List<?> data) {
		return new Results<>(Constants.REMOTE_SUCCESS, "", data);
	}

	public Results() {
	}

	public Results(int status, String msg) {
		this(status, msg, null);
	}

	public Results(int status, String msg, List<T> data) {
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
