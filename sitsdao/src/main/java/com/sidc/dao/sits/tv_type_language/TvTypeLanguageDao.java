package com.sidc.dao.sits.tv_type_language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.tvchannel.bean.TvChannelLanguageBean;

public class TvTypeLanguageDao {

	private static final class lazyHolder {
		public static TvTypeLanguageDao INSTANCE = new TvTypeLanguageDao();
	}

	public static TvTypeLanguageDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_TYPEID = "SELECT lang_code,description FROM tv_type_language WHERE type_id = ?";

	public List<TvChannelLanguageBean> selectByTypeid(final Connection conn, final String typeId) throws SQLException {
		PreparedStatement psmt = null;

		List<TvChannelLanguageBean> list = new ArrayList<TvChannelLanguageBean>();

		try {
			psmt = conn.prepareStatement(SELECT_BY_TYPEID);

			int i = 0;
			psmt.setString(++i, typeId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new TvChannelLanguageBean(rs.getString("description"), rs.getString("lang_code")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
