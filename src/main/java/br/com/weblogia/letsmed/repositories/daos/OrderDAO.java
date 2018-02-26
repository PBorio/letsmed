package br.com.weblogia.letsmed.repositories.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.User;

public class OrderDAO {

	private EntityManager entityManager;
	private User user;

	public OrderDAO(EntityManager entityManager, User user) {
		this.entityManager = entityManager;
		this.user = user;
	}
	
	public List<Order> getOrders() {
		StringBuilder sql = new StringBuilder();
		sql.append(" from Order o ");
		sql.append(" inner join fetch o.supplier s ");
		sql.append(" where o.conclusionDate is null ");
		
		if (!"Admin".equals(user.getRole().getDescription())) {
			sql.append("and s.user.id = :id ");
		}
		
		sql.append(" or exists (select 1 from Complain c where c.solvedDate is null and c.order.id = o.id ) ");
		sql.append(" order by o.supplier.supplierName asc, o.id desc ");
	
		Query query = entityManager.createQuery(sql.toString());
		query.setParameter("id", user.getId());
		
		@SuppressWarnings("unchecked")
		List<Order> orderList = (List<Order>) query.getResultList();
		return orderList;
	}

}
