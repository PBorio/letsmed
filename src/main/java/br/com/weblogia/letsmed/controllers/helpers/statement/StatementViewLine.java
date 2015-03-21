package br.com.weblogia.letsmed.controllers.helpers.statement;

import java.util.Date;

public class StatementViewLine {

	private String account;
	private Date date;
	private Double value;
	private Double balance;

	public StatementViewLine(String account, Date date, Double value, Double balance) {
		this.account = account;
		this.date = date;
		this.value = value;
		this.balance = balance;
	}

	public String getAccount() {
		return account;
	}

	public Date getDate() {
		return date;
	}

	public Double getValue() {
		return value;
	}

	public Double getBalance() {
		return balance;
	}

}
