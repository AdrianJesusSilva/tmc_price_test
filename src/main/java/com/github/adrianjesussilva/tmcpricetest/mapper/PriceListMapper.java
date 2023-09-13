/**
 * Copyright Adrian Jesus Silva
 */
package com.github.adrianjesussilva.tmcpricetest.mapper;

import com.github.adrianjesussilva.tmcpricetest.data.entity.PriceEntity;
import com.github.adrianjesussilva.tmcpricetest.model.response.PriceListResponse;

/**
 * Class with the mapper funtions 
 * 
 * @author Adrian Jesus Silva
 *
 */
public class PriceListMapper {

	public static PriceListResponse mapPriceEntityPriceResponse (PriceEntity entity) {
		
		return PriceListResponse.builder()
				.productIdentifier(entity.getProductId())
				.chainIdentifier(entity.getChainIdentifier())
				.rate(entity.getRate())
				.startDate(entity.getStartDate())
				.endDate(entity.getEndDate())
				.price(entity.getPrice())
				.build();
	}
	
}
