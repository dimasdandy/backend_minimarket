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

import com.lawencon.spring.model.Roles;
import com.lawencon.spring.model.Users;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RolesDaoTest {

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
		users.setName("Admin 003");
		users.setUsername("adm003");
		users.setPassword("12345");
		users.setRole(rolesDao.getById(2L));
		users.setIsActive(true);
		usersDao.insert(users);
		
		Roles roles = new Roles();
		roles.setCode("TEST12");
		roles.setName("TEST12");
		roles.setCreatedBy(users);
		roles.setIsActive(true);
		rolesDao.insert(roles);
	}

	@Test
	@Transactional
	@Order(2)
	public void getRoleByID() throws Exception {
		Long id = 12L;
		Roles roles = rolesDao.getById(id);
		assertNotNull(roles);
	}

	@Test
	@Transactional
	@Order(3)
	public void selectCustomerNativeQuery() throws Exception {
		Roles roles = rolesDao.getByNameNative("WEW");
		assertNotNull(roles);
	}

	@Test
	@Transactional
	@Order(4)
	public void selectCustomerHibernateQuery() throws Exception {
		Roles roles = rolesDao.getByNameHibernate("WEW");
		assertNotNull(roles);
	}

	@Test
	@Transactional
	@Rollback(false)
	@Order(5)
	public void update() {
		Roles roles = new Roles();
		try {
			roles.setCode("test2");
			roles.setName("test2");
			roles.setId(10L);
			roles.setIsActive(true);
			roles.setVersion(0L);
			rolesDao.update(roles);
			Roles roleDb = rolesDao.getById(1L);
			assertNotNull(roleDb);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		
	}

	@Test
	@Transactional
	@Order(6)
	public void getRoleByIDAfterUpdate() throws Exception {
		Long id = 1L;
		Roles roles = rolesDao.getById(id);
		assertNotNull(roles);
	}

	@Test
	@Transactional
	@Order(7)
	@Rollback(false)
	public void delete() throws Exception {
		Long id = 7L;
		rolesDao.delete(id);
	}

	@Test
	@Transactional
	@Order(8)
	public void getRoleByIDAfterDelete() throws Exception {
		Long id = 1L;
		Roles roles = rolesDao.getById(id);
		assertNull(roles);
	}
}
