package com.sidc.dao.sits.bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BillDao {
	/**
	 * @author Joe
	 */
	private BillDao() {
	}

	private static class LazyHolder {
		public static final BillDao INSTANCE = new BillDao();
	}

	public static BillDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SEARCH_BILLNO_WITH_ROOMNO = "SELECT bill_no FROM bill WHERE room_no = ? "
			+ "ORDER BY chki_time DESC";

	public String searchBillNoWithRoomNo(final Connection conn, final String roomNo) throws SQLException {

		String billNo = null;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BILLNO_WITH_ROOMNO);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				billNo = rs.getString("bill_no");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return billNo;
	}

	private final static String INSERT = "INSERT INTO bill(bill_no, chki_time, room_no) VALUES(?, ?, ?)";

	public String insert(final Connection conn, final String roomNo, final String checkinDate) throws SQLException {
		String generatedKey = UUID.randomUUID().toString().replace("-", "");
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(INSERT);

			int i = 0;
			psmt.setString(++i, generatedKey);
			psmt.setString(++i, checkinDate);
			psmt.setString(++i, roomNo);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return generatedKey;
	}

	public void insert(final Connection conn, final String roomNo, final String billNo, final String checkinDate)
			throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(INSERT);

			int i = 0;
			psmt.setString(++i, billNo);
			psmt.setString(++i, checkinDate);
			psmt.setString(++i, roomNo);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_BY_BILLNO = "DELETE FROM bill WHERE bill_no = ?";

	public void deleteByBillNo(final Connection conn, final String billNo) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(DELETE_BY_BILLNO);

			int i = 0;
			psmt.setString(++i, billNo);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_WITH_BILLNO = "UPDATE bill SET chki_time = ?, chko_time = ?, room_no = ? "
			+ "WHERE bill_no = ?";

	public void updateWithBillNo(final Connection conn, final String roomNo, final String billNo,
			final String checkInDate) throws SQLException {
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(UPDATE_WITH_BILLNO);

			int i = 0;
			psmt.setString(++i, checkInDate);
			psmt.setString(++i, null);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, billNo);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	/**
	 * 
	 * @author Allen Huang
	 *
	 */
	private final static String UPDATE_WITH_CHECK_OUT = "UPDATE bill SET chko_time = NOW() WHERE bill_no = ?";

	public void checkOut(final Connection conn, final String billNo) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_WITH_CHECK_OUT);

			int i = 0;
			psmt.setString(++i, billNo);

			psmt.executeUpdate();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SEARCH_BILLNO_WITH_BILLNO = "SELECT bill_no FROM bill WHERE bill_no = ?";

	public boolean searchBillNoWithBillNo(final Connection conn, final String billNo) throws SQLException {

		boolean isPass = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BILLNO_WITH_BILLNO);

			int i = 0;
			psmt.setString(++i, billNo);

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

	private final static String SEARCH_BILLNO_BY_BILLNO_ROOMNO = "SELECT bill_no FROM bill WHERE bill_no = ? AND room_no != ?";

	public boolean searchBillnoByBillnoRoomno(final Connection conn, final String billNo, final String roomNo)
			throws SQLException {

		boolean isPass = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BILLNO_BY_BILLNO_ROOMNO);

			int i = 0;
			psmt.setString(++i, billNo);
			psmt.setString(++i, roomNo);

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

	private final static String CHECKOUT_BY_ROOMNO = "UPDATE bill SET chko_time = NOW() WHERE room_no = ? AND "
			+ "chko_time IS NULL;";

	public void checkoutByRoomno(final Connection conn, final String roomNo) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(CHECKOUT_BY_ROOMNO);

			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.executeUpdate();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	/**
	 * 
	 * @author Allen Huang
	 *
	 */
	private final static String LIST_CHECKOUT_ROOM = "SELECT B.room_no FROM room A LEFT JOIN bill B ON A.`no` = B.room_no "
			+ "WHERE A.bill_no = '' AND "
			+ "DATE_FORMAT(chko_time, '%Y-%m-%d %H:%i') = DATE_FORMAT(DATE_SUB(NOW(), INTERVAL ? HOUR), '%Y-%m-%d %H:%i')";

	public List<String> listCheckOutRoom(final Connection conn, final int hour) throws SQLException {

		List<String> rooms = new ArrayList<String>();

		PreparedStatement psmt = null;
		try {
			int i = 0;
			psmt = conn.prepareStatement(LIST_CHECKOUT_ROOM);
			psmt.setInt(++i, hour);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				rooms.add(rs.getString("room_no"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return rooms;
	}

	private final static String SEARCH_BILLNO_WITH_CHECKIN = "SELECT bill_no FROM bill WHERE room_no = ? AND "
			+ "chko_time IS NULL ORDER BY chki_time DESC";

	public String searchBillNoWithCheckIn(final Connection conn, final String roomNo) throws SQLException {

		String billNo = null;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_BILLNO_WITH_CHECKIN);

			int i = 0;
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				billNo = rs.getString("bill_no");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return billNo;
	}

	private final static String FIND_BILL_CHECKIN_DATE = "SELECT DATE_FORMAT(chki_time, '%Y/%m/%d %H:%i:%s') AS chki_time "
			+ "FROM bill WHERE bill_no = ? AND room_no = ?";
	
	public String findBillCheckinDate(final Connection conn, final String billNo, final String roomNo) throws SQLException {
		
		String chki_time = "";
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(FIND_BILL_CHECKIN_DATE);

			int i = 0;
			psmt.setString(++i, billNo);
			psmt.setString(++i, roomNo);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				chki_time = rs.getString("chki_time");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		
		return chki_time;
	}
}
