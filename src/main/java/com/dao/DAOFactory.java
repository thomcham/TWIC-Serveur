package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import com.conf.JDBCConf;

public class DAOFactory {

	private String url;
	private String username;
	private String password;

	DAOFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DAOFactory getInstance() {
		try {
			Class.forName(JDBCConf.DRIVER);
		} catch (ClassNotFoundException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, "Error", e);
		}
		return new DAOFactory(JDBCConf.HOST, JDBCConf.LOGIN, JDBCConf.PASSWORD);
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	

	public VilleDAOImpl getImpl() throws SQLException {
		return new VilleDAOImpl(this);
	}

}
