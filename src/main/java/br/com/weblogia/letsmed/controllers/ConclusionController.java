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
public class ConclusionController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject
	private Validator validator;
	
	@Get
	@Path("/conclusion/confirm/{id}")
	@Transactional
	public void confirm(Long id) {
		Order order = entityManager.find(Order.class, id);
		validator.addIf( !order.isComissionPaid() ,new I18nMessage("ord","comission.not.paid"));
		
		if (validator.hasErrors()){
			result.include("order", order);
			validator.onErrorUsePageOf(OrdersController.class).order();
		}
		order.setConclusionDate(new Date());
		entityManager.merge(order);
		
		result.include("controller", "sendDocumentCopy");
		result.redirectTo(TimelineController.class).timeline();
	}

}
