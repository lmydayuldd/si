/**
 * 
 */
package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.photo.PhotoDao;

public class PhotoManager {

	private PhotoManager() {
	}

	private static class LazyHolder {
		public static final PhotoManager INSTANCE = new PhotoManager();
	}

	public static PhotoManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void updateWithBackup(final String referId, final String status, final byte[] backup) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			PhotoDao.getInstance().updateWithBackup(conn, referId, status, backup);
			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public List<String> seleceNameById(final String referId, final String type) throws SQLException {

		Connection conn = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = PhotoDao.getInstance().selectNameByReferid(conn, referId, type);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}
}
