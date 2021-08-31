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

import com.lawencon.spring.dto.roles.DeleteDataResDto;
import com.lawencon.spring.dto.roles.FindAllPathRolesRes;
import com.lawencon.spring.dto.roles.FindAllPathRolesResData;
import com.lawencon.spring.dto.roles.FindByIdPathRes;
import com.lawencon.spring.dto.roles.FindByIdPathResData;
import com.lawencon.spring.dto.roles.InsertDataReqDto;
import com.lawencon.spring.dto.roles.InsertDataResDto;
import com.lawencon.spring.dto.roles.InsertDataResDtoData;
import com.lawencon.spring.dto.roles.UpdateDataReqDto;
import com.lawencon.spring.dto.roles.UpdateDataResDto;
import com.lawencon.spring.dto.roles.UpdateDataResDtoData;
import com.lawencon.spring.model.Roles;
import com.lawencon.spring.service.RolesService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("roles")
public class RolesController {

	@Autowired
	private RolesService rolesService;

	@GetMapping()
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindAllPathRolesRes.class)))
	public ResponseEntity<?> findAll() {

		FindAllPathRolesRes res = new FindAllPathRolesRes();

		try {
			List<FindAllPathRolesResData> findAll = rolesService.getAll();
			res.setData(findAll);
			res.setMsg("OK");
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
			Roles roles = rolesService.getById(id);
			FindByIdPathResData resData = new FindByIdPathResData();
			resData.setId(roles.getId());
			resData.setName(roles.getName());
			resData.setCode(roles.getCode());
			resData.setIsActive(roles.getIsActive());
			resData.setVersion(roles.getVersion());
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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = InsertDataResDto.class)))
	public ResponseEntity<?> insertData(@RequestBody InsertDataReqDto roles) {
		// insert via service
		InsertDataResDto response = new InsertDataResDto();
		try {
			Long id = rolesService.insert(roles);

			InsertDataResDtoData data = new InsertDataResDtoData();

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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateDataResDto.class)))
	public ResponseEntity<?> updateData(@RequestBody UpdateDataReqDto roles) {
		// update via service
		UpdateDataResDto response = new UpdateDataResDto();
		try {
			rolesService.update(roles);
			UpdateDataResDtoData resData = new UpdateDataResDtoData();
			resData.setVersion(roles.getVersion());
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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteDataResDto.class)))
	public ResponseEntity<?> deleteData(@PathVariable("id") Long id) {
		DeleteDataResDto res = new DeleteDataResDto();
		try {
			rolesService.delete(id);
			res.setMsg("Deleted Succes!");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
