package com.sidc.dao.sits.shoplanguage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.langs.bean.LangsBean;
import com.sidc.blackcore.api.sits.shop.response.ShoppingPriceResponse;

public class ShopLanguageDao {
	private ShopLanguageDao() {
	}

	private static class LazyHolder {
		public static final ShopLanguageDao INSTANCE = new ShopLanguageDao();
	}

	public static ShopLanguageDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_SHOPID = "SELECT lang_code,name FROM shop_language WHERE shop_id = ?";

	public List<LangsBean> selectByShopid(final Connection conn, final int shopId) throws SQLException {
		PreparedStatement psmt = null;
		List<LangsBean> list = new ArrayList<LangsBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_SHOPID);

			int i = 0;
			psmt.setInt(++i, shopId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LangsBean(rs.getString("lang_code"), rs.getString("name")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

	private final static String SEARCH_SHOP_PRICE = "SELECT price FROM shop WHERE shop_id = ?";

	public List<ShoppingPriceResponse> searchShopPrice(final Connection conn, final int shopId)
			throws SQLException {

		List<ShoppingPriceResponse> list = new ArrayList<ShoppingPriceResponse>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(SEARCH_SHOP_PRICE);

			int i = 0;
			psmt.setInt(++i, shopId);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				list.add(new ShoppingPriceResponse(rs.getFloat("price")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}
}
