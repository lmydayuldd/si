package com.sidc.dao.sits.group_message_page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sidc.blackcore.api.agent.request.MessageRequest;

/**
 * 
 * @author Allen Huang
 *
 */
public class GroupMessagePageDao {

	private GroupMessagePageDao() {
	}

	private static final class lazyHolder {
		public static final GroupMessagePageDao INSTANCE = new GroupMessagePageDao();
	}

	public static GroupMessagePageDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private static final String INSERT_GROUP_MESSAGE_PAGE = "INSERT INTO group_message_page (uuid, seq, text, "
			+ "group_id) SELECT REPLACE(UUID(), '-', ''), COUNT(uuid) + 1, ?, ? FROM group_message_page "
			+ "WHERE group_id = ?";

	public void insertGroupMessagePage(final Connection conn, final MessageRequest enity, final String group_id)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(INSERT_GROUP_MESSAGE_PAGE);

			int i = 0;
			psmt.setString(++i, enity.getText());
			psmt.setString(++i, group_id);
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

	private static final String UPDATE_GROUP_MESSAGE_PAGE = "UPDATE group_message_page SET text = ? "
			+ "WHERE group_id = ?";
	public void updateGroupMessagePage(final Connection conn, final MessageRequest enity, final String group_id)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_GROUP_MESSAGE_PAGE);

			int i = 0;
			psmt.setString(++i, enity.getText());
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
	
	private static final String DELETE_GROUP_MESSAGE_PAGE = "DELETE group_message_page FROM group_message_page "
			+ "INNER JOIN group_message_send ON group_message_page.group_id = group_message_send.group_id "
			+ "WHERE group_message_send.send_rooms = ?";
	public void deleteGroupMessagePage(final Connection conn, final String roomno) throws SQLException {
		
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE_GROUP_MESSAGE_PAGE);
			
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
