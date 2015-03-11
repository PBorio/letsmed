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
		List<Order> orderList = (List<Order>) entityManager.createQuery(" from Order o where o.conclusionDate is null order by o.orderDate desc ").getResultList();
		result.include("orderList", orderList);
		result.include("controller", "timeline");
	}

}
