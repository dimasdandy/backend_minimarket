package com.lawencon.spring.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.spring.model.Users;
import com.lawencon.spring.service.UsersService;

@Service
public class ApiSecurityServiceImpl implements UserDetailsService {

	@Autowired
	private UsersService usersService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Users users = usersService.getByUsernameHibernate(username);
			User user = new User(username, users.getPassword(), new ArrayList<>());
			return user;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

}
