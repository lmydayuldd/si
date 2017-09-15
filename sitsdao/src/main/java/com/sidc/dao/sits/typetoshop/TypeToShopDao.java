package com.sidc.dao.sits.typetoshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.shop.bean.ShopItemInfoBean;

public class TypeToShopDao {
	private TypeToShopDao() {
	}

	private static class LazyHolder {
		public static final TypeToShopDao INSTANCE = new TypeToShopDao();
	}

	public static TypeToShopDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT = "SELECT s.shop_id,s.price,s.seq,s.post_location,"
			+ "(SELECT name FROM shop_language sl WHERE sl.shop_id = s.shop_id AND sl.lang_code = ?) AS name "
			+ "FROM type_to_shop ttp LEFT JOIN shop s ON ttp.shop_id = s.shop_id WHERE ttp.type_id = ? ORDER BY seq ASC;";

	public List<ShopItemInfoBean> select(final Connection conn, final int typeId, final String langCode)
			throws SQLException {

		PreparedStatement psmt = null;
		List<ShopItemInfoBean> list = new ArrayList<ShopItemInfoBean>();

		try {
			psmt = conn.prepareStatement(SELECT);

			int i = 0;
			
			psmt.setString(++i, langCode);
			psmt.setInt(++i, typeId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ShopItemInfoBean(rs.getInt("shop_id"), rs.getString("name"), rs.getString("price"),
						rs.getString("post_location"), rs.getInt("seq")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
