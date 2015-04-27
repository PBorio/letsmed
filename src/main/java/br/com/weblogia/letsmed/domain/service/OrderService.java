package br.com.weblogia.letsmed.domain.service;

import java.util.Date;

import javax.persistence.EntityManager;

import br.com.weblogia.letsmed.domain.Expense;
import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.Revenue;

public class OrderService {

	private EntityManager entityManager;

	public OrderService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void confirm(Order order) {
		order.setProformaConfirmationDate(new Date());
		entityManager.merge(order);
		
		if (order.getCustomer().getPartnerExpenseAccount() != null){
			Expense expense = new Expense();
			expense.setExpenseDate(new Date());
			expense.setExpenseAccount(order.getCustomer().getPartnerExpenseAccount());
			expense.setExpenseCategory(order.getCustomer().getPartnerExpenseCategory());
			expense.setOffice(order.getOffice());
			expense.setOrder(order);
			expense.setValue(order.getPartnerComissionValue());
			expense.setAdditionalInfo("Expense from Order N. "+String.valueOf(order.getId()));
			entityManager.persist(expense);
		}
		
		Revenue revenue = new Revenue();
		revenue.setRevenueDate(new Date());
		revenue.setRevenueAccount(order.getCustomer().getRevenueAccount());
		revenue.setOffice(order.getOffice());
		revenue.setOrder(order);
		revenue.setValue(order.getRevenueValue());
		revenue.setAdditionalInfo("Revenue from Order N. "+String.valueOf(order.getId()));
		entityManager.persist(revenue);
		
	}

}
