package br.com.weblogia.domain.login;

import java.io.Serializable;

public interface Authorizable extends Serializable {

	UserRole getRole();
	boolean isLoggedOn();
	void setLoggedOn(boolean loggedOn);
	String getLastUrlAttempt();
	void setLastUrlAttempt(String url);
	
}
