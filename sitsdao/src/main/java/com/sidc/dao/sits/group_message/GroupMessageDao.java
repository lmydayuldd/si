package com.sidc.dao.sits.group_message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import com.sidc.blackcore.api.agent.request.MessageRequest;

/**
 * 
 * @author Allen Huang
 *
 */
public class GroupMessageDao {

	private GroupMessageDao() {
	}

	private static final class lazyHolder {
		public static final GroupMessageDao INSTANCE = new GroupMessageDao();
	}

	public static GroupMessageDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private static final String INSERT_GROUP_MESSAGE = "INSERT INTO group_message (uuid, message_title, sender, "
			+ "receiver, create_date, create_user) VALUES (?, ?, ?, ?, now(), ?)";

	public String insertGroupMessage(final Connection conn, final MessageRequest enity) throws SQLException {
		String uuid = "";

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT_GROUP_MESSAGE);
			uuid = UUID.randomUUID().toString().replace("-", "");

			int i = 0;
			psmt.setString(++i, uuid);
			psmt.setString(++i, enity.getCaller());
			psmt.setString(++i, enity.getCaller());
			psmt.setString(++i, enity.getReceiver());
			psmt.setString(++i, enity.getCreateuser());

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return uuid;
	}

	private static final String UPDATE_GROUP_MESSAGE = "UPDATE group_message SET message_title = ?, sender = ?, "
			+ "receiver = ? WHERE uuid = ?";
	public void updateGroupMessage(final Connection conn, final MessageRequest enity, final String group_id)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_GROUP_MESSAGE);

			int i = 0;
			psmt.setString(++i, enity.getCaller());
			psmt.setString(++i, enity.getCaller());
			psmt.setString(++i, enity.getReceiver());
			psmt.setString(++i, group_id);
			
			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			// TODO: handle finally clause
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}
	
	private static final String DELETE_GROUP_MESSAGE = "DELETE group_message FROM group_message INNER JOIN "
			+ "group_message_send ON group_message.uuid = group_message_send.group_id "
			+ "WHERE group_message_send.send_rooms = ?";
	public void deleteGroupMessage(final Connection conn, final String roomno) throws SQLException {
		
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_GROUP_MESSAGE);
			
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
}
