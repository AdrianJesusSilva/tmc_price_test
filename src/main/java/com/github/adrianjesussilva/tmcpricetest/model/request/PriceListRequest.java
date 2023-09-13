/**
 * Copyright Adrian Jesus Silva
 */
package com.github.adrianjesussilva.tmcpricetest.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * POJO that represente the body for the search of price list
 * 
 * @author Adrian Jesus Silva
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceListRequest {

	private String applicationDate;
	private Integer productId;
	private String brand;
	
}
