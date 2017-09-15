package com.sidc.dao.sits.roomserviceorderheader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceBackendOrderInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderHeaderInfoBean;

public class RoomServiceOrderHeaderDao {
	/**
	 * @author Joe
	 */
	private RoomServiceOrderHeaderDao() {
	}

	private static class LazyHolder {
		public static final RoomServiceOrderHeaderDao INSTANCE = new RoomServiceOrderHeaderDao();
	}

	public static RoomServiceOrderHeaderDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO roomservice_order_header (roh_status,roh_amount,roh_qty,roh_service_charge,"
			+ "roh_total_amount,roh_expected_time,roh_memo,roh_room_no,roh_guest_no,roh_guest_first_name,roh_guest_last_name,"
			+ "roh_bill_no,roh_mobile_info_id,roh_modify_time,roh_creation_time) VALUES ('0',0,?,?,0,?,?,?,?,?,?,?,?,NOW(),NOW())";

	public int insert(final Connection conn, final int qty, final float serviceCharge, final String expectedTime,
			final String memo, final String roomNo, final String guestNo, final String guestFirstName,
			final String guestLastName, final String billNo, final int mobileId) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int i = 0;
			psmt.setInt(++i, qty);
			psmt.setFloat(++i, serviceCharge);
			psmt.setString(++i, expectedTime);
			psmt.setString(++i, memo);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, guestNo);
			psmt.setString(++i, guestFirstName);
			psmt.setString(++i, guestLastName);
			psmt.setString(++i, billNo);
			psmt.setInt(++i, mobileId);

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

	private final static String UPDATE_AMOUNT = "UPDATE roomservice_order_header SET roh_amount = ?,roh_total_amount = ? ,"
			+ "roh_modify_time = NOW() WHERE roh_id = ?;";

