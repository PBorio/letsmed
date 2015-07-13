package br.com.weblogia.letsmed.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.weblogia.letsmed.domain.helpers.Arredondamento;

@Entity
@Table(name="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String address;
	
	private String phoneNumber;
	
	private String phoneNumber2;
	
	private String email;
	
	private String site;
	
	private String contact;
	
	private String countryDesc;
	
	@ManyToOne
	private Country country;
	
	private String code;
	
	@ManyToOne
	private RevenueAccount revenueAccount;
	
	@ManyToOne
	private Partner partner;
	
	
	
	public ExpenseAccount getPartnerExpenseAccount() {
		
		if (this.partner == null)
			return null;
		
		if (this.partner.getExpenseAccount() == null)
			return null;
		
		return this.partner.getExpenseAccount();
	}
	
	public ExpenseCategory getPartnerExpenseCategory() {

		if (this.partner == null)
			return null;
		
		if (this.partner.getExpenseCategory() == null)
			return null;
		
		return this.partner.getExpenseCategory();
	}

	public Double getPartnerComissionTo(Double value) {
		if (partner == null)
			return 0.0;
		Double partnerComission = partner.getPartnerCommissionTo(value);
		return new Arredondamento().arredondar(partnerComission);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public RevenueAccount getRevenueAccount() {
		return revenueAccount;
	}

	public void setRevenueAccount(RevenueAccount revenueAccount) {
		this.revenueAccount = revenueAccount;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public String getCountryDesc() {
		return countryDesc;
	}

	public void setCountryDesc(String countryDesc) {
		this.countryDesc = countryDesc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		if (id == null) return super.hashCode();
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (id == null) 
				return false;
		if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
