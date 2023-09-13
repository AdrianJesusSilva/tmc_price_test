/**
 * Copyright Adrian Jesus Silva 
 */
package com.github.adrianjesussilva.tmcpricetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.adrianjesussilva.tmcpricetest.model.request.PriceListRequest;
import com.github.adrianjesussilva.tmcpricetest.model.response.PriceListResponse;
import com.github.adrianjesussilva.tmcpricetest.rest.PricesRestController;

/**
 * integration test
 * 
 * @author Adrian Jesus Silva 
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class IntegrationTest {

	@Autowired
	PricesRestController controller;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void test01 () {
		String url = "http://localhost:"+port+"/prices";
		
		PriceListRequest req = new PriceListRequest();
		req.setApplicationDate("2020-06-14-10.00.00");
		req.setBrand("XYZ");
		req.setProductId(35455);
		
		ResponseEntity<PriceListResponse> entity = restTemplate.postForEntity(url, req, PriceListResponse.class);
		
		assertTrue(entity.getStatusCode().is2xxSuccessful());
		
		PriceListResponse response = entity.getBody();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		Date startDate = null, endDate = null;
		try {
			startDate = sdf.parse("2020-06-14-00.00.00");
			endDate = sdf.parse("2020-12-31-23.59.59");
		} catch (ParseException e) {
			fail("could not prepare the dates");
		} 
		
		assertNotNull(response);
		assertEquals(35455, response.getProductIdentifier());
		assertEquals(1, response.getChainIdentifier());
		assertEquals(0.0, response.getRate());
		assertEquals(startDate, response.getStartDate());
		assertEquals(endDate, response.getEndDate());
		assertEquals(35.50, response.getPrice());
	}
	
	@Test
	public void test02 () {
		String url = "http://localhost:"+port+"/prices";
		
		PriceListRequest req = new PriceListRequest();
		req.setApplicationDate("2020-06-14-16.00.00");
		req.setBrand("XYZ");
		req.setProductId(35455);
		
		ResponseEntity<PriceListResponse> entity = restTemplate.postForEntity(url, req, PriceListResponse.class);
		
		assertTrue(entity.getStatusCode().is2xxSuccessful());
		
		PriceListResponse response = entity.getBody();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		Date startDate = null, endDate = null;
		try {
			startDate = sdf.parse("2020-06-14-15.00.00");
			endDate = sdf.parse("2020-06-14-18.30.00");
		} catch (ParseException e) {
			fail("could not prepare the dates");
		} 
		
		assertNotNull(response);
		assertEquals(35455, response.getProductIdentifier());
		assertEquals(1, response.getChainIdentifier());
		assertEquals(-28.31, response.getRate());
		assertEquals(startDate, response.getStartDate());
		assertEquals(endDate, response.getEndDate());
		assertEquals(25.45, response.getPrice());
	}

	@Test
	public void test03 () {
		String url = "http://localhost:"+port+"/prices";
		
		PriceListRequest req = new PriceListRequest();
		req.setApplicationDate("2020-06-14-21.00.00");
		req.setBrand("XYZ");
		req.setProductId(35455);
		
		ResponseEntity<PriceListResponse> entity = restTemplate.postForEntity(url, req, PriceListResponse.class);
		
		assertTrue(entity.getStatusCode().is2xxSuccessful());
		
		PriceListResponse response = entity.getBody();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		Date startDate = null, endDate = null;
		try {
			startDate = sdf.parse("2020-06-14-00.00.00");
			endDate = sdf.parse("2020-12-31-23.59.59");
		} catch (ParseException e) {
			fail("could not prepare the dates");
		} 
		
		assertNotNull(response);
		assertEquals(35455, response.getProductIdentifier());
		assertEquals(1, response.getChainIdentifier());
		assertEquals(0.0, response.getRate());
		assertEquals(startDate, response.getStartDate());
		assertEquals(endDate, response.getEndDate());
		assertEquals(35.50, response.getPrice());
	}
	
	@Test
	public void test04 () {
		String url = "http://localhost:"+port+"/prices";
		
		PriceListRequest req = new PriceListRequest();
		req.setApplicationDate("2020-06-15-10.00.00");
		req.setBrand("XYZ");
		req.setProductId(35455);
		
		ResponseEntity<PriceListResponse> entity = restTemplate.postForEntity(url, req, PriceListResponse.class);
		
		assertTrue(entity.getStatusCode().is2xxSuccessful());
		
		PriceListResponse response = entity.getBody();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		Date startDate = null, endDate = null;
		try {
			startDate = sdf.parse("2020-06-15-00.00.00");
			endDate = sdf.parse("2020-06-15-11.00.00");
		} catch (ParseException e) {
			fail("could not prepare the dates");
		} 
		
		assertNotNull(response);
		assertEquals(35455, response.getProductIdentifier());
		assertEquals(2, response.getChainIdentifier());
		assertEquals(-14.08, response.getRate());
		assertEquals(startDate, response.getStartDate());
		assertEquals(endDate, response.getEndDate());
		assertEquals(30.50, response.getPrice());
	}
	
	@Test
	public void test05 () {
		String url = "http://localhost:"+port+"/prices";
		
		PriceListRequest req = new PriceListRequest();
		req.setApplicationDate("2020-06-16-09.00.00");
		req.setBrand("XYZ");
		req.setProductId(35455);
		
		ResponseEntity<PriceListResponse> entity = restTemplate.postForEntity(url, req, PriceListResponse.class);
		
		assertTrue(entity.getStatusCode().is2xxSuccessful());
		
		PriceListResponse response = entity.getBody();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		Date startDate = null, endDate = null;
		try {
			startDate = sdf.parse("2020-06-15-16.00.00");
			endDate = sdf.parse("2020-12-31-23.59.59");
		} catch (ParseException e) {
			fail("could not prepare the dates");
		} 
		
		assertNotNull(response);
		assertEquals(35455, response.getProductIdentifier());
		assertEquals(2, response.getChainIdentifier());
		assertEquals(9.72, response.getRate());
		assertEquals(startDate, response.getStartDate());
		assertEquals(endDate, response.getEndDate());
		assertEquals(38.95, response.getPrice());
		
	}
}
