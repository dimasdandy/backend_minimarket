package com.lawencon.spring.security;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.spring.dto.login.LoginReqDto;
import com.lawencon.spring.dto.login.LoginResDto;
import com.lawencon.spring.dto.login.LoginResDtoData;
import com.lawencon.spring.model.Users;
import com.lawencon.spring.service.UsersService;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private ObjectMapper objectMapper;
	private UsersService usersService;
	private JwtBuilderComponent jwtBuilderComponent;

	public AuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper,
			UsersService usersService, JwtBuilderComponent jwtBuilderComponent) {
		super.setFilterProcessesUrl("/api/login");
		this.authenticationManager = authenticationManager;
		this.usersService = usersService;
		this.objectMapper = objectMapper;
		this.jwtBuilderComponent = jwtBuilderComponent;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		Users user = new Users();
		LoginReqDto loginReqDto = new LoginReqDto();

		try {
			loginReqDto = objectMapper.readValue(request.getInputStream(), LoginReqDto.class);
			user.setUsername(loginReqDto.getUsername());
			user.setPassword(loginReqDto.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		response.setContentType("application/json");
		LoginResDto res = new LoginResDto();

		try {
			Users user = usersService.getByUsernameHibernate(authResult.getName());
			HashMap<String, Object> mapUser = new HashMap<>();
			mapUser.put("id", user.getId());
			String token = jwtBuilderComponent.generateToken(mapUser, Duration.ofSeconds(10000), null);

			LoginResDtoData resData = new LoginResDtoData();
			resData.setToken(token);
			resData.setRole(user.getRole().getCode());
			resData.setName(user.getName());

			res.setData(resData);
			res.setMsg("OK");

		} catch (Exception e) {
			res.setMsg(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		response.getWriter().append(objectMapper.writeValueAsString(res));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		LoginResDto res = new LoginResDto();
		res.setMsg("Wrong username or password!");
		response.setContentType("application/json");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().append(objectMapper.writeValueAsString(res));
	}
}
