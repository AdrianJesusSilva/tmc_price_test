/**
 * Copyright Adrian Jesus Silva 
 */
package com.github.adrianjesussilva.tmcpricetest.logic;

import com.github.adrianjesussilva.tmcpricetest.model.request.PriceListRequest;
import com.github.adrianjesussilva.tmcpricetest.model.response.PriceListResponse;

/**
 * interface that define all the methos (functions and procedures) requiered for the implementation of the price list manager 
 * 
 * @author Adrian Jesus Silva 
 *
 */
public interface PriceListManagerApi {

	public PriceListResponse searchPriceList(PriceListRequest request) throws Exception;
	
}
