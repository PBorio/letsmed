package br.com.weblogia.letsmed.domain;

import javax.enterprise.context.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.weblogia.domain.login.Authorizable;
import br.com.weblogia.domain.login.UserRole;

@SessionScoped
@Entity
@Table(name="users")
public class User implements Authorizable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8339822335636623223L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String login;
	
	private String password;
	
	@ManyToOne
	private UserRole role;
	
	@Transient
	private boolean loggedOn;
	
	@Transient
	private String lastUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public boolean isLoggedOn() {
		return loggedOn;
	}

	public void setLoggedOn(boolean loggedOn) {
		this.loggedOn = loggedOn;
	}

	@Override
	public String getLastUrlAttempt() {
		return lastUrl;
	}

	@Override
	public void setLastUrlAttempt(String url) {
		this.lastUrl = url;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public boolean isAdmin() {
		return ("Admin".equals(this.getRole().getName()) || "SuperUser".equals(this.getRole().getName()));
	}
}
