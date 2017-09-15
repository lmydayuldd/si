package com.sidc.dao.sits.roomserviceitemtoset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetItemBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetItemInfoBean;

public class RoomServiceItemToSetDao {
	/**
	 * @author Joe
	 */
	private RoomServiceItemToSetDao() {
	}

	private static class LazyHolder {
		public static final RoomServiceItemToSetDao INSTANCE = new RoomServiceItemToSetDao();
	}

	public static RoomServiceItemToSetDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO roomservice_item_to_set(rits_set_id,rits_roomservice_item_id,rits_roomservice_creation_time)"
			+ " VALUES (?,?,NOW())";

	public void insert(final Connection conn, final int setId, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT);
			int i = 0;
			psmt.setInt(++i, setId);
			psmt.setInt(++i, itemId);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE = "DELETE FROM roomservice_item_to_set WHERE rits_set_id = ? ;";

	public void delete(final Connection conn, final int setId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);
			int i = 0;
			psmt.setInt(++i, setId);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_SET = "SELECT rits_roomservice_item_id FROM roomservice_item_to_set WHERE rits_set_id = ?";

	public List<Integer> selectSet(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			psmt = conn.prepareStatement(SELECT_SET);

			int i = 0;
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("rits_roomservice_item_id"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT = "SELECT DISTINCT RI.ri_roomservice_category_id FROM roomservice_item_to_set "
			+ "RITS LEFT JOIN roomservice_item RI ON RITS.rits_roomservice_item_id = RI.ri_id WHERE rits_set_id = ?;";

	public List<Integer> selectGroudId(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("ri_roomservice_category_id"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_SET_ITEM = "SELECT ri_id,ril_name,ril_description,ri_status,ri_sequence FROM roomservice_item_to_set "
			+ "LEFT JOIN roomservice_item ON rits_roomservice_item_id = ri_id LEFT JOIN roomservice_item_lang ON rits_roomservice_item_id"
			+ " = ril_rommservice_item_id WHERE ril_lang = ? AND ri_roomservice_category_id = ? AND rits_set_id = ?;";

	public List<RoomServiceSetItemBean> selectSetItem(final Connection conn, final int setId, final int categoryId,
			final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceSetItemBean> list = new ArrayList<RoomServiceSetItemBean>();
		try {
			psmt = conn.prepareStatement(SELECT_SET_ITEM);

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setInt(++i, categoryId);
			psmt.setInt(++i, setId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceSetItemBean(rs.getInt("ri_id"), rs.getInt("ri_status"),
						rs.getInt("ri_sequence"), rs.getString("ril_name"), rs.getString("ril_description")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String IS_EXIST = "SELECT rits_set_id FROM roomservice_item_to_set WHERE rits_set_id = ? AND "
			+ "rits_roomservice_item_id =?;";

	public boolean isExist(final Connection conn, final int setId, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST);

			int i = 0;
			psmt.setInt(++i, setId);
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				isPass = true;
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String SELECT_ITEM_BY_ITEM = "SELECT ri_id,ri_status,ri_sequence FROM roomservice_item_to_set LEFT JOIN "
			+ "roomservice_item ON rits_roomservice_item_id = ri_id WHERE ri_roomservice_category_id = ? AND rits_set_id = ?";

	public List<RoomServiceSetItemInfoBean> selectByCategoryIdSetId(final Connection conn, final int setId,
			final int categoryId) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceSetItemInfoBean> list = new ArrayList<RoomServiceSetItemInfoBean>();
		try {
			psmt = conn.prepareStatement(SELECT_ITEM_BY_ITEM);

			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setInt(++i, setId);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RoomServiceSetItemInfoBean(rs.getInt("ri_id"), rs.getInt("ri_status"),
						rs.getInt("ri_sequence")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
