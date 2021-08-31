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

import com.lawencon.spring.model.Products;
import com.lawencon.spring.model.Users;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ProductsDaoTest {

	@Autowired
	private ProductsDao productsDao;
	
	@Autowired
	private CategoriesDao categoriesDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private RolesDao rolesDao;

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

		Products products = new Products();
		products.setName("Rexona");
		products.setCode("RXNA");
//		products.setPrice(1000D);
		products.setStock(50);
		products.setCategories(categoriesDao.getById(3L));
		products.setCreatedBy(users);
		products.setIsActive(true);
		productsDao.insert(products);
	}

	@Test
	@Transactional
	@Order(2)
	public void getRoleByID() throws Exception {
		Long id = 4L;
		Products products = productsDao.getById(id);
		assertNotNull(products);
	}

	@Test
	@Transactional
	@Order(3)
	public void selectCustomerNativeQuery() throws Exception {
		Products products = productsDao.getByNameNative("WEW");
		assertNotNull(products);
	}

	@Test
	@Transactional
	@Order(4)
	public void selectCustomerHibernateQuery() throws Exception {
		Products products = productsDao.getByNameHibernate("WEW");
		assertNotNull(products);
	}

	@Test
	@Transactional
	@Rollback(false)
	@Order(5)
	public void update() {
		Products products = new Products();
		try {
			products.setName("test2");
			products.setId(10L);
			products.setCode("RXNA");
//			products.setPrice(2000.00D);
			products.setStock(50);
			products.setCategories(categoriesDao.getById(3L));
			products.setIsActive(true);
			products.setVersion(0L);
			productsDao.update(products);
			Products productsDb = productsDao.getById(1L);
			assertNotNull(productsDb);
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
		Products products = productsDao.getById(id);
		assertNotNull(products);
	}

	@Test
	@Transactional
	@Order(7)
	@Rollback(false)
	public void delete() throws Exception {
		Long id = 7L;
		productsDao.delete(id);
	}

	@Test
	@Transactional
	@Order(8)
	public void getRoleByIDAfterDelete() throws Exception {
		Long id = 1L;
		Products products = productsDao.getById(id);
		assertNull(products);
	}
}
