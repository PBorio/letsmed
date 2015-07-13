package br.com.weblogia.letsmed.controllers.statistics;

import java.util.List;

import br.com.weblogia.letsmed.domain.helpers.Arredondamento;

public class ProductCategoryView {

	private List<ProductCategoryLineDemo> productCategoryLines;
	
	public Double getTotalJanuary(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getJanuaryValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalFebrary(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getFebruaryValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalMarch(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getMarchValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalApril(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getAprilValue();
		}
		return new Arredondamento().arredondar(result);
	}

	public Double getTotalMay(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getMayValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalJune(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getJuneValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalJuly(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getJulyValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalAugust(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getAugustValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalSeptember(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getSeptemberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalOctober(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getOctoberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalNovember(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getNovemberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalDecember(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getDecemberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotal(){
		Double result = 0.0;
		for (ProductCategoryLineDemo despesa : this.productCategoryLines){
			result += despesa.getTotalValue();
		}
		return new Arredondamento().arredondar(result);
	}

	public List<ProductCategoryLineDemo> getProductCategoryLines() {
		return productCategoryLines;
	}

	public void setProductCategoryLines(
			List<ProductCategoryLineDemo> productCategoryLines) {
		this.productCategoryLines = productCategoryLines;
	}
}
