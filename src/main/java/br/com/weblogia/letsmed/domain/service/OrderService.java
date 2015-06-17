package br.com.weblogia.letsmed.domain.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;

import br.com.weblogia.letsmed.domain.Customer;
import br.com.weblogia.letsmed.domain.Expense;
import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.OrderItem;
import br.com.weblogia.letsmed.domain.Revenue;
import br.com.weblogia.letsmed.domain.helpers.StringUtils;

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
			expense.setOrder(order);
			expense.setValue(order.getPartnerComissionValue());
			expense.setAdditionalInfo("Expense from Order N. "+String.valueOf(order.getId()));
			entityManager.persist(expense);
		}
		
		Revenue revenue = new Revenue();
		revenue.setRevenueDate(new Date());
		revenue.setRevenueAccount(order.getCustomer().getRevenueAccount());
		revenue.setOrder(order);
		revenue.setValue(order.getRevenueValue());
		revenue.setAdditionalInfo("Revenue from Order N. "+String.valueOf(order.getId()));
		entityManager.persist(revenue);
		
	}
	
	public void save(Order order){
		if (order.getId() == null){
			loadOrderCode(order);
			entityManager.persist(order);
			saveItens(order);
		}else{
			entityManager.merge(order);
		}
	}
	
	private void loadOrderCode(Order order) {
		Customer customer = entityManager.find(Customer.class, order.getCustomer().getId());
		int year = new DateTime(order.getOrderDate().getTime()).getYearOfCentury();
		int next = (int) entityManager.createNativeQuery(" select value from sequences where name = 'order_number'").getSingleResult();
		
		if (customer.getCode() == null)
			throw new RuntimeException("Customer Code not found. Please inform the "+customer.getName()+" code.");
		
		String code = customer.getCode()+"-"+String.valueOf(year)+StringUtils.fillCharacts(String.valueOf(next), 3, '0');
		order.setOrderNumber(code);
		
		next++;
		Query q = entityManager.createNativeQuery(" update sequences set value = :next where name = 'order_number' ");
		q.setParameter("next", next);
		q.executeUpdate();
		
	}

	private void saveItens(Order order) {
		for (OrderItem item : order.getItens()){
			if (item.getCommision() == null)
				item.setCommision(0.0);
			
			if (item.getId() == null){
				item.setOrder(order);
				entityManager.persist(item);
			}else{
				entityManager.merge(item);
			}
		}
	}

}
