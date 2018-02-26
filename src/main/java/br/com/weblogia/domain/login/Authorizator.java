package br.com.weblogia.domain.login;

public interface Authorizator {
	
	boolean hasPermission(UserRole role, String url);

}
