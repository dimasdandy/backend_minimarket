package com.lawencon.spring.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawencon.spring.dto.products.DeleteProductsReqDto;
import com.lawencon.spring.dto.products.InsertProductsReqDto;
import com.lawencon.spring.dto.products.UpdateProductsReqDto;
import com.lawencon.spring.model.Products;
import com.lawencon.spring.service.ProductsService;

@Component
public class ProductsServiceValidation {

	@Autowired
	private ProductsService productsService;

	public void insertValidation(InsertProductsReqDto data) throws Exception {
		if (data.getName() == null || data.getName().trim().equals("")) {
			throw new Exception("Name is empty");
		} else if (data.getCode() == null || data.getCode().trim().equals("")) {
			throw new Exception("Code is empty");
		} else if (data.getPrice() == null) {
			throw new Exception("Price is empty");
		} else if (data.getStock() == null) {
			throw new Exception("Stock is empty");
		} else if (data.getCategory() == null) {
			throw new Exception("Category is empty");
		}
	}

	public void updateValidation(UpdateProductsReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("Id is empty");
		}

		Products productsVersion = productsService.getById(data.getId());
		if (data.getVersion() != productsVersion.getVersion()) {
			throw new Exception("Invalid version");
		}
	}

	public void deleteValidation(DeleteProductsReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("ID is not available");
		}

		Products productId = productsService.getById(data.getId());
		if (productId == null) {
			throw new Exception("ID is not available");
		}
	}

}
