package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.pincode.PinCodeDao;

public class PinCodeManager {

	private PinCodeManager() {
	}

	private static final class lazyHolder {
		public static final PinCodeManager INSTANCE = new PinCodeManager();
	}

	public static PinCodeManager getInstance() {
		return lazyHolder.INSTANCE;
	}

	public void insertWithPinCode(final String roomNo, final String pinCode, final Timestamp expiredTime)
			throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			PinCodeDao.getInstance().delect(conn, roomNo);
			PinCodeDao.getInstance().insert(conn, roomNo, pinCode);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public boolean checkPinCodeExist(final String pinCode) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = PinCodeDao.getInstance().isExist(conn, pinCode);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

}
