package br.com.weblogia.letsmed.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.domain.Order;

@Controller
public class TimelineController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/timeline")
	public void timeline(){
		StringBuilder sql = new StringBuilder();
		sql.append(" from Order o ");
		sql.append(" where o.conclusionDate is null ");
		sql.append(" or exists (select 1 from Complain c where c.solvedDate is null and c.order.id = o.id ) ");
		sql.append(" order by o.supplier.supplierName ");

		List<Order> orderList = (List<Order>) entityManager.createQuery(sql.toString()).getResultList();
		result.include("orderList", orderList);
		result.include("controller", "timeline");
	}

}
