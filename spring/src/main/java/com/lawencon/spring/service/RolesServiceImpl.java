package com.lawencon.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.spring.dao.RolesDao;
import com.lawencon.spring.dto.roles.DeleteDataReqDto;
import com.lawencon.spring.dto.roles.FindAllPathRolesResData;
import com.lawencon.spring.dto.roles.InsertDataReqDto;
import com.lawencon.spring.dto.roles.UpdateDataReqDto;
import com.lawencon.spring.model.Roles;
import com.lawencon.spring.validation.RolesServiceValidation;

@Service
public class RolesServiceImpl extends BaseServiceImpl implements RolesService {

	private RolesDao rolesDao;

	@Autowired
	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	@Autowired
	private RolesServiceValidation rolesServiceValidation;

	@Override
	@Transactional
	public Long insert(InsertDataReqDto data) throws Exception {
		rolesServiceValidation.insertValidation(data);

		Roles roles = new Roles();
		roles.setCode(data.getCode());
		roles.setName(data.getName());
		roles.setCreatedBy(super.users());
		roles.setIsActive(data.getIsActive());
		rolesDao.insert(roles);
		return roles.getId();
	}

	@Override
	@Transactional
	public void update(UpdateDataReqDto data) throws Exception {
		rolesServiceValidation.updateValidation(data);
		
		Roles rolesUpdate = rolesDao.getById(data.getId());
		rolesUpdate.setName(data.getName());
		rolesUpdate.setCode(data.getCode());
		rolesUpdate.setUpdatedBy(super.users());
		rolesUpdate.setIsActive(data.getIsActive());
		rolesUpdate.setVersion(data.getVersion());
		rolesDao.update(rolesUpdate);

		Roles rolesUpdateVersion = rolesDao.getById(data.getId());
		data.setVersion(rolesUpdateVersion.getVersion());
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		DeleteDataReqDto delete = new DeleteDataReqDto();
		delete.setId(id);
		rolesServiceValidation.deleteValidation(delete);
		
		rolesDao.delete(id);
	}

	@Override
	public List<FindAllPathRolesResData> getAll() throws Exception {

		List<FindAllPathRolesResData> findAll = new ArrayList<FindAllPathRolesResData>();

		List<Roles> roles = rolesDao.getAll();
		for (Roles role : roles) {
			FindAllPathRolesResData allPath = new FindAllPathRolesResData();
			allPath.setId(role.getId());
			allPath.setCode(role.getCode());
			allPath.setName(role.getName());
			findAll.add(allPath);
		}
		return findAll;
	}

	@Override
	public Roles getById(Long id) throws Exception {
		return rolesDao.getById(id);
	}

	@Override
	public Roles getByNameHibernate(String name) throws Exception {
		return rolesDao.getByNameHibernate(name);
	}

	@Override
	public Roles getByNameNative(String name) throws Exception {
		return rolesDao.getByNameNative(name);
	}

}
