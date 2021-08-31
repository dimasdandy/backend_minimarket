package com.lawencon.spring.dao;

import java.util.List;

import com.lawencon.spring.model.Payments;

public interface PaymentsDao {

	void insert(Payments data) throws Exception;

	void update(Payments data) throws Exception;
	
	void delete(Long id) throws Exception;
	
	List<Payments> getAll() throws Exception;
	
	Payments getById(Long id) throws Exception;
	
	Payments getByNameHibernate(String name) throws Exception;
	
	Payments getByNameNative(String name) throws Exception;
}
