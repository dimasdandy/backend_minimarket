package com.lawencon.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthPrincipalImpl implements AuthPrincipal{
	
	@Override
	public Long getAuthentication() {
		Authentication authen = SecurityContextHolder.getContext().getAuthentication();
		return Long.valueOf(authen.getPrincipal().toString());
	}
}
