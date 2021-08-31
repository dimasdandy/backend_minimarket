package com.lawencon.spring.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawencon.spring.dto.payments.DeletePaymentsReqDto;
import com.lawencon.spring.dto.payments.InsertPaymentsReqDto;
import com.lawencon.spring.dto.payments.UpdatePaymentsReqDto;
import com.lawencon.spring.model.Payments;
import com.lawencon.spring.service.PaymentsService;

@Component
public class PaymentsServiceValidation {

	@Autowired
	private PaymentsService paymentsService;

	public void insertValidation(InsertPaymentsReqDto data) throws Exception {
		if (data.getName() == null || data.getName().trim().equals("")) {
			throw new Exception("Name is empty");
		} else if (data.getCode() == null || data.getCode().trim().equals("")) {
			throw new Exception("Code is empty");
		}
	}

	public void updateValidation(UpdatePaymentsReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("Id is empty");
		}

		Payments paymentVersion = paymentsService.getById(data.getId());
		if (data.getVersion() != paymentVersion.getVersion()) {
			throw new Exception("Invalid version");
		}
	}

	public void deleteValidation(DeletePaymentsReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("ID is not available");
		}

		Payments paymentId = paymentsService.getById(data.getId());
		if (paymentId == null) {
			throw new Exception("ID is not available");
		}
	}

}
