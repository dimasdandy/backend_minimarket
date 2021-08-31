package com.lawencon.spring.validation;

import org.springframework.stereotype.Component;

import com.lawencon.spring.dto.transaction.InsertCartReqDto;

@Component
public class CartHeaderServiceValidation {

	public void insertValidation(InsertCartReqDto data) throws Exception {
		 if (data.getHeader().getPayment() == null) {
			throw new Exception("Payment method is empty");
		} 
	}

}
