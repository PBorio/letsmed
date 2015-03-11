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
import br.com.weblogia.letsmed.domain.Order;

@Controller
public class ShipmentController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
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

}
