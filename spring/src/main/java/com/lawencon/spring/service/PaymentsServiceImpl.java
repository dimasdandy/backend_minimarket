package com.lawencon.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.spring.dao.PaymentsDao;
import com.lawencon.spring.dto.payments.DeletePaymentsReqDto;
import com.lawencon.spring.dto.payments.FindAllPathPaymentsResData;
import com.lawencon.spring.dto.payments.InsertPaymentsReqDto;
import com.lawencon.spring.dto.payments.UpdatePaymentsReqDto;
import com.lawencon.spring.model.Payments;
import com.lawencon.spring.validation.PaymentsServiceValidation;

@Service
public class PaymentsServiceImpl extends BaseServiceImpl implements PaymentsService {

	private PaymentsDao paymentsDao;

	@Autowired
	public void setPaymentsDao(PaymentsDao paymentsDao) {
		this.paymentsDao = paymentsDao;
	}

	@Autowired
	private PaymentsServiceValidation paymentValidation;

	@Override
	@Transactional
	public Long insert(InsertPaymentsReqDto data) throws Exception {
		paymentValidation.insertValidation(data);
		Payments payments = new Payments();
		payments.setCode(data.getCode());
		payments.setName(data.getName());
		payments.setCreatedBy(super.users());
		payments.setIsActive(data.getIsActive());
		paymentsDao.insert(payments);
		return payments.getId();
	}

	@Override
	@Transactional
	public void update(UpdatePaymentsReqDto data) throws Exception {
		paymentValidation.updateValidation(data);
		Payments paymentsUpdate = paymentsDao.getById(data.getId());
		paymentsUpdate.setName(data.getName());
		paymentsUpdate.setCode(data.getCode());
		paymentsUpdate.setUpdatedBy(super.users());
		paymentsUpdate.setIsActive(data.getIsActive());
		paymentsUpdate.setVersion(data.getVersion());
		paymentsDao.update(paymentsUpdate);

		Payments paymentsVersion = paymentsDao.getById(data.getId());
		data.setVersion(paymentsVersion.getVersion());
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		DeletePaymentsReqDto delete = new DeletePaymentsReqDto();
		delete.setId(id);
		paymentValidation.deleteValidation(delete);
		paymentsDao.delete(id);
	}

	@Override
	public List<FindAllPathPaymentsResData> getAll() throws Exception {

		List<FindAllPathPaymentsResData> findAll = new ArrayList<FindAllPathPaymentsResData>();

		List<Payments> payments = paymentsDao.getAll();
		for (Payments payment : payments) {
			FindAllPathPaymentsResData allPath = new FindAllPathPaymentsResData();
			allPath.setId(payment.getId());
			allPath.setCode(payment.getCode());
			allPath.setName(payment.getName());
			findAll.add(allPath);
		}
		return findAll;
	}

	@Override
	public Payments getById(Long id) throws Exception {
		return paymentsDao.getById(id);
	}

	@Override
	public Payments getByNameHibernate(String name) throws Exception {
		return paymentsDao.getByNameHibernate(name);
	}

	@Override
	public Payments getByNameNative(String name) throws Exception {
		return paymentsDao.getByNameNative(name);
	}

}
