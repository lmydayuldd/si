package com.sidc.dao.sits.activityheader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityBean;

public class ActivityHeaderDao {

	private static final class lazyHolder {
		public static ActivityHeaderDao INSTANCE = new ActivityHeaderDao();
	}

	public static ActivityHeaderDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO activity_header(ah_type,ah_repeat_type,ah_lower_limit,ah_upper_limit,ah_status,ah_target,"
			+ "ah_registration_start_time,ah_registration_end_time,ah_charge,ah_memo,ah_repeat_frequency,ah_creation_time) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,NOW());";

	public int insert(final Connection conn, final int typeId, final String repeatType, final int lowerLimit,
			final int upperLimit, final int status, final int target, final String registrationStartTime,
			final String registrationEndTime, final int isCharge, final String memo, final String repeatFrequent)
			throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, typeId);
			psmt.setString(++i, repeatType);
			psmt.setInt(++i, lowerLimit);
			psmt.setInt(++i, upperLimit);
			psmt.setInt(++i, status);
			psmt.setInt(++i, target);
			psmt.setString(++i, registrationStartTime);
			psmt.setString(++i, registrationEndTime);
			psmt.setInt(++i, isCharge);
			psmt.setString(++i, memo);
			psmt.setString(++i, repeatFrequent);
			psmt.executeUpdate();
			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("activity_header insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return id;
	}

	private final static String SELECT = "SELECT ah_id, ah_type,ah_repeat_type,ah_lower_limit,ah_upper_limit,ah_status,ah_target,"
			+ "DATE_FORMAT(ah_registration_start_time,'%Y/%m/%d %H:%i')AS ah_registration_start_time,"
			+ "DATE_FORMAT(ah_registration_end_time,'%Y/%m/%d %H:%i')AS ah_registration_end_time,"
			+ "ah_charge,ah_repeat_frequency,ah_memo FROM activity_header ";
	private final String PARAMETER_ID = " ah_id = ? ";
	private final String PARAMETER_TYPE = " ah_type = ? ";
	private final String PARAMETER_REPEAT_TYPE = " ah_repeat_type = ? ";
	private final String PARAMETER_STATUS = " ah_status = ? ";
	private final String PARAMETER_TARGET = " ah_target = ? ";
	private final String PARAMETER_CHARGE = " ah_charge = ? ";

	public List<ActivityBean> select(final Connection conn, final int actividyId, final int type,
			final String repeatType, final int status, final int target, final int charge) throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityBean> list = new ArrayList<ActivityBean>();
		try {
			String description = "WHERE";
			int i = 0;
			int x = 0;

			if (actividyId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ID, description);
			}
			if (type > 0) {
				description = formatWhereDescription(x++, PARAMETER_TYPE, description);
			}
			if (!StringUtils.isBlank(repeatType)) {
				description = formatWhereDescription(x++, PARAMETER_REPEAT_TYPE, description);
			}
			if (status >= 0) {
				description = formatWhereDescription(x++, PARAMETER_STATUS, description);
			}
			if (target >= 0) {
				description = formatWhereDescription(x++, PARAMETER_TARGET, description);
			}
			if (charge >= 0) {
				description = formatWhereDescription(x++, PARAMETER_CHARGE, description);
			}

			if (description.equals("WHERE")) {
				description = "";
			}

			psmt = conn.prepareStatement(SELECT + description);

