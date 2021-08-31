package com.lawencon.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.spring.dao.UsersDao;
import com.lawencon.spring.dto.users.DeleteUserReqDto;
import com.lawencon.spring.dto.users.FindAllPathUsersResData;
import com.lawencon.spring.dto.users.InsertUserReqDto;
import com.lawencon.spring.dto.users.UpdateUserReqDto;
import com.lawencon.spring.model.Roles;
import com.lawencon.spring.model.Users;
import com.lawencon.spring.validation.UsersServiceValidation;

@Service
public class UsersServiceImpl extends BaseServiceImpl implements UsersService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UsersServiceValidation userValidation;

	@Override
	@Transactional
	public Long insert(InsertUserReqDto data) throws Exception {
		userValidation.insertValidation(data);
		
		Users users = new Users();
		Roles roles = rolesService.getById(data.getRole());
		users.setName(data.getName());
		users.setUsername(data.getUsername());
		users.setPassword(bCryptPasswordEncoder.encode(data.getPassword()));
		users.setRole(roles);
		users.setCreatedBy(super.users());
		users.setIsActive(data.getIsActive());
		usersDao.insert(users);
		return users.getId();
	}

	@Override
	@Transactional
	public void update(UpdateUserReqDto data) throws Exception {
		userValidation.updateValidation(data);
		
		Users users = getById(data.getId());
		Roles roles = rolesService.getById(data.getRole());

		users.setName(data.getName());
		users.setUsername(data.getUsername());
		users.setRole(roles);
		users.setUpdatedBy(super.users());
		users.setIsActive(data.getIsActive());
		users.setVersion(data.getVersion());
		usersDao.update(users);

		Users usersUpdate = usersDao.getById(data.getId());
		data.setVersion(usersUpdate.getVersion());
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		DeleteUserReqDto delete = new DeleteUserReqDto();
		delete.setId(id);
		userValidation.deleteValidation(delete);
		usersDao.delete(id);
	}

	@Override
	public List<FindAllPathUsersResData> getAll() throws Exception {
		List<FindAllPathUsersResData> findAll = new ArrayList<FindAllPathUsersResData>();

		List<Users> users = usersDao.getAll();
		for (Users user : users) {
			FindAllPathUsersResData allPath = new FindAllPathUsersResData();
			allPath.setId(user.getId());
			allPath.setName(user.getName());
			allPath.setUsername(user.getUsername());
			allPath.setRoleId(user.getRole().getId());
			allPath.setRoleCode(user.getRole().getCode());
			allPath.setRoleName(user.getRole().getName());
			findAll.add(allPath);
		}
		return findAll;
	}

	@Override
	public Users getById(Long id) throws Exception {
		return usersDao.getById(id);
	}

	@Override
	public Users getByUsernameHibernate(String username) throws Exception {
		return usersDao.getByUsernameHibernate(username);
	}

	@Override
	public Users getByUsernameNative(String username) throws Exception {
		return usersDao.getByUsernameNative(username);
	}
}
