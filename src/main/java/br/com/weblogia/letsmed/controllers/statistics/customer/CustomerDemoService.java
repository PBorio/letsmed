package br.com.weblogia.letsmed.controllers.statistics.customer;

import java.util.ArrayList;
import java.util.List;

import br.com.weblogia.letsmed.domain.Order;

public class CustomerDemoService {

	private List<Order> orders = new ArrayList<>();

	public CustomerDemoService(List<Order> orders) {
		this.orders = orders;
	}

	public List<CustomerLineDemo> getLines() {
		
		List<CustomerLineDemo> linhas = new ArrayList<CustomerLineDemo>();
		for (Order o : this.orders){
				CustomerLineDemo line = new CustomerLineDemo(o.getCustomer());
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
