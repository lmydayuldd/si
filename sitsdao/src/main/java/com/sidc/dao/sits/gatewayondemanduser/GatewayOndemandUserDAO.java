package com.sidc.dao.sits.gatewayondemanduser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author Joe
 *
 */
public class GatewayOndemandUserDAO {

	private GatewayOndemandUserDAO() {
	}

	private static final class lazyHolder {
		public static GatewayOndemandUserDAO INSTANCE = new GatewayOndemandUserDAO();
	}

	public static GatewayOndemandUserDAO getInstance() {
		return lazyHolder.INSTANCE;
	}

	
	private final static String UPDATE_ROOMNO_BY_ROOMNO = "UPDATE gateway_ondemand_user SET room_no = ? WHERE room_no = ?";

	public void updateRoomNo(final Connection conn, final String oldRoomNo, final String newRoomNo)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_ROOMNO_BY_ROOMNO);

			int i = 0;
			psmt.setString(++i, newRoomNo);
			psmt.setString(++i, oldRoomNo);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
