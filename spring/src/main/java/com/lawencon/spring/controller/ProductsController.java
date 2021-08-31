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

import com.lawencon.spring.dto.products.DeleteProductsResDto;
import com.lawencon.spring.dto.products.FindAllPathProductsRes;
import com.lawencon.spring.dto.products.FindAllPathProductsResData;
import com.lawencon.spring.dto.products.FindByIdPathProductsRes;
import com.lawencon.spring.dto.products.FindByIdPathProductsResData;
import com.lawencon.spring.dto.products.InsertProductsReqDto;
import com.lawencon.spring.dto.products.InsertProductsResDto;
import com.lawencon.spring.dto.products.InsertProductsResDtoData;
import com.lawencon.spring.dto.products.UpdateProductsReqDto;
import com.lawencon.spring.dto.products.UpdateProductsResDto;
import com.lawencon.spring.dto.products.UpdateProductsResDtoData;
import com.lawencon.spring.model.Products;
import com.lawencon.spring.service.ProductsService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("products")
public class ProductsController {

	@Autowired
	private ProductsService productsService;

	@GetMapping()
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindAllPathProductsRes.class)))
	public ResponseEntity<?> findAll() {

		FindAllPathProductsRes res = new FindAllPathProductsRes();

		try {
			List<FindAllPathProductsResData> findAll = productsService.getAll();
			res.setData(findAll);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindByIdPathProductsRes.class)))
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {

		FindByIdPathProductsRes res = new FindByIdPathProductsRes();

		try {
			Products products = productsService.getById(id);
			FindByIdPathProductsResData resData = new FindByIdPathProductsResData();
			resData.setId(products.getId());
			resData.setCode(products.getCode());
			resData.setName(products.getName());
			resData.setStock(products.getStock());
			resData.setPrice(products.getPrice());
			resData.setCategory(products.getCategories().getId());
			resData.setCategoryName(products.getCategories().getName());
			resData.setIsActive(products.getIsActive());
			resData.setVersion(products.getVersion());
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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = InsertProductsResDto.class)))
	public ResponseEntity<?> insertData(@RequestBody InsertProductsReqDto categories) {
		// insert via service
		InsertProductsResDto response = new InsertProductsResDto();
		try {
			Long id = productsService.insert(categories);

			InsertProductsResDtoData data = new InsertProductsResDtoData();

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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateProductsResDto.class)))
	public ResponseEntity<?> updateData(@RequestBody UpdateProductsReqDto products) {
		// update via service
		UpdateProductsResDto response = new UpdateProductsResDto();
		try {
			productsService.update(products);
			UpdateProductsResDtoData resData = new UpdateProductsResDtoData();
			resData.setVersion(products.getVersion());
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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteProductsResDto.class)))
	public ResponseEntity<?> deleteData(@PathVariable("id") Long id) {
		DeleteProductsResDto res = new DeleteProductsResDto();
		try {
			productsService.delete(id);
			res.setMsg("Deleted Succes!");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
