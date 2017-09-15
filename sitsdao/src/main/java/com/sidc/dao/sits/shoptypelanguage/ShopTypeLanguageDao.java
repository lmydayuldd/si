package com.sidc.dao.sits.shoptypelanguage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.langs.bean.LangsBean;

public class ShopTypeLanguageDao {
	private ShopTypeLanguageDao() {
	}

	private static class LazyHolder {
		public static final ShopTypeLanguageDao INSTANCE = new ShopTypeLanguageDao();
	}

	public static ShopTypeLanguageDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_TYPEID = "SELECT lang_code,type_name FROM shop_type_language WHERE type_id = ?";

	public List<LangsBean> selectByTypeid(final Connection conn, final int typeId) throws SQLException {
		PreparedStatement psmt = null;
		List<LangsBean> list = new ArrayList<LangsBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_TYPEID);

			int i = 0;
			psmt.setInt(++i, typeId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LangsBean(rs.getString("lang_code"), rs.getString("type_name")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
