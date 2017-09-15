package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.bill.BillDao;

/**
 * 
 * @author Allen Huang
 *
 */
public class BillManager {

	private BillManager() {
	}
	
	private static class LazyHolder {
		public static final BillManager INSTANCE = new BillManager();
	}
	
	public static BillManager getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public String findBillCheckinDate(final String billNo, final String roomNo) throws SQLException {
		
		Connection conn = null;
		String chki_time = "";
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			chki_time = BillDao.getInstance().findBillCheckinDate(conn, billNo, roomNo);

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return chki_time;
	}
}
