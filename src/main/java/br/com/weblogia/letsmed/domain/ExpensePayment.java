package br.com.weblogia.letsmed.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="expense_payments")
public class ExpensePayment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date paymentDate;
	
	@ManyToOne
	private Expense expense;
	
	@ManyToOne
	private PaymentTerm paymentTerm;
	
	private Double value;

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

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
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
	

}
