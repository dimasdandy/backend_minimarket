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

import com.lawencon.spring.model.Categories;
import com.lawencon.spring.model.Users;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CategoriesDaoTest {

	@Autowired
	private CategoriesDao categoriesDao;
	
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
		users.setName("Kasir 003");
		users.setUsername("ksr003");
		users.setPassword("12345");
		users.setRole(rolesDao.getById(2L));
		users.setIsActive(true);
		usersDao.insert(users);
	
		Categories category = new Categories();
		category.setCode("TEST2");
		category.setName("TEST2");
		category.setCreatedBy(users);
		category.setIsActive(true);
		categoriesDao.insert(category);
	}

	@Test
	@Transactional
	@Order(2)
	public void getRoleByID() throws Exception {
		Long id = 10L;
		Categories category = categoriesDao.getById(id);
		assertNotNull(category);
	}

	@Test
	@Transactional
	@Order(3)
	public void selectCustomerNativeQuery() throws Exception {
		Categories category = categoriesDao.getByNameNative("WEW");
		assertNotNull(category);
	}

	@Test
	@Transactional
	@Order(4)
	public void selectCustomerHibernateQuery() throws Exception {
		Categories category = categoriesDao.getByNameHibernate("WEW");
		assertNotNull(category);
	}

	@Test
	@Transactional
	@Rollback(false)
	@Order(5)
	public void update() {
		Categories category = new Categories();
		try {
			category.setCode("test2");
			category.setName("test2");
			category.setId(10L);
			category.setIsActive(true);
			category.setVersion(0L);
			categoriesDao.update(category);
			Categories categoryDao = categoriesDao.getById(1L);
			assertNotNull(categoryDao);
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
		Categories category = categoriesDao.getById(id);
		assertNotNull(category);
	}

	@Test
	@Transactional
	@Order(7)
	@Rollback(false)
	public void delete() throws Exception {
		Long id = 7L;
		categoriesDao.delete(id);
	}

	@Test
	@Transactional
	@Order(8)
	public void getRoleByIDAfterDelete() throws Exception {
		Long id = 1L;
		Categories category = categoriesDao.getById(id);
		assertNull(category);
	}
}
