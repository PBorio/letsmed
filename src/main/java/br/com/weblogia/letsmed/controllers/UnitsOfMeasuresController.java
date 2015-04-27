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
import br.com.weblogia.letsmed.domain.UnitOfMeasure;

@Controller
public class UnitsOfMeasuresController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void unit(){
		result.include("controller", "unitsOfMeasures");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/unitsOfMeasures")
	public List<UnitOfMeasure> list(){
		result.include("controller", "unitsOfMeasures");
		return (List<UnitOfMeasure>) entityManager.createQuery(" from UnitOfMeasure uom order by uom.description ").getResultList();
	}
	
	@Transactional
	public void save(UnitOfMeasure unitOfMeasure){
		
		validator.addIf(( unitOfMeasure.getDescription() == null || unitOfMeasure.getDescription().trim().equals("")),new I18nMessage("rev","unit.without.description"));

		if(validator.hasErrors()){
			result.include("unitOfMeasure", unitOfMeasure);
			validator.onErrorUsePageOf(this).unit();
		}
		
		if (unitOfMeasure.getId() == null){
			entityManager.persist(unitOfMeasure);
		}else{
			entityManager.merge(unitOfMeasure);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/unitsOfMeasures/{id}")
	public void edit(Long id) {
		UnitOfMeasure unitOfMeasure = entityManager.find(UnitOfMeasure.class, id);
		result.include("controller", "unitOfMeasure");
		result.include("unitOfMeasure", unitOfMeasure);
		result.of(this).unit();
	}

}
