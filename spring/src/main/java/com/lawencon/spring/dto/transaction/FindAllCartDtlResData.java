package com.lawencon.spring.dto.transaction;

import java.math.BigDecimal;

public class FindAllCartDtlResData {

	private Long id;
	private Long idCartHdr;
	private String productName;
	private BigDecimal productPrice;
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCartHdr() {
		return idCartHdr;
	}

	public void setIdCartHdr(Long idCartHdr) {
		this.idCartHdr = idCartHdr;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
