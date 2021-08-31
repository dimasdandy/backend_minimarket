package com.lawencon.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.spring.dao.MembersDao;
import com.lawencon.spring.dto.members.DeleteMembersReqDto;
import com.lawencon.spring.dto.members.FindAllPathMembersResData;
import com.lawencon.spring.dto.members.InsertMembersReqDto;
import com.lawencon.spring.dto.members.UpdateMembersReqDto;
import com.lawencon.spring.model.Members;
import com.lawencon.spring.validation.MembersServiceValidation;

@Service
public class MembersServiceImpl extends BaseServiceImpl implements MembersService {

	private MembersDao membersDao;

	@Autowired
	public void setMembersDao(MembersDao membersDao) {
		this.membersDao = membersDao;
	}

	@Autowired
	private MembersServiceValidation memberValdiation;

	@Override
	@Transactional
	public Long insert(InsertMembersReqDto data) throws Exception {
		memberValdiation.insertValidation(data);
		Members members = new Members();
		members.setName(data.getName());
		members.setAddress(data.getAddress());
		members.setPoint(data.getPoint());
		members.setPhoneNo(data.getPhoneNo());
		members.setCreatedBy(super.users());
		members.setIsActive(data.getIsActive());
		membersDao.insert(members);
		return members.getId();
	}

	@Override
	@Transactional
	public void update(UpdateMembersReqDto data) throws Exception {
		memberValdiation.updateValidation(data);
		Members members = getById(data.getId());
		members.setName(data.getName());
		members.setAddress(data.getAddress());
		members.setPoint(data.getPoint());
		members.setPhoneNo(data.getPhoneNo());
		members.setUpdatedBy(super.users());
		members.setIsActive(data.getIsActive());
		members.setVersion(data.getVersion());
		membersDao.update(members);

		Members membersUpdate = membersDao.getById(data.getId());
		data.setVersion(membersUpdate.getVersion());
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		DeleteMembersReqDto delete = new DeleteMembersReqDto();
		delete.setId(id);
		memberValdiation.deleteValidation(delete);
		membersDao.delete(id);
	}

	@Override
	public List<FindAllPathMembersResData> getAll() throws Exception {

		List<FindAllPathMembersResData> findAll = new ArrayList<FindAllPathMembersResData>();

		List<Members> members = membersDao.getAll();
		for (Members member : members) {
			FindAllPathMembersResData allPath = new FindAllPathMembersResData();
			allPath.setId(member.getId());
			allPath.setName(member.getName());
			allPath.setAddress(member.getAddress());
			allPath.setPhoneNo(member.getPhoneNo());
			allPath.setPoint(member.getPoint());
			findAll.add(allPath);
		}
		return findAll;
	}

	@Override
	public Members getById(Long id) throws Exception {
		return membersDao.getById(id);
	}

	@Override
	public Members getByNameHibernate(String name) throws Exception {
		return membersDao.getByNameHibernate(name);
	}

	@Override
	public Members getByNameNative(String name) throws Exception {
		return membersDao.getByNameNative(name);
	}
}
