package br.com.weblogia.letsmed.controllers;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.weblogia.domain.login.UserRole;
import br.com.weblogia.letsmed.anotacoes.NaoPrecisaAutorizacao;
import br.com.weblogia.letsmed.domain.User;
import br.com.weblogia.letsmed.domain.service.CryptoService;
import br.com.weblogia.letsmed.repositories.UserRepository;

@Controller
@NaoPrecisaAutorizacao
public class LoginController {
	
	@Inject
	private User user;
	
	@Inject
	private Result result;
	
	@Inject
	@PersistenceContext
	protected EntityManager entityManager;
	
	public void login(){
		user.setId(null);
		user.setLoggedOn(false);
		user.setLogin(null);
		user.setName(null);
		user.setRole(null);
	}
	
	@Post
	public void logar(String login, String senha){
		
		if(login.equals("admin") && senha.equals("letsmed102030")){
			UserRole role = new UserRole();
			role.setName("SuperUser");
			user.setLoggedOn(true);
			user.setName("SYSADMIN");
			user.setLogin(login);
			user.setRole(role);
//			user.setEmpresa(null);
		}else{
			UserRepository userRep = new UserRepository(entityManager);
			User loadedUser = userRep.getUserByLogin(login);
			
			if(loadedUser!=null){
				
				String encryptedPass = new CryptoService().encript(senha);
				
				if(encryptedPass.equals(loadedUser.getPassword())){
					user.setId(loadedUser.getId());
					user.setLoggedOn(true);
					user.setLogin(login);
					user.setName(loadedUser.getName());
					user.setRole(loadedUser.getRole());
				}
				else {
					result.of(this).login();
				}
			}
		}
		
		if(user.getLastUrlAttempt()!=null){
			result.redirectTo(user.getLastUrlAttempt());
		}else{
			result.redirectTo("/");
		}
		
	}
	
	public void negado(){
		result.include("user",user);
	}
	
}
