package br.com.weblogia.letsmed.controllers;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.controllers.helpers.accountDetails.AccountDetailView;
import br.com.weblogia.letsmed.controllers.helpers.accountDetails.DespesaPagamentoView;
import br.com.weblogia.letsmed.domain.ExpenseAccount;
import br.com.weblogia.letsmed.repositories.AccountDetailRepository;

@Controller
public class AccountDetailsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@Path("/accountDetails")
	public void list(){
		DateTime dtEnd = new DateTime(new Date().getTime());
		DateTime dtBegin = dtEnd.dayOfMonth().withMinimumValue();
		result.include("begin", dtBegin.toDate());
		result.include("end", dtEnd.toDate());
		loadList();
	}

	@Post
	public void search(Integer idAccount, Date begin, Date end){
		
		
		validator.addIf(( idAccount == null || idAccount.intValue() == -1 ),new I18nMessage("con","detail.without.account"));
		validator.addIf(( begin == null ),new I18nMessage("con","detail.without.begindate"));
		validator.addIf(( end == null  ),new I18nMessage("con","detail.without.enddate"));

		if(validator.hasErrors()){
			loadList();
			result.include("begin", begin);
			result.include("end", end);
			validator.onErrorUsePageOf(this).list();
		}
		
		DateTime dtBegin = new DateTime(begin).hourOfDay().withMinimumValue();
		begin = dtBegin.toDate();
		DateTime dtEnd = new DateTime(end).hourOfDay().withMaximumValue();
		end = dtEnd.toDate();
		
		AccountDetailRepository repository = new AccountDetailRepository(entityManager);
		AccountDetailView view = repository.createAccountDetail(idAccount, begin, end); 
		DespesaPagamentoView despesaPagamentoView = repository.createDespesaPagamento(idAccount, begin, end);
		
		loadList();
		result.include("despesaPagamentoView", despesaPagamentoView);
		result.include("begin", begin);
		result.include("end",end);
		result.include("idAccount", idAccount);
		result.include("accountDetailView", view);
		result.of(this).list();
	}

	@SuppressWarnings("unchecked")
	private void loadList() {
		List<ExpenseAccount> expenseAccountList = (List<ExpenseAccount>) entityManager.createQuery(" from ExpenseAccount ea order by ea.description ").getResultList();
		result.include("expenseAccountList", expenseAccountList);
	}
	

}
