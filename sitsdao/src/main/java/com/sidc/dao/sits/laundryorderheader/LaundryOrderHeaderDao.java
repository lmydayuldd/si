package com.sidc.dao.sits.laundryorderheader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.laundry.response.LaundryBackendOrderListResponse;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryOrderListResponse;

public class LaundryOrderHeaderDao {

	private static final class lazyHolder {
		public static LaundryOrderHeaderDao INSTANCE = new LaundryOrderHeaderDao();
	}

	public static LaundryOrderHeaderDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO laundry_order_header(loh_status,loh_bill_no,loh_room_no,loh_guest_no,"
			+ "loh_guest_first_name,loh_guest_last_name,loh_receive_time,loh_mobile_info_id,"
			+ "loh_class_id,loh_total_pieces,loh_sub_total,loh_service_charge,loh_total_amount,loh_memo,loh_modify_time,loh_creation_time)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW()); ";

	public int insert(final Connection conn, final String status, final String billNo, final String roomNo,
			final String guestNo, final String guestFirstName, final String guestLastName, final String receviceTime,
			final int classId, final float serviceCharge, final String memo, final int mobileId) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setString(++i, status);
			psmt.setString(++i, billNo);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, guestNo);
			psmt.setString(++i, guestFirstName);
			psmt.setString(++i, guestLastName);
			psmt.setString(++i, receviceTime);
			psmt.setInt(++i, mobileId);
			psmt.setInt(++i, classId);
			psmt.setInt(++i, 0);
			psmt.setInt(++i, 0);
			psmt.setFloat(++i, serviceCharge);
			psmt.setInt(++i, 0);
			psmt.setString(++i, memo);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("laundry_order_header insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String UPDATE = "UPDATE laundry_order_header SET loh_total_pieces = ?,loh_sub_total = ?"
			+ ",loh_total_amount = ?,loh_modify_time = NOW() WHERE loh_id = ?; ";

	public void update(final Connection conn, final int id, final int totalPieces, final float subTotal,
			final float totalAmount) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE);

			int i = 0;
			psmt.setInt(++i, totalPieces);
			psmt.setFloat(++i, subTotal);
			psmt.setFloat(++i, totalAmount);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_WITH_STATUS = "UPDATE laundry_order_header SET loh_status = ?,loh_modify_time = NOW() WHERE loh_id = ?; ";

