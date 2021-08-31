package com.lawencon.spring.dao;

import java.util.List;

import com.lawencon.spring.model.Users;

public interface UsersDao {

	void insert(Users data) throws Exception;

	void update(Users data) throws Exception;

	void delete(Long id) throws Exception;
	
	List<Users> getAll() throws Exception;

	Users getById(Long id) throws Exception;

	Users getByUsernameHibernate(String username) throws Exception;

	Users getByUsernameNative(String username) throws Exception;
}
