package com.lawencon.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.spring.dto.transaction.FindAllCartDtlRes;
import com.lawencon.spring.dto.transaction.FindAllCartDtlResData;
import com.lawencon.spring.dto.transaction.FindAllCartHdrRes;
import com.lawencon.spring.dto.transaction.FindAllCartHdrResData;
import com.lawencon.spring.dto.transaction.InsertCartReqDto;
import com.lawencon.spring.dto.transaction.InsertCartResDto;
import com.lawencon.spring.dto.transaction.InsertCartResDtoData;
import com.lawencon.spring.model.CartHeaders;
import com.lawencon.spring.service.transaction.CartDetailService;
import com.lawencon.spring.service.transaction.CartHeaderService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("carts")
public class CartController {

	@Autowired
	private CartHeaderService cartHeaderService;
	
	@Autowired
	private CartDetailService cartDetailService;
	
	@PostMapping
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = InsertCartResDto.class)))
	public ResponseEntity<?> insertData(@RequestBody InsertCartReqDto cartHeaders) {
		// insert via service  
		InsertCartResDto response = new InsertCartResDto();
		try {
			Long id = cartHeaderService.insert(cartHeaders);
			InsertCartResDtoData data = new InsertCartResDtoData();
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
	
	@GetMapping()
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindAllCartHdrRes.class)))
	public ResponseEntity<?> findAll() {

		FindAllCartHdrRes res = new FindAllCartHdrRes();

		try {
			List<FindAllCartHdrResData> findAll = cartHeaderService.getAll();
			res.setData(findAll);
			res.setMsg("OK");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("dtl/{idHdr}")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindAllCartDtlRes.class)))
	public ResponseEntity<?> findAllDtl(@PathVariable("idHdr") Long id){

		FindAllCartDtlRes res = new FindAllCartDtlRes();

		try {
			CartHeaders cartHdr = cartHeaderService.getById(id);
			List<FindAllCartDtlResData> findAllDtl = cartDetailService.getAllDtl(cartHdr.getId());
			res.setData(findAllDtl);
			res.setMsg("OK");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
