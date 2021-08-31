package com.lawencon.spring.service;

import java.util.List;

import com.lawencon.spring.dto.users.FindAllPathUsersResData;
import com.lawencon.spring.dto.users.InsertUserReqDto;
import com.lawencon.spring.dto.users.UpdateUserReqDto;
import com.lawencon.spring.model.Users;

public interface UsersService {

	Long insert(InsertUserReqDto users) throws Exception;

	void update(UpdateUserReqDto data) throws Exception;
	
	void delete(Long id) throws Exception;
	
	List<FindAllPathUsersResData> getAll() throws Exception;
	
	Users getById(Long id) throws Exception;
	
	Users getByUsernameHibernate(String username) throws Exception;

	Users getByUsernameNative(String username) throws Exception;
}
