package com.lawencon.spring.dto.users;

import java.util.List;

public class FindAllPathUsersRes {

	private String msg;
	private List<FindAllPathUsersResData> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FindAllPathUsersResData> getData() {
		return data;
	}

	public void setData(List<FindAllPathUsersResData> data) {
		this.data = data;
	}

}
