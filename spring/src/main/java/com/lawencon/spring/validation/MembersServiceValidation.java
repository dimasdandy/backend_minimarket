package com.lawencon.spring.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawencon.spring.dto.members.DeleteMembersReqDto;
import com.lawencon.spring.dto.members.InsertMembersReqDto;
import com.lawencon.spring.dto.members.UpdateMembersReqDto;
import com.lawencon.spring.model.Members;
import com.lawencon.spring.service.MembersService;

@Component
public class MembersServiceValidation {

	@Autowired
	private MembersService membersService;

	public void insertValidation(InsertMembersReqDto data) throws Exception {
		if (data.getName() == null || data.getName().trim().equals("")) {
			throw new Exception("Name is empty");
		} else if (data.getAddress() == null || data.getAddress().trim().equals("")) {
			throw new Exception("Address is empty");
		} else if (data.getPhoneNo() == null || data.getPhoneNo().trim().equals("")) {
			throw new Exception("Phone number is empty");
		}
	}

	public void updateValidation(UpdateMembersReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("Id is empty");
		}

		Members membersVersion = membersService.getById(data.getId());
		if (data.getVersion() != membersVersion.getVersion()) {
			throw new Exception("Invalid version");
		}
	}

	public void deleteValidation(DeleteMembersReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("ID is not available");
		}

		Members memberId = membersService.getById(data.getId());
		if (memberId == null) {
			throw new Exception("ID is not available");
		}
	}
}
