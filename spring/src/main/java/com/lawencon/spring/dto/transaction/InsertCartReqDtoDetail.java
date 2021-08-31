package com.lawencon.spring.dto.transaction;

public class InsertCartReqDtoDetail {

	private Integer quantity;
	private Long cartHeader;
	private Long product;
	private Boolean isActive;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getCartHeader() {
		return cartHeader;
	}

	public void setCartHeader(Long cartHeader) {
		this.cartHeader = cartHeader;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
