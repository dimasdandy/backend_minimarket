package com.lawencon.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.spring.model.Payments;

@Repository
public class PaymentsDaoImpl extends BaseDaoImpl implements PaymentsDao {

	@Override
	public void insert(Payments data) throws Exception {
		em.persist(data);
	}

	@Override
	public void update(Payments data) throws Exception {
		em.merge(data);
	}

	@Override
	public void delete(Long id) throws Exception {
		String sql = "delete from tb_m_payments where id =:id";
		em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
	}

	@Override
	public List<Payments> getAll() throws Exception {
		String sql = "select p from Payments p where isActive = true";
		List<Payments> listObj = em.createQuery(sql, Payments.class).getResultList();
		return listObj;
	}

	@Override
	public Payments getById(Long id) throws Exception {
		String sql = "select p from Payments p where id = :id";
		List<Payments> listObj = em.createQuery(sql, Payments.class).setParameter("id", id).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}

	// native
	@Override
	public Payments getByNameNative(String name) throws Exception {
		String sql = "select code, name from tb_m_payments where name = :name";
		List<?> obj = em.createNativeQuery(sql).setParameter("name", name).getResultList();

		Payments payments = new Payments();
		obj.forEach(val -> {
			Object[] data = (Object[]) val;
//			roles.setId(Long.valueOf(data[0].toString()));
			if (obj.size() == 2) {
				payments.setCode(data[0].toString());
				payments.setName(data[1].toString());
			}
		});
		return payments;
	}

	// hibernate
	@Override
	public Payments getByNameHibernate(String name) throws Exception {
		String sql = "select p from Payments p where name = :name";
		List<Payments> listObj = em.createQuery(sql, Payments.class).setParameter("name", name).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}

}
