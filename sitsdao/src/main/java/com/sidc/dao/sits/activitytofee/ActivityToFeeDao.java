package com.sidc.dao.sits.activitytofee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityToFeeBean;

public class ActivityToFeeDao {

	private static final class lazyHolder {
		public static ActivityToFeeDao INSTANCE = new ActivityToFeeDao();
	}

	public static ActivityToFeeDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO activity_to_fee(atf_activity_header_id,aft_fee_id,aft_price) VALUES(?,?,?);";

	public void insert(final Connection conn, final int activityId, final int feeId, final String price)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT);

			int i = 0;
			psmt.setInt(++i, activityId);
			psmt.setInt(++i, feeId);
			psmt.setString(++i, price);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

	}

	private final static String SELECT_BY_ACTIVITYID = "SELECT aft_fee_id,aft_price FROM activity_to_fee WHERE atf_activity_header_id = ?;";

	public List<ActivityToFeeBean> selectByActivityId(final Connection conn, final int activityId) throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityToFeeBean> list = new ArrayList<ActivityToFeeBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_ACTIVITYID);

			int i = 0;
			psmt.setInt(++i, activityId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityToFeeBean(rs.getInt("aft_fee_id"), rs.getString("aft_price")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_PRICE = "SELECT aft_price FROM activity_to_fee WHERE atf_activity_header_id = ? AND aft_fee_id = ?;";

	public String selectPrice(final Connection conn, final int activityId, final int feeId) throws SQLException {

		PreparedStatement psmt = null;
		String price = null;
		try {
			psmt = conn.prepareStatement(SELECT_PRICE);

			int i = 0;
			psmt.setInt(++i, activityId);
			psmt.setInt(++i, feeId);
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				price = rs.getString("aft_price");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return price;
	}

	private final static String DELETE = "DELETE FROM activity_to_fee WHERE atf_activity_header_id = ?;";

	public void delete(final Connection conn, final int activityId) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setInt(++i, activityId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

	}

	private final static String SELECT_BY_ID = "SELECT atf_activity_header_id FROM activity_to_fee WHERE atf_activity_header_id = ?"
			+ " AND aft_fee_id = ?;";

	public boolean isExist(final Connection conn, final int activityId, final int feeId) throws SQLException {

		PreparedStatement psmt = null;
		boolean isExist = false;
		try {
			psmt = conn.prepareStatement(SELECT_BY_ID);

			int i = 0;
			psmt.setInt(++i, activityId);
			psmt.setInt(++i, feeId);
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

}
