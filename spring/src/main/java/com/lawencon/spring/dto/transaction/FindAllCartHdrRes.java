package com.lawencon.spring.dto.transaction;

import java.util.List;

public class FindAllCartHdrRes {

	private String msg;
	private List<FindAllCartHdrResData> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FindAllCartHdrResData> getData() {
		return data;
	}

	public void setData(List<FindAllCartHdrResData> data) {
		this.data = data;
	}

}
