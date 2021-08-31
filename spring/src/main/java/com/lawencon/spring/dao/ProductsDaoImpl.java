package com.lawencon.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.spring.model.Products;

@Repository
public class ProductsDaoImpl extends BaseDaoImpl implements ProductsDao {

	@Override
	public void insert(Products data) throws Exception {
		em.persist(data);
	}

	@Override
	public void update(Products data) throws Exception {
		em.merge(data);
	}

	@Override
	public void delete(Long id) throws Exception {
		String sql = "delete from tb_m_products where id =:id";
		em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
	}

	@Override
	public List<Products> getAll() throws Exception {
		String sql = "select p from Products p where isActive = true";
		List<Products> listObj = em.createQuery(sql, Products.class).getResultList();
		return listObj;
	}

	@Override
	public Products getById(Long id) throws Exception {
		String sql = "select p from Products p where id = :id";
		List<Products> listObj = em.createQuery(sql, Products.class).setParameter("id", id).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}

	// native
	@Override
	public Products getByNameNative(String name) throws Exception {
		String sql = "select code, name from tb_m_products where name = :name";
		List<?> obj = em.createNativeQuery(sql).setParameter("name", name).getResultList();

		Products product = new Products();
		obj.forEach(val -> {
			Object[] data = (Object[]) val;
			if (obj.size() == 2) {
				product.setCode(data[0].toString());
				product.setName(data[1].toString());
			}
		});
		return product;
	}

	// hibernate
	@Override
	public Products getByNameHibernate(String name) throws Exception {
		String sql = "select p from Products p where name = :name";
		List<Products> listObj = em.createQuery(sql, Products.class).setParameter("name", name).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}
}
