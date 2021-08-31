package com.lawencon.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.spring.dto.categories.DeleteCategoriesResDto;
import com.lawencon.spring.dto.categories.FindAllPathRes;
import com.lawencon.spring.dto.categories.FindAllPathResData;
import com.lawencon.spring.dto.categories.InsertCategoriesReqDto;
import com.lawencon.spring.dto.categories.InsertCategoriesResDto;
import com.lawencon.spring.dto.categories.InsertCategoriesResDtoData;
import com.lawencon.spring.dto.categories.UpdateCategoriesReqDto;
import com.lawencon.spring.dto.categories.UpdateCategoriesResDto;
import com.lawencon.spring.dto.categories.UpdateCategoriesResDtoData;
import com.lawencon.spring.dto.roles.FindByIdPathRes;
import com.lawencon.spring.dto.roles.FindByIdPathResData;
import com.lawencon.spring.model.Categories;
import com.lawencon.spring.service.CategoriesService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("categories")
public class CategoriesController {

	@Autowired
	private CategoriesService categoriesService;

	@GetMapping()
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindAllPathRes.class)))
	public ResponseEntity<?> findAll() {

		FindAllPathRes res = new FindAllPathRes();

		try {
			List<FindAllPathResData> findAll = categoriesService.getAll();
			res.setData(findAll);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindByIdPathRes.class)))
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {

		FindByIdPathRes res = new FindByIdPathRes();

		try {
			Categories categories = categoriesService.getById(id);
			FindByIdPathResData resData = new FindByIdPathResData();
			resData.setId(categories.getId());
			resData.setName(categories.getName());
			resData.setCode(categories.getCode());
			resData.setIsActive(categories.getIsActive());
			resData.setVersion(categories.getVersion());
			res.setMsg("OK");
			res.setData(resData);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = InsertCategoriesResDto.class)))
	public ResponseEntity<?> insertData(@RequestBody InsertCategoriesReqDto categories) {
		// insert via service
		InsertCategoriesResDto response = new InsertCategoriesResDto();
		try {
			Long id = categoriesService.insert(categories);

			InsertCategoriesResDtoData data = new InsertCategoriesResDtoData();

			data.setId(id);
			response.setData(data);
			response.setMsg("Insert success");

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMsg(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateCategoriesResDto.class)))
	public ResponseEntity<?> updateData(@RequestBody UpdateCategoriesReqDto categories) {
		// update via service
		UpdateCategoriesResDto response = new UpdateCategoriesResDto();
		try {
			categoriesService.update(categories);
			UpdateCategoriesResDtoData resData = new UpdateCategoriesResDtoData();
			resData.setVersion(categories.getVersion());
			response.setData(resData);
			response.setMsg("Updated Success!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMsg(e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("{id}")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteCategoriesResDto.class)))
	public ResponseEntity<?> deleteData(@PathVariable("id") Long id) {
		DeleteCategoriesResDto res = new DeleteCategoriesResDto();
		try {
			categoriesService.delete(id);
			res.setMsg("Deleted Succes!");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
