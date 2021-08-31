package com.lawencon.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.spring.model.Members;

@Repository
public class MembersDaoImpl extends BaseDaoImpl implements MembersDao {

	@Override
	public void insert(Members data) throws Exception {
		em.persist(data);
	}

	@Override
	public void update(Members data) throws Exception {
		em.merge(data);
	}

	@Override
	public void delete(Long id) throws Exception {
		String sql = "delete from tb_m_members where id =:id";
		em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
	}

	@Override
	public List<Members> getAll() throws Exception {
		String sql = "select m from Members m where isActive = true";
		List<Members> listObj = em.createQuery(sql, Members.class).getResultList();
		return listObj;
	}

	@Override
	public Members getById(Long id) throws Exception {
		String sql = "select m from Members m where id = :id";
		List<Members> listObj = em.createQuery(sql, Members.class).setParameter("id", id).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}

	// native
	@Override
	public Members getByNameNative(String name) throws Exception {
		String sql = "select name, phoneNo from tb_m_members where name = :name";
		List<?> obj = em.createNativeQuery(sql).setParameter("name", name).getResultList();

		Members members = new Members();
		obj.forEach(val -> {
			Object[] data = (Object[]) val;
			if (obj.size() == 2) {
				members.setName(data[0].toString());
				members.setPhoneNo(data[1].toString());
			}
		});
		return members;
	}

	// hibernate
	@Override
	public Members getByNameHibernate(String name) throws Exception {
		String sql = "select p from Products p where name = :name";
		List<Members> listObj = em.createQuery(sql, Members.class).setParameter("name", name).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}
}