	public void updateWithStatus(final Connection conn, final int id, final String status) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_WITH_STATUS);

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

	private final static String UPDATE_WITH_RECEIVE = "UPDATE laundry_order_header SET loh_status = '10',loh_receive_time = ?,"
			+ "loh_modify_time = NOW() " + "WHERE loh_id = ?; ";

	public void updateWithReceive(final Connection conn, final int id, final String receiveTime) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_WITH_RECEIVE);

			int i = 0;
			psmt.setString(++i, receiveTime);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_BY_ID = "SELECT loh_id FROM laundry_order_header WHERE loh_id = ? ;";

	public boolean isExist(final Connection conn, final int id) throws SQLException {

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

	private final static String SELECT_BY_PARAMETER = "SELECT loh_id,loh_status,loh_bill_no,loh_room_no,loh_class_id,"
			+ "loh_receive_time,loh_total_pieces,loh_sub_total,loh_service_charge,loh_total_amount,loh_memo,DATE_FORMAT"
			+ "(loh_creation_time,'%Y/%m/%d %H:%i')AS loh_creation_time,"
			+ "(SELECT lcl_name FROM laundry_class_lang WHERE lcl_laundry_class_id = loh_class_id AND lcl_lang = ? LIMIT 1) AS "
			+ "class_name FROM laundry_order_header ";
	private final String PARAMETER_ORDER_ID = " loh_id = ? ";
	private final String PARAMETER_STATUS = " loh_status = ? ";
	private final String PARAMETER_MOBILE_ID = " loh_mobile_info_id = ? ";
	private final String PARAMETER_ROOMNO = " loh_room_no = ? ";
	private final String PARAMETER_CLASS_ID = " loh_class_id = ? ";
	private final String PARAMETER_LESS_THAN_CREATIONTIME = " ? >= loh_creation_time ";
	private final String PARAMETER_MORE_THAN_CREATIONTIME = " loh_creation_time >= ? ";
	private final String PARAMETER_BETWEEN_CREATIONTIME = " loh_creation_time BETWEEN ? AND ? ";

	public List<LaundryOrderListResponse> select(final Connection conn, final int mobileId, final int orderId,
			final String status, final int classId, final String startTime, final String endTime, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryOrderListResponse> list = new ArrayList<LaundryOrderListResponse>();

		try {

			String description = "WHERE";
			int i = 0;
			int x = 0;

			description = formatWhereDescription(x++, PARAMETER_MOBILE_ID, description);

			if (orderId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ORDER_ID, description);
			}
			if (!StringUtils.isBlank(status)) {
				description = formatWhereDescription(x++, PARAMETER_STATUS, description);
			}
			if (classId > 0) {
				description = formatWhereDescription(x++, PARAMETER_CLASS_ID, description);
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

			psmt.setString(++i, langCode);
			psmt.setInt(++i, mobileId);

			if (orderId > 0) {
				psmt.setInt(++i, orderId);
			}
			if (!StringUtils.isBlank(status)) {
				psmt.setString(++i, status);
			}
			if (classId > 0) {
				psmt.setInt(++i, classId);
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
				list.add(new LaundryOrderListResponse(rs.getInt("loh_id"), rs.getString("loh_status"),
						rs.getString("loh_receive_time"), rs.getInt("loh_class_id"), rs.getString("class_name"),
						rs.getInt("loh_total_pieces"), rs.getFloat("loh_sub_total"), rs.getFloat("loh_service_charge"),
						rs.getFloat("loh_total_amount"), rs.getString("loh_memo"), rs.getString("loh_creation_time")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	public List<LaundryBackendOrderListResponse> select(final Connection conn, final int orderId, final String status,
			final String roomNo, final int classId, final String startTime, final String endTime, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<LaundryBackendOrderListResponse> list = new ArrayList<LaundryBackendOrderListResponse>();

		try {

			String description = "WHERE";
			int i = 0;
			int x = 0;

			if (orderId > 0) {
				description = formatWhereDescription(x++, PARAMETER_ORDER_ID, description);
			}
			if (!StringUtils.isBlank(status)) {
				description = formatWhereDescription(x++, PARAMETER_STATUS, description);
			}
			if (!StringUtils.isBlank(roomNo)) {
				description = formatWhereDescription(x++, PARAMETER_ROOMNO, description);
			}
			if (classId > 0) {
				description = formatWhereDescription(x++, PARAMETER_CLASS_ID, description);
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

			psmt.setString(++i, langCode);

			if (orderId > 0) {
				psmt.setInt(++i, orderId);
			}
			if (!StringUtils.isBlank(status)) {
				psmt.setString(++i, status);
			}
			if (!StringUtils.isBlank(roomNo)) {
				psmt.setString(++i, roomNo);
			}
			if (classId > 0) {
				psmt.setInt(++i, classId);
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
				list.add(new LaundryBackendOrderListResponse(rs.getInt("loh_id"), rs.getString("loh_status"),
						rs.getString("loh_room_no"), rs.getString("loh_receive_time"), rs.getInt("loh_class_id"),
						rs.getString("class_name"), rs.getInt("loh_total_pieces"), rs.getFloat("loh_sub_total"),
						rs.getFloat("loh_service_charge"), rs.getFloat("loh_total_amount"), rs.getString("loh_memo"),
						rs.getString("loh_creation_time")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_STATUS = "SELECT loh_status FROM laundry_order_header WHERE loh_id = ?;";

	public String selectStatus(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String status = null;
		try {
			psmt = conn.prepareStatement(SELECT_STATUS);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				status = rs.getString("loh_status");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return status;
	}

	private final static String SELECT_BILLNO = "SELECT loh_bill_no FROM laundry_order_header WHERE loh_id = ?;";

	public String findBillNo(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String billNo = null;
		try {
			psmt = conn.prepareStatement(SELECT_BILLNO);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				billNo = rs.getString("loh_bill_no");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return billNo;
	}

	private final static String SELECT_ROOMNO = "SELECT loh_room_no FROM laundry_order_header WHERE loh_id = ?;";

	public String findRoomNo(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String roomNo = null;
		try {
			psmt = conn.prepareStatement(SELECT_ROOMNO);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				roomNo = rs.getString("loh_room_no");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return roomNo;
	}

	private final static String SELECT_MOBILEID = "SELECT loh_mobile_info_id FROM laundry_order_header WHERE loh_id = ?;";

	public int findMobileId(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		int mobileId = -999;
		try {
			psmt = conn.prepareStatement(SELECT_MOBILEID);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				mobileId = rs.getInt("loh_mobile_info_id");
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
