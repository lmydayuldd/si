package com.sidc.dao.sits.group_message_send;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sidc.blackcore.api.agent.request.MessageRequest;

/**
 * 
 * @author Allen Huang
 *
 */
public class GroupMessageSendDao {

	private GroupMessageSendDao() {
	}

	private static final class lazyHolder {
		public static final GroupMessageSendDao INSTANCE = new GroupMessageSendDao();
	}

	public static GroupMessageSendDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private static final String INSERT_GROUP_MESSAGE_SNED = "INSERT INTO group_message_send (send_id, group_id, "
			+ "start_date, is_one_time, is_all_rooms, send_floors, send_rooms, create_date) VALUES (REPLACE(UUID(), '-', ''), ?, "
			+ "now(), 1, 0, ?, ?, now())";

	public void insertGroupMessageSend(final Connection conn, final MessageRequest enity, final String group_id)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT_GROUP_MESSAGE_SNED);
			String floors = enity.getRoomno().substring(0, enity.getRoomno().length() - 2);

			int i = 0;
			psmt.setString(++i, group_id);
			psmt.setString(++i, floors);
			psmt.setString(++i, enity.getRoomno());

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private static final String SELECT_GROUP_MESSAGE_SNED = "SELECT group_id FROM group_message_send "
			+ "WHERE send_rooms = ?";

	public String findGroupId(final Connection conn, final String room_no) throws SQLException {
		String group_id = null;

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SELECT_GROUP_MESSAGE_SNED);

			int i = 0;
			psmt.setString(++i, room_no);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				group_id = rs.getString("group_id");
			}
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return group_id;
	}

	private static final String DELETE_GROUP_MESSAGE_SEND = "DELETE FROM group_message_send WHERE send_rooms = ?";

	public void deleteGroupMessageSend(final Connection conn, final String roomno) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_GROUP_MESSAGE_SEND);

			int i = 0;
			psmt.setString(++i, roomno);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private static final String UPDATE_ROOMNO_WITH_ROOMCHANGE = "UPDATE group_message_send SET send_rooms = ? WHERE send_rooms = ?";

	public void updateRoomNoWithRoomChange(final Connection conn, final String oldRoomNo, final String newRoomNo)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_ROOMNO_WITH_ROOMCHANGE);

			int i = 0;
			psmt.setString(++i, newRoomNo);
			psmt.setString(++i, oldRoomNo);
			psmt.executeUpdate();
		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
}
