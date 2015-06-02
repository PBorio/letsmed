package br.com.weblogia.letsmed.controllers;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.domain.Complain;
import br.com.weblogia.letsmed.domain.Order;

@Controller
public class ComplainsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@Get
	@Path("/complains/order/{id}")
	public void complain(Long id){
		Order order = entityManager.find(Order.class, id);
		Complain complain = new Complain();
		complain.setOrder(order);
		result.include(order);
		result.include("complain", complain);
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/complains")
	public void list(){
		List<Order> orderList = (List<Order>) entityManager.createQuery(" from Order o where confirmationDate is not null and originalDocumentDate is null order by o.orderDate desc ").getResultList();
		result.include("orderList", orderList);
		result.include("url", "forwardDetails/order");
		result.include("controller", "forwardDetails");
		result.of(OrdersController.class).list();
	}
	
	@Transactional
	public void save(Complain complain){
		
		complain.setComplainDate(new Date());
		validateComplain(complain);

		if(validator.hasErrors()){
			result.include("complain", complain);
			validator.onErrorRedirectTo(this).complain(complain.getOrder().getId());
		}
		
		if (complain.getId() == null){
			entityManager.persist(complain);
		}else{
			entityManager.merge(complain);
		}
		result.redirectTo(TimelineController.class).timeline();
	}
	
	@Transactional
	public void solve(Complain complain){
		
		complain.setSolvedDate(new Date());
		validateComplain(complain);

		if(validator.hasErrors()){
			result.include("complain", complain);
			validator.onErrorRedirectTo(this).complain(complain.getOrder().getId());
		}
		
		if (complain.getId() == null){
			entityManager.persist(complain);
		}else{
			entityManager.merge(complain);
		}
		result.redirectTo(TimelineController.class).timeline();
	}

	@Get
	@Path("/complains/{id}")
	public void edit(Long id) {
		Complain complain = entityManager.find(Complain.class, id);
		result.include("complain", complain);
		result.include("order", complain.getOrder());
		result.include("controller", "revenuePayments");
		result.of(this).complain(null);
	}
	
	@Path("/complains/delete/{id}")
	public void deleteItem(Long id){
		Complain complain = entityManager.find(Complain.class, id);
		Long orderId = complain.getOrder().getId();
		entityManager.remove(complain);
		result.redirectTo(this).complain(orderId);
	}

	private void validateComplain(Complain complain) {
		validator.addIf( complain.getDescription() == null || complain.getDescription().trim().equals(""),new I18nMessage("rev","complain.without.description"));
	}

}
