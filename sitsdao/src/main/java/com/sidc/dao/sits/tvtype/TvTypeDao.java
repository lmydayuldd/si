package com.sidc.dao.sits.tvtype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.tvchannel.bean.TvTypeBean;

public class TvTypeDao {
	private TvTypeDao() {
	}

	private static final class lazyHolder {
		public static TvTypeDao INSTANCE = new TvTypeDao();
	}

	public static TvTypeDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String SELECT = "SELECT tt.type_id,tt.sequence,(SELECT ttl.description	FROM tv_type_language ttl "
			+ "WHERE ttl.type_id = tt.type_id AND ttl.lang_code = ?) AS description "
			+ "FROM tv_type tt WHERE tt.suspend = \'N\'  ORDER BY sequence;";

	public List<TvTypeBean> select(final Connection conn, final String langCode) throws SQLException {
		PreparedStatement psmt = null;

		List<TvTypeBean> list = new ArrayList<TvTypeBean>();

		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new TvTypeBean(rs.getString("type_id"), rs.getString("description"), rs.getInt("sequence")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
