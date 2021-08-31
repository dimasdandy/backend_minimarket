package com.lawencon.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.spring.dao.CategoriesDao;
import com.lawencon.spring.dto.categories.DeleteCategoriesReqDto;
import com.lawencon.spring.dto.categories.FindAllPathResData;
import com.lawencon.spring.dto.categories.InsertCategoriesReqDto;
import com.lawencon.spring.dto.categories.UpdateCategoriesReqDto;
import com.lawencon.spring.model.Categories;
import com.lawencon.spring.validation.CategoriesServiceValidation;

@Service
public class CategoriesServiceImpl extends BaseServiceImpl implements CategoriesService {

	private CategoriesDao categoriesDao;

	@Autowired
	public void setCategoriesDao(CategoriesDao categoriesDao) {
		this.categoriesDao = categoriesDao;
	}

	@Autowired
	private CategoriesServiceValidation categoriesValidation;

	@Override
	@Transactional
	public Long insert(InsertCategoriesReqDto data) throws Exception {
		categoriesValidation.insertValidation(data);
		Categories categories = new Categories();
		categories.setCode(data.getCode());
		categories.setName(data.getName());
		categories.setCreatedBy(super.users());
		categories.setIsActive(data.getIsActive());
		categoriesDao.insert(categories);
		return categories.getId();
	}

	@Override
	@Transactional
	public void update(UpdateCategoriesReqDto data) throws Exception {
		categoriesValidation.updateValidation(data);
		Categories categoriesUpdate = categoriesDao.getById(data.getId());
		categoriesUpdate.setName(data.getName());
		categoriesUpdate.setCode(data.getCode());
		categoriesUpdate.setUpdatedBy(super.users());
		categoriesUpdate.setIsActive(data.getIsActive());
		categoriesUpdate.setVersion(data.getVersion());
		categoriesDao.update(categoriesUpdate);

		Categories categoriesUpdateVersion = categoriesDao.getById(data.getId());
		data.setVersion(categoriesUpdateVersion.getVersion());
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		DeleteCategoriesReqDto delete = new DeleteCategoriesReqDto();
		delete.setId(id);
		categoriesValidation.deleteValidation(delete);
		categoriesDao.delete(id);
	}

	@Override
	public List<FindAllPathResData> getAll() throws Exception {

		List<FindAllPathResData> findAll = new ArrayList<FindAllPathResData>();

		List<Categories> categories = categoriesDao.getAll();
		for (Categories category : categories) {
			FindAllPathResData allPath = new FindAllPathResData();
			allPath.setId(category.getId());
			allPath.setCode(category.getCode());
			allPath.setName(category.getName());
			findAll.add(allPath);
		}
		return findAll;
	}

	@Override
	public Categories getById(Long id) throws Exception {
		return categoriesDao.getById(id);
	}

	@Override
	public Categories getByNameHibernate(String name) throws Exception {
		return categoriesDao.getByNameHibernate(name);
	}

	@Override
	public Categories getByNameNative(String name) throws Exception {
		return categoriesDao.getByNameNative(name);
	}
}
