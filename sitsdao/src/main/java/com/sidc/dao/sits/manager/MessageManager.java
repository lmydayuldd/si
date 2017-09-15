package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.agent.request.MessageRequest;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.message.bean.ChatMettingMessageBean;
import com.sidc.blackcore.api.mobile.message.bean.NewslettersBean;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.chatmeeting.ChatMettingDao;
import com.sidc.dao.sits.group_message.GroupMessageDao;
import com.sidc.dao.sits.group_message_page.GroupMessagePageDao;
import com.sidc.dao.sits.group_message_send.GroupMessageSendDao;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.dao.sits.message.MessageDao;
import com.sidc.dao.sits.mobileinfo.MobileInfoDao;
import com.sidc.dao.sits.photo.PhotoDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.roomallocation.RoomAllocationDao;
import com.sidc.dao.sits.tokenheader.TokenHeaderDao;
import com.sidc.dao.sits.tokenkey.TokenKeyDao;
import com.sidc.dao.sits.tokenroomdetail.TokenRoomDetailDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class MessageManager {

	private MessageManager() {
	}

	private static class lazyHolder {
		public static final MessageManager INSTANCE = new MessageManager();
	}

	public static MessageManager getInstance() {
		return lazyHolder.INSTANCE;
	}

	public void insertMessage(MessageRequest enity) throws SiDCException, SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (StringUtils.isBlank(enity.getGuestno())) {
				MessageDao.getInstance().insertGuestMessage(conn, enity);
			} else {
				MessageDao.getInstance().insertMessage(conn, enity);
				String group_id = GroupMessageDao.getInstance().insertGroupMessage(conn, enity);
				GroupMessagePageDao.getInstance().insertGroupMessagePage(conn, enity, group_id);
				GroupMessageSendDao.getInstance().insertGroupMessageSend(conn, enity, group_id);
			}

			RoomDao.getInstance().updateNewMessage(conn, enity.getRoomno());

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

	public void updateMessage(MessageRequest enity) throws SiDCException, SQLException {
		boolean isPass = false;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = MessageDao.getInstance().existMessage(conn, enity.getRoomno());
			if (isPass) {
				MessageDao.getInstance().updateMessage(conn, enity);
				String group_id = GroupMessageSendDao.getInstance().findGroupId(conn, enity.getRoomno());
				GroupMessagePageDao.getInstance().updateGroupMessagePage(conn, enity, group_id);
				GroupMessageDao.getInstance().updateGroupMessage(conn, enity, group_id);
			} else {
				throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "agent id does not exist.");
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

	public List<String> getMobilePushToken(final int mobileInfoId) throws SiDCException, SQLException {

		Connection conn = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list.add(MobileInfoDao.getInstance().getPushToken(conn, mobileInfoId));

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

	public List<String> getMobilePushToken(final String roomNo) throws SiDCException, SQLException {

		Connection conn = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = MobileInfoDao.getInstance().getPushToken(conn, roomNo);

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

	public List<String> getMobilePushTokenByGroupId(final String groupId) throws SiDCException, SQLException {

		Connection conn = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			List<String> roomList = GuestDao.getInstance().selectRoomList(conn, groupId);

			for (final String roomNo : roomList) {
				list.addAll(MobileInfoDao.getInstance().getPushToken(conn, roomNo));
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

	public List<String> getMobileInfoId(final String roomNo) throws SiDCException, SQLException {

		Connection conn = null;
		List<String> mobileIdList = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final String token = TokenHeaderDao.getInstance().select(conn, roomNo);
			mobileIdList = TokenRoomDetailDao.getInstance().selectMobileInfoId(conn, roomNo, token);

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return mobileIdList;
	}

	public List<String> getMobileInfoIdByGroupId(final String groupId) throws SiDCException, SQLException {

		Connection conn = null;
		List<String> mobileIdList = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<String> roomList = GuestDao.getInstance().selectRoomList(conn, groupId);

			for (final String roomNo : roomList) {
				final String token = TokenHeaderDao.getInstance().select(conn, roomNo);
				mobileIdList = TokenRoomDetailDao.getInstance().selectMobileInfoId(conn, roomNo, token);
			}

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return mobileIdList;
	}

	public List<String> getMobileInfoIdWithCheckin() throws SiDCException, SQLException {

		Connection conn = null;
		List<String> mobileIdList = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<String> roomList = RoomDao.getInstance().listRoomOfCheckIn(conn);

			for (final String roomNo : roomList) {
				final String token = TokenHeaderDao.getInstance().select(conn, roomNo);
				mobileIdList.addAll(TokenRoomDetailDao.getInstance().selectMobileInfoId(conn, roomNo, token));
			}

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return mobileIdList;
	}

	public List<String> getMobilePushTokenWithAllCheckIn() throws SiDCException, SQLException {

		Connection conn = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<String> roomList = RoomDao.getInstance().listRoomOfCheckIn(conn);

			for (final String roomNo : roomList) {
				list.addAll(MobileInfoDao.getInstance().getPushToken(conn, roomNo));
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

	public List<String> insertChatMessageWithHotel(final String roomNo, final String senderName,
			final List<String> receiverNameList, final String message) throws SiDCException, SQLException {

		Connection conn = null;
		List<String> idList = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final String receiverName : receiverNameList) {
				idList.add(ChatMettingDao.getInstance().insert(conn, "chat", roomNo, senderName, 0, receiverName, 1,
						null, message));
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
		return idList;
	}

	public List<String> insertNewlettersMessage(final String roomNo, final String senderName,
			final List<String> receiverNameList, final String title, final String message,
			final List<ActivityPhotoUploadBean> photoList) throws SiDCException, SQLException {

		Connection conn = null;
		List<String> idList = new ArrayList<String>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final String receiverName : receiverNameList) {
				idList.add(ChatMettingDao.getInstance().insert(conn, "newsletters", roomNo, senderName, 0, receiverName,
						1, title, message));
			}

			for (final String id : idList) {
				// photo 處理
				for (final ActivityPhotoUploadBean photoEntity : photoList) {
					PhotoDao.getInstance().insert(conn, String.valueOf(id),
							"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
							photoEntity.getName() + "." + photoEntity.getExtension(), "1",
							PhotoType.NEWSLETTERS.toString());
				}

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
		return idList;
	}

	public String insertChatMessageWithGuest(final String publicKey, final String privateKey, final String roomNo,
			final String senderName, final String message) throws SQLException {

		Connection conn = null;
		String messageId = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			// 剛開始建立資料時，不一定會有 user name
			// 這邊補上
			final int mobileInfoId = TokenKeyDao.getInstance().selectMobileInfoId(conn, publicKey, privateKey);
			MobileInfoDao.getInstance().updateName(conn, mobileInfoId, senderName);

			// 處理 接收人員
			List<String> receiverList = RoomAllocationDao.getInstance().selectBelongId(conn, roomNo, 1);

			if (receiverList.isEmpty()) {
				final String staffId = RoomAllocationDao.getInstance().getAllocationStaffId(conn, 1);

				if (StringUtils.isBlank(staffId)) {
					throw new SiDCException(APIStatus.STAFF_OFFLINE, "no staff online.");
				}

				// 分配給平均處理數量最小的 就將這個訊息分配給他
				RoomAllocationDao.getInstance().insert(conn, roomNo, staffId, 1);
				receiverList.add(staffId);
			}

			for (final String id : receiverList) {
				messageId = ChatMettingDao.getInstance().insert(conn, "chat", roomNo, String.valueOf(mobileInfoId), 1,
						id, 0, null, message);
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
		return messageId;
	}

	public List<ChatMettingMessageBean> selectGuestMessageList(final String roomNo, final String device)
			throws SiDCException, SQLException {

		Connection conn = null;
		List<ChatMettingMessageBean> list = new ArrayList<ChatMettingMessageBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileInfoId = MobileInfoDao.getInstance().getId(conn, device);

			if (mobileInfoId <= 0)
				throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find push token.");

			list = ChatMettingDao.getInstance().selectMessage(conn, roomNo, mobileInfoId);

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

	public List<NewslettersBean> selectNewslettersList(final String newslettersId, final String device)
			throws SQLException {

		Connection conn = null;
		List<NewslettersBean> list = new ArrayList<NewslettersBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileInfoId = MobileInfoDao.getInstance().getId(conn, device);

			list = ChatMettingDao.getInstance().selectNewslettersMessage(conn, newslettersId, mobileInfoId);

			for (NewslettersBean entity : list) {
				entity.setPhotolist(
						PhotoDao.getInstance().selectByReferid(conn, newslettersId, PhotoType.NEWSLETTERS.toString()));
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

	public void updateStatus(final List<String> idList, final int status) throws SiDCException, SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final String id : idList) {
				ChatMettingDao.getInstance().updateStatus(conn, id, status);
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

	public boolean isExistByDevice(final String device) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (MobileInfoDao.getInstance().getId(conn, device) > 0) {
				isExist = true;
			}
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
}
