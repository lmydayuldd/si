package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.system_properties.SystemPropertiesDao;

/**
 * 
 * @author Allen Huang
 *
 */
public class SystemPropertiesManager {

	private SystemPropertiesManager() {
	}

	private static final class lazyHolder {
		public static final SystemPropertiesManager INSTANCE = new SystemPropertiesManager();
	}

	public static SystemPropertiesManager getInstance() {
		return lazyHolder.INSTANCE;
	}

	public String findPropertiesMessage(String key) throws SQLException {
		String value = "";

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			value = SystemPropertiesDao.getInstance().findPropertiesValue(conn, key);

			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return value;
	}

	public Map<String, String> findPropertiesMessage(final String[] keys) throws SQLException {
		Map<String, String> map = new HashMap<String, String>();

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final String key : keys) {
				map.put(key, SystemPropertiesDao.getInstance().findPropertiesValue(conn, key));
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return map;
	}
}
