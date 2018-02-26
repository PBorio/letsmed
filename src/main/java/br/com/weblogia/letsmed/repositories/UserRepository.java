package br.com.weblogia.letsmed.repositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.weblogia.letsmed.domain.User;

public class UserRepository {

	private EntityManager entityManager;

	public UserRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public User getUserByLogin(String login) {
		 StringBuilder hql = new StringBuilder();
	        hql.append(" From User u "); 
	        hql.append(" where u.login = :login "); 

	        Query query = entityManager.createQuery(hql.toString());
	        query.setParameter("login", login);
	        
	        User user = null;
	        
	        try{
	        	user = (User) query.getSingleResult(); 
	        }catch(NoResultException e){
	        	return user;
	        }

	        return user;
	}

}
