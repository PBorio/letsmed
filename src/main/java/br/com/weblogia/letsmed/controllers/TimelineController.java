package br.com.weblogia.letsmed.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.weblogia.letsmed.controllers.helpers.TimelineViewer;
import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.User;

@Controller
public class TimelineController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject
	private User user;
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/timeline")
	public void timeline(){
		StringBuilder sql = new StringBuilder();
		sql.append(" from Order o ");
		sql.append(" inner join fetch o.supplier s ");
		sql.append(" where o.conclusionDate is null ");
		
		if (!user.isAdmin()) {
			sql.append(" and s.user.id = :id ");
		}
		
		sql.append(" or exists (select 1 from Complain c where c.solvedDate is null and c.order.id = o.id ) ");
		sql.append(" order by o.supplier.supplierName asc, o.id desc ");

		Query query = entityManager.createQuery(sql.toString());
		
		System.out.println(user.getId());
		if (!user.isAdmin()) {
			query.setParameter("id", user.getId());
		}
		
		List<Order> orderList = (List<Order>) query.getResultList();
		
		TimelineViewer timelineViewer = new TimelineViewer(orderList);
		
		result.include("timelineViewer", timelineViewer);
		result.include("controller", "timeline");
		result.include("user", user);
	}
	
	@SuppressWarnings("unchecked")
	@Path("/timeline/search")
	public void search(Order order) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from Order o ");
		sql.append(" where ( o.conclusionDate is null ");
		sql.append(" or exists (select 1 from Complain c where c.solvedDate is null and c.order.id = o.id ) ) ");
		
		if (order != null){
			if (order.getOrderNumber() != null && !"".equals(order.getOrderNumber().trim())){
				sql.append(" and o.orderNumber like :orderNumber");
			}
			
			if (order.getCustomer() != null && order.getCustomer().getName() != null && !"".equals(order.getCustomer().getName().trim())){
				sql.append(" and o.customer.name like :customerName ");
			}
			
			if (order.getSupplier() != null && order.getSupplier().getSupplierName() != null && !"".equals(order.getSupplier().getSupplierName().trim())){
				sql.append(" and o.supplier.supplierName like :supplierName ");
			}
			
			if (order.getSupplier() != null && order.getSupplier().getUser() != null && order.getSupplier().getUser().getLogin() != null && !"".equals(order.getSupplier().getUser().getLogin().trim())){
				sql.append(" and o.supplier.user.login like :user ");
			}
		}
		
		sql.append(" order by o.supplier.supplierName asc, o.id desc ");

		Query q = entityManager.createQuery(sql.toString());
		
		if (order != null){
			if (order.getOrderNumber() != null && !"".equals(order.getOrderNumber().trim())){
				q.setParameter("orderNumber", "%"+order.getOrderNumber()+"%");
			}
			
			if (order.getCustomer() != null && order.getCustomer().getName() != null && !"".equals(order.getCustomer().getName().trim())){
				q.setParameter("customerName", "%"+order.getCustomer().getName()+"%");
			}
			
			if (order.getSupplier() != null && order.getSupplier().getSupplierName() != null && !"".equals(order.getSupplier().getSupplierName().trim())){
				q.setParameter("supplierName", "%"+order.getSupplier().getSupplierName()+"%");
			}
			
			if (order.getSupplier() != null && order.getSupplier().getUser() != null && order.getSupplier().getUser().getLogin() != null && !"".equals(order.getSupplier().getUser().getLogin().trim())){
				q.setParameter("user", "%"+order.getSupplier().getUser().getLogin()+"%");
			}
		}
		
		List<Order> orderList = (List<Order>) q.getResultList();
		result.include(order);
		result.include("orderList", orderList);
		result.of(this).timeline();
	}

}
