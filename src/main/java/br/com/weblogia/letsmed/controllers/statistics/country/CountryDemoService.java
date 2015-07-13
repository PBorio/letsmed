package br.com.weblogia.letsmed.controllers.statistics.country;

import java.util.ArrayList;
import java.util.List;

import br.com.weblogia.letsmed.domain.Order;

public class CountryDemoService {

	private List<Order> orders = new ArrayList<>();

	public CountryDemoService(List<Order> orders) {
		this.orders = orders;
	}

	public List<CountryLineDemo> getLines() {
		
		List<CountryLineDemo> linhas = new ArrayList<CountryLineDemo>();
		for (Order o : this.orders){
				CountryLineDemo line = new CountryLineDemo(o.getCustomer().getCountry());
				if (linhas.contains(line)){
					line = linhas.get(linhas.indexOf(line));
				}else{
					linhas.add(line);
				}
				
				line.sumValue(o);
		}
		
		return linhas;
	}

}
