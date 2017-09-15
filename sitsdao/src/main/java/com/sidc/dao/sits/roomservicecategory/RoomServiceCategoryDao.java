package com.sidc.dao.sits.roomservicecategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceCategoryBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetCategoryBean;
import com.sidc.blackcore.api.sits.roomservice.reponse.RoomServiceCategoryResponse;

public class RoomServiceCategoryDao {
	/**
	 * @author Joe
	 */
	private RoomServiceCategoryDao() {
	}

	private static class LazyHolder {
		public static final RoomServiceCategoryDao INSTANCE = new RoomServiceCategoryDao();
	}

	public static RoomServiceCategoryDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO roomservice_category(rc_status,rc_refer_id,rc_service_star_time,rc_service_end_time,"
			+ "rc_sequence,rc_modify_time,rc_creation_time) VALUES (?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int status, final int referId, final String serviceStartTime,
			final String serviceEndTime, final int sequence) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, status);
			psmt.setInt(++i, referId);
			psmt.setString(++i, serviceStartTime);
			psmt.setString(++i, serviceEndTime);
			psmt.setInt(++i, sequence);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("roomservice_category insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE roomservice_category SET rc_status = ?,rc_refer_id = ?,rc_service_star_time=?"
			+ ",rc_service_end_time=?,rc_sequence=?,rc_modify_time = NOW() WHERE rc_id = ? ; ";

	public void update(final Connection conn, final int rcId, final int status, final int referId,
			final String serviceStarTime, final String serviceEndTime, final int sequence) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setInt(++i, referId);
			psmt.setString(++i, serviceStarTime);
			psmt.setString(++i, serviceEndTime);
			psmt.setInt(++i, sequence);
			psmt.setInt(++i, rcId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_STATUS = "UPDATE roomservice_category SET rc_status = ?,rc_modify_time = NOW() WHERE rc_id = ? ; ";

	public void updateStatus(final Connection conn, final int rcId, final int status) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_STATUS);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setInt(++i, rcId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_REFERID = "UPDATE roomservice_category SET rc_refer_id = ?,rc_modify_time = NOW() WHERE rc_id = ? ;";

	public void updateReferId(final Connection conn, final int rcId, final int referId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_REFERID);

			int i = 0;
			psmt.setInt(++i, referId);
			psmt.setInt(++i, rcId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String IS_EXIST = "SELECT rc_id FROM roomservice_category WHERE rc_id = ?";

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

	private final static String IS_EXIST_BY_REFERID = "SELECT rc_refer_id FROM roomservice_category WHERE rc_refer_id = ?";

	public boolean isExistByReferId(final Connection conn, final int referId) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST_BY_REFERID);

			int i = 0;
			psmt.setInt(++i, referId);

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

	private final static String SELECT_TOP_LEVEL = "SELECT rc_id,rc_status,rc_refer_id,rc_service_star_time,rc_service_end_time,rcl_name,"
			+ "rc_sequence,rcl_description,DATE_FORMAT(rc_creation_time,'%Y/%m/%d %H:%i')AS rc_creation_time FROM roomservice_category RC "
			+ "LEFT JOIN roomservice_category_lang RCL ON RC.rc_id = RCL.rcl_roomservice_category_id WHERE RCL.rcl_lang = ? AND rc_id = "
			+ "rc_refer_id AND rc_status = ?;";

	public List<RoomServiceCategoryBean> selectTopLevelCategory(final Connection conn, final int status,
			final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceCategoryBean> list = new ArrayList<RoomServiceCategoryBean>();
		try {
			psmt = conn.prepareStatement(SELECT_TOP_LEVEL);

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setInt(++i, status);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceCategoryBean(rs.getInt("rc_id"), rs.getInt("rc_status"),
						rs.getInt("rc_refer_id"), rs.getInt("rc_sequence"), rs.getString("rc_service_star_time"),
						rs.getString("rc_service_end_time"), rs.getString("rcl_name"), rs.getString("rcl_description"),
						rs.getString("rc_creation_time")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_OTHER_LEVEL = "SELECT rc_id,rc_status,rc_refer_id,rc_service_star_time,rc_service_end_time,rcl_name,"
			+ "rc_sequence,rcl_description,DATE_FORMAT(rc_creation_time,'%Y/%m/%d %H:%i')AS rc_creation_time FROM roomservice_category RC LEFT "
			+ "JOIN roomservice_category_lang RCL ON RC.rc_id = RCL.rcl_roomservice_category_id WHERE RCL.rcl_lang = ? AND rc_id != "
			+ "rc_refer_id AND rc_status = ? AND rc_refer_id = ?;";

	public List<RoomServiceCategoryBean> selectCategory(final Connection conn, final int status, final int referId,
			final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceCategoryBean> list = new ArrayList<RoomServiceCategoryBean>();
		try {
			psmt = conn.prepareStatement(SELECT_OTHER_LEVEL);

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setInt(++i, status);
			psmt.setInt(++i, referId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceCategoryBean(rs.getInt("rc_id"), rs.getInt("rc_status"),
						rs.getInt("rc_refer_id"), rs.getInt("rc_sequence"), rs.getString("rc_service_star_time"),
						rs.getString("rc_service_end_time"), rs.getString("rcl_name"), rs.getString("rcl_description"),
						rs.getString("rc_creation_time")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_ = "SELECT rc_id,rc_status,rc_refer_id,rc_service_star_time,rc_service_end_time,rcl_name,rcl_description,"
			+ "rc_sequence,rc_creation_time,(SELECT rcil_limit_qty FROM roomservice_category_item_limit WHERE rcil_roomservice_category_id = "
			+ "rc_id AND rcil_roomservice_item_id = ? ) AS limit_qty FROM roomservice_category RC LEFT JOIN roomservice_category_lang RCL ON "
			+ "RC.rc_id = RCL.rcl_roomservice_category_id WHERE RCL.rcl_lang = ? AND rc_status = ? AND rc_id IN (SELECT DISTINCT "
			+ "RI.ri_roomservice_category_id FROM roomservice_item_to_set RITS LEFT JOIN roomservice_item RI ON RITS.rits_roomservice_item_id "
			+ "= RI.ri_id WHERE rits_set_id = ?);";

	public List<RoomServiceSetCategoryBean> select(final Connection conn, final int itemId, final int status,
			final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceSetCategoryBean> list = new ArrayList<RoomServiceSetCategoryBean>();
		try {
			psmt = conn.prepareStatement(SELECT_);

			int i = 0;
			psmt.setInt(++i, itemId);
			psmt.setString(++i, langCode);
			psmt.setInt(++i, status);
			psmt.setInt(++i, itemId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceSetCategoryBean(rs.getInt("rc_id"), rs.getInt("rc_status"),
						rs.getInt("limit_qty"), rs.getInt("rc_sequence"), rs.getString("rcl_name"),
						rs.getString("rcl_description")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_PARAMETER = "SELECT rc_id,rc_status,rc_sequence,rc_refer_id,rc_service_star_time,rc_service_end_time,"
			+ "DATE_FORMAT(rc_creation_time,'%Y/%m/%d %H:%i')AS rc_creation_time FROM roomservice_category ";
	private final String PARAMETER_ID = " rc_id = ? ";
	private final String PARAMETER_STATUS = " rc_status = ? ";
	private final String PARAMETER_REFERID = " rc_refer_id = ? AND rc_id = ? ";

	public List<RoomServiceCategoryResponse> select(final Connection conn, final int categoryId, final int referId,
			final int status) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceCategoryResponse> list = new ArrayList<RoomServiceCategoryResponse>();
		try {

			String description = "";

			int x = 0;

			if (categoryId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ID, description);
			}
			if (referId > 0) {
				description = formatWhereDescription(x++, PARAMETER_REFERID, description);
			}
			if (status > -1) {
				description = formatWhereDescription(x++, PARAMETER_STATUS, description);
			}
			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			int i = 0;
			if (categoryId > 0) {
				psmt.setInt(++i, categoryId);
			}
			if (referId > 0) {
				// rc_refer_id = rc_id 這樣只會列出最上一層的資料
				psmt.setInt(++i, referId);
				psmt.setInt(++i, referId);
			}
			if (status > -1) {
				psmt.setInt(++i, status);
			}

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceCategoryResponse(rs.getInt("rc_id"), rs.getInt("rc_status"),
						rs.getInt("rc_refer_id"), rs.getInt("rc_sequence"), rs.getString("rc_service_star_time"),
						rs.getString("rc_service_end_time"), rs.getString("rc_creation_time")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECTE_BY_REDERID = "SELECT rc_id,rc_status,rc_sequence,rc_refer_id,rc_service_star_time,rc_service_end_time,"
			+ "DATE_FORMAT(rc_creation_time,'%Y/%m/%d %H:%i')AS rc_creation_time FROM roomservice_category WHERE rc_refer_id = ? AND rc_id != "
			+ "rc_refer_id";

	public List<RoomServiceCategoryResponse> selectByReferId(final Connection conn, final int referId)
			throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceCategoryResponse> list = new ArrayList<RoomServiceCategoryResponse>();
		try {

			int i = 0;

			psmt = conn.prepareStatement(SELECTE_BY_REDERID);
			psmt.setInt(++i, referId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new RoomServiceCategoryResponse(rs.getInt("rc_id"), rs.getInt("rc_status"),
						rs.getInt("rc_refer_id"), rs.getInt("rc_sequence"), rs.getString("rc_service_star_time"),
						rs.getString("rc_service_end_time"), rs.getString("rc_creation_time")));
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
