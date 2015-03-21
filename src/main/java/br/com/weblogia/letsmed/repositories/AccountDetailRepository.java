package br.com.weblogia.letsmed.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.weblogia.letsmed.controllers.helpers.accountDetails.AccountDetailLine;
import br.com.weblogia.letsmed.controllers.helpers.accountDetails.AccountDetailView;
import br.com.weblogia.letsmed.controllers.helpers.accountDetails.DespesaPagamentoView;

public class AccountDetailRepository {

	private EntityManager entityManager;

	public AccountDetailRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public AccountDetailView createAccountDetail(Integer idAccount, Date begin,	Date end) {
		
		StringBuilder sqlBefore = createSql();
		sqlBefore.append(" and allofthem.date < :begin ");
		sqlBefore.append(" order by allofthem.date ");
		Query queryBefore = entityManager.createNativeQuery(sqlBefore.toString());
		queryBefore.setParameter("account", idAccount);
		queryBefore.setParameter("begin", begin);
		List<Object[]> resultBefore = queryBefore.getResultList();
		AccountDetailView viewBefore = createView(resultBefore);
		
		StringBuilder sqlAfter = createSql();
		sqlAfter.append(" and allofthem.date between :begin and :end ");
		sqlAfter.append(" order by allofthem.date ");
		Query queryAfter = entityManager.createNativeQuery(sqlAfter.toString());
		queryAfter.setParameter("account", idAccount);
		queryAfter.setParameter("begin", begin);
		queryAfter.setParameter("end", end);
		List<Object[]> resultAfter = queryAfter.getResultList();
		AccountDetailView view = createView(resultAfter);
		view.setInitialBalance(viewBefore.getBalance());
		return view;
	}
	
	private AccountDetailView createView(List<Object[]> queryResult) {
		
		AccountDetailView view = new AccountDetailView();
		
		for (Object[] os : queryResult){
			AccountDetailLine line = new AccountDetailLine(String.valueOf(os[0]),String.valueOf(os[1]),(Date)os[2],((Number)os[3]).doubleValue());
			view.add(line);
		}
		return view;
	}

	private StringBuilder createSql() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT allofthem.description, allofthem.info, allofthem.date, allofthem.value ");
		sql.append(" FROM (");
		sql.append(" SELECT account.description as description, expense.additionalInfo as info, expense.expenseDate as date, expense.value as value ");
		sql.append(" FROM expenses expense ");
		sql.append(" join expense_accounts account on expense.expenseAccount_id = account.id ");
		sql.append(" where account.id = :account ");
		sql.append(" union all ");
		sql.append(" SELECT account.description as description, payment.additionalInfo as info, payment.paymentDate as date, (payment.value * -1) as value ");
		sql.append("  FROM expense_payments payment ");
		sql.append("   join expense_accounts account on payment.expenseAccount_id = account.id ");
		sql.append(" where account.id = :account ");
		sql.append(" ) as allofthem ");
		sql.append(" where 1 = 1 ");
		return sql;
	}

	public DespesaPagamentoView createDespesaPagamento(Integer idAccount,
			Date begin, Date end) {
		
		StringBuilder sqlBefore = new StringBuilder();
		sqlBefore.append(" SELECT account.description as description, ");
		sqlBefore.append(" ( select COALESCE(sum(e.value),0) from expenses e where e.expenseAccount_id = account.id and e.expenseDate between :begin and :end), ");
		sqlBefore.append(" ( select COALESCE(sum(p.value),0) from expense_payments p where p.expenseAccount_id = account.id and p.paymentDate between :begin and :end) ");
		sqlBefore.append(" from expense_accounts account");
		sqlBefore.append(" where account.id = :account ");
		sqlBefore.append(" group by account.id ");
		
		Query queryBefore = entityManager.createNativeQuery(sqlBefore.toString());
		queryBefore.setParameter("account", idAccount);
		queryBefore.setParameter("begin", begin);
		queryBefore.setParameter("end", end);
		Object[] result = (Object[]) queryBefore.getSingleResult();
		DespesaPagamentoView despesaPagamentoView = new DespesaPagamentoView(String.valueOf(result[0]),((Number)result[1]).doubleValue(),((Number)result[2]).doubleValue());
		return despesaPagamentoView;
	}

}
