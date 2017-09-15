package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sidc.blackcore.api.agent.request.GuestRequest;
import com.sidc.blackcore.api.mobile.guest.bean.GuestInfoBean;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.utils.exception.SiDCException;

public class GuestManager {

	private GuestManager() {
	}

	private static class lazyHolder {
		public static final GuestManager INSTANCE = new GuestManager();
	}

	public static GuestManager getInstance() {
		return lazyHolder.INSTANCE;
	}

	public List<GuestInfoBean> selectGuestInfo(final String roomNo) throws SiDCException, SQLException {

		Connection conn = null;

		List<GuestInfoBean> guestInfoList = new ArrayList<GuestInfoBean>();

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			guestInfoList = GuestDao.getInstance().selectGuestInfo(conn, roomNo);

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return guestInfoList;
	}

	public boolean isExist(final String roomNo, final String guestNo) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = GuestDao.getInstance().selectByIdGuestNo(conn, guestNo, roomNo);

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isExist;

	}

	/**
	 * 
	 * @param roomMap
	 *            [key=roomNo,value=guestNo]
	 * @return list
	 * @throws SiDCException
	 * @throws SQLException
	 */
	public List<String> isExist(final Map<String, List<String>> roomMap) throws SiDCException, SQLException {
		Connection conn = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (Map.Entry<String, List<String>> entry : roomMap.entrySet()) {
				final String roomNo = entry.getKey();

				for (final String guestNo : entry.getValue()) {
					if (!GuestDao.getInstance().selectByIdGuestNo(conn, roomNo, guestNo)) {
						list.add(guestNo);
					}
				}
			}

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;

	}

	
	public List<GuestRequest> findGuestInfo(final String roomNo) throws SQLException {
		
		Connection conn = null;
		List<GuestRequest> list = new ArrayList<GuestRequest>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			list = GuestDao.getInstance().findWithRoomChange(conn, roomNo);

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}
}
