package com.sidc.dao.sits.tv_language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.tvchannel.bean.TvChannelLanguageBean;

public class TvLanguageDao {

	private static final class lazyHolder {
		public static TvLanguageDao INSTANCE = new TvLanguageDao();
	}

	public static TvLanguageDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_TVID = "SELECT lang_code,introduction FROM tv_language WHERE tv_id = ?";

	public List<TvChannelLanguageBean> selectByTvid(final Connection conn, final String tvId) throws SQLException {
		PreparedStatement psmt = null;

		List<TvChannelLanguageBean> list = new ArrayList<TvChannelLanguageBean>();

		try {
			psmt = conn.prepareStatement(SELECT_BY_TVID);

			int i = 0;
			psmt.setString(++i, tvId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new TvChannelLanguageBean(rs.getString("introduction"), rs.getString("lang_code")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
