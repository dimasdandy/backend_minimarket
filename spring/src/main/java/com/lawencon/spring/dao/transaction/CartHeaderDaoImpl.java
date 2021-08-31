package com.lawencon.spring.dao.transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.spring.dao.BaseDaoImpl;
import com.lawencon.spring.dto.transaction.FindAllCartHdrResData;
import com.lawencon.spring.model.CartHeaders;

@Repository
public class CartHeaderDaoImpl extends BaseDaoImpl implements CartHeaderDao {

	@Override
	public void insert(CartHeaders data) throws Exception {
		em.persist(data);
	}

	@Override
	public CartHeaders getById(Long id) throws Exception {
		String sql = "select ch from CartHeaders ch where id = :id";
		List<CartHeaders> listObj = em.createQuery(sql, CartHeaders.class).setParameter("id", id).getResultList();
		return !listObj.isEmpty() ? listObj.get(0) : null;
	}

	@Override
	public List<CartHeaders> getAll() throws Exception {
		String sql = "select ch from CartHeaders ch where isActive = true";
		List<CartHeaders> listObj = em.createQuery(sql, CartHeaders.class).getResultList();
		return listObj;
	}

	@Override
	public List<FindAllCartHdrResData> getTotalPrice() throws Exception {
		String sql = "select hdr.id, hdr.transaction_date, users.name as user_name, payments.name as payment_name, " + 
				"members.name as member_name, sum(product.price*dtl.quantity) as total_price, members.id as member_id " + 
				"from tb_trx_cart_hdr as hdr " + 
				"left join tb_m_users as users on users.id = hdr.id_user " + 
				"inner join tb_m_payments as payments on payments.id = hdr.id_payment " + 
				"left join tb_m_members as members on members.id = hdr.id_member " + 
				"inner join tb_trx_cart_dtl as dtl on dtl.id_cart_hdr = hdr.id " + 
				"inner join tb_m_products as product on product.id = dtl.id_product " + 
				"group by hdr.id, hdr.transaction_date, users.name, payments.name, members.name, members.id "+
				"order by hdr.transaction_date desc";
		List<?> listObj = em.createNativeQuery(sql).getResultList();
		
		List<FindAllCartHdrResData> listCartHdr = new ArrayList<FindAllCartHdrResData>();
		
		listObj.forEach(val -> {
			Object[] data = (Object[]) val;
			FindAllCartHdrResData findAllHdr = new FindAllCartHdrResData();
			findAllHdr.setId(Long.valueOf(data[0].toString()));
			findAllHdr.setTransactionDate(((Timestamp) data[1]).toLocalDateTime());
			findAllHdr.setCashierName(data[2] != null ? data[2].toString() : "-" );
			findAllHdr.setPaymentName(data[3].toString());
			findAllHdr.setMemberName(data[4] != null ? data[4].toString() : "-");
			findAllHdr.setTotalPrice((BigDecimal) data[5]);
			findAllHdr.setIsMember(data[6] != null ? true : false);
			listCartHdr.add(findAllHdr);
		});
		return listCartHdr;
	}

}
