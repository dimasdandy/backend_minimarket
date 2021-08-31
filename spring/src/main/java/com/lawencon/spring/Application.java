package com.lawencon.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lawencon.spring.model.Roles;
import com.lawencon.spring.model.Users;
import com.lawencon.spring.service.RolesService;
import com.lawencon.spring.service.UsersService;

@SpringBootApplication
public class Application {

	Roles roles = new Roles();
	Users users = new Users();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(RolesService rolesService, UsersService usersService) {
//		return args -> {
//			insertData(rolesService, usersService);
//			updateData(rolesService, usersService);
//			deleteData(rolesService, usersService);
//			getData(rolesService, usersService);			
//		};
//	}

	private void insertData(RolesService rolesService, UsersService usersService) {
		roles.setCode("KSR");
		roles.setName("Kasir");
//		rolesService.insert(roles);

//		Roles role = rolesService.getById(1L);
		users.setName("Admin 001");
//		users.setRole(role);
		users.setUsername("admin001");
		users.setPassword("admin1");
//		usersService.insert(users);
	}

	private void updateData(RolesService rolesService, UsersService usersService) {
		roles.setId(1L);
		roles.setCode("KSR");
		roles.setName("Kasir");
		roles.setVersion(1L);
//		rolesService.update(roles);

//		Roles role = rolesService.getById(1L);
		users.setName("Admin 001");
//		users.setRole(role);
		users.setUsername("admin001");
		users.setPassword("admin1");
		users.setVersion(1L);
//		usersService.update(users);
	}
	
	private void deleteData(RolesService rolesService, UsersService usersService) {
//		rolesService.delete(1L);
//		usersService.delete(1L);
	}

	private void getData(RolesService rolesService, UsersService usersService) {
//		Roles role = rolesService.getById(1L);
//		System.out.println("Role id : " + role.getId());
		
//		Users user = usersService.getById(1L);
//		System.out.println("User id : " + user.getId());
	}
}
