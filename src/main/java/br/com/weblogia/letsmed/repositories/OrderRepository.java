package br.com.weblogia.letsmed.repositories;

import javax.persistence.EntityManager;

public class OrderRepository {

	@SuppressWarnings("unused")
	private EntityManager entityManager;

	public OrderRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
