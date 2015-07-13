package br.com.weblogia.letsmed.controllers.statistics.customer;

import java.util.List;

import br.com.weblogia.letsmed.domain.helpers.Arredondamento;

public class CustomerView {

	private List<CustomerLineDemo> customerLines;
	
	public Double getTotalJanuary(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getJanuaryValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalFebrary(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getFebruaryValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalMarch(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getMarchValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalApril(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getAprilValue();
		}
		return new Arredondamento().arredondar(result);
	}

	public Double getTotalMay(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getMayValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalJune(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getJuneValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalJuly(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getJulyValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalAugust(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getAugustValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalSeptember(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getSeptemberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalOctober(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getOctoberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalNovember(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getNovemberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalDecember(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getDecemberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotal(){
		Double result = 0.0;
		for (CustomerLineDemo despesa : this.customerLines){
			result += despesa.getTotalValue();
		}
		return new Arredondamento().arredondar(result);
	}

	public List<CustomerLineDemo> getCustomerLines() {
		return customerLines;
	}

	public void setCustomerLines(List<CustomerLineDemo> customerLines) {
		this.customerLines = customerLines;
	}

}
