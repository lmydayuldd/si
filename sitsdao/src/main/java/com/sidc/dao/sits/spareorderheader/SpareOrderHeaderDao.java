package com.sidc.dao.sits.spareorderheader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.spare.response.SpareBackendOrderResponse;
import com.sidc.blackcore.api.sits.spare.response.SpareOrderResponse;

public class SpareOrderHeaderDao {
	/**
	 * @author Joe
	 */
	private SpareOrderHeaderDao() {
	}

	private static class LazyHolder {
		public static final SpareOrderHeaderDao INSTANCE = new SpareOrderHeaderDao();
	}

	public static SpareOrderHeaderDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO spare_order_header (soh_docno,soh_status,soh_qty,soh_total_amount,soh_memo,"
			+ "soh_room_no,soh_bill_no,soh_guest_no,soh_guest_first_name,soh_guest_last_name,soh_mobile_info_id,soh_modify_time,"
			+ "soh_creation_time) VALUES ('0','0',?,?,?,?,?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int qty, final float amount, final String memo, final String roomNo,
			final String guestNo, final String guestFirstName, final String guestLastName, final String billNo,
			final int mobileId) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, qty);
			psmt.setFloat(++i, amount);
			psmt.setString(++i, memo);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, billNo);
			psmt.setString(++i, guestNo);
			psmt.setString(++i, guestFirstName);
			psmt.setString(++i, guestLastName);
			psmt.setInt(++i, mobileId);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("spare_order_header insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE_AMOUNT = "UPDATE spare_order_header SET soh_total_amount = ?,"
			+ "soh_modify_time = NOW() WHERE soh_id = ?;";

	public void updateAmount(final Connection conn, final int id, final float totalAmount) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_AMOUNT);

			int i = 0;

			psmt.setFloat(++i, totalAmount);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_STATUS = "UPDATE spare_order_header SET soh_status = ?,soh_modify_time = NOW() WHERE soh_id = ?;";

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

	private final static String IS_EXIST = "SELECT soh_id FROM spare_order_header WHERE soh_id = ?;";

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

	private final static String SELECT_BILLNO = "SELECT soh_bill_no FROM spare_order_header WHERE soh_id = ?;";

	public String findBillNo(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String billNo = null;
		try {
			psmt = conn.prepareStatement(SELECT_BILLNO);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				billNo = rs.getString("soh_bill_no");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return billNo;
	}

	private final static String SELECT_ROOMNO = "SELECT soh_room_no FROM spare_order_header WHERE soh_id = ?;";

	public String findRoomNo(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String roomNo = null;
		try {
			psmt = conn.prepareStatement(SELECT_ROOMNO);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				roomNo = rs.getString("soh_room_no");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return roomNo;
	}

	private final static String SELECT_GUESTNO = "SELECT soh_guest_no FROM spare_order_header WHERE soh_id = ?;";

	public String findGuestNo(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String guestNo = null;
		try {
			psmt = conn.prepareStatement(SELECT_GUESTNO);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				guestNo = rs.getString("soh_guest_no");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return guestNo;
	}

	private final static String SELECT_STATUS = "SELECT soh_status FROM spare_order_header WHERE soh_id = ?;";

	public String selectStatus(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String status = null;
		try {
			psmt = conn.prepareStatement(SELECT_STATUS);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				status = rs.getString("soh_status");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return status;
	}

	private final static String SELECT_BY_PARAMETER = "SELECT soh_id,soh_status,soh_qty,soh_total_amount,soh_room_no,soh_memo,"
			+ "soh_guest_first_name,soh_guest_last_name,DATE_FORMAT(soh_creation_time,'%Y/%m/%d %H:%i')AS soh_creation_time FROM "
			+ "spare_order_header ";
	private final String PARAMETER_ORDER_ID = " soh_id = ? ";
	private final String PARAMETER_WHEREIN_ORDER_ID = " soh_id IN ";
	private final String PARAMETER_MOBILE_ID = " soh_mobile_info_id = ? ";
	private final String PARAMETER_ROOMNO = " soh_room_no = ? ";
	private final String PARAMETER_STATUS = " soh_status = ? ";
	private final String PARAMETER_LESS_THAN_CREATIONTIME = " ? >= soh_creation_time ";
	private final String PARAMETER_MORE_THAN_CREATIONTIME = " soh_creation_time >= ? ";
	private final String PARAMETER_BETWEEN_CREATIONTIME = " soh_creation_time BETWEEN ? AND ? ";

	public List<SpareOrderResponse> select(final Connection conn, final int mobileId, final int orderId,
			final String status, final String startTime, final String endTime) throws SQLException {

		PreparedStatement psmt = null;
		List<SpareOrderResponse> list = new ArrayList<SpareOrderResponse>();
		try {
			String description = "WHERE";
			int i = 0;
			int x = 0;

			if (!StringUtils.isBlank(status)) {
				description = formatWhereDescription(x++, PARAMETER_STATUS, description);
			}
			if (orderId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ORDER_ID, description);
			}

			description = formatWhereDescription(x++, PARAMETER_MOBILE_ID, description);

			if (!StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime)) {
				description = formatWhereDescription(x++, PARAMETER_BETWEEN_CREATIONTIME, description);
			} else if (!StringUtils.isBlank(startTime)) {
				description = formatWhereDescription(x++, PARAMETER_MORE_THAN_CREATIONTIME, description);
			} else if (!StringUtils.isBlank(endTime)) {
				description = formatWhereDescription(x++, PARAMETER_LESS_THAN_CREATIONTIME, description);
			}

			if (description.equals("WHERE")) {
				description = "";
			}

			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			if (!StringUtils.isBlank(status)) {
				psmt.setString(++i, status);
			}
			if (orderId > 0) {
				psmt.setInt(++i, orderId);
			}

			psmt.setInt(++i, mobileId);

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
				list.add(new SpareOrderResponse(rs.getInt("soh_id"), rs.getString("soh_status"),
						rs.getFloat("soh_total_amount"), rs.getInt("soh_qty"), rs.getString("soh_creation_time")));
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	public List<SpareBackendOrderResponse> select(final Connection conn, final int orderId,
			final String orderIdWhereInStr, final String roomNo, final String status, final String startTime,
			final String endTime) throws SQLException {

		PreparedStatement psmt = null;
		List<SpareBackendOrderResponse> list = new ArrayList<SpareBackendOrderResponse>();
		try {
			String description = "WHERE";
			int i = 0;
			int x = 0;

			if (!StringUtils.isBlank(status)) {
				description = formatWhereDescription(x++, PARAMETER_STATUS, description);
			}
			if (!StringUtils.isBlank(roomNo)) {
				description = formatWhereDescription(x++, PARAMETER_ROOMNO, description);
			}
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
			if (!StringUtils.isBlank(startTime) && !StringUtils.isBlank(endTime)) {
				description = formatWhereDescription(x++, PARAMETER_BETWEEN_CREATIONTIME, description);
			} else if (!StringUtils.isBlank(startTime)) {
				description = formatWhereDescription(x++, PARAMETER_MORE_THAN_CREATIONTIME, description);
			} else if (!StringUtils.isBlank(endTime)) {
				description = formatWhereDescription(x++, PARAMETER_LESS_THAN_CREATIONTIME, description);
			}

			if (description.equals("WHERE")) {
				description = "";
			}

			psmt = conn.prepareStatement(SELECT_BY_PARAMETER + description);

			if (!StringUtils.isBlank(status)) {
				psmt.setString(++i, status);
			}
			if (!StringUtils.isBlank(roomNo)) {
				psmt.setString(++i, roomNo);
			}
			if (orderId > 0) {
				psmt.setInt(++i, orderId);
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
				list.add(new SpareBackendOrderResponse(rs.getInt("soh_id"), rs.getString("soh_room_no"),
						rs.getString("soh_status"), rs.getFloat("soh_total_amount"), rs.getInt("soh_qty"),
						rs.getString("soh_memo"), rs.getString("soh_guest_first_name"),
						rs.getString("soh_guest_last_name"), rs.getString("soh_creation_time")));
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_MOBILEID = "SELECT soh_mobile_info_id FROM spare_order_header WHERE soh_id = ?;";

	public int findMobileId(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		int mobileId = -999;
		try {
			psmt = conn.prepareStatement(SELECT_MOBILEID);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				mobileId = rs.getInt("soh_mobile_info_id");
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
			description += sqlWhereDescription;
		} else {
			description += "AND" + sqlWhereDescription;
		}
		return description;
	}

}
