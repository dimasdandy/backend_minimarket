package com.lawencon.spring.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDaoImpl {

	@PersistenceContext
	protected EntityManager em;
}
