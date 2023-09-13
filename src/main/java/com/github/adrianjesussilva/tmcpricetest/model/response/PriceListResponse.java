/**
 * Copyright Adrian Jesus Silva
 */
package com.github.adrianjesussilva.tmcpricetest.model.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * POJO that represente the body with the price list if apply
 * 
 * @author Adrian Jesus Silva
 *
 */
@Data
@Builder
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceListResponse {
	
	private Integer productIdentifier;
	private Integer chainIdentifier;
	private Double rate;
	private Date startDate;
	private Date endDate;
	private Double price;
	
}
