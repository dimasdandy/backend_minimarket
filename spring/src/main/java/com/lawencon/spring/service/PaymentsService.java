package com.lawencon.spring.service;

import java.util.List;

import com.lawencon.spring.dto.payments.FindAllPathPaymentsResData;
import com.lawencon.spring.dto.payments.InsertPaymentsReqDto;
import com.lawencon.spring.dto.payments.UpdatePaymentsReqDto;
import com.lawencon.spring.model.Payments;

public interface PaymentsService {

	Long insert(InsertPaymentsReqDto data) throws Exception;

	void update(UpdatePaymentsReqDto data) throws Exception;

	void delete(Long id) throws Exception;

	List<FindAllPathPaymentsResData> getAll() throws Exception;

	Payments getById(Long id) throws Exception;

	Payments getByNameHibernate(String name) throws Exception;

	Payments getByNameNative(String name) throws Exception;
}
