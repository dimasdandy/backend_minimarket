package com.lawencon.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.spring.dao.ProductsDao;
import com.lawencon.spring.dto.products.DeleteProductsReqDto;
import com.lawencon.spring.dto.products.FindAllPathProductsResData;
import com.lawencon.spring.dto.products.InsertProductsReqDto;
import com.lawencon.spring.dto.products.UpdateProductsReqDto;
import com.lawencon.spring.model.Categories;
import com.lawencon.spring.model.Products;
import com.lawencon.spring.validation.ProductsServiceValidation;

@Service
public class ProductsServiceImpl extends BaseServiceImpl implements ProductsService {

	private ProductsDao productsDao;

	@Autowired
	public void setCategoriesDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
	}

	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private ProductsServiceValidation productValidation;

	@Override
	@Transactional
	public Long insert(InsertProductsReqDto data) throws Exception {
		productValidation.insertValidation(data);

		Products products = new Products();
		Categories categories = categoriesService.getById(data.getCategory());
		products.setName(data.getName());
		products.setCode(data.getCode());
		products.setPrice(data.getPrice());
		products.setStock(data.getStock());
		products.setCategories(categories);
		products.setCreatedBy(super.users());
		products.setIsActive(data.getIsActive());
		productsDao.insert(products);
		return products.getId();
	}

	@Override
	@Transactional
	public void update(UpdateProductsReqDto data) throws Exception {
		productValidation.updateValidation(data);

		Products products = getById(data.getId());
		Categories categories = categoriesService.getById(data.getCategory());
		products.setName(data.getName());
		products.setCode(data.getCode());
		products.setPrice(data.getPrice());
		products.setStock(data.getStock());
		products.setCategories(categories);
		products.setUpdatedBy(super.users());
		products.setIsActive(data.getIsActive());
		products.setVersion(data.getVersion());
		productsDao.insert(products);

		Products productUpdate = productsDao.getById(data.getId());
		data.setVersion(productUpdate.getVersion());
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		DeleteProductsReqDto delete = new DeleteProductsReqDto();
		delete.setId(id);
		productValidation.deleteValidation(delete);
		productsDao.delete(id);
	}

	@Override
	public List<FindAllPathProductsResData> getAll() throws Exception {

		List<FindAllPathProductsResData> findAll = new ArrayList<FindAllPathProductsResData>();

		List<Products> products = productsDao.getAll();
		for (Products product : products) {
			FindAllPathProductsResData allPath = new FindAllPathProductsResData();
			allPath.setId(product.getId());
			allPath.setCode(product.getCode());
			allPath.setName(product.getName());
			allPath.setStock(product.getStock());
			allPath.setPrice(product.getPrice());
			allPath.setCategoryName(product.getCategories().getName());
			findAll.add(allPath);
		}
		return findAll;
	}

	@Override
	public Products getById(Long id) throws Exception {
		return productsDao.getById(id);
	}

	@Override
	public Products getByNameHibernate(String name) throws Exception {
		return productsDao.getByNameHibernate(name);
	}

	@Override
	public Products getByNameNative(String name) throws Exception {
		return productsDao.getByNameNative(name);
	}
}
