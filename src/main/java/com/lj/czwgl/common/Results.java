package com.lj.czwgl.common;

public class Results<T> {
	int status=0;
	String msg="";
	T data[];

	public Results(int status, String msg, T[] data) {
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
	public T[] getData() {
		return data;
	}
	public void setData(T[] data) {
		this.data = data;
	}
	
}
