package com.lawencon.spring.service.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.spring.dao.transaction.CartDetailDao;
import com.lawencon.spring.dto.transaction.FindAllCartDtlResData;
import com.lawencon.spring.dto.transaction.InsertCartReqDtoDetail;
import com.lawencon.spring.model.CartDetails;
import com.lawencon.spring.model.CartHeaders;
import com.lawencon.spring.model.Products;
import com.lawencon.spring.service.BaseServiceImpl;
import com.lawencon.spring.service.ProductsService;
import com.lawencon.spring.validation.CartDetailServiceValidation;

@Service
public class CartDetailServiceImpl extends BaseServiceImpl implements CartDetailService {

	@Autowired
	private CartDetailDao cartDetailDao;

	@Autowired
	private CartHeaderService cartHeaderService;
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private CartDetailServiceValidation cartDtlValidation;

	@Override
	@Transactional
	public Long insert(InsertCartReqDtoDetail data) throws Exception {
		cartDtlValidation.insertValidation(data);
		
		CartDetails cartDetails = new CartDetails();
		CartHeaders cartHeaders = cartHeaderService.getById(data.getCartHeader());
		Products products = productsService.getById(data.getProduct());
		cartDetails.setQuantity(data.getQuantity());
		cartDetails.setCartHeader(cartHeaders);
		cartDetails.setProduct(products);
		cartDetails.setIsActive(true);
		cartDetails.setCreatedBy(super.users());
		cartDetailDao.insert(cartDetails);	
		return cartDetails.getId();
	}

	@Override
	public List<FindAllCartDtlResData> getAllDtl(Long id) throws Exception {
		List<FindAllCartDtlResData> findAllDtl = new ArrayList<FindAllCartDtlResData>();
		List<CartDetails> cartDtl = cartDetailDao.getAllDtl(id);
		for (CartDetails cartDetail : cartDtl) {
			FindAllCartDtlResData allPath = new FindAllCartDtlResData();
					
			allPath.setId(cartDetail.getId());
			allPath.setIdCartHdr(cartDetail.getCartHeader().getId());
			allPath.setProductName(cartDetail.getProduct().getName());
			allPath.setProductPrice(cartDetail.getProduct().getPrice());
			allPath.setQuantity(cartDetail.getQuantity());
			findAllDtl.add(allPath);
		}
		return findAllDtl;
	}
	
	

}
