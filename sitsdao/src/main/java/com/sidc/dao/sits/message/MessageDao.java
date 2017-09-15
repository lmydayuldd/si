package com.sidc.dao.sits.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.agent.request.MessageRequest;

/**
 * 
 * @author Allen Huang
 *
 */
public class MessageDao {

	private MessageDao() {
	}

	private static class LazyHolder {
		public static final MessageDao INSTANCE = new MessageDao();
	}

	public static final MessageDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String EXIST_MESSAGE = "SELECT id FROM message WHERE room_no = ?";

	public boolean existMessage(final Connection conn, final String roomno) throws SQLException {
		boolean isPass = false;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(EXIST_MESSAGE);

			int i = 0;
			psmt.setString(++i, roomno);

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

	private final static String INSERT_MESSAGE = "INSERT INTO message(id, no, room_no, guest_no, caller, "
			+ "fromPMS, text, receiver, create_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";

	public void insertMessage(final Connection conn, final MessageRequest enity) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT_MESSAGE);

			int i = 0;
			psmt.setString(++i, UUID.randomUUID().toString().replace("-", ""));
			psmt.setString(++i, StringUtils.isBlank(enity.getMessageno()) ? "-" : enity.getMessageno());
			psmt.setString(++i, StringUtils.isBlank(enity.getRoomno()) ? "-" : enity.getRoomno());
			psmt.setString(++i, enity.getGuestno());
			psmt.setString(++i, enity.getCaller());
			psmt.setBoolean(++i, enity.isIsfrompms());
			psmt.setString(++i, enity.getText());
			psmt.setString(++i, enity.getReceiver());

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final String INSERT_GUEST_MESSAGE = "INSERT INTO message (id, no, room_no, guest_no, caller, fromPMS, message.text, receiver, pms_group_id, create_time) "
			+ " SELECT ?, ?, room_no, guest_no, ?, ?, ?, ?, group_id, now() " 
			+ " FROM   guest "
			+ " WHERE  room_no = ?";

	public void insertGuestMessage(final Connection conn, final MessageRequest enity) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT_GUEST_MESSAGE);

			int i = 0;
			psmt.setString(++i, UUID.randomUUID().toString().replace("-", ""));
			psmt.setString(++i, enity.getMessageno());
			psmt.setString(++i, enity.getCaller());
			psmt.setBoolean(++i, enity.isIsfrompms());
			psmt.setString(++i, enity.getText());
			psmt.setString(++i, enity.getReceiver());
			psmt.setString(++i, enity.getRoomno());

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
	
	private final static String UPDATE_MESSAGE = "UPDATE message SET caller = ?, text = ?, "
			+ "receiver = ? WHERE room_no = ?";

	public void updateMessage(final Connection conn, final MessageRequest enity) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_MESSAGE);

			int i = 0;
			psmt.setString(++i, enity.getCaller());
			psmt.setString(++i, enity.getText());
			psmt.setString(++i, enity.getReceiver());
			psmt.setString(++i, enity.getRoomno());

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String DELETE_MESSAGE = "DELETE FROM message WHERE room_no = ?";

	public void deleteMessage(final Connection conn, final String roomno) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_MESSAGE);

			int i = 0;
			psmt.setString(++i, roomno);

			psmt.executeUpdate();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_ROOMNO_BY_ROOMNO = "UPDATE message SET room_no = ? WHERE room_no = ?";

	public void updateRoomNo(final Connection conn, final String oldRoomNo, final String newRoomNo)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_ROOMNO_BY_ROOMNO);

			int i = 0;
			psmt.setString(++i, newRoomNo);
			psmt.setString(++i, oldRoomNo);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
