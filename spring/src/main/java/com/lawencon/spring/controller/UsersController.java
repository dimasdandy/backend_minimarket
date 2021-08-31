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

import com.lawencon.spring.dto.roles.InsertDataResDto;
import com.lawencon.spring.dto.roles.InsertDataResDtoData;
import com.lawencon.spring.dto.users.DeleteUserResDto;
import com.lawencon.spring.dto.users.FindAllPathUsersRes;
import com.lawencon.spring.dto.users.FindAllPathUsersResData;
import com.lawencon.spring.dto.users.FindByIdUsersPathRes;
import com.lawencon.spring.dto.users.FindByIdUsersPathResData;
import com.lawencon.spring.dto.users.InsertUserReqDto;
import com.lawencon.spring.dto.users.UpdateUserReqDto;
import com.lawencon.spring.dto.users.UpdateUserResDto;
import com.lawencon.spring.dto.users.UpdateUserResDtoData;
import com.lawencon.spring.model.Users;
import com.lawencon.spring.service.UsersService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping()
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindAllPathUsersRes.class)))
	public ResponseEntity<?> findAll() {

		FindAllPathUsersRes res = new FindAllPathUsersRes();

		try {
			List<FindAllPathUsersResData> findAll = usersService.getAll();
			res.setData(findAll);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindByIdUsersPathRes.class)))
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		FindByIdUsersPathRes res = new FindByIdUsersPathRes();

		try {
			Users users = usersService.getById(id);
			FindByIdUsersPathResData resData = new FindByIdUsersPathResData();
			resData.setId(users.getId());
			resData.setName(users.getName());
			resData.setUsername(users.getUsername());
			resData.setRole(users.getRole().getId());
			resData.setIsActive(users.getIsActive());
			resData.setVersion(users.getVersion());
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
	public ResponseEntity<?> insertData(@RequestBody InsertUserReqDto users) {
		// proses insert via service
		InsertDataResDto response = new InsertDataResDto();
		try {
			Long id = usersService.insert(users);
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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateUserResDto.class)))
	public ResponseEntity<?> updateData(@RequestBody UpdateUserReqDto users) {
		// update via service
		UpdateUserResDto response = new UpdateUserResDto();
		try {
			usersService.update(users);
			UpdateUserResDtoData resData = new UpdateUserResDtoData();
			resData.setVersion(users.getVersion());
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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteUserResDto.class)))
	public ResponseEntity<?> deleteData(@PathVariable("id") Long id) {
		DeleteUserResDto res = new DeleteUserResDto();
		try {
			usersService.delete(id);
			res.setMsg("Deleted Succes!");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
