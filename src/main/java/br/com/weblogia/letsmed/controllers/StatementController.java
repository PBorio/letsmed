package br.com.weblogia.letsmed.controllers;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.controllers.helpers.statement.StatementView;
import br.com.weblogia.letsmed.domain.BankAccount;
import br.com.weblogia.letsmed.repositories.StatementRepository;

@Controller
public class StatementController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/statement")
	public void statement(){
		DateTime dtEnd = new DateTime(new Date().getTime());
		DateTime dtBegin = dtEnd.dayOfMonth().withMinimumValue();
		result.include("begin", dtBegin.toDate());
		result.include("end", dtEnd.toDate());
		List<BankAccount> bankAccountList = (List<BankAccount>) entityManager.createQuery(" from BankAccount b order by b.description ").getResultList();
		result.include("bankAccountList", bankAccountList);
	}
	
	@SuppressWarnings("unchecked")
	@Post
	public void search(Long idAccount, Date begin, Date end){
		
		
		validator.addIf(( idAccount == null || idAccount.intValue() == -1 ),new I18nMessage("con","detail.without.account"));
		validator.addIf(( begin == null ),new I18nMessage("con","detail.without.begindate"));
		validator.addIf(( end == null  ),new I18nMessage("con","detail.without.enddate"));

		if(validator.hasErrors()){
			List<BankAccount> bankAccountList = (List<BankAccount>) entityManager.createQuery(" from BankAccount b order by b.description ").getResultList();
			result.include("contaList", bankAccountList);
			result.include("begin", begin);
			result.include("end", end);
			validator.onErrorUsePageOf(this).statement();
		}
		
		BankAccount bank = entityManager.find(BankAccount.class, idAccount);
		StatementRepository statementRepository = new StatementRepository(entityManager, bank);
		StatementView statemenView = statementRepository.createBankStatement(idAccount, begin, end);
		
		List<BankAccount> bankAccountList = (List<BankAccount>) entityManager.createQuery(" from BankAccount b order by b.description ").getResultList();
		result.include("bankAccountList", bankAccountList);
		result.include("idAccount", idAccount);
		result.include("begin", begin);
		result.include("end", end);
		result.include("statemenView", statemenView);
		result.of(this).statement();
	}

}
