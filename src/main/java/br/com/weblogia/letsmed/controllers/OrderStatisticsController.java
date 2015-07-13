package br.com.weblogia.letsmed.controllers;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.DateTime;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.controllers.statistics.ProductCategoryDemoService;
import br.com.weblogia.letsmed.controllers.statistics.ProductCategoryView;
import br.com.weblogia.letsmed.controllers.statistics.country.CountryDemoService;
import br.com.weblogia.letsmed.controllers.statistics.country.CountryView;
import br.com.weblogia.letsmed.controllers.statistics.customer.CustomerDemoService;
import br.com.weblogia.letsmed.controllers.statistics.customer.CustomerView;
import br.com.weblogia.letsmed.domain.Order;

@Controller
public class OrderStatisticsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	@Path("/orderStatistics/orders")
	public void listOrders(){
		DateTime dtEnd = new DateTime(new Date().getTime());
		DateTime dtBegin = dtEnd.dayOfMonth().withMinimumValue();
		result.include("begin", dtBegin.toDate());
		result.include("end", dtEnd.toDate());
		result.of(this).list();
	}
	
	@SuppressWarnings("unchecked")
	@Path("/orderStatistics/orders/search")
	public void searchOrders(Date begin, Date end){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from Order o ");
		sql.append(" where ( o.orderDate >= :begin ");
		sql.append("  and    o.orderDate <= :end ) ");
		Query q = entityManager.createQuery(sql.toString());
		q.setParameter("begin", begin);
		q.setParameter("end", end);
		
		List<Order> orders = q.getResultList();
		result.include("begin", begin);
		result.include("end", end);
		result.include("orders", orders);
		result.of(this).list();
	}
	
	public void list(){
		
	}

	@SuppressWarnings("unchecked")
	@Path("/orderStatistics/products/categories")
	public void listCategories(){
		
		DateTime hoje = new DateTime(new Date().getTime());
		DateTime dtEnd = hoje.monthOfYear().withMaximumValue();
		dtEnd = dtEnd.dayOfMonth().withMaximumValue();
		
		DateTime dtBegin = hoje.monthOfYear().withMinimumValue();
		dtBegin = dtBegin.dayOfMonth().withMinimumValue();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from Order o ");
		sql.append(" where ( o.orderDate >= :begin ");
		sql.append("  and    o.orderDate <= :end ) ");
		Query q = entityManager.createQuery(sql.toString());
		q.setParameter("begin", dtBegin.toDate());
		q.setParameter("end", dtEnd.toDate());
		
		List<Order> orders = q.getResultList();
		
		ProductCategoryDemoService service = new ProductCategoryDemoService(orders);
		ProductCategoryView productCategoryView = new ProductCategoryView();
		productCategoryView.setProductCategoryLines(service.getLines());
		
		result.include("year", hoje.getYear());
		result.include("productCategoryView", productCategoryView);
	}
	
	@SuppressWarnings("unchecked")
	@Path("/orderStatistics/countries")
	public void listCountries(){
		
		DateTime hoje = new DateTime(new Date().getTime());
		DateTime dtEnd = hoje.monthOfYear().withMaximumValue();
		dtEnd = dtEnd.dayOfMonth().withMaximumValue();
		
		DateTime dtBegin = hoje.monthOfYear().withMinimumValue();
		dtBegin = dtBegin.dayOfMonth().withMinimumValue();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from Order o ");
		sql.append(" where ( o.orderDate >= :begin ");
		sql.append("  and    o.orderDate <= :end ) ");
		Query q = entityManager.createQuery(sql.toString());
		q.setParameter("begin", dtBegin.toDate());
		q.setParameter("end", dtEnd.toDate());
		
		List<Order> orders = q.getResultList();
		
		CountryDemoService service = new CountryDemoService(orders);
		CountryView countryView = new CountryView();
		countryView.setCountryLines(service.getLines());
		
		result.include("year", hoje.getYear());
		result.include("countryView", countryView);
	}
	
	@SuppressWarnings("unchecked")
	@Path("/orderStatistics/customers")
	public void listCustomers(){
		
		DateTime hoje = new DateTime(new Date().getTime());
		DateTime dtEnd = hoje.monthOfYear().withMaximumValue();
		dtEnd = dtEnd.dayOfMonth().withMaximumValue();
		
		DateTime dtBegin = hoje.monthOfYear().withMinimumValue();
		dtBegin = dtBegin.dayOfMonth().withMinimumValue();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from Order o ");
		sql.append(" where ( o.orderDate >= :begin ");
		sql.append("  and    o.orderDate <= :end ) ");
		Query q = entityManager.createQuery(sql.toString());
		q.setParameter("begin", dtBegin.toDate());
		q.setParameter("end", dtEnd.toDate());
		
		List<Order> orders = q.getResultList();
		
		CustomerDemoService service = new CustomerDemoService(orders);
		CustomerView customerView = new CustomerView();
		customerView.setCustomerLines(service.getLines());
		
		result.include("year", hoje.getYear());
		result.include("customerView", customerView);
	}
}
