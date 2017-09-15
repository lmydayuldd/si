package com.sidc.dao.sits.wakeup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 * @author Allen Huang
 *
 */
public class WakeUpDao {

	private WakeUpDao() {
	}
	
	private static final class lazyHolder {
		public static final WakeUpDao INSTANCE = new WakeUpDao();
	}
	
	public static WakeUpDao getInstance() {
		return lazyHolder.INSTANCE;
	}
	
	private static final String DELETE_WAKE_UP = "DELETE wake_up FROM wake_up INNER JOIN "
			+ "stb ON wake_up.ip = stb.ip WHERE stb.room_no = ?";
	public void deleteWakeUp(final Connection conn, final String room_no) throws SQLException {
		
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_WAKE_UP);
			
			int i = 0;
			psmt.setString(++i, room_no);
			
			psmt.addBatch();
			psmt.executeBatch();
			
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
	
}
