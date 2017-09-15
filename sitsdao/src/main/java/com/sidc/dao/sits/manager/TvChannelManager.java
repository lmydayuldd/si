package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.tvchannel.bean.TvChannelBean;
import com.sidc.blackcore.api.sits.tvchannel.bean.TvListBean;
import com.sidc.blackcore.api.sits.tvchannel.bean.TvTypeBean;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.channelfrequencylist.ChannelFrequencyListDao;
import com.sidc.dao.sits.tvtype.TvTypeDao;

public class TvChannelManager {
	public TvChannelManager() {
		// TODO Auto-generated constructor stub
	}

	private static class LazyHolder {
		public static final TvChannelManager INSTANCE = new TvChannelManager();
	}

	public static TvChannelManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public List<TvListBean> selectAllChannel(final String langCode) throws SQLException {

		Connection conn = null;

		List<TvListBean> tvList = new ArrayList<TvListBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<TvTypeBean> catList = TvTypeDao.getInstance().select(conn, langCode);

			for (final TvTypeBean catEntity : catList) {

				List<TvChannelBean> tvChannelList = ChannelFrequencyListDao.getInstance().selectByType(conn, langCode,
						catEntity.getTypeid());
				tvList.add(new TvListBean(catEntity.getTypename(), catEntity.getTypeid(), catEntity.getSequence(),
						tvChannelList));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return tvList;
	}
}
