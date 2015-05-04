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
import br.com.weblogia.letsmed.domain.ProductCategory;

@Controller
public class ProductCategoriesController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void category(){
		result.include("controller", "productCategories");
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/productCategories")
	public List<ProductCategory> list(){
		result.include("controller", "productCategories");
		return (List<ProductCategory>) entityManager.createQuery(" from ProductCategory pc order by pc.description ").getResultList();
	}
	
	@Transactional
	public void save(ProductCategory productCategory){
		
		validator.addIf(( productCategory.getDescription() == null || productCategory.getDescription().trim().equals("")),new I18nMessage("rev","category.without.description"));

		if(validator.hasErrors()){
			result.include("productCategory", productCategory);
			result.include("controller", "productCategories");
			validator.onErrorUsePageOf(this).category();
		}
		
		if (productCategory.getId() == null){
			entityManager.persist(productCategory);
		}else{
			entityManager.merge(productCategory);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/productCategories/{id}")
	public void edit(Long id) {
		ProductCategory productCategoryList = entityManager.find(ProductCategory.class, id);
		result.include("controller", "productCategories");
		result.include("productCategoryList", productCategoryList);
		result.of(this).category();
	}

}
