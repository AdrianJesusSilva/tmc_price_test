/**
 * Copyright Adrian Jesus Silva
 */
package com.github.adrianjesussilva.tmcpricetest.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.adrianjesussilva.tmcpricetest.data.entity.PriceEntity;
import com.github.adrianjesussilva.tmcpricetest.data.repository.PriceRepository;
import com.github.adrianjesussilva.tmcpricetest.mapper.PriceListMapper;
import com.github.adrianjesussilva.tmcpricetest.model.request.PriceListRequest;
import com.github.adrianjesussilva.tmcpricetest.model.response.PriceListResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * implementation of the price manager api 
 * 
 * @author Adrian Jesus Silva 
 *
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class PriceListManagerImplementation implements PriceListManagerApi {

	private final PriceRepository repository;
	
	@Override
	public PriceListResponse searchPriceList(PriceListRequest request) throws Exception {
		log.info("Search price list for the product id {}, brand {} and the date {}", request.getProductId(), request.getBrand(), request.getApplicationDate());
		
		// validate the information
		List<String> elements = new ArrayList<String>();
		if(Objects.isNull(request.getProductId()))
			elements.add("product id");
		if(Objects.isNull(request.getBrand()) || StringUtils.isBlank(request.getBrand()))
			elements.add("brand");
		if(Objects.isNull(request.getApplicationDate()) || StringUtils.isBlank(request.getApplicationDate()))
			elements.add("application date");
		
		if(elements.size() > 0) {
			StringBuffer sb = new StringBuffer();
			elements.forEach(s -> sb.append(s).append(", "));
			throw new Exception (sb.substring(0, sb.lastIndexOf(", ")) + " are required parameters ");
		}
		
		//call the search with the given parameters 
		Integer brandId = (request.getBrand().equalsIgnoreCase("XYZ"))? 1 : 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
		Date applicationDate = null;
		try {
			applicationDate = sdf.parse(request.getApplicationDate());
		} catch (ParseException e) {
			log.error("could not parse the date");
			throw new Exception("could not parse the date");
		}
		
		// search and collect all the price entity with the given parameters 
		List<PriceEntity> listPriceEntity = repository.findPriceListByApplicationDateAndProductIdAndBrandId(applicationDate, request.getProductId(), brandId);

		if(listPriceEntity.size() > 0) {
			// select the entity with the greatest priority and/or price
			Integer maxPriority = listPriceEntity.stream().mapToInt(PriceEntity::getPriority).max().getAsInt();
			listPriceEntity = listPriceEntity.stream().filter(pePriority -> pePriority.getPriority().equals(maxPriority)).collect(Collectors.toList());
			if(listPriceEntity.size() > 1) {
				Double maxPrice = listPriceEntity.stream().mapToDouble(PriceEntity::getPrice).max().getAsDouble();
				listPriceEntity = listPriceEntity.stream().filter(pePrice -> pePrice.getPrice().equals(maxPrice)).collect(Collectors.toList());
			}
			return PriceListMapper.mapPriceEntityPriceResponse(listPriceEntity.get(0));
		}
		
		return null;
	}

}
