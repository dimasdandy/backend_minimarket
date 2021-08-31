package com.lawencon.spring.validation;

import org.springframework.stereotype.Component;

import com.lawencon.spring.dto.transaction.InsertCartReqDtoDetail;

@Component
public class CartDetailServiceValidation {

	public void insertValidation(InsertCartReqDtoDetail data) throws Exception {
		if (data.getCartHeader() == null) {
			throw new Exception("ID Cart Header is empty");
		} else if (data.getProduct() == null) {
			throw new Exception("Product is empty");
		} else if (data.getQuantity() == null) {
			throw new Exception("Quantity is empty");
		}
	}

}
