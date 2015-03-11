package br.com.weblogia.letsmed.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.weblogia.letsmed.domain.helpers.Arredondamento;

@Entity
@Table(name="partners")
public class Partner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String partnerName;
	
	private String address;
	
	private String phoneNumber;
	
	private String phoneNumber2;
	
	private String email;
	
	private String site;
	
	private String contact;
	
	private Double percentual;
	
	@ManyToOne
	private ExpenseAccount expenseAccount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public ExpenseAccount getExpenseAccount() {
		return expenseAccount;
	}

	public void setExpenseAccount(ExpenseAccount expenseAccount) {
		this.expenseAccount = expenseAccount;
	}

	public Double getPartnerCommissionTo(Double comission) {
		Double result = comission * (this.percentual/100);
		return new Arredondamento().arredondar(result);
	}

}
