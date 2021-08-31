package com.lawencon.spring.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawencon.spring.dto.categories.DeleteCategoriesReqDto;
import com.lawencon.spring.dto.categories.InsertCategoriesReqDto;
import com.lawencon.spring.dto.categories.UpdateCategoriesReqDto;
import com.lawencon.spring.model.Categories;
import com.lawencon.spring.service.CategoriesService;

@Component
public class CategoriesServiceValidation {

	@Autowired
	private CategoriesService categoriesService;

	public void insertValidation(InsertCategoriesReqDto data) throws Exception {
		if (data.getName() == null || data.getName().trim().equals("")) {
			throw new Exception("Name is empty");
		} else if (data.getCode() == null || data.getCode().trim().equals("")) {
			throw new Exception("Code is empty");
		}
	}

	public void updateValidation(UpdateCategoriesReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("Id is empty");
		}

		Categories categoriesVersion = categoriesService.getById(data.getId());
		if (data.getVersion() != categoriesVersion.getVersion()) {
			throw new Exception("Invalid version");
		}
	}

	public void deleteValidation(DeleteCategoriesReqDto data) throws Exception {
		if (data.getId() == null) {
			throw new Exception("ID is not available");
		}

		Categories categoryId = categoriesService.getById(data.getId());
		if (categoryId == null) {
			throw new Exception("ID is not available");
		}
	}

}
