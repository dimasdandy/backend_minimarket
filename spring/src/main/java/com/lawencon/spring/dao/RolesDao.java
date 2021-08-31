package com.lawencon.spring.dao;

import java.util.List;

import com.lawencon.spring.model.Roles;

public interface RolesDao {

	void insert(Roles data) throws Exception;

	void update(Roles data) throws Exception;
	
	void delete(Long id) throws Exception;
	
	List<Roles> getAll() throws Exception;
	
	Roles getById(Long id) throws Exception;
	
	Roles getByNameHibernate(String name) throws Exception;
	
	Roles getByNameNative(String name) throws Exception;
	
	Roles getByCode(String code) throws Exception;
}
