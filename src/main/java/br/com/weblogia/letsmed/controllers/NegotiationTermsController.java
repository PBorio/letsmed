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
import br.com.weblogia.letsmed.domain.NegotiationTerm;

@Controller
public class NegotiationTermsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void term(){
		result.include("controller", "negotiationTerms");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/negotiationTerms")
	public List<NegotiationTerm> list(){
		result.include("controller", "negotiationTerms");
		return (List<NegotiationTerm>) entityManager.createQuery(" from NegotiationTerm ea order by ea.description ").getResultList();
	}
	
	@Transactional
	public void save(NegotiationTerm negotiationTerm){
		
		validator.addIf(( negotiationTerm.getDescription() == null || negotiationTerm.getDescription().trim().equals("")),new I18nMessage("rev","term.without.description"));

		if(validator.hasErrors()){
			result.include("negotiationTerm", negotiationTerm);
			validator.onErrorUsePageOf(this).term();
		}
		
		if (negotiationTerm.getId() == null){
			entityManager.persist(negotiationTerm);
		}else{
			entityManager.merge(negotiationTerm);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/negotiationTerms/{id}")
	public void edit(Long id) {
		NegotiationTerm negotiationTerm = entityManager.find(NegotiationTerm.class, id);
		result.include("controller", "negotiationTerms");
		result.include("negotiationTerm", negotiationTerm);
		result.of(this).term();
	}

}
