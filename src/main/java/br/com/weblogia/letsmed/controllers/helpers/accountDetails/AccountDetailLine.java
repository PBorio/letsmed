package br.com.weblogia.letsmed.controllers.helpers.accountDetails;

import java.util.Date;

public class AccountDetailLine {

	private String description;
	private String info;
	private Date date;
	private Double value;

	public AccountDetailLine(String description, String additionalInfo, Date date, Double value) {
		this.description = description;
		this.info = additionalInfo;
		this.date = date;
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public String getInfo() {
		return info;
	}

	public Date getDate() {
		return date;
	}

	public Double getValue() {
		return value;
	}

}
