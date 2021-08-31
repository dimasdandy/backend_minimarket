package com.lawencon.spring.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawencon.spring.dto.users.DeleteUserReqDto;
import com.lawencon.spring.dto.users.InsertUserReqDto;
import com.lawencon.spring.dto.users.UpdateUserReqDto;
import com.lawencon.spring.model.Users;
import com.lawencon.spring.service.UsersService;

@Component
public class UsersServiceValidation {

	@Autowired
	private UsersService usersService;

	public void insertValidation(InsertUserReqDto data) throws Exception {
		if (data.getName() == null || data.getName().trim().equals("")) {
			throw new Exception("Name is empty");
		} else if (data.getUsername() == null || data.getUsername().trim().equals("")) {
			throw new Exception("Username is empty");
		} else if (data.getPassword() == null || data.getPassword().trim().equals("")) {
			throw new Exception("Password is empty");
		} else if (data.getRole() == null || data.getRole() == 0) {
			throw new Exception("Roles is empty");
		}
	}

	public void updateValidation(UpdateUserReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("Id is empty");
		}

		Users userVersion = usersService.getById(data.getId());
		if (data.getVersion() != userVersion.getVersion()) {
			throw new Exception("Invalid version");
		}
	}

	public void deleteValidation(DeleteUserReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("ID is not available");
		}

		Users userId = usersService.getById(data.getId());
		if (userId == null) {
			throw new Exception("ID is not available");
		}
	}
}
