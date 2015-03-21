package br.com.weblogia.letsmed.controllers.helpers.accountDetails;

import java.util.ArrayList;
import java.util.List;

import br.com.weblogia.letsmed.domain.helpers.Arredondamento;

public class AccountDetailView {
	
	List<AccountDetailLine> lines = new ArrayList<AccountDetailLine>();
	private Double initialBalance = 0.0;

	public void add(AccountDetailLine line) {
		this.lines.add(line);
	}

	public List<AccountDetailLine> getLines() {
		return lines;
	}
	
	public Double getBalance(){
		Double result = initialBalance;
		for (AccountDetailLine line : this.lines){
			result += line.getValue();
		}
		return new Arredondamento().arredondar(result);
	}

	public void setInitialBalance(Double balance) {
		this.initialBalance = balance;
	}

	public Double getInitialBalance() {
		return initialBalance;
	}

}
