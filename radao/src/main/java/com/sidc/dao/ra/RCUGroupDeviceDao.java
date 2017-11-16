/**
 * 
 */
package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcugroupdevice.bean.RcuGroupDeviceGroupInfoBean;

/**
 * @author Nandin
 *
 */
public class RCUGroupDeviceDao {

	private RCUGroupDeviceDao() {
	}

	private static class LazyHolder {
		public static final RCUGroupDeviceDao INSTANCE = new RCUGroupDeviceDao();
	}

	public static RCUGroupDeviceDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO rcu_group_device(rcu_group_id, rcu_device_id, createdtime, modifiedtime) "
			+ " VALUES(?, ?, now(), now())";

	public int insert(Connection conn, int groupId, int deviceId) throws SQLException {

		int id = -1;
		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, groupId);
			psmt.setInt(++i, deviceId);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String DELETE_ALL = "DELETE FROM rcu_group_device";

	public void delete(Connection conn) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(DELETE_ALL);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

	}

	private final static String SELECT_BY_RCUGROUPID = "SELECT rcu_device_id ,rcu_device_group_id ,(SELECT name FROM "
			+ "rcu_device_group rdg WHERE rdg.id = rcu_device_group_id LIMIT 1) AS groupname "
			+ "FROM rcu_group_device LEFT JOIN "
			+ "rcu_device ON rcu_group_device.rcu_device_id = rcu_device.id WHERE rcu_group_id = ?";

	public List<RcuGroupDeviceGroupInfoBean> selectByRcuGroupid(final Connection conn, final int rcuGroupid)
			throws SQLException {
		List<RcuGroupDeviceGroupInfoBean> list = new ArrayList<RcuGroupDeviceGroupInfoBean>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_BY_RCUGROUPID);

			int i = 0;
			psmt.setInt(++i, rcuGroupid);

			final ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RcuGroupDeviceGroupInfoBean(rs.getInt("rcu_device_id"), rs.getInt("rcu_device_group_id"),
						rs.getString("groupname")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE = "DELETE FROM rcu_group_device WHERE rcu_group_id = ?;";

	public void deleteByGrouId(Connection conn, final int groupId) throws SQLException {

		PreparedStatement psmt = null;
		try {

			psmt = conn.prepareStatement(DELETE);
			int i = 0;
			psmt.setInt(++i, groupId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String IS_EXIST = "SELECT id FROM rcu_group_device WHERE rcu_group_id = ? AND rcu_device_id = ?;";

	public boolean isExist(final Connection conn, final int groupId, final int deviceId) throws SQLException {
		boolean isExist = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(IS_EXIST);

			int i = 0;
			psmt.setInt(++i, groupId);
			psmt.setInt(++i, deviceId);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				isExist = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isExist;
	}
}
