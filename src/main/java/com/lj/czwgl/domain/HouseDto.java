package com.lj.czwgl.domain;

import java.util.List;

public class HouseDto {
	List<House> rows;
	List<String> selectedRowKeys;

	public List<House> getRows() {
		return rows;
	}

	public void setRows(List<House> rows) {
		this.rows = rows;
	}

	public List<String> getSelectedRowKeys() {
		return selectedRowKeys;
	}

	public void setSelectedRowKeys(List<String> selectedRowKeys) {
		this.selectedRowKeys = selectedRowKeys;
	}


}
