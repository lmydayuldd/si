package com.sidc.dao.sits.chatmeeting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.message.bean.ChatMettingMessageBean;
import com.sidc.blackcore.api.mobile.message.bean.NewslettersBean;

public class ChatMettingDao {
	/**
	 * @author Joe
	 */
	private ChatMettingDao() {
	}

	private static class LazyHolder {
		public static final ChatMettingDao INSTANCE = new ChatMettingDao();
	}

	public static ChatMettingDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO chat_meeting(cm_id,cm_type,cm_roomno,cm_sender_name,cm_sender_type,cm_receiver_name,"
			+ "cm_receiver_type,cm_title,cm_content,cm_creation_time)VALUES(?,?,?,?,?,?,?,?,?,now())";

	public String insert(final Connection conn, final String type, final String roomNo, final String senderName,
			final int senderType, final String receiverName, final int receiverType, final String title,
			final String content) throws SQLException {

		String generatedKey = UUID.randomUUID().toString().replace("-", "");
		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(INSERT);

			int i = 0;
			psmt.setString(++i, generatedKey);
			psmt.setString(++i, type);
			psmt.setString(++i, roomNo);
			psmt.setString(++i, senderName);
			psmt.setInt(++i, senderType);
			psmt.setString(++i, receiverName);
			psmt.setInt(++i, receiverType);
			psmt.setString(++i, title);
			psmt.setString(++i, content);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return generatedKey;
	}

	private final static String SELECT = "SELECT cm_id,cm_sender_name,cm_sender_type,cm_receiver_name,cm_receiver_type,cm_content,"
			+ "DATE_FORMAT(cm_creation_time,'%Y/%m/%d %H:%i:%S')AS cm_creation_time FROM chat_meeting WHERE cm_roomno = ? AND (cm_sender_name = ? "
			+ "OR cm_receiver_name = ?) ORDER BY cm_creation_time ASC;";

	public List<ChatMettingMessageBean> selectMessage(final Connection conn, final String roomNo,
			final int mobileInfoId) throws SQLException {

		PreparedStatement psmt = null;

		List<ChatMettingMessageBean> list = new ArrayList<ChatMettingMessageBean>();

		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setString(++i, roomNo);
			psmt.setString(++i, String.valueOf(mobileInfoId));
			psmt.setString(++i, String.valueOf(mobileInfoId));

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ChatMettingMessageBean(rs.getString("cm_id"), rs.getString("cm_sender_name"),
						rs.getInt("cm_sender_type"), rs.getString("cm_receiver_name"), rs.getInt("cm_receiver_type"),
						rs.getString("cm_content"), rs.getString("cm_creation_time")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String UPDATE_STATUS = "UPDATE chat_meeting SET cm_status = ? WHERE cm_id = ?;";

	public void updateStatus(final Connection conn, final String id, final int status) throws SQLException {

		PreparedStatement psmt = null;

		try {
			psmt = conn.prepareStatement(UPDATE_STATUS);

			int i = 0;
			psmt.setInt(++i, status);
			psmt.setString(++i, id);

			psmt.addBatch();
			psmt.executeBatch();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_NEWSLETTERS = "SELECT cm_id,cm_title,cm_content,cm_creation_time FROM chat_meeting "
			+ "WHERE cm_receiver_type = 1 AND cm_type = 'newsletters' AND cm_receiver_name = ?";

	public List<NewslettersBean> selectNewslettersMessage(final Connection conn, final String id,
			final int mobileInfoId) throws SQLException {

		PreparedStatement psmt = null;
		final String sqlOrderby = " ORDER BY cm_creation_time ASC ;";

		List<NewslettersBean> list = new ArrayList<NewslettersBean>();

		try {
			String sqldescription = SELECT_NEWSLETTERS;

			if (!StringUtils.isBlank(id)) {
				sqldescription += " AND cm_id = '" + id + "'";
			}

			psmt = conn.prepareStatement(sqldescription + sqlOrderby);

			int i = 0;
			psmt.setString(++i, String.valueOf(mobileInfoId));

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new NewslettersBean(rs.getString("cm_id"), rs.getString("cm_title"),
						rs.getString("cm_content"), rs.getString("cm_creation_time")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
