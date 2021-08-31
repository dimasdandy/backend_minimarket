package com.lawencon.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.spring.model.Users;

@Repository
public class UsersDaoImpl extends BaseDaoImpl implements UsersDao {

	@Override
	public void insert(Users data) throws Exception {
		em.persist(data);
	}

	@Override
	public void update(Users data) throws Exception {
		em.merge(data);

	}

	@Override
	public void delete(Long id) throws Exception {
		String sql = "delete from tb_m_users where id =:id";
		em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
	}

	@Override
	public List<Users> getAll() throws Exception {
		String sql = "select u from Users u where isActive = true";
		List<Users> listObj = em.createQuery(sql, Users.class).getResultList();
		return listObj;
	}

	@Override
	public Users getById(Long id) throws Exception {
		String sql = "select u from Users u where id = :id";
		List<Users> listObj = em.createQuery(sql, Users.class).setParameter("id", id).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}

	@Override
	public Users getByUsernameHibernate(String username) throws Exception {
		String sql = "select u from Users u where username = :username";
		List<Users> listObj = em.createQuery(sql, Users.class).setParameter("username", username).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}

	@Override
	public Users getByUsernameNative(String username) throws Exception {
		String sql = "select name, username from tb_m_users where username = :username";
		List<?> obj = em.createNativeQuery(sql).setParameter("username", username).getResultList();

		Users users = new Users();
		obj.forEach(val -> {
			Object[] data = (Object[]) val;
			users.setName(data[0] != null ? data[0].toString() : null);
			users.setUsername(data[1] != null ? data[1].toString() : null);
		});
		return users;
	}

}
