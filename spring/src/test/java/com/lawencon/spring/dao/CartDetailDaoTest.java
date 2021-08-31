package com.lawencon.spring.dao;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lawencon.spring.dao.transaction.CartDetailDao;
import com.lawencon.spring.dao.transaction.CartHeaderDao;
import com.lawencon.spring.model.CartDetails;
import com.lawencon.spring.model.Users;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CartDetailDaoTest {

	@Autowired
	private CartDetailDao cartDetailDao;

	@Autowired
	private CartHeaderDao cartHeaderDao;

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private UsersDao usersDao;

	@Test
	@Transactional
	@Order(1)
	@Rollback(false)
	public void insert() throws Exception {
		Users users = new Users();
		users.setName("Kasir 0098");
		users.setUsername("adm0098");
		users.setPassword("12345");
		users.setRole(rolesDao.getById(2L));
		users.setIsActive(true);
		usersDao.insert(users);

		CartDetails cartDetails = new CartDetails();
		cartDetails.setQuantity(12);
		cartDetails.setCartHeader(cartHeaderDao.getById(36L));
		cartDetails.setProduct(productsDao.getById(4L));
		cartDetails.setCreatedBy(users);
		cartDetails.setIsActive(true);
		cartDetailDao.insert(cartDetails);
	}
}
