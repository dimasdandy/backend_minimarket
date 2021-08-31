package com.lawencon.spring.service.transaction;

import java.util.List;

import com.lawencon.spring.dto.transaction.FindAllCartHdrResData;
import com.lawencon.spring.dto.transaction.InsertCartReqDto;
import com.lawencon.spring.model.CartHeaders;

public interface CartHeaderService {

	Long insert(InsertCartReqDto cartHeaders) throws Exception;

	List<FindAllCartHdrResData> getAll() throws Exception;
	
	CartHeaders getById(Long id) throws Exception;
}
