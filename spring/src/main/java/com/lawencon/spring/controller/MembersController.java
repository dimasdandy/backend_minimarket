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

import com.lawencon.spring.dto.members.DeleteMembersResDto;
import com.lawencon.spring.dto.members.FindAllPathMembersRes;
import com.lawencon.spring.dto.members.FindAllPathMembersResData;
import com.lawencon.spring.dto.members.FindByIdMembersPathRes;
import com.lawencon.spring.dto.members.FindByIdMembersPathResData;
import com.lawencon.spring.dto.members.InsertMembersReqDto;
import com.lawencon.spring.dto.members.InsertMembersResDto;
import com.lawencon.spring.dto.members.InsertMembersResDtoData;
import com.lawencon.spring.dto.members.UpdateMembersReqDto;
import com.lawencon.spring.dto.members.UpdateMembersResDto;
import com.lawencon.spring.dto.members.UpdateMembersResDtoData;
import com.lawencon.spring.model.Members;
import com.lawencon.spring.service.MembersService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("members")
public class MembersController {

	@Autowired
	private MembersService membersService;

	@GetMapping()
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindAllPathMembersRes.class)))
	public ResponseEntity<?> findAll() {

		FindAllPathMembersRes res = new FindAllPathMembersRes();

		try {
			List<FindAllPathMembersResData> findAll = membersService.getAll();
			res.setData(findAll);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = FindByIdMembersPathRes.class)))
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {

		FindByIdMembersPathRes res = new FindByIdMembersPathRes();

		try {
			Members members = membersService.getById(id);
			FindByIdMembersPathResData resData = new FindByIdMembersPathResData();
			resData.setId(members.getId());
			resData.setName(members.getName());
			resData.setAddress(members.getAddress());
			resData.setPhoneNo(members.getPhoneNo());
			resData.setPoint(members.getPoint());
			resData.setIsActive(members.getIsActive());
			resData.setVersion(members.getVersion());
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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = InsertMembersResDto.class)))
	public ResponseEntity<?> insertData(@RequestBody InsertMembersReqDto members) {
		// insert via service
		InsertMembersResDto response = new InsertMembersResDto();
		try {
			Long id = membersService.insert(members);

			InsertMembersResDtoData data = new InsertMembersResDtoData();

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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateMembersResDto.class)))
	public ResponseEntity<?> updateData(@RequestBody UpdateMembersReqDto members) {
		// update via service
		UpdateMembersResDto response = new UpdateMembersResDto();
		try {
			membersService.update(members);
			UpdateMembersResDtoData resData = new UpdateMembersResDtoData();
			resData.setVersion(members.getVersion());
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
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteMembersResDto.class)))
	public ResponseEntity<?> deleteData(@PathVariable("id") Long id) {
		DeleteMembersResDto res = new DeleteMembersResDto();
		try {
			membersService.delete(id);
			res.setMsg("Deleted Succes!");
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			res.setMsg(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