			if (actividyId > 0) {
				psmt.setInt(++i, actividyId);
			}
			if (type > 0) {
				psmt.setInt(++i, type);
			}
			if (!StringUtils.isBlank(repeatType)) {
				psmt.setString(++i, repeatType);
			}
			if (status >= 0) {
				psmt.setInt(++i, status);
			}
			if (target >= 0) {
				psmt.setInt(++i, target);
			}
			if (charge >= 0) {
				psmt.setInt(++i, charge);
			}

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityBean(rs.getInt("ah_id"), rs.getInt("ah_type"), rs.getInt("ah_status"),
						rs.getString("ah_repeat_type"), rs.getInt("ah_lower_limit"), rs.getInt("ah_upper_limit"),
						rs.getInt("ah_target"), rs.getString("ah_registration_start_time"),
						rs.getString("ah_registration_end_time"), rs.getInt("ah_charge"),
						rs.getString("ah_repeat_frequency"), rs.getString("ah_memo")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

	private final static String UPDATE = "UPDATE activity_header SET ah_type =?,ah_repeat_type=?,ah_lower_limit=?,ah_upper_limit=?,ah_status=?,"
			+ "ah_target=?,ah_registration_start_time=?,ah_registration_end_time=?,ah_charge=?,ah_memo=?,ah_repeat_frequency=? "
			+ "WHERE ah_id = ?;";

	public void update(final Connection conn, final int id, final int typeId, final String repeatType,
			final int lowerLimit, final int upperLimit, final int status, final int target,
			final String registrationStartTime, final String registrationEndTime, final int isCharge, final String memo,
			final String repeatFrequentJson) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, typeId);
			psmt.setString(++i, repeatType);
			psmt.setInt(++i, lowerLimit);
			psmt.setInt(++i, upperLimit);
			psmt.setInt(++i, status);
			psmt.setInt(++i, target);
			psmt.setString(++i, registrationStartTime);
			psmt.setString(++i, registrationEndTime);
			psmt.setInt(++i, isCharge);
			psmt.setString(++i, memo);
			psmt.setString(++i, repeatFrequentJson);
			psmt.setInt(++i, id);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_ID = "SELECT ah_id FROM activity_header WHERE ah_id = ?;";

	public boolean selectById(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);

			int i = 0;
			psmt.setInt(++i, id);

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

	private final static String SELECT_UPPER_LIMIT = "SELECT ah_upper_limit FROM activity_header WHERE ah_id = ? AND ah_upper_limit >= (SELECT "
			+ "CASE WHEN SUM(aoh_qty) IS NULL THEN 0 ELSE SUM(aoh_qty) END + ? FROM activity_order_header WHERE aoh_activity_header_id = "
			+ "ah_id AND aoh_activity_repeat_id = ? AND aoh_status >= 0);";

	public boolean isOverLimit(final Connection conn, final int id, final int repeatId, final int count)
			throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = true;
		try {
			psmt = conn.prepareStatement(SELECT_UPPER_LIMIT);

			int i = 0;
			psmt.setInt(++i, id);
			psmt.setInt(++i, count);
			psmt.setInt(++i, repeatId);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isPass = false;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String SELECT_CHARGE = "SELECT ah_id FROM activity_header WHERE ah_id = ? AND ah_charge = 1; ";

	public boolean isCharge(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(SELECT_CHARGE);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				isPass = true;
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return isPass;
	}

	private final static String SELECT_REPEAT_FREQUENT = "SELECT ah_repeat_frequency FROM activity_header WHERE ah_id = ?;";

	public String selectRepeatFrequent(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String repeatFrequent = null;
		try {
			psmt = conn.prepareStatement(SELECT_REPEAT_FREQUENT);

			int i = 0;
			psmt.setInt(++i, id);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				repeatFrequent = rs.getString("ah_repeat_frequency");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return repeatFrequent;
	}

	private final static String SELECT_REPEAT_TPYE = "SELECT ah_repeat_type FROM activity_header WHERE ah_id = ?;";

	public String selectRepeatType(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String repeatType = null;
		try {
			psmt = conn.prepareStatement(SELECT_REPEAT_TPYE);

			int i = 0;
			psmt.setInt(++i, id);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				repeatType = rs.getString("ah_repeat_type");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return repeatType;
	}

	private String formatWhereDescription(int i, final String sqlWhereDescription, String description) {
		if (i == 0) {
			description += sqlWhereDescription;
		} else {
			description += "AND" + sqlWhereDescription;
		}
		return description;
	}

}
