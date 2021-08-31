package com.lawencon.spring.security;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtBuilderComponent {

	//secret key untuk pengecekan JWT
	private final String secretKey = "JDJhJDEwJFNQSXhydGhIeS56RmdiaWlJRmVlWnVvSHVqai50WVJqV1RHWGhnUzI3eS5xSjdLa1p6enNp";
	private SecretKey key;

	public JwtBuilderComponent() {
		key = Keys.hmacShaKeyFor(secretKey.getBytes());
	}

	//generate token ada 3 input (auten)
	public String generateToken(Map<String, Object> claims, Duration duration, String subject) {
		io.jsonwebtoken.JwtBuilder builder = Jwts.builder().signWith(key);

		//payload
		if (claims != null)
			builder.setClaims(claims);

		//expired
		if (duration != null) {
			LocalDateTime expiredDate = LocalDateTime.now().plusSeconds(duration.getSeconds());
			builder.setExpiration(java.sql.Timestamp.valueOf(expiredDate));
		}

		//subject => id
		if (subject != null)
			builder.setSubject(subject);

		return builder.compact();
	}

	//ngecek token => validasi,format (autor)
	public Map<String, Object> getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}
}
