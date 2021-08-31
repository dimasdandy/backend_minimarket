package com.lawencon.spring.dao.transaction;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.spring.dao.BaseDaoImpl;
import com.lawencon.spring.model.CartDetails;

@Repository
public class CartDetailDaoImpl extends BaseDaoImpl implements CartDetailDao {

	@Override
	public void insert(CartDetails data) throws Exception {
		em.persist(data);
	}

	@Override
	public List<CartDetails> getAllDtl(Long id) throws Exception {
		String sql = "select cd from CartDetails cd where isActive = true and cd.cartHeader.id = :id";
		List<CartDetails> listObj = em.createQuery(sql, CartDetails.class).setParameter("id", id).getResultList();
		return listObj; 
	}

}
