package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuModeDeviceResponse;

public class RcuModeDeviceDao {
	private static class LazyHolder {
		public static final RcuModeDeviceDao INSTANCE = new RcuModeDeviceDao();
	}

	public static RcuModeDeviceDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SEARCH_BY_MODELID = "SELECT rmd.id,rmd.mode_id,rmd.rcu_device_id,rd.device FROM rcu_mode_device rmd LEFT JOIN rcu_device rd ON rmd.rcu_device_id = rd.id WHERE mode_id = ?;";

	public List<RcuModeDeviceResponse> searchByModeId(final Connection conn, final int modeId) throws SQLException {
		List<RcuModeDeviceResponse> list = new ArrayList<RcuModeDeviceResponse>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BY_MODELID);
			int i = 0;
			psmt.setInt(++i, modeId);

			final ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RcuModeDeviceResponse(rs.getInt("mode_id"), rs.getInt("rcu_device_id"),rs.getString("device")));
			}

			conn.commit();
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String INSERT = "INSERT INTO rcu_mode_device (`mode_id`, `rcu_device_id`, `createdtime`) VALUES ( ?, ?, NOW() );";

	public void insert(Connection conn, final int modeId, final int deviceId) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, modeId);
			psmt.setInt(++i, deviceId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE = "DELETE FROM rcu_mode_device WHERE mode_id = ? AND rcu_device_id = ?";

	public void delete(Connection conn, final int modeId, final int deviceId) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, modeId);
			psmt.setInt(++i, deviceId);

			psmt.executeUpdate();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
	
	private final static String DELETE_BY_MODEID = "DELETE FROM rcu_mode_device WHERE mode_id = ?";

	public void delete(Connection conn, final int modeId) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(DELETE_BY_MODEID);

			int i = 0;
			psmt.setInt(++i, modeId);

			psmt.executeUpdate();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
