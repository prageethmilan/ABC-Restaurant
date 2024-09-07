package com.abc.restaurant;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationInitializerTests {

	@Autowired
	private MockMvc mockMvc;

//	@Autowired
//	private ObjectMapper objectMapper;

	@Test
	void testLoginWithBasicAuth() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/oauth/token")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.header(HttpHeaders.AUTHORIZATION, "Basic VVNFUjo=")
				.param("username", "prageethmilan99@gmail.com")
				.param("password", "Customer@123")
				.param("grant_type", "password"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.valueOf("application/json;charset=UTF-8")));
	}

	@Test
	void testUserRegistration() {

	}

}
