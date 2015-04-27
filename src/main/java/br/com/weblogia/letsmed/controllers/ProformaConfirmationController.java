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
import br.com.weblogia.letsmed.domain.service.OrderService;

@Controller
public class ProformaConfirmationController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Get
	@Path("/proformaConfirmation/confirm/{id}")
	@Transactional
	public void confirm(Long id) {
		Order order = entityManager.find(Order.class, id);
		OrderService service = new OrderService(entityManager);
		service.confirm(order);
//		order.setProformaConfirmationDate(new Date());
//		entityManager.merge(order);
		result.redirectTo(TimelineController.class).timeline();
	}
	
	@Get
	@Path("/supplierProforma/confirm/{id}")
	@Transactional
	public void supplierConfirm(Long id) {
		Order order = entityManager.find(Order.class, id);
		order.setSupplierProformaDate(new Date());
		entityManager.merge(order);
		result.redirectTo(TimelineController.class).timeline();
	}

}
