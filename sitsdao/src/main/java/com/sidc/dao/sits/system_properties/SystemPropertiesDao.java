package com.sidc.dao.sits.system_properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Allen Huang
 *
 */
public class SystemPropertiesDao {

	private SystemPropertiesDao() {
	}
	
	private static final class lazyHolder {
		public static final SystemPropertiesDao INSTANCE = new SystemPropertiesDao();
	}
	
	public static SystemPropertiesDao getInstance() {
		return lazyHolder.INSTANCE;
	}
	
	private static final String PROPERTIES_VALUE = "SELECT property_value FROM system_properties "
			+ "WHERE property_key = ?";
	public String findPropertiesValue(final Connection conn, final String key) throws SQLException {
		String value = "";
		
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(PROPERTIES_VALUE);
			
			int i = 0;
			psmt.setString(++i, key);
			
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				value = rs.getString("property_value");
			}
			
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		
		return value;
	}
}
