package br.com.weblogia.letsmed.controllers;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

@Controller
public class IndexController {
	
	@Inject
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Get
	@Path("/")
	public void index(){
		result.redirectTo(TimelineController.class).timeline();
	}

}
