package com.lawencon.spring.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lawencon.spring.dao.RolesDao;
import com.lawencon.spring.dao.UsersDao;
import com.lawencon.spring.dto.roles.InsertDataReqDto;
import com.lawencon.spring.dto.users.InsertUserReqDto;
import com.lawencon.spring.dto.users.UpdateUserReqDto;
import com.lawencon.spring.model.Users;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UsersServiceTest {

	@Mock
	private UsersDao usersDao;
	
	@Mock
	private RolesDao rolesDao;

	@Autowired
	private UsersService usersService = new UsersServiceImpl();
	
	@Autowired
	private RolesService rolesService = new RolesServiceImpl();

	@Test
	@Order(1)
	@Rollback(false)
	public void insert() throws Exception {
		InsertDataReqDto roles = new InsertDataReqDto();
		roles.setCode("ADM");
		roles.setName("Admin");
		rolesService.insert(roles);

		InsertUserReqDto users = new InsertUserReqDto();
		users.setName("Admin 001");
		users.setUsername("admin001");
		users.setPassword("adm001");
		users.setRole(1L);
		usersService.insert(users);
	}

	@Test
	@Order(2)
	public void getByID() throws Exception {
		Users users = usersService.getById(1L);
		assertNotNull(users);
	}

	@Test
	@Order(3)
	public void selectUsernameNativeQuery() throws Exception {
		Users users = usersService.getByUsernameNative("admin001");
		assertNotNull(users);
	}

	@Test
	@Order(4)
	public void selectUsernameHibernateQuery() throws Exception {
		Users users = usersService.getByUsernameHibernate("admin001");
		assertNotNull(users);
	}

	@Test
	@Rollback(false)
	@Order(5)
	public void update() throws Exception {
		UpdateUserReqDto users = new UpdateUserReqDto();
		users.setName("Admin 002");
		users.setUsername("admin002");
		users.setRole(1L);
		users.setId(1L);
		users.setVersion(0L);
		usersService.update(users);
	}

	@Test
	@Order(6)
	public void getUserByIDAfterUpdate() throws Exception {
		Users users = usersService.getById(1L);
		assertNotNull(users);
	}

	@Test
	@Order(7)
	@Rollback(false)
	public void delete() throws Exception {
		usersService.delete(1L);
	}

	@Test
	@Order(8)
	public void getUserByIDAfterDelete() throws Exception {
		Users users = usersService.getById(1L);
		assertNull(users);
	}
}
