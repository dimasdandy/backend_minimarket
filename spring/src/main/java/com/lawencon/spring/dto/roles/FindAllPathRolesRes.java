 package com.lawencon.spring.dto.roles;

import java.util.List;

public class FindAllPathRolesRes {

	private String msg;
	private List<FindAllPathRolesResData> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FindAllPathRolesResData> getData() {
		return data;
	}

	public void setData(List<FindAllPathRolesResData> data) {
		this.data = data;
	}

}
