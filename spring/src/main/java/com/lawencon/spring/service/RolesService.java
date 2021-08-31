package com.lawencon.spring.service;

import java.util.List;

import com.lawencon.spring.dto.roles.FindAllPathRolesResData;
import com.lawencon.spring.dto.roles.InsertDataReqDto;
import com.lawencon.spring.dto.roles.UpdateDataReqDto;
import com.lawencon.spring.model.Roles;

public interface RolesService {

	Long insert(InsertDataReqDto data) throws Exception;

	void update(UpdateDataReqDto data) throws Exception;

	void delete(Long id) throws Exception;

	List<FindAllPathRolesResData> getAll() throws Exception;
	
	Roles getById(Long id) throws Exception;

	Roles getByNameHibernate(String name) throws Exception;

	Roles getByNameNative(String name) throws Exception;
}
