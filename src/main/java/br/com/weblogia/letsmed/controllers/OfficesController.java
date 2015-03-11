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
import br.com.weblogia.letsmed.domain.Office;

@Controller
public class OfficesController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void office(){
		result.include("controller", "offices");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/offices")
	public List<Office> list(){
		result.include("controller", "offices");
		return (List<Office>) entityManager.createQuery(" from Office o order by o.officeName ").getResultList();
	}
	
	@Transactional
	public void save(Office office){
		
		validator.addIf(( office.getOfficeName() == null || office.getOfficeName().trim().equals("")),new I18nMessage("off","office.without.name"));
		validator.addIf(( office.getTaxNumber() == null || office.getTaxNumber().trim().equals("")),new I18nMessage("off","office.without.taxnumber"));
		validator.addIf(( office.getAddress() == null || office.getAddress().trim().equals("")),new I18nMessage("off","office.without.address"));

		if(validator.hasErrors()){
			result.include("office", office);
			validator.onErrorUsePageOf(this).office();
		}
		
		if (office.getId() == null){
			entityManager.persist(office);
		}else{
			entityManager.merge(office);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/offices/{id}")
	public void edit(Long id) {
		Office office = entityManager.find(Office.class, id);
		result.include("controller", "offices");
		result.include("office", office);
		result.of(this).office();
	}

}
