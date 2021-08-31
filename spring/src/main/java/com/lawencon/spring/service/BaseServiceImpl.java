package com.lawencon.spring.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawencon.spring.model.Users;
import com.lawencon.spring.security.AuthPrincipal;

public class BaseServiceImpl {

	@Autowired
	private AuthPrincipal authPrincipal;
	
	@Autowired
	private UsersService usersService;
	
	public Users users() throws Exception{
		Long userId = Long.valueOf(authPrincipal.getAuthentication());
        Users user = usersService.getById(userId);
		return user;
	}
}
