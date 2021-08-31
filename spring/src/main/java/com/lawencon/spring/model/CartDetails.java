package com.lawencon.spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_trx_cart_dtl")
public class CartDetails extends BaseModels{

	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "id_cart_hdr")
	private CartHeaders cartHeader;
	
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Products product;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public CartHeaders getCartHeader() {
		return cartHeader;
	}

	public void setCartHeader(CartHeaders cartHeader) {
		this.cartHeader = cartHeader;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}
	
	
}
