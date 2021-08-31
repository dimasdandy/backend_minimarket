package com.lawencon.spring.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lawencon.spring.model.Payments;
import com.lawencon.spring.model.Users;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PaymentsDaoTest {

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
		users.setName("Admin 006");
		users.setUsername("adm006");
		users.setPassword("12345");
		users.setRole(rolesDao.getById(2L));
		users.setIsActive(true);
		usersDao.insert(users);

		Payments payments = new Payments();
		payments.setCode("TEST5");
		payments.setName("TEST5");
		payments.setCreatedBy(users);
		payments.setIsActive(true);
		paymentsDao.insert(payments);
	}

	@Test
	@Transactional
	@Order(2)
	public void getRoleByID() throws Exception {
		Long id = 7L;
		Payments payments = paymentsDao.getById(id);
		assertNotNull(payments);
	}

	@Test
	@Transactional
	@Order(3)
	public void selectCustomerNativeQuery() throws Exception {
		Payments payments = paymentsDao.getByNameNative("WEW");
		assertNotNull(payments);
	}

	@Test
	@Transactional
	@Order(4)
	public void selectCustomerHibernateQuery() throws Exception {
		Payments payments = paymentsDao.getByNameHibernate("WEW");
		assertNotNull(payments);
	}

	@Test
	@Transactional
	@Rollback(false)
	@Order(5)
	public void update() {
		Payments payments = new Payments();
		try {
			payments.setCode("test4");
			payments.setName("test4");
			payments.setId(7L);
			payments.setIsActive(true);
			payments.setVersion(0L);
			paymentsDao.update(payments);
			Payments paymentDb = paymentsDao.getById(7L);
			assertNotNull(paymentDb);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}

	}

	@Test
	@Transactional
	@Order(6)
	public void getRoleByIDAfterUpdate() throws Exception {
		Long id = 7L;
		Payments payments = paymentsDao.getById(id);
		assertNotNull(payments);
	}

	@Test
	@Transactional
	@Order(7)
	@Rollback(false)
	public void delete() throws Exception {
		Long id = 7L;
		paymentsDao.delete(id);
	}

	@Test
	@Transactional
	@Order(8)
	public void getRoleByIDAfterDelete() throws Exception {
		Long id = 1L;
		Payments payments = paymentsDao.getById(id);
		assertNull(payments);
	}
}
