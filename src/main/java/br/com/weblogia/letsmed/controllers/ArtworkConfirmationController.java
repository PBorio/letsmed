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
public class ArtworkConfirmationController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Get
	@Path("/artworkConfirmation/confirm/{id}")
	@Transactional
	public void confirm(Long id) {
		Order order = entityManager.find(Order.class, id);
		order.setArtworkConfirmationDate(new Date());
		entityManager.merge(order);
		result.include("controller", "proformaConfirmation");
		result.redirectTo(TimelineController.class).timeline();
	}


}
