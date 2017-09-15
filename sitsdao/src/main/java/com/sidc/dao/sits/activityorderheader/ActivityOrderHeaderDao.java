package com.sidc.dao.sits.activityorderheader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.response.ActivityOrderListResponse;

public class ActivityOrderHeaderDao {

	private static final class lazyHolder {
		public static ActivityOrderHeaderDao INSTANCE = new ActivityOrderHeaderDao();
	}

	public static ActivityOrderHeaderDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO activity_order_header(aoh_status,aoh_activity_header_id,aoh_activity_repeat_id,"
			+ "aoh_payment_status,aoh_amount,aoh_qty,aoh_bill_no,aoh_room_no,aoh_mobile_info_id,aoh_activity_date,aoh_memo,aoh_modify_time,"
			+ "aoh_creation_time) VALUES (?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW());";

	public int insert(final Connection conn, final int activityId, final int repeatId, final String paymentStatus,
			final String billNo, final String roomNo, final int mobileInfoId, final String activityDate,
			final String memo) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setString(++i, "0");
			psmt.setInt(++i, activityId);
			psmt.setInt(++i, repeatId);
			psmt.setString(++i, paymentStatus);
			psmt.setFloat(++i, 0);
			psmt.setInt(++i, 0);
			psmt.setString(++i, billNo);
			psmt.setString(++i, roomNo);
			psmt.setInt(++i, mobileInfoId);
			psmt.setString(++i, activityDate);
			psmt.setString(++i, memo);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("activity_order_header insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE_AMOUNT_QTY = "UPDATE activity_order_header SET aoh_amount = ?,aoh_qty = ? ,"
			+ "aoh_modify_time = NOW() WHERE aoh_id = ?;";

	public void updateAmountQty(final Connection conn, final int id, final float amount, final int qty)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_AMOUNT_QTY);

			int i = 0;

			psmt.setFloat(++i, amount);
			psmt.setInt(++i, qty);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_STATUS = "UPDATE activity_order_header SET aoh_status = ?,"
			+ "aoh_modify_time = NOW() WHERE aoh_id = ?;";

	public void updateStatus(final Connection conn, final int id, final String status) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_STATUS);

			int i = 0;

			psmt.setString(++i, status);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_PAYMENTSTATUS = "UPDATE activity_order_header SET aoh_payment_status = ?,"
			+ "aoh_modify_time = NOW() WHERE aoh_id = ?;";

