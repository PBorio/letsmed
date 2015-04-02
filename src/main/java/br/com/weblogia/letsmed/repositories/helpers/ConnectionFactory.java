package br.com.weblogia.letsmed.repositories.helpers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;

public class ConnectionFactory {

	private final EntityManager entityManager;
	private Connection conn;
	private ConnectionProvider cp;

	public ConnectionFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("deprecation")
	public Connection criarConnection() {
		this.conn = null;
		Session session = null;
		SessionFactoryImplementor sfi = null;
		this.cp = null;
		try {
			session = (Session) entityManager.getDelegate();
			sfi = (SessionFactoryImplementor) session.getSessionFactory();
			cp = sfi.getConnectionProvider();
			conn = cp.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter a connection", e);
		}
		return conn;
	}
	
	public void closeConnection(){
		try{
			this.cp.closeConnection(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

