package com.sidc.dao.sits.roomserviceitem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemCategoryBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemSetBean;
import com.sidc.blackcore.api.sits.roomservice.reponse.RoomServiceItemResponse;

public class RoomServiceItemDao {
	/**
	 * @author Joe
	 */
	private RoomServiceItemDao() {
	}

	private static class LazyHolder {
		public static final RoomServiceItemDao INSTANCE = new RoomServiceItemDao();
	}

	public static RoomServiceItemDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO roomservice_item(ri_roomservice_category_id,ri_status,ri_price,ri_type,"
			+ "ri_sequence,ri_modify_time,ri_creation_time) VALUES (?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int status, final int categoryId, final float price,
			final String type, final int sequence) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setInt(++i, status);
			psmt.setFloat(++i, price);
			psmt.setString(++i, type);
			psmt.setInt(++i, sequence);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("roomservice_item insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE roomservice_item SET ri_roomservice_category_id = ?,ri_status = ?,ri_sequence=?,"
			+ "ri_price=?,ri_type=?,ri_modify_time = NOW() WHERE ri_id = ? ; ";

	public void update(final Connection conn, final int riId, final int categoryId, final int status, final float price,
			final String type, final int sequence) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setInt(++i, status);
			psmt.setInt(++i, sequence);
			psmt.setFloat(++i, price);
			psmt.setString(++i, type);
			psmt.setInt(++i, riId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String IS_EXIST = "SELECT ri_id FROM roomservice_item WHERE ri_id = ?";

	public boolean isExist(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST);

			int i = 0;
			psmt.setInt(++i, id);

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

	private final static String SELECT = "SELECT ri_id,ri_status,ri_type,ri_price,ril_name,ril_description,ri_sequence FROM roomservice_item "
			+ "RI LEFT JOIN roomservice_item_lang RIL ON RI.ri_id = RIL.ril_rommservice_item_id WHERE ri_roomservice_category_id = ? "
			+ "AND ril_lang =? AND ri_status = ?;";

	public List<RoomServiceItemBean> selectItem(final Connection conn, final int status, final int categoryId,
			final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceItemBean> list = new ArrayList<RoomServiceItemBean>();
		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setInt(++i, categoryId);
			psmt.setString(++i, langCode);
			psmt.setInt(++i, status);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceItemBean(rs.getInt("ri_id"), rs.getInt("ri_status"), rs.getInt("ri_sequence"),
						rs.getString("ri_type"), rs.getString("ril_name"), rs.getString("ril_description"),
						rs.getFloat("ri_price")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_ID = "SELECT ri_id,ri_status,ri_type,ri_price,ril_name,ril_description,ri_sequence FROM "
			+ "roomservice_item RI LEFT JOIN roomservice_item_lang RIL ON RI.ri_id = RIL.ril_rommservice_item_id WHERE ri_id = ? "
			+ "AND ril_lang =? AND ri_status = ?;";

	public RoomServiceItemBean selectItemById(final Connection conn, final int itemId, final int status,
			final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		RoomServiceItemBean entity = null;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);

			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setString(++i, langCode);
			psmt.setInt(++i, status);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				entity = new RoomServiceItemBean(rs.getInt("ri_id"), rs.getInt("ri_status"), rs.getInt("ri_sequence"),
						rs.getString("ri_type"), rs.getString("ril_name"), rs.getString("ril_description"),
						rs.getFloat("ri_price"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String SELECT_INFO_BY_ID = "SELECT ri_id,ri_roomservice_category_id,ri_price FROM roomservice_item WHERE ri_id = ?;";

	public RoomServiceItemInfoBean selectItemWithOrderLine(final Connection conn, final int itemId)
			throws SQLException {

		PreparedStatement psmt = null;
		RoomServiceItemInfoBean entity = null;
		try {
			psmt = conn.prepareStatement(SELECT_INFO_BY_ID);

			int i = 0;
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				entity = new RoomServiceItemInfoBean(rs.getInt("ri_id"), rs.getInt("ri_roomservice_category_id"),
						rs.getFloat("ri_price"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String IS_ENABLE = "SELECT ri_id FROM roomservice_item WHERE ri_id = ? AND ri_status = 1";

	public boolean isEnable(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_ENABLE);

			int i = 0;
			psmt.setInt(++i, id);

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

	private final static String IS_SET = "SELECT ri_id FROM roomservice_item WHERE ri_id = ? AND ri_type = 'set';";

	public boolean isSet(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_SET);

			int i = 0;
			psmt.setInt(++i, id);

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

	private final static String SELECT_BY_PARAMETER = "SELECT ri_id,ri_roomservice_category_id,ri_status,ri_sequence,ri_price,ri_type,"
			+ "DATE_FORMAT(ri_creation_time,'%Y/%m/%d %H:%i')AS ri_creation_time FROM roomservice_item ";
	private final String PARAMETER_ID = " ri_id = ? ";
	private final String PARAMETER_STATUS = " ri_status = ? ";
	private final String PARAMETER_CATEGORYID = " ri_roomservice_category_id = ? ";
	private final String PARAMETER_TYPE = " ri_type = ? ";

	public List<RoomServiceItemResponse> select(final Connection conn, final int categoryId, final int itemId,
			final int status, final String type) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceItemResponse> list = new ArrayList<RoomServiceItemResponse>();
		try {

			String description = "";

			int x = 0;

			if (categoryId > 0) {
				description = formatWhereDescription(x++, PARAMETER_CATEGORYID, description);
			}
			if (itemId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ID, description);
			}
			if (!StringUtils.isBlank(type)) {
				description = formatWhereDescription(x++, PARAMETER_TYPE, description);
			}
			if (status > -1) {
				description = formatWhereDescription(x++, PARAMETER_STATUS, description);
			}
			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			int i = 0;
			if (categoryId > 0) {
				psmt.setInt(++i, categoryId);
			}
			if (itemId > 0) {
				psmt.setInt(++i, itemId);
			}
			if (!StringUtils.isBlank(type)) {
				psmt.setString(++i, type);
			}
			if (status > -1) {
				psmt.setInt(++i, status);
			}

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceItemResponse(rs.getInt("ri_id"), rs.getInt("ri_roomservice_category_id"),
						rs.getInt("ri_status"), rs.getInt("ri_sequence"), rs.getFloat("ri_price"),
						rs.getString("ri_type"), rs.getString("ri_creation_time")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_WITH_SET = "SELECT ri_roomservice_category_id,ri_status,ri_sequence,"
			+ "DATE_FORMAT(ri_creation_time,'%Y/%m/%d %H:%i')AS ri_creation_time FROM roomservice_item WHERE ri_id = ?;";

	public RoomServiceItemSetBean selectWithSet(final Connection conn, final int itemId) throws SQLException {

		PreparedStatement psmt = null;
		RoomServiceItemSetBean entity = null;
		try {
			psmt = conn.prepareStatement(SELECT_WITH_SET);

			int i = 0;
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				entity = new RoomServiceItemSetBean(itemId, rs.getInt("ri_roomservice_category_id"),
						rs.getInt("ri_status"), rs.getInt("ri_sequence"), rs.getString("ri_creation_time"));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return entity;
	}

	private final static String SELECTE_CATEGORY_BY_ITEMID = "SELECT rc_id,rc_sequence,rc_creation_time,(SELECT rcil_limit_qty FROM "
			+ "roomservice_category_item_limit WHERE rcil_roomservice_category_id = rc_id AND rcil_roomservice_item_id = ?) AS limit_qty FROM "
			+ "roomservice_category WHERE rc_id IN(SELECT ri_roomservice_category_id FROM roomservice_item WHERE ri_id IN "
			+ "(SELECT rits_roomservice_item_id FROM roomservice_item_to_set WHERE rits_set_id = ?));";

	public List<RoomServiceItemCategoryBean> selectCategoryByItemId(final Connection conn, final int itemId)
			throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceItemCategoryBean> list = new ArrayList<RoomServiceItemCategoryBean>();
		try {

			int i = 0;

			psmt = conn.prepareStatement(SELECTE_CATEGORY_BY_ITEMID);
			psmt.setInt(++i, itemId);
			psmt.setInt(++i, itemId);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceItemCategoryBean(rs.getInt("rc_id"), rs.getInt("limit_qty"),
						rs.getInt("rc_sequence")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private String formatWhereDescription(int i, final String sqlWhereDescription, String description) {
		if (i == 0) {
			description += "WHERE" + sqlWhereDescription;
		} else {
			description += "AND" + sqlWhereDescription;
		}
		return description;
	}
}
