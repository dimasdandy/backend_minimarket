package com.lawencon.spring.service;

import java.util.List;

import com.lawencon.spring.dto.categories.FindAllPathResData;
import com.lawencon.spring.dto.categories.InsertCategoriesReqDto;
import com.lawencon.spring.dto.categories.UpdateCategoriesReqDto;
import com.lawencon.spring.model.Categories;

public interface CategoriesService {

	Long insert(InsertCategoriesReqDto data) throws Exception;

	void update(UpdateCategoriesReqDto data) throws Exception;

	void delete(Long id) throws Exception;

	List<FindAllPathResData> getAll() throws Exception;

	Categories getById(Long id) throws Exception;

	Categories getByNameHibernate(String name) throws Exception;

	Categories getByNameNative(String name) throws Exception;
}
