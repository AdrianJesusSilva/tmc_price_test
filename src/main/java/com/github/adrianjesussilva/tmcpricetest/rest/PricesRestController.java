/**
 * Copyright Adrian Jesus Silva 
 */
package com.github.adrianjesussilva.tmcpricetest.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.adrianjesussilva.tmcpricetest.logic.PriceListManagerApi;
import com.github.adrianjesussilva.tmcpricetest.model.request.PriceListRequest;
import com.github.adrianjesussilva.tmcpricetest.model.response.PriceListResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * controller that define all the entry poinst of the services for this test
 * 
 * @author Adrian Jesus Silva
 *
 */
@Log4j2
@RestController
@RequiredArgsConstructor
public class PricesRestController {

	private final PriceListManagerApi manager;
	private final ObjectMapper objectMapper;
	
	@PostMapping("/prices")
	public ResponseEntity<Object> getSearchPriceList(@RequestBody PriceListRequest request){
		
		log.info("Recived a search petition, looking for the data");
		
		PriceListResponse priceList = null;
		
		try {
			priceList = manager.searchPriceList(request);
			if(Objects.nonNull(priceList))
				return ResponseEntity.status(HttpStatus.OK).body(priceList);
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		catch (Exception e) {
			log.error("Could not complete the search for invalid data, error: ", e);
			Map<String, String> message = new HashMap<String, String>();
			message.put("message", e.getMessage());
			try {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectMapper.writeValueAsString(message));
			} 
			catch (JsonProcessingException e1) {
				log.error("Could not write error message in json format");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}
	}
	
}
