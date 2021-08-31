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

import com.lawencon.spring.dto.payments.DeletePaymentsResDto;
import com.lawencon.spring.dto.payments.FindAllPathPaymentsRes;
import com.lawencon.spring.dto.payments.FindAllPathPaymentsResData;
import com.lawencon.spring.dto.payments.FindByIdPathPaymentsRes;
import com.lawencon.spring.dto.payments.FindByIdPathPaymentsResData;
import com.lawencon.spring.dto.payments.InsertPaymentsReqDto;
import com.lawencon.spring.dto.payments.InsertPaymentsResDto;
import com.lawencon.spring.dto.payments.InsertPaymentsResDtoData;
import com.lawencon.spring.dto.payments.UpdatePaymentsReqDto;
import com.lawencon.spring.dto.payments.UpdatePaymentsResDto;
import com.lawencon.spring.dto.payments.UpdatePaymentsResDtoData;
import com.lawencon.spring.model.Payments;
import com.lawencon.spring.service.PaymentsService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("payments")
public class PaymentsController {

	@Autowired
	private PaymentsService paymentsService;

	@GetMapping()
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindAllPathPaymentsRes.class)))
	public ResponseEntity<?> findAll() {

		FindAllPathPaymentsRes res = new FindAllPathPaymentsRes();

		try {
			List<FindAllPathPaymentsResData> findAll = paymentsService.getAll();
			res.setData(findAll);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindByIdPathPaymentsRes.class)))
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {

		FindByIdPathPaymentsRes res = new FindByIdPathPaymentsRes();

		try {
			Payments payments = paymentsService.getById(id);
			FindByIdPathPaymentsResData resData = new FindByIdPathPaymentsResData();
			resData.setId(payments.getId());
			resData.setName(payments.getName());
			resData.setCode(payments.getCode());
			resData.setIsActive(payments.getIsActive());
			resData.setVersion(payments.getVersion());
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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = InsertPaymentsResDto.class)))
	public ResponseEntity<?> insertData(@RequestBody InsertPaymentsReqDto payments) {
		// insert via service
		InsertPaymentsResDto response = new InsertPaymentsResDto();
		try {
			Long id = paymentsService.insert(payments);

			InsertPaymentsResDtoData data = new InsertPaymentsResDtoData();

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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdatePaymentsResDto.class)))
	public ResponseEntity<?> updateData(@RequestBody UpdatePaymentsReqDto payments) {
		// update via service
		UpdatePaymentsResDto response = new UpdatePaymentsResDto();
		try {
			paymentsService.update(payments);
			UpdatePaymentsResDtoData resData = new UpdatePaymentsResDtoData();
			resData.setVersion(payments.getVersion());
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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeletePaymentsResDto.class)))
	public ResponseEntity<?> deleteData(@PathVariable("id") Long id) {
		DeletePaymentsResDto res = new DeletePaymentsResDto();
		try {
			paymentsService.delete(id);
			res.setMsg("Deleted Succes!");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
