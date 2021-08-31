package com.lawencon.spring.dto.transaction;

import java.util.List;

public class InsertCartReqDto {

	private InsertCartReqDtoHeader header;
	private List<InsertCartReqDtoDetail> details;

	public InsertCartReqDtoHeader getHeader() {
		return header;
	}

	public void setHeader(InsertCartReqDtoHeader header) {
		this.header = header;
	}

	public List<InsertCartReqDtoDetail> getDetails() {
		return details;
	}

	public void setDetails(List<InsertCartReqDtoDetail> details) {
		this.details = details;
	}

}
