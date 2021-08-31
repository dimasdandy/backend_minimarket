package com.lawencon.spring.dto.transaction;

import java.util.List;

public class FindAllCartDtlRes {

	private String msg;
	private List<FindAllCartDtlResData> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FindAllCartDtlResData> getData() {
		return data;
	}

	public void setData(List<FindAllCartDtlResData> data) {
		this.data = data;
	}

}
