package br.com.weblogia.letsmed.domain.service;

import java.util.Date;

import javax.persistence.EntityManager;

import br.com.weblogia.letsmed.domain.BuyOrderItem;
import br.com.weblogia.letsmed.domain.BuyOrder;
import br.com.weblogia.letsmed.domain.Expense;
import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.OrderItem;
import br.com.weblogia.letsmed.domain.Revenue;

public class OrderService {

	private EntityManager entityManager;

	public OrderService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void confirm(Order order) {
		order.setConfirmationDate(new Date());
		entityManager.merge(order);
		
		if (order.getCustomer().getPartnerExpenseAccount() != null){
			Expense expense = new Expense();
			expense.setExpenseDate(new Date());
			expense.setExpenseAccount(order.getCustomer().getPartnerExpenseAccount());
			expense.setExpenseCategory(order.getCustomer().getPartnerExpenseCategory());
			expense.setOffice(order.getCustomer().getOffice());
			expense.setOrder(order);
			expense.setValue(order.getCustomer().getPartnerComissionTo(order.getTotalValue()));
			expense.setAdditionalInfo("Expense from Order N. "+String.valueOf(order.getId()));
			entityManager.persist(expense);
		}
		
		Revenue revenue = new Revenue();
		revenue.setRevenueDate(new Date());
		revenue.setRevenueAccount(order.getCustomer().getRevenueAccount());
		revenue.setOffice(order.getCustomer().getOffice());
		revenue.setOrder(order);
		revenue.setValue(order.getCustomer().getComissionTo(order.getTotalValue()));
		revenue.setAdditionalInfo("Revenue from Order N. "+String.valueOf(order.getId()));
		entityManager.persist(revenue);
		
		BuyOrder buyOrder = new BuyOrder();
		buyOrder.setOrderDate(new Date());
		buyOrder.setOrder(order);
		buyOrder.setSupplier(order.getSupplier());
		entityManager.persist(buyOrder);
		
		for (OrderItem item : order.getItens()){
			BuyOrderItem buyOrderItem = new BuyOrderItem();
			buyOrderItem.setBuyOrder(buyOrder);
			buyOrderItem.setQuantity(item.getQuantity());
			buyOrderItem.setUnitPrice(item.getUnitPrice());
			buyOrderItem.setProduct(item.getProduct());
			entityManager.persist(buyOrderItem);
		}
	}

}
