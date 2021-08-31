package com.lawencon.spring.dto.members;

import java.util.List;

public class FindAllPathMembersRes {

	private String msg;
	private List<FindAllPathMembersResData> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FindAllPathMembersResData> getData() {
		return data;
	}

	public void setData(List<FindAllPathMembersResData> data) {
		this.data = data;
	}

}
