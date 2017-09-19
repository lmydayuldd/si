package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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

	public void updateGuest(final String roomNo, final List<GuestRequest> guests) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			String guestNo = "";
			if (guests.size() == 1)
				guestNo = GuestDao.getInstance().selectByIdGuestNo(conn, roomNo);

			for (GuestRequest guest : guests) {
				boolean isExist = false;
				if (StringUtils.isBlank(guestNo))
					guestNo = guest.getGuestno();

				isExist = GuestDao.getInstance().selectByIdGuestNo(conn, roomNo, guestNo);
				if (isExist)
					UpdateGuestInfo(conn, guest, guestNo);
			}

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

	}

	public void UpdateGuestInfo(final Connection conn, final GuestRequest guest, final String guestNo)
			throws SQLException {

		try {
			String UPDATE_INFO = "";
			if (!StringUtils.isBlank(guest.getFirstname())) {
				if (!StringUtils.isBlank(UPDATE_INFO))
					UPDATE_INFO += ", ";
				UPDATE_INFO += "en_first_name = '" + guest.getFirstname() + "'";
			}
			if (!StringUtils.isBlank(guest.getLastname())) {
				if (!StringUtils.isBlank(UPDATE_INFO))
					UPDATE_INFO += ", ";
				UPDATE_INFO += "en_last_name = '" + guest.getLastname() + "'";
			}
			if (!StringUtils.isBlank(guest.getDepdate())) {
				if (!StringUtils.isBlank(UPDATE_INFO))
					UPDATE_INFO += ", ";
				UPDATE_INFO += "departure_date = '" + guest.getDepdate() + "'";
			}
			if (!StringUtils.isBlank(guest.getBirthd())) {
				if (!StringUtils.isBlank(UPDATE_INFO))
					UPDATE_INFO += ", ";
				// DB 吃的是日期是數字格式...0代表沒帶入
				final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				final Date birthday = formatter.parse(guest.getBirthd());
				final int month = birthday.getMonth() + 1;
				final int day = birthday.getDate();
				UPDATE_INFO += "birth_month = '" + month + "', birth_day = '" + day + "'";
			}
			if (!StringUtils.isBlank(guest.getSalutation())) {
				if (!StringUtils.isBlank(UPDATE_INFO))
					UPDATE_INFO += ", ";
				UPDATE_INFO += "salutation = '" + guest.getSalutation() + "'";
			}

			if (!UPDATE_INFO.equals(""))
				GuestDao.getInstance().updateGuestInfo(conn, UPDATE_INFO, guestNo);

		} catch (Exception e) {
			throw new SQLException(e);
		}
	}
}
