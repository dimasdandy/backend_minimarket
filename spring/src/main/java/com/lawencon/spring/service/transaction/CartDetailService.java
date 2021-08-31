package com.lawencon.spring.service.transaction;

import java.util.List;

import com.lawencon.spring.dto.transaction.FindAllCartDtlResData;
import com.lawencon.spring.dto.transaction.InsertCartReqDtoDetail;

public interface CartDetailService {

	Long insert(InsertCartReqDtoDetail cartDetails) throws Exception;

	List<FindAllCartDtlResData> getAllDtl(Long id) throws Exception;

}
