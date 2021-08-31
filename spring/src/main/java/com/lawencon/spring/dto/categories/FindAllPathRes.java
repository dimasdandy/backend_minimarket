package com.lawencon.spring.dto.categories;

import java.util.List;

public class FindAllPathRes {

	private String msg;
	private List<FindAllPathResData> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FindAllPathResData> getData() {
		return data;
	}

	public void setData(List<FindAllPathResData> data) {
		this.data = data;
	}

}
