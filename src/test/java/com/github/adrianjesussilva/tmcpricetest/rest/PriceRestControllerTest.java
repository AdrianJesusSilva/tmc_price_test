/**
 * Copyright Adrian Jesus Silva 
 */
package com.github.adrianjesussilva.tmcpricetest.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.adrianjesussilva.tmcpricetest.logic.PriceListManagerApi;
import com.github.adrianjesussilva.tmcpricetest.model.request.PriceListRequest;
import com.github.adrianjesussilva.tmcpricetest.model.response.PriceListResponse;


/**
 * test case to check the different posible of response
 * 
 * @author Adrian Jesus Silva 
 *
 */
@WebMvcTest(PricesRestController.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class PriceRestControllerTest {

	@MockBean
	PriceListManagerApi manager;
	
	@Autowired
	MockMvc mockMvc;
	
	String json;
	
	@BeforeEach
	void beforeEach() throws JsonProcessingException {
		PriceListRequest req = new PriceListRequest();
		req.setApplicationDate("2020-06-14-00.00.00");
		req.setBrand("XYZ");
		req.setProductId(12345);
		
		ObjectMapper om = new ObjectMapper();
		json = om.writeValueAsString(req);
	}
	
	@Test
	void test00OkTest() throws Exception {
		Date startDate = new Date(); 
		Date endDate = new Date();
				
		Mockito.when(manager.searchPriceList(Mockito.any(PriceListRequest.class))).thenReturn(PriceListResponse.builder().chainIdentifier(1).endDate(endDate).price(10.00).productIdentifier(12345).rate(10.00).startDate(startDate).build());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		mockMvc.perform(post("/prices").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk())
		.andExpect(jsonPath("chainIdentifier", Matchers.is(1)))
		.andExpect(jsonPath("endDate", Matchers.is(sdf.format(endDate).replace("Z", "+00:00"))))
		.andExpect(jsonPath("price", Matchers.is(10.00)))
		.andExpect(jsonPath("productIdentifier", Matchers.is(12345)))
		.andExpect(jsonPath("rate", Matchers.is(10.00)))
		.andExpect(jsonPath("startDate", Matchers.is(sdf.format(startDate).replace("Z", "+00:00"))));
		
			
	}
	
	@Test
	void test01NotFoundTest() throws Exception {
		Mockito.when(manager.searchPriceList(Mockito.any(PriceListRequest.class))).thenReturn(null);
		
		mockMvc.perform(post("/prices").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isNotFound());
	}
	
	@Test
	void test02Exception() throws Exception {
		Mockito.when(manager.searchPriceList(Mockito.any(PriceListRequest.class))).thenThrow(new Exception("TEST"));
		
		mockMvc.perform(post("/prices").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());
		
	}

}