	public void updatePaymentStatus(final Connection conn, final int id, final String paymentStatus)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_PAYMENTSTATUS);

			int i = 0;

			psmt.setString(++i, paymentStatus);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT = "SELECT aoh_id,aoh_activity_header_id,aoh_status,aoh_payment_status,aoh_amount,aoh_qty,aoh_room_no,"
			+ "aoh_activity_repeat_id,aoh_memo," + "DATE_FORMAT(ar_start_time,'%Y/%m/%d %H:%i')AS ar_start_time,"
			+ "DATE_FORMAT(ar_end_time,'%Y/%m/%d %H:%i')AS ar_end_time,"
			+ "DATE_FORMAT(aoh_activity_date,'%Y/%m/%d')AS aoh_activity_date, "
			+ "DATE_FORMAT(aoh_creation_time,'%Y/%m/%d %H:%i')AS aoh_creation_time "
			+ "FROM activity_order_header LEFT JOIN activity_repeat ON aoh_activity_header_id = "
			+ "ar_activity_id AND aoh_activity_repeat_id = ar_id ";
	private final String PARAMETER_ORDER_ID = " aoh_id = ? ";
	private final String PARAMETER_WHEREIN_ORDER_ID = " aoh_id IN ";
	private final String PARAMETER_ACTIVITY_ID = " aoh_activity_header_id = ? ";
	private final String PARAMETER_ACTIVITY_REPEAT_ID = " aoh_activity_repeat_id = ? ";
	private final String PARAMETER_ROOM_NO = " aoh_room_no = ? ";
	private final String PARAMETER_MOBILE_ID = " aoh_mobile_info_id = ? ";
	private final String PARAMETER_PAYMENT_STATUS = " aoh_payment_status = ? ";
	private final String PARAMETER_STATUS = " aoh_status = ? ";
	private final String PARAMETER_LESS_THAN_CREATIONTIME = " ? >= aoh_creation_time ";
	private final String PARAMETER_MORE_THAN_CREATIONTIME = " aoh_creation_time >= ? ";
	private final String PARAMETER_BETWEEN_CREATIONTIME = " aoh_creation_time BETWEEN ? AND ? ";

	public List<ActivityOrderListResponse> select(final Connection conn, final int orderId, final int activityId,
			final String status, final String paymentStatus, final String startTime, final String endTime,
			final int mobileId, final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityOrderListResponse> list = new ArrayList<ActivityOrderListResponse>();
		try {
			String description = "";
			int x = 0;

			description = formatWhereDescription(x++, PARAMETER_MOBILE_ID, description);

			if (orderId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ORDER_ID, description);
			}
			if (activityId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ACTIVITY_ID, description);
			}
			if (!StringUtils.isBlank(status)) {
				description = formatWhereDescription(x++, PARAMETER_STATUS, description);
			}
			if (!StringUtils.isBlank(paymentStatus)) {
				description = formatWhereDescription(x++, PARAMETER_PAYMENT_STATUS, description);
			}

			if (!StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime)) {
				description = formatWhereDescription(x++, PARAMETER_BETWEEN_CREATIONTIME, description);
			} else if (!StringUtils.isBlank(startTime)) {
				description = formatWhereDescription(x++, PARAMETER_MORE_THAN_CREATIONTIME, description);
			} else if (!StringUtils.isBlank(endTime)) {
				description = formatWhereDescription(x++, PARAMETER_LESS_THAN_CREATIONTIME, description);
			}

			psmt = conn.prepareStatement(SELECT + description);

			int i = 0;

			psmt.setInt(++i, mobileId);
			if (orderId > 0) {
				psmt.setInt(++i, orderId);
			}
			if (activityId > 0) {
				psmt.setInt(++i, activityId);
			}
			if (!StringUtils.isBlank(status)) {
				psmt.setString(++i, status);
			}
			if (!StringUtils.isBlank(paymentStatus)) {
				psmt.setString(++i, paymentStatus);
			}

			if (!StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime)) {
				description = formatWhereDescription(x++, PARAMETER_BETWEEN_CREATIONTIME, description);
				psmt.setString(++i, startTime);
				psmt.setString(++i, endTime);
			} else if (!StringUtils.isBlank(startTime)) {
				psmt.setString(++i, startTime);
			} else if (!StringUtils.isBlank(endTime)) {
				psmt.setString(++i, endTime);
			}

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new ActivityOrderListResponse(rs.getInt("aoh_id"), rs.getInt("aoh_activity_header_id"),
						rs.getInt("aoh_activity_repeat_id"), rs.getString("aoh_status"),
						rs.getString("aoh_payment_status"), rs.getFloat("aoh_amount"), rs.getInt("aoh_qty"),
						rs.getString("aoh_room_no"), rs.getString("aoh_memo"), rs.getString("ar_start_time"),
						rs.getString("ar_end_time"), rs.getString("aoh_activity_date"),
						rs.getString("aoh_creation_time")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	public List<ActivityOrderListResponse> select(final Connection conn, final int orderId,
			final String orderIdWhereInStr, final int activityId, final int activityRepeatId, final String status,
			final String paymentStatus, final String roomNo, final String startTime, final String endTime,
			final String langCode) throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityOrderListResponse> list = new ArrayList<ActivityOrderListResponse>();
		try {
			String description = "";
			int x = 0;

			if (orderId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ORDER_ID, description);
			}
			if (!StringUtils.isBlank(orderIdWhereInStr)) {

				/**
				 * 不用 setArray 因為 sits MySQL版本太舊... 會出現
				 * SQLFeatureNotSupportedException, MySQL version 5.0 ....
				 * 2017/08/07
				 */
				description = formatWhereDescription(x++, PARAMETER_WHEREIN_ORDER_ID + "(" + orderIdWhereInStr + ")",
						description);
			}
			if (activityId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ACTIVITY_ID, description);
			}
			if (activityRepeatId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ACTIVITY_REPEAT_ID, description);
			}
			if (!StringUtils.isBlank(status)) {
				description = formatWhereDescription(x++, PARAMETER_STATUS, description);
			}
			if (!StringUtils.isBlank(paymentStatus)) {
				description = formatWhereDescription(x++, PARAMETER_PAYMENT_STATUS, description);
			}
			if (!StringUtils.isBlank(roomNo)) {
				description = formatWhereDescription(x++, PARAMETER_ROOM_NO, description);
			}

			if (!StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime)) {
				description = formatWhereDescription(x++, PARAMETER_BETWEEN_CREATIONTIME, description);
			} else if (!StringUtils.isBlank(startTime)) {
				description = formatWhereDescription(x++, PARAMETER_MORE_THAN_CREATIONTIME, description);
			} else if (!StringUtils.isBlank(endTime)) {
				description = formatWhereDescription(x++, PARAMETER_LESS_THAN_CREATIONTIME, description);
			}

			if (description.equals("")) {
				psmt = conn.prepareStatement(SELECT);
			} else {
				psmt = conn.prepareStatement(SELECT + description);
			}

			int i = 0;

			if (orderId > 0) {
				psmt.setInt(++i, orderId);
			}
			if (activityId > 0) {
				psmt.setInt(++i, activityId);
			}
			if (activityRepeatId > 0) {
				psmt.setInt(++i, activityRepeatId);
			}
			if (!StringUtils.isBlank(status)) {
				psmt.setString(++i, status);
			}
			if (!StringUtils.isBlank(paymentStatus)) {
				psmt.setString(++i, paymentStatus);
			}
			if (!StringUtils.isBlank(roomNo)) {
				psmt.setString(++i, roomNo);
			}
			if (!StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime)) {
				description = formatWhereDescription(x++, PARAMETER_BETWEEN_CREATIONTIME, description);
				psmt.setString(++i, startTime);
				psmt.setString(++i, endTime);
			} else if (!StringUtils.isBlank(startTime)) {
				psmt.setString(++i, startTime);
			} else if (!StringUtils.isBlank(endTime)) {
				psmt.setString(++i, endTime);
			}

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityOrderListResponse(rs.getInt("aoh_id"), rs.getInt("aoh_activity_header_id"),
						rs.getInt("aoh_activity_repeat_id"), rs.getString("aoh_status"),
						rs.getString("aoh_payment_status"), rs.getFloat("aoh_amount"), rs.getInt("aoh_qty"),
						rs.getString("aoh_room_no"), rs.getString("aoh_memo"), rs.getString("ar_start_time"),
						rs.getString("ar_end_time"), rs.getString("aoh_activity_date"),
						rs.getString("aoh_creation_time")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_ID = "SELECT aoh_id FROM activity_order_header WHERE aoh_id = ?;";

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

	private final static String SELECT_STATUS = "SELECT aoh_status FROM activity_order_header WHERE aoh_id = ?;";

	public String selectStatusById(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String status = null;
		try {
			psmt = conn.prepareStatement(SELECT_STATUS);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				status = rs.getString("aoh_status");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return status;
	}

	private final static String SELECT_MOBILEID = "SELECT aoh_mobile_info_id FROM activity_order_header WHERE aoh_id = ?;";

	public int findMobileId(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		int mobileId = -999;
		try {
			psmt = conn.prepareStatement(SELECT_MOBILEID);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				mobileId = rs.getInt("aoh_mobile_info_id");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return mobileId;
	}

	private String formatWhereDescription(int i, final String sqlWhereDescription, String description) {
		if (i == 0) {
			description += "WHERE " + sqlWhereDescription;
		} else {
			description += "AND" + sqlWhereDescription;
		}
		return description;
	}
}
