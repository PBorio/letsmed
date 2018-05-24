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
import br.com.weblogia.letsmed.domain.OriginalDocumentShipment;

@Controller
public class OriginalDocumentController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@Get
	@Path("/originalDocument/order/{id}")
	public void originalDocument(Long id){
		Order order = entityManager.find(Order.class, id);
		OriginalDocumentShipment originalDocumentShipment = new OriginalDocumentShipment();
		originalDocumentShipment.setShipDate(new Date());
		originalDocumentShipment.setOrder(order);
		result.include(order);
		result.include("originalDocumentShipment", originalDocumentShipment);
		result.include("controller", "originalDocuments");
	}
	
	@Transactional
	public void save(OriginalDocumentShipment originalDocumentShipment){
		
		validateOriginalDocument(originalDocumentShipment);

		if(validator.hasErrors()){
			result.include("originalDocumentShipment", originalDocumentShipment);
			validator.onErrorUsePageOf(this).originalDocument(null);
		}
		
		if (originalDocumentShipment.getId() == null){
			entityManager.persist(originalDocumentShipment);
		}else{
			entityManager.merge(originalDocumentShipment);
		}
		result.redirectTo(TimelineController.class).timeline();
	}
	
	private void validateOriginalDocument(OriginalDocumentShipment originalDocumentShipment) {
		validator.addIf( originalDocumentShipment.getShipDate() == null,new I18nMessage("doc","docshipment.without.date"));
	}


}
