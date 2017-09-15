package com.sidc.dao.sits.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceInfoBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopTypeInfoBean;

public class ShopTypeDao {
	private ShopTypeDao() {
	}

	private static class LazyHolder {
		public static final ShopTypeDao INSTANCE = new ShopTypeDao();
	}

	public static ShopTypeDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_SHOP = "SELECT type_id,seq,kind,(SELECT type_name FROM shop_type_language stl "
			+ "WHERE stl.type_id = st.type_id AND stl.lang_code = ?) AS name FROM shop_type st WHERE st.kind = \'SHOPPING\' ORDER BY st.seq ASC;";

	public List<ShopTypeInfoBean> selectShop(final Connection conn, final String langCode) throws SQLException {
		PreparedStatement psmt = null;
		List<ShopTypeInfoBean> list = new ArrayList<ShopTypeInfoBean>();
		try {
			psmt = conn.prepareStatement(SELECT_SHOP);

			int i = 0;
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new ShopTypeInfoBean(rs.getInt("type_id"), rs.getInt("seq"), rs.getString("name")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SELECT_MEAL = "SELECT type_id,seq,kind,(SELECT type_name FROM shop_type_language stl "
			+ "WHERE stl.type_id = st.type_id AND stl.lang_code = ?) AS name FROM shop_type st WHERE st.kind = \'MEAL\' ORDER BY st.seq ASC;";

	public List<RoomServiceInfoBean> selectMeal(final Connection conn, final String langCode) throws SQLException {
		PreparedStatement psmt = null;
		List<RoomServiceInfoBean> list = new ArrayList<RoomServiceInfoBean>();
		try {
			psmt = conn.prepareStatement(SELECT_MEAL);

			int i = 0;
			psmt.setString(++i, langCode);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new RoomServiceInfoBean(rs.getInt("type_id"), rs.getInt("seq"), rs.getString("name")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
