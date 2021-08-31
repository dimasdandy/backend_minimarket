package com.lawencon.spring.service;

import java.util.List;

import com.lawencon.spring.dto.members.FindAllPathMembersResData;
import com.lawencon.spring.dto.members.InsertMembersReqDto;
import com.lawencon.spring.dto.members.UpdateMembersReqDto;
import com.lawencon.spring.model.Members;

public interface MembersService {

	Long insert(InsertMembersReqDto data) throws Exception;

	void update(UpdateMembersReqDto data) throws Exception;

	void delete(Long id) throws Exception;

	List<FindAllPathMembersResData> getAll() throws Exception;

	Members getById(Long id) throws Exception;

	Members getByNameHibernate(String name) throws Exception;

	Members getByNameNative(String name) throws Exception;
}
