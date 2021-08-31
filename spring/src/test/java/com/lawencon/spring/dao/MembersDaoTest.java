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

import com.lawencon.spring.model.Members;
import com.lawencon.spring.model.Users;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MembersDaoTest {

	@Autowired
	private MembersDao membersDao;

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

		Members members = new Members();
		members.setName("Anton");
		members.setAddress("Jl. Cipinang");
		members.setPhoneNo("085445596321");
		members.setPoint(230);
		members.setCreatedBy(users);
		members.setIsActive(true);
		membersDao.insert(members);
	}

	@Test
	@Transactional
	@Order(2)
	public void getRoleByID() throws Exception {
		Long id = 4L;
		Members members = membersDao.getById(id);
		assertNotNull(members);
	}

	@Test
	@Transactional
	@Order(3)
	public void selectCustomerNativeQuery() throws Exception {
		Members members = membersDao.getByNameNative("WEW");
		assertNotNull(members);
	}

	@Test
	@Transactional
	@Order(4)
	public void selectCustomerHibernateQuery() throws Exception {
		Members members = membersDao.getByNameHibernate("WEW");
		assertNotNull(members);
	}

	@Test
	@Transactional
	@Rollback(false)
	@Order(5)
	public void update() {
		Members members = new Members();
		try {
			members.setName("test2");
			members.setId(10L);
			members.setAddress("Jl. Pinang ranti");
			members.setPhoneNo("085422982300");
			members.setIsActive(true);
			members.setVersion(0L);
			membersDao.update(members);
			Members memberDb = membersDao.getById(1L);
			assertNotNull(memberDb);
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
		Members members = membersDao.getById(id);
		assertNotNull(members);
	}

	@Test
	@Transactional
	@Order(7)
	@Rollback(false)
	public void delete() throws Exception {
		Long id = 7L;
		membersDao.delete(id);
	}

	@Test
	@Transactional
	@Order(8)
	public void getRoleByIDAfterDelete() throws Exception {
		Long id = 1L;
		Members members = membersDao.getById(id);
		assertNull(members);
	}
}
