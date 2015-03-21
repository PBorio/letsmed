package br.com.weblogia.letsmed.controllers.helpers.accountDetails;

public class DespesaPagamentoView {

	private String account;
	private double expense;
	private double payment;

	public DespesaPagamentoView(String account, double expense, double payment) {
		this.account = account;
		this.expense = expense;
		this.payment = payment;
		
	}

	public String getAccount() {
		return account;
	}

	public double getExpense() {
		return expense;
	}

	public double getPayment() {
		return payment;
	}

}
