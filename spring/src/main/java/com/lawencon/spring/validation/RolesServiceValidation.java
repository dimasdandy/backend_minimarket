package com.lawencon.spring.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawencon.spring.dto.roles.DeleteDataReqDto;
import com.lawencon.spring.dto.roles.InsertDataReqDto;
import com.lawencon.spring.dto.roles.UpdateDataReqDto;
import com.lawencon.spring.model.Roles;
import com.lawencon.spring.service.RolesService;

@Component
public class RolesServiceValidation {

	@Autowired
	private RolesService rolesService;
	
	public void insertValidation(InsertDataReqDto data) throws Exception {
		if (data.getName() == null || data.getName().trim().equals("")) {
			throw new Exception("Name is empty");
		} else if (data.getCode() == null || data.getCode().trim().equals("")) {
			throw new Exception("Code is empty");
		}
	}

	public void updateValidation(UpdateDataReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("Id is empty");
		} 
		
		Roles roleVersion = rolesService.getById(data.getId());
		if(data.getVersion() != roleVersion.getVersion()) {
			throw new Exception("Invalid version");
		}
	}
	
	public void deleteValidation(DeleteDataReqDto data) throws Exception{
		if(data.getId() == null) {
			throw new Exception("ID is not available");
		}
		
		Roles roleId = rolesService.getById(data.getId());
		if (roleId == null) {
			throw new Exception("ID is not available");
		}
	}
}
