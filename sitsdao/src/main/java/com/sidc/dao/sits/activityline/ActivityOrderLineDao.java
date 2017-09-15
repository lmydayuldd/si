package com.sidc.dao.sits.activityline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivitySignUpDetailBean;

public class ActivityOrderLineDao {

	private static final class lazyHolder {
		public static ActivityOrderLineDao INSTANCE = new ActivityOrderLineDao();
	}

	public static ActivityOrderLineDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO activity_order_line(aol_activity_order_header_id,aol_first_name,aol_last_name,"
			+ "aol_sex,aol_identity_type,aol_identity,aol_passport,aol_phone,aol_guestno,aol_email,aol_activity_fee_id,aol_modify_time,"
			+ "aol_creation_time) VALUES(?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW());";

	public int insert(final Connection conn, final int orderId, final String firstName, final String lastName,
			final int sex, final String identity, final String identityType, final String passport, final String phone,
			final String guestNo, final String email, final int feedId) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setInt(++i, orderId);
			psmt.setString(++i, firstName);
			psmt.setString(++i, lastName);
			psmt.setInt(++i, sex);
			psmt.setString(++i, identity);
			psmt.setString(++i, identityType);
			psmt.setString(++i, passport);
			psmt.setString(++i, phone);
			psmt.setString(++i, guestNo);
			psmt.setString(++i, email);
			psmt.setInt(++i, feedId);
			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("activity_order_line insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT = "SELECT aol_id,aol_activity_order_header_id, aol_first_name,aol_last_name,aol_sex,aol_identity_type,"
			+ "aol_identity,aol_passport,aol_phone,aol_guestno,aol_email,aol_activity_fee_id ,(SELECT afl_name FROM activity_fee_lang WHERE "
			+ "afl_lang = ? AND afl_activity_fee_id = aol_activity_fee_id LIMIT 1) AS fee_name "
			+ "FROM activity_order_line WHERE aol_activity_order_header_id = ? ";

	public List<ActivitySignUpDetailBean> select(final Connection conn, final int activityId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ActivitySignUpDetailBean> list = new ArrayList<ActivitySignUpDetailBean>();

		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setInt(++i, activityId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivitySignUpDetailBean(rs.getInt("aol_id"), rs.getInt("aol_activity_order_header_id"),
						rs.getString("aol_first_name"), rs.getString("aol_last_name"), rs.getInt("aol_sex"),
						rs.getString("aol_identity_type"), rs.getString("aol_identity"), rs.getString("aol_passport"),
						rs.getString("aol_phone"), rs.getString("aol_guestno"), rs.getString("aol_email"),
						rs.getInt("aol_activity_fee_id"), rs.getString("fee_name")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String IS_EXIST_IDENTITY = "SELECT aol_id FROM activity_order_line WHERE aol_activity_order_header_id IN (SELECT aoh_id"
			+ " FROM activity_order_header WHERE aoh_activity_header_id = ? AND aoh_activity_repeat_id = ?) AND aol_identity = ?";

	public boolean isExistOfIdentity(final Connection conn, final int activityId, final int repeatId,
			final String identity) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST_IDENTITY);

			int i = 0;
			psmt.setInt(++i, activityId);
			psmt.setInt(++i, repeatId);
			psmt.setString(++i, identity);

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

	private final static String IS_EXIST_PASSPORT = "SELECT aol_id FROM activity_order_line WHERE aol_activity_order_header_id IN (SELECT aoh_id"
			+ " FROM activity_order_header WHERE aoh_activity_header_id = ? AND aoh_activity_repeat_id = ?) AND aol_passport = ?";

	public boolean isExistOfPassport(final Connection conn, final int activityId, final int repeatId,
			final String passport) throws SQLException {

		PreparedStatement psmt = null;
		boolean isPass = false;
		try {
			psmt = conn.prepareStatement(IS_EXIST_PASSPORT);

			int i = 0;
			psmt.setInt(++i, activityId);
			psmt.setInt(++i, repeatId);
			psmt.setString(++i, passport);

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

	private final static String SELECT_BY_PARAMETER = "SELECT DISTINCT aol_activity_order_header_id FROM activity_order_line ";
	private final String PARAMETER_FEE_ID = " aol_activity_fee_id IN ";
	private final String PARAMETER_IDENTITY_TYPE_ID = " aol_identity_type IN ";

	public List<Integer> selectOrderIdByWhereIn(final Connection conn, final String feeIdWhererInStr,
			final String identityTypeWhereInStr) throws SQLException {

		PreparedStatement psmt = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			String description = "WHERE";
			int x = 0;

			/**
			 * 不用 setArray 因為 sits MySQL版本太舊... 會出現
			 * SQLFeatureNotSupportedException, MySQL version 5.0 ....
			 * 2017/08/07
			 */

			if (!StringUtils.isBlank(feeIdWhererInStr)) {
				description = formatWhereDescription(x++, PARAMETER_FEE_ID + "(" + feeIdWhererInStr + ")", description);
			}

			if (!StringUtils.isBlank(identityTypeWhereInStr)) {
				description = formatWhereDescription(x++,
						PARAMETER_IDENTITY_TYPE_ID + "(" + identityTypeWhereInStr + ")", description);
			}

			if (description.equals("WHERE")) {
				description = "";
			}

			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt("aol_activity_order_header_id"));
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
			description += sqlWhereDescription;
		} else {
			description += "AND" + sqlWhereDescription;
		}
		return description;
	}

}
