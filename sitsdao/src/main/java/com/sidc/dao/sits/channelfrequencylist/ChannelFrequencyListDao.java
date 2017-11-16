package com.sidc.dao.sits.channelfrequencylist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.tvchannel.bean.TvChannelBean;

public class ChannelFrequencyListDao {

	private static final class lazyHolder {
		public static ChannelFrequencyListDao INSTANCE = new ChannelFrequencyListDao();
	}

	public static ChannelFrequencyListDao getInstance() {
		return lazyHolder.INSTANCE;
	}

	private final static String SELECT_BY_TYPE = "SELECT ttt.type_id,tv.tv_id,tv.channel_id,cfl.ip,cfl.port,tv.post_location,"
			+ "(SELECT tl.introduction FROM tv_language tl WHERE tv.tv_id = tl.tv_id AND tl.lang_code = ?) as name "
			+ ",tv.price FROM type_to_tv ttt LEFT JOIN tv ON ttt.tv_id = tv.tv_id "
			+ "LEFT JOIN channel_frequency_list cfl ON tv.channel_id = cfl.channel_id WHERE ttt.type_id = ? "
			+ "AND cfl.open_status = 'Y' ORDER BY tv.price";

	public List<TvChannelBean> selectByType(final Connection conn, final String langCode, final String typeId)
			throws SQLException {
		PreparedStatement psmt = null;
		List<TvChannelBean> list = new ArrayList<TvChannelBean>();

		try {
			psmt = conn.prepareStatement(SELECT_BY_TYPE);

			int i = 0;
			psmt.setString(++i, langCode);
			psmt.setString(++i, typeId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				list.add(new TvChannelBean(rs.getString("tv_id"), rs.getString("channel_id"), rs.getInt("price"),
						rs.getString("name"), rs.getString("ip"), rs.getString("port"), rs.getString("post_location")));
			}

		} finally {
			if (psmt != null && !psmt.isClosed()) {
				psmt.close();
			}
		}
		return list;
	}

}
