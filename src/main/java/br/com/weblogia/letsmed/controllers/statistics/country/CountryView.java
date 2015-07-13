package br.com.weblogia.letsmed.controllers.statistics.country;

import java.util.List;

import br.com.weblogia.letsmed.domain.helpers.Arredondamento;

public class CountryView {

	private List<CountryLineDemo> countryLines;
	
	public Double getTotalJanuary(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getJanuaryValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalFebrary(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getFebruaryValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalMarch(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getMarchValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalApril(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getAprilValue();
		}
		return new Arredondamento().arredondar(result);
	}

	public Double getTotalMay(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getMayValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalJune(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getJuneValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalJuly(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getJulyValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalAugust(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getAugustValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalSeptember(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getSeptemberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalOctober(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getOctoberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalNovember(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getNovemberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotalDecember(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getDecemberValue();
		}
		return new Arredondamento().arredondar(result);
	}
	
	public Double getTotal(){
		Double result = 0.0;
		for (CountryLineDemo despesa : this.countryLines){
			result += despesa.getTotalValue();
		}
		return new Arredondamento().arredondar(result);
	}

	public List<CountryLineDemo> getCountryLines() {
		return countryLines;
	}

	public void setCountryLines(List<CountryLineDemo> countryLines) {
		this.countryLines = countryLines;
	}

}
