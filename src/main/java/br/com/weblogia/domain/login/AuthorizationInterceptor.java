package br.com.weblogia.domain.login;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.weblogia.letsmed.anotacoes.NaoPrecisaAutorizacao;
import br.com.weblogia.letsmed.domain.User;

@Intercepts
@RequestScoped
public class AuthorizationInterceptor {
	
private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	
	private Authorizator authorizator;
	private Authorizable authorizable;
	private Result result;
	private HttpServletRequest request;

	private User user;

	/**
	 * @deprecated cdi eyes only
	 */
	@Deprecated
	protected AuthorizationInterceptor() {

	}

	@Inject
	public AuthorizationInterceptor(Authorizator autorizador,Authorizable autorizavel, Result result, HttpServletRequest request,User user) {
		this.authorizator = autorizador;
		this.authorizable = autorizavel;
		this.result = result;
		this.request = request;
		this.user = user;
	}

	@Accepts
	public boolean accepts(ControllerMethod method) {
		return !(method.getMethod().isAnnotationPresent(NaoPrecisaAutorizacao.class) || isAnnotationPresent(method.getController().getType()));
	}

	@AroundCall
	public void intercept(SimpleInterceptorStack stack) {
		
		Authorizable authorizable = this.authorizable;
		
		if (authorizable == null) {
			LOGGER.error("User not found");
			throw new IllegalStateException("Authentication could not be done");
		}else if (! authorizable.isLoggedOn()){
			authorizable.setLastUrlAttempt(getCurrentURL());
			result.include("user",user);
			result.redirectTo("/login/login");
		} else if (isAllowed(authorizable)) {
			result.include("user",user);
			stack.next();
		} else {
			result.include("user",user);
			result.redirectTo("/login/negado");
		}
	}

	private boolean isAllowed(Authorizable autorizavel) {
		String currentURL = getCurrentURL();
		
		if(autorizavel.getRole().getName().equals("SuperUsuario"))
			return true;
		
		if (authorizator.hasPermission(autorizavel.getRole(), currentURL)) {
			return true;
		}
		return false;
	}

	private String getCurrentURL() {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		return requestURI.replaceFirst(contextPath, "");
	}

	private boolean isAnnotationPresent(Class<?> type) {
		return type.isAnnotationPresent(NaoPrecisaAutorizacao.class) || (!type.equals(Object.class) && isAnnotationPresent(type.getSuperclass()));
	}

}
