package com.lawencon.spring.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_trx_cart_hdr")
public class CartHeaders extends BaseModels {

	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private Users user;

	@ManyToOne
	@JoinColumn(name = "id_payment")
	private Payments payment;

	@ManyToOne
	@JoinColumn(name = "id_member")
	private Members member;

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Payments getPayment() {
		return payment;
	}

	public void setPayment(Payments payment) {
		this.payment = payment;
	}

	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}

}
