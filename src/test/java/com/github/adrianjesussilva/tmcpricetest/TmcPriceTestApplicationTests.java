package com.github.adrianjesussilva.tmcpricetest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.adrianjesussilva.tmcpricetest.rest.PricesRestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class TmcPriceTestApplicationTests {
	
	@Autowired
	PricesRestController controller;
	

	@Test
	void contextLoads() {
		log.info("controller test");
		assertNotNull(controller);
	}

}
