/**
 * 
 */
package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.bean.RcuGroupModuleBean;
import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;
import com.sidc.dao.ra.response.RcuModule;

/**
 * @author Nandin
 *
 */
public class RcuDeviceDao {

	private RcuDeviceDao() {
	}

	private static class LazyHolder {
		public static final RcuDeviceDao INSTANCE = new RcuDeviceDao();
	}

	public static RcuDeviceDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String LIST_RCU_DEVICE = "SELECT id, device FROM rcu_device";

	public List<RcuModule> listRcuDevice(final Connection conn) throws SQLException {

		List<RcuModule> list = new ArrayList<RcuModule>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(LIST_RCU_DEVICE);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RcuModule(rs.getInt("id"), rs.getString("device")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String LIST_RCU_MODULE_BY_LANG = "SELECT A.id, A.device, B.name "
			+ " FROM rcu_device A LEFT JOIN rcu_device_lang B ON (A.id = B.rcu_deviceid) " + " WHERE lang = ?";

	public List<RcuModule> listRcuModuleByLang(final Connection conn, final String lang) throws SQLException {

		List<RcuModule> list = new ArrayList<RcuModule>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(LIST_RCU_MODULE_BY_LANG);
			int i = 0;
			psmt.setString(++i, lang);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RcuModule(rs.getInt("id"), rs.getString("device")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String SEARCH_BY_RCUDEVICEGROUP = "SELECT id,device FROM rcu_device WHERE rcu_device_group_id = (SELECT id FROM rcu_device_group WHERE name = ? LIMIT 1);";

	public List<RcuDeviceEnity> searchByRcuDeviceGroup(final Connection conn, final String groupName)
			throws SQLException {

		List<RcuDeviceEnity> list = new ArrayList<RcuDeviceEnity>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BY_RCUDEVICEGROUP);
			int i = 0;
			psmt.setString(++i, groupName);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RcuDeviceEnity(rs.getInt("id"), rs.getString("device"), groupName));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String SELECT_ALL_DEVICE = "SELECT id,device,rcu_device_group_id ,(SELECT rdg.name FROM rcu_device_group rdg WHERE rcu_device_group_id = rdg.id LIMIT 1) AS rcu_group_name FROM rcu_device ORDER BY rcu_device_group_id ASC;";

	public List<RcuDeviceEnity> selectAllDevice(final Connection conn) throws SQLException {

		List<RcuDeviceEnity> list = new ArrayList<RcuDeviceEnity>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_ALL_DEVICE);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RcuDeviceEnity(rs.getInt("id"), rs.getString("device"), rs.getString("rcu_group_name")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String SELECT_BY_DEVICE = "SELECT rd.id,rd.device,(SELECT name FROM rcu_device_group rdg WHERE rdg.id = rd.rcu_device_group_id) AS rcu_group_name FROM rcu_device rd WHERE rd.device = ? ;";

	public List<RcuDeviceEnity> searchByDevice(final Connection conn, final String deviceName) throws SQLException {

		List<RcuDeviceEnity> list = new ArrayList<RcuDeviceEnity>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_BY_DEVICE);
			int i = 0;
			psmt.setString(++i, deviceName);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RcuDeviceEnity(rs.getInt("id"), rs.getString("device"), rs.getString("rcu_group_name")));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String SELECT_DEVICE_BY_ID = "SELECT device FROM rcu_device rd WHERE id = ?;";

	public String findDevice(final Connection conn, final int id) throws SQLException {

		String result = "";

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_DEVICE_BY_ID);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("device");
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return result;
	}

	private final static String LIST_RCU_DEVICE_GROUP = "SELECT device,rdg.name FROM rcu_device rd LEFT JOIN rcu_device_group rdg"
			+ " ON rd.rcu_device_group_id = rdg.id;";

	public List<RcuGroupModuleBean> listRcuDeviceGroup(final Connection conn) throws SQLException {

		List<RcuGroupModuleBean> list = new ArrayList<RcuGroupModuleBean>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(LIST_RCU_DEVICE_GROUP);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RcuGroupModuleBean(rs.getString("device"), rs.getString("name")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String IS_EXIST = "SELECT id FROM rcu_device WHERE device = ?;";

	public boolean isExist(final Connection conn, final String device) throws SQLException {

		boolean isExist = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(IS_EXIST);

			int i = 0;
			psmt.setString(++i, device);

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

	private final static String SELECT_ID_BY_DEVICE = "SELECT id FROM rcu_device WHERE device= ?;";

	public int findId(final Connection conn, final String device) throws SQLException {

		int id = -9999;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_ID_BY_DEVICE);

			int i = 0;
			psmt.setString(++i, device);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}
}
