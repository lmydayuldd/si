package com.sidc.dao.sits.cons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sidc.blackcore.api.agent.request.AgentPostRequest;
import com.sidc.blackcore.api.sits.type.ConsType;

/**
 * 
 * @author Allen Huang
 *
 */
public class ConsDao {

	private ConsDao() {
	}

	private static final class lazyHolder {
		public static final ConsDao INSTANCE = new ConsDao();
	}

	public static ConsDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private static final String EXIST_AGENT_ID = "SELECT id FROM cons WHERE agent_id = ?";

	public boolean existAgentId(final Connection conn, final String agentid) throws SQLException {
		boolean isPass = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(EXIST_AGENT_ID);

			int i = 0;
			psmt.setString(++i, agentid);

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

	private static final String UPDATE_STATUS = "UPDATE cons SET status = ?, room_no = ? WHERE agent_id = ?";

	public void updateStatus(final Connection conn, final AgentPostRequest enity) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_STATUS);

			int i = 0;
			psmt.setString(++i, enity.getStatus());
			psmt.setString(++i, enity.getRoomno());
			psmt.setString(++i, enity.getAgentid());

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SEARCH_ID_BY_BILLNO = "SELECT id FROM cons WHERE bill_no = ?";

	public List<String> searchIDByBillNo(final Connection conn, final String billNo) throws SQLException {
		List<String> idList = new ArrayList<String>();
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_ID_BY_BILLNO);

			int i = 0;
			psmt.setString(++i, billNo);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				idList.add(rs.getString("id"));
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return idList;
	}

	private final static String UPDATE_BILLNO_ROOMNO = "UPDATE cons SET bill_no = ?,room_no=? WHERE id = ?";

	public void updateBillNORoomNO(final Connection conn, final String billNo, final String roomNo, final String id)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_BILLNO_ROOMNO);

			int i = 0;
			psmt.setString(++i, billNo);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, id);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SEARCH_MAX_SEQUENCE = "SELECT MAX(sequence) AS sequence FROM cons WHERE sequence > 0";

	public int findMaxSequence(final Connection conn) throws SQLException {

		int sequence = 0;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_MAX_SEQUENCE);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				sequence = rs.getInt("sequence");
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return sequence;
	}

	private final static String FIND_CONS_STATUS = "SELECT status FROM cons WHERE id = ?";

	public String findConsStatus(final Connection conn, final String id) throws SQLException {

		String status = "";

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(FIND_CONS_STATUS);

			int i = 0;
			psmt.setString(++i, id);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				status = rs.getString("status");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return status;
	}

	private final static String INSERT_CONS = "INSERT INTO cons (id, bill_no, create_time, price, type, description, "
			+ "status, agent_id, sequence, room_no,refer_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

	public void insertCons(final Connection conn, final String id, final String billNo, final float price,
			final short type, final String description, final int sequence, final String roomNo, final String referId)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT_CONS);

			int i = 0;
			psmt.setString(++i, id);
			psmt.setString(++i, billNo);
			psmt.setTimestamp(++i, new Timestamp(System.currentTimeMillis()));
			psmt.setFloat(++i, price);
			psmt.setShort(++i, type);
			psmt.setString(++i, description);
			psmt.setString(++i, ConsumeType.UNPOST);
			psmt.setString(++i, UUID.randomUUID().toString());
			psmt.setInt(++i, sequence);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, referId);

			psmt.addBatch();
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_CONS_PRICE = "UPDATE cons SET price = ? WHERE id = ?";

	public void updateConsPrice(final Connection conn, final String id, final float price) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_CONS_PRICE);

			int i = 0;
			psmt.setFloat(++i, price);
			psmt.setString(++i, id);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_CONS = "DELETE FROM cons WHERE id = ?";

	public void deleteCons(final Connection conn, final String id) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_CONS);

			int i = 0;
			psmt.setString(++i, id);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_BY_REFERID = "DELETE FROM cons WHERE bill_no = ? AND type = ? AND refer_id = ?";

	public void deleteConsByReferId(final Connection conn, final String billNo, final int type, final String referId)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_BY_REFERID);

			int i = 0;
			psmt.setString(++i, billNo);
			psmt.setInt(++i, type);
			psmt.setString(++i, referId);
			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String FIND_MOVICE_CONS = "SELECT id FROM cons WHERE bill_no = ? AND room_no = ? AND description = ? AND type = ?";

	public boolean findMovieCons(final Connection conn, final String billNo, final String roomNo, final String fileName)
			throws SQLException {

		boolean isExist = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(FIND_MOVICE_CONS);

			int i = 0;
			psmt.setString(++i, billNo);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, fileName);
			psmt.setString(++i, ConsType.MOVIE.getValue());

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
