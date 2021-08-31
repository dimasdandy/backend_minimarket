package com.lawencon.spring.service.transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.spring.dao.transaction.CartHeaderDao;
import com.lawencon.spring.dto.transaction.FindAllCartHdrResData;
import com.lawencon.spring.dto.transaction.InsertCartReqDto;
import com.lawencon.spring.dto.transaction.InsertCartReqDtoDetail;
import com.lawencon.spring.model.CartHeaders;
import com.lawencon.spring.model.Members;
import com.lawencon.spring.model.Payments;
import com.lawencon.spring.model.Users;
import com.lawencon.spring.service.BaseServiceImpl;
import com.lawencon.spring.service.MembersService;
import com.lawencon.spring.service.PaymentsService;
import com.lawencon.spring.service.UsersService;
import com.lawencon.spring.validation.CartHeaderServiceValidation;

@Service
public class CartHeaderServiceImpl extends BaseServiceImpl implements CartHeaderService {

	@Autowired
	private CartHeaderDao cartHeaderDao;
	
	@Autowired
	private CartDetailService cartDetailService;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private PaymentsService paymentsService;
	
	@Autowired
	private MembersService membersService;
	
	@Autowired
	private CartHeaderServiceValidation cartHdrValidation;

	@Override
	@Transactional
	public Long insert(InsertCartReqDto data) throws Exception {
		cartHdrValidation.insertValidation(data);
		
		CartHeaders cartHeaders = new CartHeaders();
		Users users = usersService.getById(data.getHeader().getUser());
		Payments payments = paymentsService.getById(data.getHeader().getPayment());
		Members members = membersService.getById(data.getHeader().getMember());
		cartHeaders.setTransactionDate(LocalDateTime.now());
		cartHeaders.setUser(super.users());
		cartHeaders.setPayment(payments);
		cartHeaders.setMember(members);
		cartHeaders.setIsActive(true);
		cartHeaders.setCreatedBy(super.users());
		cartHeaderDao.insert(cartHeaders);
		
		for(InsertCartReqDtoDetail cartDtoDetails : data.getDetails()) {
			cartDtoDetails.setCartHeader(cartHeaders.getId());
			cartDetailService.insert(cartDtoDetails);
		}
		return cartHeaders.getId();
	}
	
	@Override
	public CartHeaders getById(Long id) throws Exception {
		return cartHeaderDao.getById(id);
	}

	@Override
	public List<FindAllCartHdrResData> getAll() throws Exception {
		List<FindAllCartHdrResData> findAll = cartHeaderDao.getTotalPrice();	
		return findAll;
	}


}