	public void updateAmount(final Connection conn, final int id, final float amount, final float totalAmount)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_AMOUNT);

			int i = 0;

			psmt.setFloat(++i, amount);
			psmt.setFloat(++i, totalAmount);
			psmt.setInt(++i, id);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_STATUS = "UPDATE roomservice_order_header SET roh_status = ?,roh_modify_time = NOW() WHERE roh_id = ?;";

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

	private final static String IS_EXIST = "SELECT roh_id FROM roomservice_order_header WHERE roh_id = ?;";

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

	private final static String SELECT_STATUS = "SELECT roh_status FROM roomservice_order_header WHERE roh_id = ?;";

	public String selectStatus(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String status = null;
		try {
			psmt = conn.prepareStatement(SELECT_STATUS);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				status = rs.getString("roh_status");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return status;
	}

	private final static String SELECT_BY_PARAMETER = "SELECT roh_id,roh_room_no,roh_status,roh_qty,roh_total_amount,roh_memo,"
			+ "DATE_FORMAT(roh_creation_time,'%Y/%m/%d %H:%i')AS roh_creation_time,"
			+ "DATE_FORMAT(roh_expected_time,'%Y/%m/%d %H:%i')AS roh_expected_time,roh_guest_first_name,roh_guest_last_name"
			+ " FROM roomservice_order_header ";
	private final String PARAMETER_ORDER_ID = " roh_id = ? ";
	private final String PARAMETER_ROOMNO = " roh_room_no = ? ";
	private final String PARAMETER_MOBILE_ID = " roh_mobile_info_id = ? ";
	private final String PARAMETER_STATUS = " roh_status = ? ";
	private final String PARAMETER_LESS_THAN_CREATIONTIME = " ? >= roh_creation_time ";
	private final String PARAMETER_MORE_THAN_CREATIONTIME = " roh_creation_time >= ? ";
	private final String PARAMETER_BETWEEN_CREATIONTIME = " roh_creation_time BETWEEN ? AND ? ";

	public List<RoomServiceOrderHeaderInfoBean> select(final Connection conn, final int mobileId, final int orderId,
			final String status, final String startTime, final String endTime) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceOrderHeaderInfoBean> list = new ArrayList<RoomServiceOrderHeaderInfoBean>();
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
			if (mobileId > 0) {
				description = formatWhereDescription(x++, PARAMETER_MOBILE_ID, description);
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
			if (orderId > 0) {
				psmt.setInt(++i, orderId);
			}
			if (mobileId > 0) {
				psmt.setInt(++i, mobileId);
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
				list.add(new RoomServiceOrderHeaderInfoBean(rs.getInt("roh_id"), rs.getString("roh_status"),
						rs.getFloat("roh_total_amount"), rs.getInt("roh_qty"), rs.getString("roh_expected_time"),
						rs.getString("roh_creation_time")));
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	public List<RoomServiceBackendOrderInfoBean> select(final Connection conn, final int orderId, final String status,
			final String startTime, final String endTime, final String roomNo) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceBackendOrderInfoBean> list = new ArrayList<RoomServiceBackendOrderInfoBean>();
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
			if (!StringUtils.isBlank(roomNo)) {
				description = formatWhereDescription(x++, PARAMETER_ROOMNO, description);
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
			if (orderId > 0) {
				psmt.setInt(++i, orderId);
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
				list.add(new RoomServiceBackendOrderInfoBean(rs.getInt("roh_id"), rs.getString("roh_room_no"),
						rs.getString("roh_status"), rs.getFloat("roh_total_amount"), rs.getInt("roh_qty"),
						rs.getString("roh_guest_first_name"), rs.getString("roh_guest_last_name"),
						rs.getString("roh_creation_time"), rs.getString("roh_expected_time"),
						rs.getString("roh_memo")));
			}

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_STATUS = "SELECT roh_id,roh_status,roh_qty,roh_total_amount,"
			+ "DATE_FORMAT(roh_creation_time,'%Y/%m/%d %H:%i')AS roh_creation_time, "
			+ "DATE_FORMAT(roh_expected_time,'%Y/%m/%d %H:%i')AS roh_expected_time FROM  "
			+ "roomservice_order_header WHERE roh_status = ?;";

	public List<RoomServiceOrderHeaderInfoBean> selectByStatus(final Connection conn, final String status)
			throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceOrderHeaderInfoBean> list = new ArrayList<RoomServiceOrderHeaderInfoBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_STATUS);

			int i = 0;
			psmt.setString(++i, status);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RoomServiceOrderHeaderInfoBean(rs.getInt("roh_id"), rs.getString("roh_status"),
						rs.getFloat("roh_total_amount"), rs.getInt("roh_qty"), rs.getString("roh_expected_time"),
						rs.getString("roh_creation_time")));
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_BETWEEN_CREATIONTIME = "SELECT roh_id,roh_status,roh_qty,roh_total_amount,"
			+ "DATE_FORMAT(roh_creation_time,'%Y/%m/%d %H:%i')AS roh_creation_time,"
			+ "DATE_FORMAT(roh_expected_time,'%Y/%m/%d %H:%i')AS roh_expected_time FROM  "
			+ "roomservice_order_header WHERE roh_creation_time BETWEEN ? AND ? ;";

	public List<RoomServiceOrderHeaderInfoBean> selectByBetweenCreationTime(final Connection conn,
			final String starTime, final String endTime) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceOrderHeaderInfoBean> list = new ArrayList<RoomServiceOrderHeaderInfoBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_BETWEEN_CREATIONTIME);

			int i = 0;
			psmt.setString(++i, starTime);
			psmt.setString(++i, endTime);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RoomServiceOrderHeaderInfoBean(rs.getInt("roh_id"), rs.getString("roh_status"),
						rs.getFloat("roh_total_amount"), rs.getInt("roh_qty"), rs.getString("roh_expected_time"),
						rs.getString("roh_creation_time")));
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BY_MORETHAN_CREATIONTIME = "SELECT roh_id,roh_status,roh_qty,roh_total_amount,"
			+ "DATE_FORMAT(roh_creation_time,'%Y/%m/%d %H:%i')AS roh_creation_time,"
			+ "DATE_FORMAT(roh_expected_time,'%Y/%m/%d %H:%i')AS roh_expected_time FROM  "
			+ "roomservice_order_header WHERE roh_creation_time >= ? ;";

	public List<RoomServiceOrderHeaderInfoBean> selectByMoreThanCreationTime(final Connection conn,
			final String starTime, final String endTime) throws SQLException {

		PreparedStatement psmt = null;
		List<RoomServiceOrderHeaderInfoBean> list = new ArrayList<RoomServiceOrderHeaderInfoBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_MORETHAN_CREATIONTIME);

			int i = 0;
			psmt.setString(++i, starTime);
			psmt.setString(++i, endTime);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RoomServiceOrderHeaderInfoBean(rs.getInt("roh_id"), rs.getString("roh_status"),
						rs.getFloat("roh_total_amount"), rs.getInt("roh_qty"), rs.getString("roh_expected_time"),
						rs.getString("roh_creation_time")));
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_BILLNO = "SELECT roh_bill_no FROM roomservice_order_header WHERE roh_id = ?;";

	public String findBillNo(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String billNo = null;
		try {
			psmt = conn.prepareStatement(SELECT_BILLNO);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				billNo = rs.getString("roh_bill_no");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return billNo;
	}

	private final static String SELECT_ROOMNO = "SELECT roh_room_no FROM roomservice_order_header WHERE roh_id = ?;";

	public String findRoomNo(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		String roomNo = null;
		try {
			psmt = conn.prepareStatement(SELECT_ROOMNO);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				roomNo = rs.getString("roh_room_no");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return roomNo;
	}

	private final static String SELECT_MOBILEID = "SELECT roh_mobile_info_id FROM roomservice_order_header WHERE roh_id = ?;";

	public int findMobileId(final Connection conn, final int id) throws SQLException {

		PreparedStatement psmt = null;
		int mobileId = -999;
		try {
			psmt = conn.prepareStatement(SELECT_MOBILEID);

			int i = 0;
			psmt.setInt(++i, id);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				mobileId = rs.getInt("roh_mobile_info_id");
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
