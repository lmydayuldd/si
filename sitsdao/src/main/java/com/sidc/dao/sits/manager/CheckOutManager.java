package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.agent.request.CheckOutRequest;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.bill.BillDao;
import com.sidc.dao.sits.group_message.GroupMessageDao;
import com.sidc.dao.sits.group_message_page.GroupMessagePageDao;
import com.sidc.dao.sits.group_message_send.GroupMessageSendDao;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.dao.sits.message.MessageDao;
import com.sidc.dao.sits.moviebookmarkrecord.MovieBookMarkRecordDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.tokenroomdetail.TokenRoomDetailDao;
import com.sidc.dao.sits.tvchargerecord.TvChargeRecordDao;
import com.sidc.dao.sits.wakeup.WakeUpDao;

/**
 * 
 * @author Allen Huang
 *
 */
public class CheckOutManager {

	private CheckOutManager() {
	}

	private static final class lazyHolder {
		public static final CheckOutManager INSTANCE = new CheckOutManager();
	}

	public static CheckOutManager getInstance() {
		return lazyHolder.INSTANCE;
	}

	public void doCheckOut(final Connection conn, final CheckOutRequest enity) throws SQLException {
		try {
			String billNo = RoomDao.getInstance().selectByBill(conn, enity.getRoomno());
			if (!billNo.equals("")) {
				BillDao.getInstance().checkOut(conn, billNo);
				GuestDao.getInstance().deleteGuest(conn, billNo, enity.getRoomno());
			}
			RoomDao.getInstance().checkOut(conn, enity.getRoomno());
			MessageDao.getInstance().deleteMessage(conn, enity.getRoomno());
			GroupMessageDao.getInstance().deleteGroupMessage(conn, enity.getRoomno());
			GroupMessagePageDao.getInstance().deleteGroupMessagePage(conn, enity.getRoomno());
			GroupMessageSendDao.getInstance().deleteGroupMessageSend(conn, enity.getRoomno());
			MovieBookMarkRecordDao.getInstance().deleteMovieBookMarkRecord(conn, enity.getRoomno());
			TvChargeRecordDao.getInstance().deleteTvChargeRecord(conn, enity.getRoomno());
			WakeUpDao.getInstance().deleteWakeUp(conn, enity.getRoomno());

			TokenRoomDetailDao.getInstance().deleteTokenInfoByRoomNo(conn, enity.getRoomno());

		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	public void doCheckOutWithRoomChange(final Connection conn, final CheckOutRequest enity) throws SQLException {
		try {
			BillDao.getInstance().checkoutByRoomno(conn, enity.getRoomno());
			GuestDao.getInstance().deleteGuestByRoomNo(conn, enity.getRoomno());
			RoomDao.getInstance().checkOut(conn, enity.getRoomno());

			MessageDao.getInstance().deleteMessage(conn, enity.getRoomno());
			GroupMessageDao.getInstance().deleteGroupMessage(conn, enity.getRoomno());
			GroupMessagePageDao.getInstance().deleteGroupMessagePage(conn, enity.getRoomno());
			GroupMessageSendDao.getInstance().deleteGroupMessageSend(conn, enity.getRoomno());
			MovieBookMarkRecordDao.getInstance().deleteMovieBookMarkRecord(conn, enity.getRoomno());
			TvChargeRecordDao.getInstance().deleteTvChargeRecord(conn, enity.getRoomno());
			WakeUpDao.getInstance().deleteWakeUp(conn, enity.getRoomno());

			// final List<String> tokenList =
			// TokenRoomDetailDao.getInstance().selectTokenId(conn,
			// enity.getRoomno());
			//
			// TokenKeyDao.getInstance().deleteByRoom(conn, enity.getRoomno());
			// TokenRoomDetailDao.getInstance().deleteByRoom(conn,
			// enity.getRoomno());
			//
			// for (final String id : tokenList) {
			// TokenHeaderDao.getInstance().deleteById(conn, id);
			// }
			//

		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	public void checkout(CheckOutRequest enity) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			doCheckOut(conn, enity);

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

	public List<String> doCheckOutRCURoom(final int hour) throws SQLException {

		List<String> rooms = new ArrayList<String>();

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			rooms = BillDao.getInstance().listCheckOutRoom(conn, hour);

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return rooms;
	}
}
