package com.lawencon.spring.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.spring.dto.login.AuthorizationResDto;

import io.jsonwebtoken.ExpiredJwtException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	private JwtBuilderComponent jwtBuilderComponent;
	private ObjectMapper objectMapper;
	
	public AuthorizationFilter(AuthenticationManager authenticationManager, JwtBuilderComponent jwtBuilderComponent, ObjectMapper objectMapper) {
		super(authenticationManager);
		this.jwtBuilderComponent = jwtBuilderComponent;
		this.objectMapper = objectMapper;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		response.setContentType("application/json");

		String header = request.getHeader("Authorization");
		if (header == null || header.isEmpty() || !header.startsWith("Bearer")) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}

		Long id = null;
		// validate token here
		String token = header.substring(6, header.length());
		AuthorizationResDto res = new AuthorizationResDto();
		
		try {
			Map<String, Object> map =  jwtBuilderComponent.getClaims(token);
			id = Long.valueOf(map.get("id").toString());	
		} catch (ExpiredJwtException exp) {
			exp.printStackTrace();
			res.setMsg("Expired token.");
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg("Invalid token.");
		}
		
		if (res.getMsg() != null) {
		            response.getWriter().append(objectMapper.writeValueAsString(res));
		            response.setStatus(HttpStatus.UNAUTHORIZED.value());
		            return;
		        }

		Authentication auth = new UsernamePasswordAuthenticationToken(id, null, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		chain.doFilter(request, response);
	}

}
