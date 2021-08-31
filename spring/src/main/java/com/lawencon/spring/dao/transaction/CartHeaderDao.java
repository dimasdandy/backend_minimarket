package com.lawencon.spring.dao.transaction;

import java.util.List;

import com.lawencon.spring.dto.transaction.FindAllCartHdrResData;
import com.lawencon.spring.model.CartHeaders;

public interface CartHeaderDao {

	void insert(CartHeaders data) throws Exception;
	
	List<CartHeaders> getAll() throws Exception;
	
	CartHeaders getById(Long id) throws Exception;
	
	List<FindAllCartHdrResData> getTotalPrice() throws Exception;
}
