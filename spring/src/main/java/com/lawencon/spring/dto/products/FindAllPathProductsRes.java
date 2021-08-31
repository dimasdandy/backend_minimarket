package com.lawencon.spring.dto.products;

import java.util.List;

public class FindAllPathProductsRes {

	private String msg;
	private List<FindAllPathProductsResData> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FindAllPathProductsResData> getData() {
		return data;
	}

	public void setData(List<FindAllPathProductsResData> data) {
		this.data = data;
	}

}
