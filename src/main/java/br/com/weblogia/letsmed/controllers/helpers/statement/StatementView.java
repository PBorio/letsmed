package br.com.weblogia.letsmed.controllers.helpers.statement;

import java.util.ArrayList;
import java.util.List;

public class StatementView {

	private List<StatementViewLine> lines = new ArrayList<StatementViewLine>();
	private Double initialBalance = 0.0;
	
	public void add(StatementViewLine line) {
		this.lines.add(line);
	}
	public Double getBalance() {
		Double result = this.initialBalance ;
		for (StatementViewLine line : this.lines){
			result += line.getValue();
		}
		return result;
	}
	public void setInitialBalance(Double balance) {
		this.initialBalance = balance;
	}
	public Double getInitialBalance() {
		return initialBalance;
	}
	public List<StatementViewLine> getLines() {
		return lines;
	}

}
