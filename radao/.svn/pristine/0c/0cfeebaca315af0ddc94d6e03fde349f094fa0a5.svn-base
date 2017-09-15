/**
 * 
 */
package com.sidc.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Nandin
 *
 */
public class ProxoolConnection {

	private final static String PROXOOL = "proxool.";
	private final static String SITS = "SiTS";

	private ProxoolConnection() {
	}

	private static class LazyHolder {
		public static final ProxoolConnection INSTANCE = new ProxoolConnection();
	}

	public static ProxoolConnection getInstance() {
		return LazyHolder.INSTANCE;
	}

	public Connection connectSiTS() throws SQLException {
		return DriverManager.getConnection(PROXOOL + SITS);
	}
	
}
