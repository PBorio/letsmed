package br.com.weblogia.letsmed.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="buy_orders")
public class BuyOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date orderDate;
	
	private String orderNumber;
	
	private String additionalInfo;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private Supplier supplier;
	
	@ManyToOne
	private PaymentTerm paymentTerm;
	
	@OneToMany(mappedBy="buyOrder")
	private List<BuyOrderItem> itens = new ArrayList<BuyOrderItem>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public PaymentTerm getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(PaymentTerm paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public List<BuyOrderItem> getItens() {
		return itens;
	}

	public void setItens(List<BuyOrderItem> itens) {
		this.itens = itens;
	}
}
