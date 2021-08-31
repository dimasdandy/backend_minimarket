package com.lawencon.spring.dto.payments;

import java.util.List;

public class FindAllPathPaymentsRes {

	private String msg;
	private List<FindAllPathPaymentsResData> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FindAllPathPaymentsResData> getData() {
		return data;
	}

	public void setData(List<FindAllPathPaymentsResData> data) {
		this.data = data;
	}

}
