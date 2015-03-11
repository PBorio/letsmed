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

import org.joda.time.DateTime;

import br.com.weblogia.letsmed.domain.helpers.Arredondamento;
@Entity
@Table(name="revenues")
public class Revenue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date revenueDate;
	
	@ManyToOne
	private Office office;
	
	@ManyToOne
	private RevenueAccount revenueAccount;
	
	@ManyToOne
	private Order order;
	
	private Double value;
	
	@OneToMany(mappedBy="revenue")
	private List<RevenuePayment> payments = new ArrayList<RevenuePayment>();
	
	private String additionalInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRevenueDate() {
		return revenueDate;
	}

	public void setRevenueDate(Date revenueDate) {
		this.revenueDate = revenueDate;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public RevenueAccount getRevenueAccount() {
		return revenueAccount;
	}

	public void setRevenueAccount(RevenueAccount revenueAccount) {
		this.revenueAccount = revenueAccount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public List<RevenuePayment> getPayments() {
		return this.payments;
	}
	
	public Double getBalance(){
		return new Arredondamento().arredondar(this.value - this.getPaidValue());
	}

	public Double getPaidValue() {
		Double result = 0.0;
		
		for (RevenuePayment payment : this.payments){
			result += payment.getValue();
		}
		
		return new Arredondamento().arredondar(result);
	}

	public Date getLastPaymentDate() {
		if (this.payments == null || this.payments.size() == 0)
			return null;
		
		DateTime ultimaData = null;
		
		for (RevenuePayment rp : this.payments){
			if (ultimaData == null || ultimaData.isBefore(new DateTime(rp.getPaymentDate().getTime())))
				ultimaData = new DateTime(rp.getPaymentDate().getTime());
		}
		
		return ultimaData.toDate();
	}

}
