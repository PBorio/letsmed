package br.com.weblogia.letsmed.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.weblogia.letsmed.controllers.helpers.statement.StatementView;
import br.com.weblogia.letsmed.controllers.helpers.statement.StatementViewLine;
import br.com.weblogia.letsmed.domain.BankAccount;
import br.com.weblogia.letsmed.domain.helpers.Arredondamento;


public class StatementRepository {

	private EntityManager entityManager;
	private BankAccount bankAccount;

	public StatementRepository(EntityManager entityManager, BankAccount bankAccount) {
		this.entityManager = entityManager;
		this.bankAccount = bankAccount;
	}

	@SuppressWarnings("unchecked")
	public StatementView createBankStatement(Long idAccount, Date begin, Date end) {
		
		StringBuilder sqlBefore = createSql();
		sqlBefore.append(" and allofthem.date < :begin ");
		sqlBefore.append(" order by allofthem.date ");
		Query queryBefore = entityManager.createNativeQuery(sqlBefore.toString());
		queryBefore.setParameter("account", idAccount);
		queryBefore.setParameter("begin", begin);
		List<Object[]> resultBefore = queryBefore.getResultList();
		StatementView viewBefore = createView(resultBefore, this.bankAccount.getBalance());
		viewBefore.setInitialBalance(this.bankAccount.getBalance());
		
		StringBuilder sqlAfter = createSql();
		sqlAfter.append(" and allofthem.date between :begin and :end ");
		sqlAfter.append(" order by allofthem.date ");
		Query queryAfter = entityManager.createNativeQuery(sqlAfter.toString());
		queryAfter.setParameter("account", idAccount);
		queryAfter.setParameter("begin", begin);
		queryAfter.setParameter("end", end);
		List<Object[]> resultAfter = queryAfter.getResultList();
		StatementView view = createView(resultAfter, viewBefore.getBalance());
		view.setInitialBalance(viewBefore.getBalance());
		return view;
	}
	
	private StatementView createView(List<Object[]> queryResult, Double balance) {
		StatementView view = new StatementView();
		
		Double currentBalance = balance;
		for (Object[] os : queryResult){
			Double value = ((Number)os[2]).doubleValue();
			currentBalance += value;
			StatementViewLine line = new StatementViewLine(String.valueOf(os[0]),(Date)os[1], value, new Arredondamento().arredondar(currentBalance));
			view.add(line);
		}
		return view;
	}

	private StringBuilder createSql() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT allofthem.description, allofthem.date, allofthem.value ");
		sql.append(" FROM (");
		sql.append(" SELECT account.description as description, payment.paymentDate as date, (payment.value * -1) as value ");
		sql.append(" FROM expense_accounts account ");
		sql.append(" join expense_payments payment on payment.expenseAccount_id = account.id ");
		sql.append(" where payment.bankAccount_id = :account ");
		sql.append(" union all ");
		sql.append(" SELECT account.description as description, payment.paymentDate as date, payment.value as value ");
		sql.append("  FROM revenue_payments payment ");
		sql.append("   join revenues revenue on revenue.id = payment.revenue_id ");
		sql.append("   join revenue_accounts account on revenue.revenueAccount_id = account.id ");
		sql.append(" where payment.bankAccount_id = :account ");
		sql.append(" ) as allofthem ");
		sql.append(" where 1 = 1 ");
		return sql;
	}

}
