package br.com.weblogia.letsmed.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.domain.BuyOrder;
import br.com.weblogia.letsmed.domain.BuyOrderItem;
import br.com.weblogia.letsmed.domain.TransactionTerm;
import br.com.weblogia.letsmed.domain.Product;
import br.com.weblogia.letsmed.domain.Supplier;

@Controller
public class BuyOrdersController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void order(){
		loadLists();
		result.include("controller", "buyOrders");
	}

	@SuppressWarnings("unchecked")
	@Get
	@Path("/buyOrders")
	public List<BuyOrder> list(){
		result.include("controller", "buyOrders");
		return (List<BuyOrder>) entityManager.createQuery(" select bo from BuyOrder bo join bo.order o where o.conclusionDate is null order by bo.orderDate desc ").getResultList();
	}
	
	@Transactional
	public void save(BuyOrder buyOrder){
		
		validateOrder(buyOrder);

		if(validator.hasErrors()){
			loadLists();
			result.include("buyOrder", buyOrder);
			validator.onErrorUsePageOf(this).order();
		}
		
		if (buyOrder.getId() == null){
			entityManager.persist(buyOrder);
			saveItens(buyOrder);
		}else{
			entityManager.merge(buyOrder);
			saveItens(buyOrder);
		}
		result.redirectTo(this).list();
	}

	private void saveItens(BuyOrder buyOrder) {
		for (BuyOrderItem item : buyOrder.getItens()){
			if (item.getId() == null){
				item.setBuyOrder(buyOrder);
				entityManager.persist(item);
			}else{
				entityManager.merge(item);
			}
		}
	}

	@Get
	@Path("/buyOrders/{id}")
	public void edit(Long id) {
		BuyOrder buyOrder = entityManager.find(BuyOrder.class, id);
		loadLists();
		result.include("buyOrder", buyOrder);
		result.include("controller", "buyOrders");
		result.of(this).order();
	}
	
	@Get
	@Path("/buyOrders/show/{id}")
	public void show(Long id) {
		BuyOrder buyOrder = entityManager.find(BuyOrder.class, id);
		result.include("buyOrder", buyOrder);
		result.include("controller", "buyOrders");
	}
	
	@Transactional
	@Path("/buyOrders/delete/item/{id}")
	public void deleteItem(Long id){
		if (id == null)
			return;
		BuyOrderItem item = entityManager.find(BuyOrderItem.class, id);
		entityManager.remove(item);
		result.nothing();
	}

	@SuppressWarnings("unchecked")
	private void loadLists() {
		List<Product> productList = (List<Product>) entityManager.createQuery(" from Product p order by p.description ").getResultList();
		List<Supplier> supplierList = (List<Supplier>) entityManager.createQuery(" from Supplier s order by s.supplierName ").getResultList();
		List<TransactionTerm> paymentTermList = (List<TransactionTerm>) entityManager.createQuery(" from PaymentTerm p order by p.description ").getResultList();
		result.include("productList", productList);
		result.include("supplierList", supplierList);
		result.include("paymentTermList", paymentTermList);
	}
	
	private void validateOrder(BuyOrder buyOrder) {
		if (buyOrder.getSupplier().getId() == -1) 	buyOrder.setSupplier(null);
		if (buyOrder.getPaymentTerm().getId() == -1) 	buyOrder.setPaymentTerm(null);
		
		validator.addIf( buyOrder.getOrderDate() == null,new I18nMessage("cus","order.without.date"));
		validator.addIf( buyOrder.getPaymentTerm() == null,new I18nMessage("cus","order.without.payment"));
		

		for (BuyOrderItem item : buyOrder.getItens()){
			if (item.getProduct().getId() == -1) item.setProduct(null);
			validator.addIf( item.getProduct() == null,new I18nMessage("ord","item.without.product"));
			validator.addIf( item.getUnitPrice() == null,new I18nMessage("ord","order.without.price"));
			validator.addIf( item.getQuantity() == null,new I18nMessage("ord","order.without.quantity"));
		}
	}

}
