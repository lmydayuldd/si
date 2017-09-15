package com.sidc.dao.sits.photo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;

public class PhotoDao {

	private static final class lazyHolder {
		public static PhotoDao INSTANCE = new PhotoDao();
	}

	public static PhotoDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String INSERT = "INSERT INTO photo(p_refer_id,p_link,p_name,p_status,p_refer_type,p_creation_time) VALUES"
			+ "(?,?,?,?,?,NOW());";

	public int insert(final Connection conn, final String referId, final String link, final String name,
			final String status, final String referType) throws SQLException {

		PreparedStatement psmt = null;
		int id = -1;
		try {
			psmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			int i = 0;
			psmt.setString(++i, referId);
			psmt.setString(++i, link);
			psmt.setString(++i, name);
			psmt.setString(++i, status);
			psmt.setString(++i, referType);

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			} else {
				throw new SQLException("photo insert fail.");
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return id;
	}

	private final static String SELECT_BY_REFERID = "SELECT p_link FROM photo WHERE p_refer_id = ? AND p_refer_type = ?;";

	public List<ActivityPhotoBean> selectByReferid(final Connection conn, final String referId, final String type)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ActivityPhotoBean> list = new ArrayList<ActivityPhotoBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_REFERID);

			int i = 0;
			psmt.setString(++i, referId);
			psmt.setString(++i, type);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ActivityPhotoBean(rs.getString("p_link")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String DELETE = "DELETE FROM photo WHERE p_refer_id = ? AND p_refer_type = ?;";

	public void delete(final Connection conn, final String referId, final String referType) throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(DELETE);

			int i = 0;
			psmt.setString(++i, referId);
			psmt.setString(++i, referType);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String UPDATE_WITH_BACKUP = "UPDATE photo SET p_status = ? ,p_backup = ? WHERE p_refer_id = ?";

	public void updateWithBackup(final Connection conn, final String referId, final String status, final byte[] bak)
			throws SQLException {

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(UPDATE_WITH_BACKUP);

			int i = 0;
			psmt.setString(++i, status);
			psmt.setBytes(++i, bak);
			psmt.setString(++i, referId);

			psmt.executeUpdate();

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
	}

	private final static String SELECT_NAME_BY_REFERID = "SELECT p_name FROM photo WHERE p_refer_id = ? AND p_refer_type = ?;";

	public List<String> selectNameByReferid(final Connection conn, final String referId, final String type)
			throws SQLException {

		PreparedStatement psmt = null;
		List<String> list = new ArrayList<String>();
		try {
			psmt = conn.prepareStatement(SELECT_NAME_BY_REFERID);

			int i = 0;
			psmt.setString(++i, referId);
			psmt.setString(++i, type);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("p_name"));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
