package com.lawencon.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.spring.model.Categories;

@Repository
public class CategoriesDaoImpl extends BaseDaoImpl implements CategoriesDao {

	@Override
	public void insert(Categories data) throws Exception {
		em.persist(data);
	}

	@Override
	public void update(Categories data) throws Exception {
		em.merge(data);
	}

	@Override
	public void delete(Long id) throws Exception {
		String sql = "delete from tb_m_categories where id =:id";
		em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
	}
	
	@Override
	public List<Categories> getAll() throws Exception {
		String sql = "select c from Categories c where isActive = true";
		List<Categories> listObj = em.createQuery(sql, Categories.class).getResultList();
		return listObj;
	}

	@Override
	public Categories getById(Long id) throws Exception {
		String sql = "select c from Categories c where id = :id";
		List<Categories> listObj = em.createQuery(sql, Categories.class).setParameter("id", id).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}

	// native
	@Override
	public Categories getByNameNative(String name) throws Exception {
		String sql = "select code, name from tb_m_roles where name = :name";
		List<?> obj = em.createNativeQuery(sql).setParameter("name", name).getResultList();

		Categories categories = new Categories();
		obj.forEach(val -> {
			Object[] data = (Object[]) val;
			if (obj.size() == 2) {
				categories.setCode(data[0].toString());
				categories.setName(data[1].toString());
			}
		});
		return categories;
	}

	// hibernate
	@Override
	public Categories getByNameHibernate(String name) throws Exception {
		String sql = "select c from Categories c where name = :name";
		List<Categories> listObj = em.createQuery(sql, Categories.class).setParameter("name", name).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}
}
