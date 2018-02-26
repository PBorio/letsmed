package br.com.weblogia.domain.login;

public class DBAuthorizator implements Authorizator {

	@Override
	public boolean hasPermission(UserRole role, String url) {
		return true;
	}

}
