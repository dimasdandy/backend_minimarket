package com.lawencon.spring.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lawencon.spring.dao.transaction.CartHeaderDao;
import com.lawencon.spring.model.CartHeaders;
import com.lawencon.spring.model.Users;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CartHeaderDaoTest {

	@Autowired
	private CartHeaderDao cartHeaderDao;
	
	@Autowired
	private MembersDao membersDao;
	
	@Autowired
	private PaymentsDao paymentsDao;
	
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
		users.setName("Kasir 0099");
		users.setUsername("adm0099");
		users.setPassword("12345");
		users.setRole(rolesDao.getById(2L));
		users.setIsActive(true);
		usersDao.insert(users);
		
		CartHeaders cartHeader = new CartHeaders();
		cartHeader.setTransactionDate(LocalDateTime.now());
		cartHeader.setUser(users);
		cartHeader.setMember(membersDao.getById(1L));
		cartHeader.setPayment(paymentsDao.getById(1L));
		cartHeader.setCreatedBy(users);
		cartHeader.setIsActive(true);
		cartHeaderDao.insert(cartHeader);
	}

	@Test
	@Transactional
	@Order(2)
	public void getRoleByID() throws Exception {
		Long id = 36L;
		CartHeaders cartHeader = cartHeaderDao.getById(id);
		assertNotNull(cartHeader);
	}


}
