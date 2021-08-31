package com.lawencon.spring.dao.transaction;

import java.util.List;

import com.lawencon.spring.model.CartDetails;

public interface CartDetailDao {

	void insert(CartDetails data) throws Exception;
	
	List<CartDetails> getAllDtl(Long id) throws Exception;

}
