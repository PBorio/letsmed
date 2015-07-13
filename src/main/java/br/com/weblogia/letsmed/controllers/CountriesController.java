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
import br.com.weblogia.letsmed.domain.Country;

@Controller
public class CountriesController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void country(){
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/countries")
	public List<Country> list(){
		return (List<Country>) entityManager.createQuery(" from Country c order by c.name ").getResultList();
	}
	
	@Transactional
	public void save(Country country){
		
		validator.addIf(( country.getName() == null || country.getName().trim().equals("")),new I18nMessage("off","country.without.name"));

		if(validator.hasErrors()){
			result.include("country", country);
			validator.onErrorUsePageOf(this).country();
		}
		
		if (country.getId() == null){
			entityManager.persist(country);
		}else{
			entityManager.merge(country);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/countries/{id}")
	public void edit(Long id) {
		Country country = entityManager.find(Country.class, id);
		result.include("country", country);
		result.of(this).country();
	}
	

}
