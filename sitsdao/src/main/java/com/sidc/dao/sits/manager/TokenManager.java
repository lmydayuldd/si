package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.sidc.blackcore.api.sits.token.bean.InfoBean;
import com.sidc.blackcore.api.sits.token.bean.TokenInfoBean;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.hotelstaff.HotelStaffDao;
import com.sidc.dao.sits.mobileinfo.MobileInfoDao;
import com.sidc.dao.sits.pincode.PinCodeDao;
import com.sidc.dao.sits.stb.StbDao;
import com.sidc.dao.sits.tokenheader.TokenHeaderDao;
import com.sidc.dao.sits.tokenkey.TokenKeyDao;
import com.sidc.dao.sits.tokenroomdetail.TokenRoomDetailDao;
import com.sidc.dao.sits.tokenstaffdetail.TokenStaffDetailDao;

public class TokenManager {

	private TokenManager() {
	}

	private static final class lazyHolder {
		public static final TokenManager INSTANCE = new TokenManager();
	}

	public static TokenManager getInstance() {
		return lazyHolder.INSTANCE;
	}

	public String insertWithPublicKey(final int type, final String roomNo, final Timestamp expiredTime)
			throws SQLException {

		Connection conn = null;
		String token = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			token = TokenHeaderDao.getInstance().insert(conn, type, expiredTime);
			TokenRoomDetailDao.getInstance().insertByRoomNo(conn, roomNo, token);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return token;
	}

	public String insertWithPrivateKey(final String publicToken, final String roomNo, final String stbIp,
			final InfoBean mobileEntity) throws SQLException {

		Connection conn = null;
		String token = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			TokenRoomDetailDao.getInstance().delete(conn, roomNo, publicToken);

			int mobileInfoId = MobileInfoDao.getInstance().getId(conn, mobileEntity.getDeviceid(),
					mobileEntity.getOperatingsystem());

			// 有 mobile 資料就更新 沒有就新增一筆
			if (mobileInfoId <= 0) {
				mobileInfoId = MobileInfoDao.getInstance().insert(conn, mobileEntity.getDeviceid(),
						mobileEntity.getOperatingsystem(), mobileEntity.getVersion(), mobileEntity.getPushtoken(),
						null);
			} else {
				MobileInfoDao.getInstance().update(conn, mobileInfoId, mobileEntity.getVersion(),
						mobileEntity.getPushtoken());
			}

			// 把舊的 token key刪掉
			final List<Integer> list = TokenRoomDetailDao.getInstance().selectId(conn, roomNo, mobileInfoId);

			for (final int detailId : list) {
				TokenKeyDao.getInstance().deleteByDetailId(conn, detailId);
			}

			TokenRoomDetailDao.getInstance().deleteByRoomMobileId(conn, roomNo, mobileInfoId);

			int trdId = TokenRoomDetailDao.getInstance().insert(conn, roomNo, stbIp, publicToken, mobileInfoId);

			token = TokenKeyDao.getInstance().insert(conn, publicToken, trdId);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return token;
	}

	public boolean checkExpired(final int type, final String roomNo) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = TokenHeaderDao.getInstance().checkExpired(conn, type, roomNo);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean checkExpired(final String token, final int type, final String roomNo) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = TokenHeaderDao.getInstance().checkExpired(conn, token, type, roomNo);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean checkExpiredWithChat(final String userId) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = TokenHeaderDao.getInstance().checkExpiredWithChat(conn, 1, userId);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean checkMobileInfo(final String token, final String roomNo, final String stbIp, final String deviceId,
			final int operatingSystem, final String version) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			int mobileInfoId = MobileInfoDao.getInstance().getId(conn, deviceId, operatingSystem);

			isPass = TokenRoomDetailDao.getInstance().checkMobileInfo(conn, roomNo, stbIp, token, mobileInfoId);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public String select(final String roomNo) throws SQLException {

		Connection conn = null;
		String token = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			token = TokenHeaderDao.getInstance().select(conn, roomNo);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return token;
	}

	public String insertChatMeetingToken(final int type, final String staffId, final Timestamp expiredTime,
			final InfoBean mobileEntity) throws SQLException {

		Connection conn = null;
		String token = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			token = TokenHeaderDao.getInstance().insert(conn, type, expiredTime);

			final String staffName = HotelStaffDao.getInstance().seleceNameById(conn, staffId);

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

			TokenStaffDetailDao.getInstance().deleteById(conn, staffId);
			TokenStaffDetailDao.getInstance().deleteByFailure(conn);
			TokenHeaderDao.getInstance().deleteByTypeFailure(conn, 1);

			TokenStaffDetailDao.getInstance().insert(conn, token, staffId, mobileInfoId);

			HotelStaffDao.getInstance().updateMobileInfo(conn, mobileInfoId, staffId);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return token;
	}

	public String insertWithPincode(final String pincode, final InfoBean infoEntity, final Timestamp expiredTime)
			throws SQLException {

		Connection conn = null;
		String token = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			TokenHeaderDao.getInstance().delete(conn);

			final String roomNo = PinCodeDao.getInstance().selectRoomNoByPinCode(conn, pincode);

			token = TokenHeaderDao.getInstance().insert(conn, 0, expiredTime);

			int mobileInfoId = MobileInfoDao.getInstance().getId(conn, infoEntity.getDeviceid(),
					infoEntity.getOperatingsystem());

			if (mobileInfoId <= 0) {
				mobileInfoId = MobileInfoDao.getInstance().insert(conn, infoEntity.getDeviceid(),
						infoEntity.getOperatingsystem(), infoEntity.getVersion(), infoEntity.getPushtoken(), null);
			} else {
				MobileInfoDao.getInstance().update(conn, mobileInfoId, infoEntity.getVersion(),
						infoEntity.getPushtoken());
			}
			final String stbIp = StbDao.getInstance().selectSingleStbIp(conn, roomNo);

			TokenRoomDetailDao.getInstance().insert(conn, roomNo, stbIp, token, mobileInfoId);

			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return token;
	}

	public void deleteWithExpired() throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			deleteWithExpiredProcess(conn);
			conn.commit();

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void deleteWithExpired(Connection conn) throws SQLException {
		deleteWithExpiredProcess(conn);
	}

	private void deleteWithExpiredProcess(Connection conn) throws SQLException {
		List<TokenInfoBean> list = TokenHeaderDao.getInstance().selectWithExpired(conn);
		for (final TokenInfoBean entity : list) {
			switch (entity.getType()) {
			case 0:
				TokenKeyDao.getInstance().deleteByToken(conn, entity.getId());
				TokenRoomDetailDao.getInstance().deleteByTokenHeaderId(conn, entity.getId());
				break;
			case 1:
				TokenStaffDetailDao.getInstance().deleteByTokenHeaderId(conn, entity.getId());
				break;
			}
			TokenHeaderDao.getInstance().deleteById(conn, entity.getId());
		}
	}

}
