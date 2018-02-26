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
import br.com.weblogia.domain.login.UserRole;
import br.com.weblogia.letsmed.domain.User;
import br.com.weblogia.letsmed.domain.service.CryptoService;

@Controller
public class UsersController {
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject 
	Validator validator;
	
	public void user(){
		loadLists();
	}
	
	@SuppressWarnings("unchecked")
	@Get
	@Path("/users")
	public void list(){
		
		List<User> userList = (List<User>) entityManager.createQuery(" from User u order by u.name ").getResultList();
		result.include("userList", userList);
		
	}

	@Transactional
	public void save(User user, String retype){
		
		validator.addIf(( user.getName() == null || user.getName().trim().equals("")),new I18nMessage("off","user.without.name"));
		validator.addIf(( user.getLogin() == null || user.getLogin().trim().equals("")),new I18nMessage("off","user.without.name"));
		validator.addIf(( user.getPassword() == null || user.getPassword().trim().equals("")),new I18nMessage("off","user.without.name"));
		validator.addIf(!(user.getPassword().equals(retype)),new I18nMessage("off","user.password.does.not.match "));
		
		if(validator.hasErrors()){
			loadLists();
			result.include("user", user);
			validator.onErrorUsePageOf(this).user();
		}
		String encryptedPass = new CryptoService().encript(user.getPassword());
		user.setPassword(encryptedPass);
		if (user.getId() == null){
			entityManager.persist(user);
		}else{
			entityManager.merge(user);
		}
		result.redirectTo(this).list();
	}
	
	@Get
	@Path("/users/{id}")
	public void edit(Long id) {
		User user = entityManager.find(User.class, id);
		loadLists();
		result.include("user", user);
		result.of(this).user();
	}
	
	@SuppressWarnings("unchecked")
	private void loadLists() {
		List<UserRole> roles = (List<UserRole>) entityManager.createQuery(" from UserRole ur order by ur.description ").getResultList();
		result.include("roles", roles);
	}
}
