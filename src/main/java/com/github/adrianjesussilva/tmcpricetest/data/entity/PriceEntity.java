/**
 * Copyright Adrian Jesus Silva 
 */
package com.github.adrianjesussilva.tmcpricetest.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * POJO and Entity that represents the price table
 * 
 * @author Adrian Jesus Silva 
 *
 */
@Data
@Entity(name = "PRICES")
@Table(name = "PRICES")
public class PriceEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "PRICE_LIST")
	private Integer priceList;
	
	@Column(name = "BRAND_ID")
	private Integer brandId;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Column(name = "PRODUCT_ID")
	private Integer productId;
	
	@Column(name = "PRIORITY")
	private Integer priority;
	
	@Column(name = "PRICE")
	private Double price;
	
	@Column(name = "CURR")
	private String currency;
	
	@Column(name = "CHAIN_IDENTIFIER")
	private Integer chainIdentifier;
	
	@Column(name = "RATE")
	private Double rate;
	
}
