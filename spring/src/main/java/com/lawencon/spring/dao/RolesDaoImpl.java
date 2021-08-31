package com.lawencon.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.spring.model.Roles;

@Repository
public class RolesDaoImpl extends BaseDaoImpl implements RolesDao {

	@Override
	public void insert(Roles data) throws Exception {
		em.persist(data);
	}

	@Override
	public void update(Roles data) throws Exception {
		em.merge(data);
	}

	@Override
	public void delete(Long id) throws Exception {
		String sql = "delete from tb_m_roles where id =:id";
		em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
	}

	@Override
	public List<Roles> getAll() throws Exception {
		String sql = "select r from Roles r where isActive = true";
		List<Roles> listObj = em.createQuery(sql, Roles.class).getResultList();
		return listObj;
	}

	@Override
	public Roles getById(Long id) throws Exception {
		String sql = "select r from Roles r where id = :id";
		List<Roles> listObj = em.createQuery(sql, Roles.class).setParameter("id", id).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}

	// native
	@Override
	public Roles getByNameNative(String name) throws Exception {
		String sql = "select code, name from tb_m_roles where name = :name";
		List<?> obj = em.createNativeQuery(sql).setParameter("name", name).getResultList();

		Roles roles = new Roles();
		obj.forEach(val -> {
			Object[] data = (Object[]) val;
			if (obj.size() == 2) {
				roles.setCode(data[0].toString());
				roles.setName(data[1].toString());
			}
		});
		return roles;
	}

	// hibernate
	@Override
	public Roles getByNameHibernate(String name) throws Exception {
		String sql = "select r from Roles r where name = :name";
		List<Roles> listObj = em.createQuery(sql, Roles.class).setParameter("name", name).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}

	@Override
	public Roles getByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
