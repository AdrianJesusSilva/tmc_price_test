/**
 * Copyright Adrian Jesus Silva 
 */
package com.github.adrianjesussilva.tmcpricetest.logic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.adrianjesussilva.tmcpricetest.data.entity.PriceEntity;
import com.github.adrianjesussilva.tmcpricetest.data.repository.PriceRepository;
import com.github.adrianjesussilva.tmcpricetest.model.request.PriceListRequest;
import com.github.adrianjesussilva.tmcpricetest.model.response.PriceListResponse;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * test to check the logic of the manager of price list
 * 
 * @author Adrian Jesus Silva
 *
 */
@Log4j2
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class PriceListManagerImplementationTest {

	@InjectMocks
	PriceListManagerImplementation manager;
	
	@Mock
	PriceRepository repository;
	
	PriceListRequest req;
	
	@BeforeEach
	void beforeEach() {		
		req = new PriceListRequest();
		req.setApplicationDate("2020-06-14-10.00.00");
		req.setBrand("XYZ");
		req.setProductId(35455);
	}
	
	@Test
	void test00FindPriceList() {
		PriceEntity pe1 = new PriceEntity();
		pe1.setBrandId(1);
		pe1.setChainIdentifier(1);
		pe1.setCurrency("EUR");
		pe1.setEndDate(new Date());
		pe1.setPrice(10.00);
		pe1.setPriceList(1);
		pe1.setPriority(0);
		pe1.setProductId(12345);
		pe1.setRate(10.00);
		pe1.setStartDate(new Date());
		
		PriceEntity pe2 = new PriceEntity();
		pe2.setBrandId(1);
		pe2.setChainIdentifier(2);
		pe2.setCurrency("EUR");
		pe2.setEndDate(new Date());
		pe2.setPrice(20.00);
		pe2.setPriceList(2);
		pe2.setPriority(1);
		pe2.setProductId(67890);
		pe2.setRate(20.00);
		pe2.setStartDate(new Date());
		
		List<PriceEntity> listPriceEntity = new ArrayList<PriceEntity>();
		listPriceEntity.add(pe1);
		listPriceEntity.add(pe2);
		
		when(repository.findPriceListByApplicationDateAndProductIdAndBrandId(Mockito.any(Date.class), Mockito.anyInt(), Mockito.anyInt())).thenReturn(listPriceEntity);
		
		PriceListResponse response = null;
		try {
			response = manager.searchPriceList(req);
		} catch (Exception e) {
			log.error("could not complete the test, by validation exception, e: ", e);
			fail("could not complete the test, by validation exception");
		}
		
		assertNotNull(response);
		assertEquals(2, response.getChainIdentifier());
		assertEquals(20.00, response.getPrice());
		assertEquals(67890, response.getProductIdentifier());
		assertEquals(20.00, response.getRate());
	}
	
	@Test
	void test01DoNotFindPriceList() {
		
		when(repository.findPriceListByApplicationDateAndProductIdAndBrandId(Mockito.any(Date.class), Mockito.anyInt(), Mockito.anyInt())).thenReturn(Arrays.asList());
		
		PriceListResponse response = null;
		try {
			response = manager.searchPriceList(req);
		} catch (Exception e) {
			log.error("could not complete the test, by validation exception, e: ", e);
			fail("could not complete the test, by validation exception");
		}
		
		assertNull(response);
	}
	
	@Test
	void test02Validation() {
		
		try {
			manager.searchPriceList(new PriceListRequest());
		} catch (Exception e) {
			log.info("test correct, exception generate, exception: " , e);
			assertEquals("product id, brand, application date are required parameters ", e.getMessage());
			return;
		}
		
		fail("Should not complete the call ");
	}

}
