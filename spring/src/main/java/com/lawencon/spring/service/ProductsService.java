package com.lawencon.spring.service;

import java.util.List;

import com.lawencon.spring.dto.products.FindAllPathProductsResData;
import com.lawencon.spring.dto.products.InsertProductsReqDto;
import com.lawencon.spring.dto.products.UpdateProductsReqDto;
import com.lawencon.spring.model.Products;

public interface ProductsService {

	Long insert(InsertProductsReqDto data) throws Exception;

	void update(UpdateProductsReqDto data) throws Exception;

	void delete(Long id) throws Exception;

	List<FindAllPathProductsResData> getAll() throws Exception;

	Products getById(Long id) throws Exception;

	Products getByNameHibernate(String name) throws Exception;

	Products getByNameNative(String name) throws Exception;
}
