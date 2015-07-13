package br.com.weblogia.letsmed.controllers.statistics;

import java.util.ArrayList;
import java.util.List;

import br.com.weblogia.letsmed.domain.Order;
import br.com.weblogia.letsmed.domain.OrderItem;

public class ProductCategoryDemoService {

	private List<Order> orders = new ArrayList<>();

	public ProductCategoryDemoService(List<Order> orders) {
		this.orders = orders;
	}

	public List<ProductCategoryLineDemo> getLines() {
		
		List<ProductCategoryLineDemo> linhas = new ArrayList<ProductCategoryLineDemo>();
		for (Order o : this.orders){
			for (OrderItem i : o.getItens()){	
				ProductCategoryLineDemo line = new ProductCategoryLineDemo(i.getProduct().getProductCategory());
				if (linhas.contains(line)){
					line = linhas.get(linhas.indexOf(line));
				}else{
					linhas.add(line);
				}
				
				line.sumItemValue(i);
			}
		}
		
		return linhas;
	}
	

}
