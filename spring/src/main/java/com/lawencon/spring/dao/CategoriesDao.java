package com.lawencon.spring.dao;

import java.util.List;

import com.lawencon.spring.model.Categories;

public interface CategoriesDao {

	void insert(Categories data) throws Exception;

	void update(Categories data) throws Exception;
	
	void delete(Long id) throws Exception;
	
	List<Categories> getAll() throws Exception;
	
	Categories getById(Long id) throws Exception;
	
	Categories getByNameHibernate(String name) throws Exception;
	
	Categories getByNameNative(String name) throws Exception;
}
