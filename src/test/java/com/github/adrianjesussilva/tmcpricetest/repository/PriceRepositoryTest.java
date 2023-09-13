/**
 * Copyright Adrian Jesus Silva 
 */
package com.github.adrianjesussilva.tmcpricetest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.adrianjesussilva.tmcpricetest.data.entity.PriceEntity;
import com.github.adrianjesussilva.tmcpricetest.data.repository.PriceRepository;

import lombok.extern.log4j.Log4j2;

/**
 * Unit Test for the repository 
 * 
 * @author Adrian Jesus Silva 
 *
 */
@Log4j2
@DataJpaTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PriceRepositoryTest {

	@Autowired
	private PriceRepository repository;
	
	@Test
	void test00H2BDConfigDataForDemo() {
		
		log.info("Testing that the data for the demo is present");
		
		List<PriceEntity> priceList = this.repository.findAll();
		
		assertEquals(4, priceList.size());
		
	}
	
	@Test
	void test01CustomSearchQuery() {
		log.info("Testing custom search query");		
		
		// Format the test date 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		Date applicationDate = null;
		try {
			applicationDate = sdf.parse("2020-06-14-10.00.00");
		} catch (ParseException e) {
			log.error("could not parse the date, failing the test");
			fail("could not parse the date");
		}
		
		List<PriceEntity> priceList = this.repository.findPriceListByApplicationDateAndProductIdAndBrandId(applicationDate, 35455, 1);
		
		assertNotNull(priceList);
		assertThat(priceList.size()).isGreaterThan(0);
	}

}
