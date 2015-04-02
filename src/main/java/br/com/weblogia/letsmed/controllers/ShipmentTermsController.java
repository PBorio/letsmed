package br.com.weblogia.letsmed.controllers;

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
import br.com.weblogia.letsmed.domain.ShipmentTerm;

@Controller
public class ShipmentTermsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void term(){
		result.include("controller", "shipmentTerms");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/shipmentTerms")
	public List<ShipmentTerm> list(){
		result.include("controller", "shipmentTerms");
		return (List<ShipmentTerm>) entityManager.createQuery(" from ShipmentTerm s order by s.description ").getResultList();
	}
	
	@Transactional
	public void save(ShipmentTerm shipmentTerm){
		
		validator.addIf(( shipmentTerm.getDescription() == null || shipmentTerm.getDescription().trim().equals("")),new I18nMessage("rev","shipment.without.description"));

		if(validator.hasErrors()){
			result.include("shipmentTerm", shipmentTerm);
			validator.onErrorUsePageOf(this).term();
		}
		
		if (shipmentTerm.getId() == null){
			entityManager.persist(shipmentTerm);
		}else{
			entityManager.merge(shipmentTerm);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/shipmentTerms/{id}")
	public void edit(Long id) {
		ShipmentTerm shipmentTerm = entityManager.find(ShipmentTerm.class, id);
		result.include("controller", "shipmentTerms");
		result.include("shipmentTerm", shipmentTerm);
		result.of(this).term();
	}

}
