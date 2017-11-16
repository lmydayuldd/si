/**
 * 
 */
package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.room.RoomDao;
import com.sidc.dao.ra.sosrecord.SosRecordDao;

/**
 * @author Joe
 *
 */
public class SosRecordManager {

	private SosRecordManager() {
	}

	private static class LazyHolder {
		public static final SosRecordManager INSTANCE = new SosRecordManager();
	}

	public static SosRecordManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public int insert(final String roomNo, final String action, final String clientIp, final String clientRole)
			throws SQLException {
		Connection conn = null;
		int id = 0;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			// 帶入bill 來區別房客
			final String billNo = RoomDao.getInstance().findBillNo(conn, roomNo);

			id = SosRecordDao.getInstance().insert(conn, roomNo, billNo, action, clientIp, clientRole);

			conn.commit();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return id;
	}

}
