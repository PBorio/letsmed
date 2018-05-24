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
import br.com.weblogia.letsmed.domain.ForwardDetail;
import br.com.weblogia.letsmed.domain.Order;

@Controller
public class ForwardDetailsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@Get
	@Path("/forwardDetails/order/{id}")
	public void forwardDetail(Long id){
		Order order = entityManager.find(Order.class, id);
		ForwardDetail forwardDetail = new ForwardDetail();
		forwardDetail.setOrder(order);;
		result.include(order);
		result.include("forwardDetail", forwardDetail);
		result.include("controller", "forwardDetails");
	}

	@Transactional
	public void save(ForwardDetail forwardDetail){
		
		forwardDetail.setForwardDetailDate(new Date());
		validateForwardDetail(forwardDetail);

		if(validator.hasErrors()){
			result.include("forwardDetail", forwardDetail);
			validator.onErrorRedirectTo(this).forwardDetail(forwardDetail.getOrder().getId());
		}
		
		if (forwardDetail.getId() == null){
			entityManager.persist(forwardDetail);
		}else{
			entityManager.merge(forwardDetail);
		}
		result.redirectTo(TimelineController.class).timeline();
	}

	@Get
	@Path("/forwardDetails/{id}")
	public void edit(Long id) {
		ForwardDetail forwardDetail = entityManager.find(ForwardDetail.class, id);
		result.include("forwardDetail", forwardDetail);
		result.include("order", forwardDetail.getOrder());
		result.include("controller", "revenuePayments");
		result.of(this).forwardDetail(null);
	}
	
	@Path("/forwardDetails/delete/{id}")
	public void deleteItem(Long id){
		if (id == null)
			return;
		ForwardDetail forwardDetail = entityManager.find(ForwardDetail.class, id);
		Long orderId = forwardDetail.getOrder().getId();
		entityManager.remove(forwardDetail);
		result.redirectTo(this).forwardDetail(orderId);
	}

	private void validateForwardDetail(ForwardDetail forwardDetail) {
		validator.addIf( forwardDetail.getDescription() == null || forwardDetail.getDescription().trim().equals(""),new I18nMessage("rev","forwarddetail.without.description"));
	}

}
