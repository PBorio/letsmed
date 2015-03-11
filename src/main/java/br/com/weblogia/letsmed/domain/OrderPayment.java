package br.com.weblogia.letsmed.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_payments")
public class OrderPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date paymentDate;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private PaymentTerm paymentTerm;
	
	private Double value;
	
	public String getPaymentTermDescription(){
		if (this.paymentTerm == null)
			return "";
		
		return this.paymentTerm.getDescription();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentTerm getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(PaymentTerm paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
}
