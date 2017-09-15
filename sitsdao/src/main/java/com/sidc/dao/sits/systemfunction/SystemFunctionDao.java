package com.sidc.dao.sits.systemfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.userauthorization.bean.AuthorizationFunctionBean;

public class SystemFunctionDao {

	private static final class lazyHolder {
		public static SystemFunctionDao INSTANCE = new SystemFunctionDao();
	}

	public static SystemFunctionDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_FUNCTIONID = "SELECT function_id,url,function_desc,module,crud_group FROM system_function"
			+ " WHERE function_id LIKE ?";

	public List<AuthorizationFunctionBean> select(final Connection conn, final String functionCode)
			throws SQLException {
		PreparedStatement psmt = null;

		List<AuthorizationFunctionBean> list = new ArrayList<AuthorizationFunctionBean>();
		try {
			psmt = conn.prepareStatement(SELECT_BY_FUNCTIONID);

			int i = 0;
			psmt.setString(++i, "%" + functionCode + "%");
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				list.add(new AuthorizationFunctionBean(rs.getString("function_id"), rs.getString("url"),
						rs.getString("function_desc"), rs.getString("module"), rs.getString("crud_group")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
