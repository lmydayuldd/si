
package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sidc.blackcore.api.agent.request.CheckInRequest;
import com.sidc.blackcore.api.agent.request.CheckOutRequest;
import com.sidc.blackcore.api.agent.request.RoomChangeRequest;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.bill.BillDao;
import com.sidc.dao.sits.cons.ConsDao;
import com.sidc.dao.sits.gatewayondemanduser.GatewayOndemandUserDAO;
import com.sidc.dao.sits.group_message_send.GroupMessageSendDao;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.dao.sits.message.MessageDao;
import com.sidc.dao.sits.moviebookmarkrecord.MovieBookMarkRecordDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.tvchargerecord.TvChargeRecordDao;

/**
 * @author Joe
 *
 */
public class RoomChangeManager {

	private RoomChangeManager() {
	}

	private static class LazyHolder {
		public static final RoomChangeManager INSTANCE = new RoomChangeManager();
	}

	public static RoomChangeManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String searchBillNo(final String roomNo) throws SQLException {
		String billNo = null;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			billNo = BillDao.getInstance().searchBillNoWithRoomNo(conn, roomNo);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return billNo;
	}

	/**
	 * 更新Room的billNo欄位與更新Bill的roomNo欄位. 更新Guest的roomNo/billNo欄位.
	 * 更新Cons的roomNo欄位. 更新MovieBookmark的roomNo/stbIp欄位.
	 * 更新gateway_ondemand_user的roomNo/billNo欄位. 更新Message的roomNo欄位.
	 **/
	public void roomChange(RoomChangeRequest entity, CheckInRequest checkInEntity, CheckOutRequest checkOutEntity,
			final String pincode) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			//2017/05/25 新增　pincode需求
			CheckInManager.getInstance().doCheckIn(conn, checkInEntity, pincode);

			final String billNo = BillDao.getInstance().searchBillNoWithRoomNo(conn, entity.getNewRoomNumber());
			final List<String> consIDList = ConsDao.getInstance().searchIDByBillNo(conn, billNo);

			for (String item : consIDList) {
				ConsDao.getInstance().updateBillNORoomNO(conn, billNo, entity.getNewRoomNumber(), item);
			}

			MovieBookMarkRecordDao.getInstance().updateRoomNoIP(conn, entity.getOldRoomNumber(),
					entity.getNewRoomNumber());

			GatewayOndemandUserDAO.getInstance().updateRoomNo(conn, entity.getOldRoomNumber(),
					entity.getNewRoomNumber());

			TvChargeRecordDao.getInstance().updateRoomNoIP(conn, entity.getOldRoomNumber(), entity.getNewRoomNumber());

			MessageDao.getInstance().updateRoomNo(conn, entity.getOldRoomNumber(), entity.getNewRoomNumber());

			GroupMessageSendDao.getInstance().updateRoomNoWithRoomChange(conn, entity.getOldRoomNumber(),
					entity.getNewRoomNumber());

			// check out 要最後做 否則資料會被刪除 取不到
			CheckOutManager.getInstance().doCheckOutWithRoomChange(conn, checkOutEntity);

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

	public CheckInRequest getCheckInRequest(final String oldRoomNo, final String newRoomNo) throws SQLException {
		CheckInRequest entity = null;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			entity = RoomDao.getInstance().searchWithCheckInfo(conn, oldRoomNo, newRoomNo);
			entity.setGuests(GuestDao.getInstance().findWithRoomChange(conn, oldRoomNo));

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return entity;
	}

}
