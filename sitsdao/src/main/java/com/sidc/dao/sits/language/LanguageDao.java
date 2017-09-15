package com.sidc.dao.sits.language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageDao {

	private LanguageDao() {
	}
	
	private static class LazyHolder {
		public static final LanguageDao INSTANCE = new LanguageDao();
	}
	
	public static LanguageDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private final static String FIND_LANG_CODE = "SELECT lang_code FROM (SELECT lang_code FROM language_list "
			+ "WHERE pms_lang_code = ? AND is_Enable = 'Y' UNION SELECT lang_code FROM language_list "
			+ "WHERE default_flag = 'Y') AS A LIMIT 1";
	
	public String findLangCode(final Connection conn, final String pmsLangCode) throws SQLException {
		
		String langCode = "";
		
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(FIND_LANG_CODE);

			int i = 0;
			psmt.setString(++i, pmsLangCode);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				langCode = rs.getString("lang_code");
			}
		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		
		return langCode;
	}
}
