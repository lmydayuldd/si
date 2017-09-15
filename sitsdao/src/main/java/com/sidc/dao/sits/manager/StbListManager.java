package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.stb.StbDao;

/**
 * 
 * @author Allen Huang
 *
 */
public class StbListManager {

	private StbListManager() {
		// TODO Auto-generated constructor stub
	}

	private static final class lazyHolder {
		public static final StbListManager INSTANCE = new StbListManager();
	}

	public static StbListManager getInstance() {
		return lazyHolder.INSTANCE;
	}

	public List<String> listStbIp(String roomno) throws SQLException {
		List<String> list = new ArrayList<String>();

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = StbDao.getInstance().selectStbIp(conn, roomno);

			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return list;
	}

	public boolean isExisted(final String roomNo, final String stbip) throws SQLException {

		boolean isPass = false;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = StbDao.getInstance().isExisted(conn, roomNo, stbip);

			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return isPass;
	}
}
