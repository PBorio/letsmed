package br.com.weblogia.letsmed.domain.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;

import br.com.weblogia.letsmed.domain.Customer;
import br.com.weblogia.letsmed.domain.Expense;
import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.OrderHistory;
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
			
			entityManager.persist(order);
			saveItens(order);
			createHistoricForInsert(order);
		}else{
			
			createHistoric(order);
			entityManager.merge(order);
		}
	}
	
	private void createHistoric(Order order) {
		Order old = entityManager.find(Order.class, order.getId());
		
		if (order.getDeliveryDate() != null && old.getDeliveryDate() != null){
			if (!order.getDeliveryDate().equals(old.getDeliveryDate())){
				OrderHistory oh = new OrderHistory();
				oh.setDeliveryDate(order.getDeliveryDate());
				oh.setChangeDate(new Date());
				oh.setOrder(order);
				entityManager.persist(oh);
			}
		}
		
		if (order.getDeliveryDate() == null && old.getDeliveryDate() != null){
			OrderHistory oh = new OrderHistory();
			oh.setDeliveryDate(order.getDeliveryDate());
			oh.setChangeDate(new Date());
			oh.setOrder(order);
			entityManager.persist(oh);
		}
	}
	
	private void createHistoricForInsert(Order order) {
		
			OrderHistory oh = new OrderHistory();
			oh.setDeliveryDate(order.getDeliveryDate());
			oh.setChangeDate(new Date());
			oh.setOrder(order);
			entityManager.persist(oh);
	}

	@SuppressWarnings("unused")
	private void loadOrderCode(Order order) {
		
		if (order.getOrderNumber() == null || "".equals(order.getOrderNumber().trim()))
			throw new RuntimeException("Order number not found. Please inform the order number.");
		
		String[] codes = order.getOrderNumber().split("-");
		if (codes[0] == null){
			Customer customer = entityManager.find(Customer.class, order.getCustomer().getId());
			throw new RuntimeException("Customer Code not found. Please inform the "+customer.getName()+" code.");
		}

		int thiscode = Integer.parseInt(codes[1]);
		
		int next = getNextSequence();
		int year = new DateTime().getYearOfCentury();
		int nextWithYear = Integer.parseInt(String.valueOf(year)+String.valueOf(next));
		
		if (thiscode == nextWithYear){
			next++;
			Query q = entityManager.createNativeQuery(" update sequences set value = :next where name = 'order_number' ");
			q.setParameter("next", next);
			q.executeUpdate();
		}
		
	}

	private String composeNextOrderNumber(Customer customer,	int next) {
		int year = new DateTime().getYearOfCentury();
		String code = customer.getCode()+"-"+String.valueOf(year)+StringUtils.fillCharacts(String.valueOf(next), 3, '0');
		return code;
	}

	private int getNextSequence() {
		int next = (int) entityManager.createNativeQuery(" select value from sequences where name = 'order_number'").getSingleResult();
		return next;
	}
	
	public String getNextOrderNumber(Customer customer){
		int next = getNextSequence();
		return composeNextOrderNumber(customer, next);
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
