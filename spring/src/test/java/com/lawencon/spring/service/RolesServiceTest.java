package com.lawencon.spring.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import com.lawencon.spring.dao.RolesDao;
import com.lawencon.spring.dto.roles.InsertDataReqDto;
import com.lawencon.spring.dto.roles.UpdateDataReqDto;
import com.lawencon.spring.model.Roles;
import com.lawencon.spring.security.AuthPrincipal;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RolesServiceTest extends BaseServiceImpl {

	@MockBean
	private AuthPrincipal authPrincipal;

	@Autowired
	private RolesService rolesService;

	@Test
	@Order(1)
	@Rollback(false)
	@Commit
	public void insert() throws Exception {
		Mockito.when(authPrincipal.getAuthentication()).thenReturn(1L);
		InsertDataReqDto roles = new InsertDataReqDto();
		roles.setCode("COBA4");
		roles.setName("Coba4");
		roles.setIsActive(true);
		Long role = rolesService.insert(roles);
	}

	@Test
	@Order(2)
	public void getRoleByID() throws Exception {
		Long id = 20L;
		Roles roles = rolesService.getById(id);
		assertNotNull(roles);
	}

	@Test
	@Order(3)
	public void selectCustomerNativeQuery() throws Exception {
		Roles roles = rolesService.getByNameNative("Admin");
		assertNotNull(roles);
	}

	@Test
	@Order(4)
	public void selectCustomerHibernateQuery() throws Exception {
		Roles roles = rolesService.getByNameHibernate("Admin");
		assertNotNull(roles);
	}

	@Test
	@Rollback(false)
	@Order(5)
	public void update() throws Exception {
		UpdateDataReqDto roles = new UpdateDataReqDto();

		roles.setCode("KSR");
		roles.setName("Kasir");
		roles.setId(1L);
		roles.setVersion(0L);
		rolesService.update(roles);

		Roles roleDb = rolesService.getById(1L);
		assertNotNull(roleDb);
	}

	@Test
	@Order(6)
	public void getRoleByIDAfterUpdate() throws Exception {
		Long id = 1L;
		Roles roles = rolesService.getById(id);
		assertNotNull(roles);
	}

	@Test
	@Order(7)
	@Rollback(false)
	public void delete() throws Exception {
		Long id = 1L;
		rolesService.delete(id);
	}

	@Test
	@Order(8)
	public void getRoleByIDAfterDelete() throws Exception {
		Long id = 1L;
		Roles roles = rolesService.getById(id);
		assertNull(roles);
	}
}
