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

import com.lawencon.spring.model.Users;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UsersDaoTest {

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
		users.setName("Kasir 001");
		users.setUsername("ksr001");
		users.setPassword("12345");
		users.setRole(rolesDao.getById(2L));
		users.setIsActive(true);
		usersDao.insert(users);
	}

	@Test
	@Transactional
	@Order(2)
	public void getByID() throws Exception {
		Users users = usersDao.getById(37L);
		assertNotNull(users);
	}

	@Test
	@Transactional
	@Order(3)
	public void selectCustomerNativeQuery() throws Exception {
		Users users = usersDao.getByUsernameNative("admin001");
		assertNotNull(users);
	}

	@Test
	@Transactional
	@Order(4)
	public void selectCustomerHibernateQuery() throws Exception {
		Users users = usersDao.getByUsernameHibernate("admin001");
		assertNotNull(users);
	}

	@Test
	@Transactional
	@Rollback(false)
	@Order(5)
	public void update() throws Exception {
		Users users = new Users();
		users.setName("Admin 002");
		users.setUsername("admin002");
		users.setPassword("adm002");
		users.setRole(rolesDao.getById(1L));
		users.setId(1L);
		users.setIsActive(true);
		users.setVersion(0L);
		usersDao.update(users);
	}

	@Test
	@Transactional
	@Order(6)
	public void getUserByIDAfterUpdate() throws Exception {
		Users users = usersDao.getById(1L);
		assertNotNull(users);
	}

	@Test
	@Transactional
	@Order(7)
	@Rollback(false)
	public void delete() throws Exception {
		usersDao.delete(37L);
	}

	@Test
	@Transactional
	@Order(8)
	public void getUserByIDAfterDelete() throws Exception {
		Users users = usersDao.getById(1L);
		assertNull(users);
	}
}
