package com.sidc.dao.sits.tvchargerecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author Allen Huang
 *
 */
public class TvChargeRecordDao {

	private TvChargeRecordDao() {
	}

	private static final class lazyHolder {
		public static TvChargeRecordDao INSTANCE = new TvChargeRecordDao();
	}

	public static TvChargeRecordDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private static final String DELETE_TV_CHARGE_RECORD = "DELETE FROM tv_charge_record WHERE room_no = ?";

	public void deleteTvChargeRecord(final Connection conn, final String roomno) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_TV_CHARGE_RECORD);

			int i = 0;
			psmt.setString(++i, roomno);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private static final String UPDATE_ROOMNO_IP_BY_ROOMNO = "UPDATE tv_charge_record SET room_no = ?,"
			+ "stb_ip = (SELECT ip FROM stb WHERE room_no = ? LIMIT 1) WHERE room_no = ?";

	public void updateRoomNoIP(final Connection conn, final String oldRoomNo, final String newRoomNo)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_ROOMNO_IP_BY_ROOMNO);

			int i = 0;
			psmt.setString(++i, newRoomNo);
			psmt.setString(++i, newRoomNo);
			psmt.setString(++i, oldRoomNo);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
