package com.lawencon.spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.lawencon.spring.service.RolesService;

@WebMvcTest(controllers = RolesController.class)
public class RolesControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RolesService rolesService;
	
	@Test
	public void findIdByPath() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/roles/3"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("3"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Admin"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("ADM"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.version").value(0))
				;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
