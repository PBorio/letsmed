package br.com.weblogia.letsmed.controllers;

import java.util.Date;

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
import br.com.weblogia.letsmed.domain.Order;

@Controller
public class ShipmentController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@Get
	@Path("/shipment/order/{id}")
	public void shipment(Long id){
		Order order = entityManager.find(Order.class, id);
		result.include("order", order);
		result.include("controller", "forwardDetails");
	}
	
	@Get
	@Path("/shipment/confirm/{id}")
	@Transactional
	public void confirm(Long id) {
		Order order = entityManager.find(Order.class, id);
		order.setShipDate(new Date());
		entityManager.merge(order);
		result.include("controller", "shipment");
		result.redirectTo(TimelineController.class).timeline();
	}
	
	@Transactional
	public void save(Order order){
		
		validator.addIf( order.getShipDate() == null ,new I18nMessage("rev","order.without.shipdate"));
		
		if(validator.hasErrors()){
			result.include("order", order);
			validator.onErrorRedirectTo(this).shipment(order.getId());
		}
		
		Order savedOrder = entityManager.find(Order.class, order.getId());
		savedOrder.setShipDate(order.getShipDate());
		entityManager.merge(savedOrder);
		
		result.redirectTo(TimelineController.class).timeline();
	}

}
