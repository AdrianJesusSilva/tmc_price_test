/**
 * Copyright Adrian Jesus Silva 
 */
package com.github.adrianjesussilva.tmcpricetest.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.adrianjesussilva.tmcpricetest.data.entity.PriceEntity;

/**
 * Interface with the SQL calls to CRUD the data
 * 
 * @author Adrian Jesus Silva 
 *
 */
public interface PriceRepository extends JpaRepository<PriceEntity, Integer>{
	
	@Query(value = "SELECT p FROM PRICES p WHERE :applicationDate >= START_DATE AND :applicationDate <= END_DATE AND PRODUCT_ID = :productId AND BRAND_ID = :brandId")
	List<PriceEntity> findPriceListByApplicationDateAndProductIdAndBrandId(@Param("applicationDate") Date applicationDate, @Param("productId") Integer productId, @Param("brandId") Integer brandId);
		
}
