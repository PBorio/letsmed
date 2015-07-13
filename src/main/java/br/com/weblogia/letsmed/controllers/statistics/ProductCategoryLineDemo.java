package br.com.weblogia.letsmed.controllers.statistics;

import org.joda.time.DateTime;

import br.com.weblogia.letsmed.domain.OrderItem;
import br.com.weblogia.letsmed.domain.ProductCategory;
import br.com.weblogia.letsmed.domain.helpers.Arredondamento;

public class ProductCategoryLineDemo {
	
	private ProductCategory category;
	
	private Double januaryValue = 0.0;
	private Double februaryValue = 0.0;
	private Double marchValue = 0.0;
	private Double aprilValue = 0.0;
	private Double mayValue = 0.0;
	private Double juneValue = 0.0; 
	private Double julyValue = 0.0;
	private Double augustValue = 0.0;
	private Double septemberValue = 0.0;
	private Double octoberValue = 0.0;
	private Double novemberValue = 0.0;
	private Double decemberValue = 0.0;
	
	
	
	
	public ProductCategoryLineDemo(ProductCategory category) {
		this.category = category;
	}
	
	public ProductCategory getCategory() {
		return category;
	}
	public Double getJanuaryValue() {
		return januaryValue;
	}
	public Double getFebruaryValue() {
		return februaryValue;
	}
	public Double getMarchValue() {
		return marchValue;
	}
	public Double getAprilValue() {
		return aprilValue;
	}
	public Double getMayValue() {
		return mayValue;
	}
	public Double getJuneValue() {
		return juneValue;
	}
	public Double getJulyValue() {
		return julyValue;
	}
	public Double getAugustValue() {
		return augustValue;
	}
	public Double getSeptemberValue() {
		return septemberValue;
	}
	public Double getOctoberValue() {
		return octoberValue;
	}
	public Double getNovemberValue() {
		return novemberValue;
	}
	public Double getDecemberValue() {
		return decemberValue;
	}

	public void sumItemValue(OrderItem item) {
		int mes  = new DateTime(item.getOrder().getOrderDate()).getMonthOfYear();
		Double valor = item.getTotalValue();
		if (mes == 1){
			this.januaryValue += valor;
		}else if (mes==2){
			this.februaryValue += valor;
		}else if (mes==3){
			this.marchValue += valor;
		}else if (mes==4){
			this.aprilValue += valor;
		}else if (mes==5){
			this.mayValue += valor;
		}else if (mes==6){
			this.juneValue += valor;
		}else if (mes==7){
			this.julyValue += valor;
		}else if (mes==8){
			this.augustValue += valor;
		}else if (mes==9){
			this.septemberValue += valor;
		}else if (mes==10){
			this.octoberValue += valor;
		}else if (mes==11){
			this.novemberValue += valor;
		}else if (mes==12){
			this.decemberValue += valor;
		}
	}
	
	public Double getTotalValue(){
		Double result = (januaryValue+februaryValue+marchValue+aprilValue+mayValue+juneValue+
						julyValue+augustValue+septemberValue+octoberValue+novemberValue+decemberValue);
		return new Arredondamento().arredondar(result);
	}

	
	@Override
	public int hashCode() {
		if (this.category == null) return super.hashCode();
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
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
		ProductCategoryLineDemo other = (ProductCategoryLineDemo) obj;
		if (category == null)
				return false;
		if (!category.equals(other.category))
			return false;
		return true;
	}
}
