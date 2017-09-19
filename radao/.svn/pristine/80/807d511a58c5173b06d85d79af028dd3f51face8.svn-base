/**
 * 
 */
package com.sidc.dao.ra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.langs.bean.LangsBean;

public class RcuDeviceGroupLangDao {

	private RcuDeviceGroupLangDao() {
	}

	private static class LazyHolder {
		public static final RcuDeviceGroupLangDao INSTANCE = new RcuDeviceGroupLangDao();
	}

	public static RcuDeviceGroupLangDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private final static String LIST_LANG = "SELECT name,lang FROM rcu_device_group_lang WHERE rcu_device_group_id = ?";

	public List<LangsBean> listLang(final Connection conn, final int groupId) throws SQLException {

		List<LangsBean> list = new ArrayList<LangsBean>();

		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(LIST_LANG);

			int i = 0;
			psmt.setInt(++i, groupId);

			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new LangsBean(rs.getString("lang"), rs.getString("name")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}

		return list;
	}

}
