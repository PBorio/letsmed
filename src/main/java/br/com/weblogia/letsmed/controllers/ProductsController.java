package br.com.weblogia.letsmed.controllers;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.weblogia.letsmed.domain.Product;
import br.com.weblogia.letsmed.domain.ProductCategory;

@Controller
public class ProductsController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void product(){
		loadLists();
		result.include("controller", "products");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/products")
	public List<Product> list(){
		result.include("controller", "products");
		return (List<Product>) entityManager.createQuery(" from Product p order by p.description ").getResultList();
	}
	
	@Transactional
	public void save(Product product){
		
		validator.addIf(( product.getDescription() == null || product.getDescription().trim().equals("")),new I18nMessage("off","product.without.description"));
		
		if (product.getProductCategory() != null && product.getProductCategory().getId() == -1)
			product.setProductCategory(null);
		
		if(validator.hasErrors()){
			result.include("product", product);
			validator.onErrorUsePageOf(this).product();
		}
		
		if (product.getId() == null){
			entityManager.persist(product);
		}else{
			entityManager.merge(product);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/products/{id}")
	public void edit(Long id) {
		loadLists();
		Product product = entityManager.find(Product.class, id);
		result.include("controller", "products");
		result.include("product", product);
		result.of(this).product();
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/products/searchByDescription.json")
	public void searchProductsByDescripion(String term) {
		String[] terms = term.split(" ");
		StringBuilder sql = new StringBuilder();
		sql.append(" from Product p ");
		sql.append(" where 1 = 1 ");
		for (int i = 0; i < terms.length; i++){
			sql.append(" and p.description like :description"+i);
		}
		
		Query q = entityManager.createQuery(sql.toString());
		
		for (int i = 0; i < terms.length; i++){
			q.setParameter("description"+i, "%"+terms[i]+"%");
		}
		result.use(json()).withoutRoot()
				.from((List<Product>)q.getResultList()).recursive()
				.serialize();
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/products/find.json")
	public void buscarUmClientePeloNome(String description) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from Product p ");
		sql.append(" where p.description = :description ");
		
		Query q = entityManager.createQuery(sql.toString());
		q.setParameter("description", description);
		
		List<Product> products = (List<Product>)q.getResultList();
		
		if (products.size() > 0){
			Product product = products.get(0);
			result.use(json()).withoutRoot().from(product).recursive().serialize();
		}
	}

	private void loadLists() {
		@SuppressWarnings("unchecked")
		List<ProductCategory> productCategoryList = (List<ProductCategory>) entityManager.createQuery(" from ProductCategory pc order by pc.description ").getResultList();
		result.include("productCategoryList", productCategoryList);		
	}
	
	public static void main(String[] args) {
		Double total = 0.0;
		
		for (int i = 0; i< 100; i++){
			total += (total*(0.01));
			total += 500.0;
			System.out.println(i+1);
		}
		
		System.out.println(total);
	}
}
