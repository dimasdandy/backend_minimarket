package com.lawencon.spring.dao;

import java.util.List;

import com.lawencon.spring.model.Products;

public interface ProductsDao {

	void insert(Products data) throws Exception;

	void update(Products data) throws Exception;
	
	void delete(Long id) throws Exception;
	
	List<Products> getAll() throws Exception;
	
	Products getById(Long id) throws Exception;
	
	Products getByNameHibernate(String name) throws Exception;
	
	Products getByNameNative(String name) throws Exception;
}
