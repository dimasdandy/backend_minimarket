package com.lawencon.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.spring.service.UsersService;

@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ApiSecurityServiceImpl apiSecurityServiceImpl;

	@Autowired
	private UsersService usersService;

	@Autowired
	private JwtBuilderComponent jwtBuilderComponent;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();

		// authentication
		http.addFilter(new AuthenticationFilter(super.authenticationManager(), objectMapper, usersService,
				jwtBuilderComponent));

		// authorization
		http.addFilter(new AuthorizationFilter(super.authenticationManager(), jwtBuilderComponent, objectMapper));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(apiSecurityServiceImpl).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
//			.antMatchers(HttpMethod.POST, "/users")
			.antMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html");
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods(HttpMethod.POST.name(),
                        HttpMethod.GET.name(), HttpMethod.PATCH.name(), HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name());
            }
        };
    }

}
