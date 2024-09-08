package com.abc.restaurant;

import com.abc.restaurant.dto.MenuOrderAddressDTO;
import com.abc.restaurant.dto.request.*;
import com.abc.restaurant.enums.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationInitializerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

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
	void testUserRegistration() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/user/register")
				.param("name", "nimal")
				.param("email", "nimal@gmail.com")
				.param("password", "Nimal@123")
				.param("nic", "992200863v")
				.param("phoneNumber", "0715411908")
				.param("homeAddress", "Galle")
				.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"message\": \"User registered successfully\",\"success\":  true}"));
	}

	@Test
	void testSaveTableReservation() throws Exception {

		TableReservationRequestDTO reqDTO = TableReservationRequestDTO.builder()
				.restaurantId(1L)
				.name("Prageeth Milan")
				.email("prageethmilan99@gmail.com")
				.phone("0715485958")
				.date(new Date())
				.reservationType(TableReservationType.STREET_DINING)
				.seats(4)
				.note("Birthday dinner reservation")
				.build();

		String jsonPayload = objectMapper.writeValueAsString(reqDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservation/table")
						.contentType(MediaType.APPLICATION_JSON)
						.header(HttpHeaders.AUTHORIZATION, "Basic VVNFUjo=")
						.content(jsonPayload))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("{\"success\":true,\"message\":\"Table reservation saved successfully.\"}"));
	}

	@Test
	void testGetQueriesFromExistingReservation() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/reservation/TABLE/1")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("{\"message\":\"\",\"success\":true}"));
	}

	@Test
	void testGetQueriesFromExistingUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/reservation/query/TABLE/1")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("{\"message\":\"\",\"success\":true}"));
	}

	@Test
	void testSaveTableQueryFormCustomerSide() throws Exception {

		SaveQueryRequestDTO queryRequestDTO = new SaveQueryRequestDTO();
		queryRequestDTO.setId(1L);
		queryRequestDTO.setTableReservationId(1L);
		queryRequestDTO.setUserRole(UserRole.CUSTOMER); // Example role
		queryRequestDTO.setQueryType(QueryType.TABLE); // Example query type
		queryRequestDTO.setMessage("This is a test query message.");
		queryRequestDTO.setUserId(1L);
		queryRequestDTO.setStatus(CommonStatus.ACTIVE);

		// Convert the object to a JSON string using ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonPayload = objectMapper.writeValueAsString(queryRequestDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservation/query")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPayload))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("{\"success\":true,\"message\":\"Query saved successfully.\"}"));
	}

	@Test
	void saveMenuItem() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/meal/product")
						.param("id", "0")
						.param("restaurantId", "1")
						.param("name", "Item 2")
						.param("mainCategory", MenuMainCategory.LUNCH.name())
						.param("subCategory", MenuSubCategory.BIRIYANI.name())
						.param("menuType", MenuType.SRI_LANKA.name())
						.param("price", "1000.0")
						.param("discount", "100.0")
						.param("status", CommonStatus.ACTIVE.name())
						.param("rating", "5")
						.param("description", "Item 2")
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("{\"success\":true,\"message\":\"Menu item saved successfully\"}"));
	}

	@Test
	void testSaveMealOrder() throws Exception {

		MenuOrderItemRequest item1 = MenuOrderItemRequest.builder()
				.id(1L)
				.qty(2.0F)
				.build();

		MenuOrderItemRequest item2 = MenuOrderItemRequest.builder()
				.id(2L)
				.qty(1.5F)
				.build();

		MenuOrderAddressDTO address = MenuOrderAddressDTO.builder()
				.address("")
				.fullName("")
				.mobileNumber("")
				.restaurantId(1L)
				.build();

		MenuItemOrderRequestDTO reqDTO = MenuItemOrderRequestDTO.builder()
				.userEmail("prageethmilan99@gmail.com")
				.restaurantId(1L)
				.isDiffAddress(true)
				.orderType(MenuOrderType.ONLINE)
				.items(new ArrayList<>(Arrays.asList(item1, item2)))
				.address(address)
				.build();

		String jsonPayload = objectMapper.writeValueAsString(reqDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/reservation/meal")
						.contentType(MediaType.APPLICATION_JSON)
						.header(HttpHeaders.AUTHORIZATION, "Basic VVNFUjo=")
						.content(jsonPayload))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("{\"success\":true,\"message\":\"Save menu items order successfully\"}"));
	}
}
