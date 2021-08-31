package com.lawencon.spring.dao;

import java.util.List;

import com.lawencon.spring.model.Members;

public interface MembersDao {

	void insert(Members data) throws Exception;

	void update(Members data) throws Exception;
	
	void delete(Long id) throws Exception;
	
	List<Members> getAll() throws Exception;
	
	Members getById(Long id) throws Exception;
	
	Members getByNameHibernate(String name) throws Exception;
	
	Members getByNameNative(String name) throws Exception;
}
