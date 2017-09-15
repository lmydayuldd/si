/**
 * 
 */
package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.token.bean.InfoBean;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.account.AccountDao;
import com.sidc.dao.sits.hotelstaff.HotelStaffDao;
import com.sidc.dao.sits.mobileinfo.MobileInfoDao;
import com.sidc.dao.sits.roomallocation.RoomAllocationDao;
import com.sidc.dao.sits.tokenheader.TokenHeaderDao;
import com.sidc.dao.sits.tokenstaffdetail.TokenStaffDetailDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public class AccountManager {

	private AccountManager() {
	}

	private static class LazyHolder {
		public static final AccountManager INSTANCE = new AccountManager();
	}

	public static AccountManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String login(final String user, final String password, final InfoBean mobileEntity)
			throws SQLException, SiDCException {

		Connection conn = null;
		String token = null;

		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 1);

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!AccountDao.getInstance().login(conn, user, password)) {
				throw new SiDCException(APIStatus.FAIL_AUTHORIZATION, "user/password is not correct.");
			}

			doLogout(conn, user);

			token = TokenHeaderDao.getInstance().insert(conn, 1, new Timestamp(cal.getTimeInMillis()));

			final String staffName = HotelStaffDao.getInstance().seleceNameById(conn, user);

			int mobileInfoId = MobileInfoDao.getInstance().getId(conn, mobileEntity.getDeviceid(),
					mobileEntity.getOperatingsystem());

			if (mobileInfoId <= 0) {
				mobileInfoId = MobileInfoDao.getInstance().insert(conn, mobileEntity.getDeviceid(),
						mobileEntity.getOperatingsystem(), mobileEntity.getVersion(), mobileEntity.getPushtoken(),
						staffName);
			} else {
				MobileInfoDao.getInstance().update(conn, mobileInfoId, mobileEntity.getVersion(),
						mobileEntity.getPushtoken(), staffName);
			}

			TokenStaffDetailDao.getInstance().deleteById(conn, user);
			TokenStaffDetailDao.getInstance().deleteByFailure(conn);
			TokenHeaderDao.getInstance().deleteByTypeFailure(conn, 1);

			TokenStaffDetailDao.getInstance().insert(conn, token, user, mobileInfoId);

			HotelStaffDao.getInstance().updateMobileInfo(conn, mobileInfoId, user);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return token;
	}

	public void logout(final String userId) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			doLogout(conn, userId);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

	}

	public void insert(final String id, final String name, final String email, final String password,
			final String staffCode) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			AccountDao.getInstance().insert(conn, id, "ht001", name, email, password);
			HotelStaffDao.getInstance().insert(conn, staffCode, name, id);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public boolean checkId(final String id) throws SQLException {

		Connection conn = null;

		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = AccountDao.getInstance().isExist(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public void doLogout(final Connection conn, final String userId) throws SQLException {

		try {

			List<String> tokenList = TokenStaffDetailDao.getInstance().select(conn, userId);

			// DB FK 關係 要先刪除 staff_detail
			TokenStaffDetailDao.getInstance().deleteById(conn, userId);

			for (final String id : tokenList) {
				TokenHeaderDao.getInstance().deleteById(conn, id);
			}

			// 假設還有負責的房間 重新分配
			List<String> roomList = RoomAllocationDao.getInstance().selectRoomNo(conn, userId);

			RoomAllocationDao.getInstance().delete(conn, userId, 1);

			for (final String roomNo : roomList) {
				final String staffId = RoomAllocationDao.getInstance().getAllocationStaffId(conn, 1);

				if (!StringUtils.isBlank(staffId)) {
					RoomAllocationDao.getInstance().insert(conn, roomNo, staffId, 1);
				}
			}

		} catch (Exception e) {
			throw new SQLException(e);
		}

	}
}
