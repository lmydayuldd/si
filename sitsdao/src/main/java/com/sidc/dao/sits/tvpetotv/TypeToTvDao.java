package com.sidc.dao.sits.tvpetotv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.tvchannel.bean.TvTypeBean;

public class TypeToTvDao {

	private TypeToTvDao() {
	}

	private static final class lazyHolder {
		public static TypeToTvDao INSTANCE = new TypeToTvDao();
	}

	public static TypeToTvDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String SELECT = "SELECT ttv.type_id,"
			+ "(SELECT ttl.description	FROM tv_type_language ttl WHERE ttl.type_id = ttv.type_id AND ttl.lang_code = ?) AS description "
			+ "FROM type_to_tv ttv LEFT JOIN tv_type tt ON	ttv.type_id = tt.type_id " 
			+ "WHERE tt.suspend = \'N\' ;";

	public List<TvTypeBean> select(final Connection conn, final String langCode) throws SQLException {
		PreparedStatement psmt = null;

		List<TvTypeBean> list = new ArrayList<TvTypeBean>();

		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new TvTypeBean(rs.getString("type_id"), rs.getString("description")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
