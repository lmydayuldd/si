package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.guest.bean.GuestInfoBean;
import com.sidc.blackcore.api.sits.spare.bean.SpareLangBean;
import com.sidc.blackcore.api.sits.spare.bean.SpareOrderItemBean;
import com.sidc.blackcore.api.sits.spare.bean.SpareOrderLineAmountBean;
import com.sidc.blackcore.api.sits.spare.response.SpareBackendOrderResponse;
import com.sidc.blackcore.api.sits.spare.response.SpareCategoryResponse;
import com.sidc.blackcore.api.sits.spare.response.SpareItemResponse;
import com.sidc.blackcore.api.sits.spare.response.SpareOrderResponse;
import com.sidc.blackcore.api.sits.type.ConsType;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.bill.BillDao;
import com.sidc.dao.sits.cons.ConsDao;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.dao.sits.mobileinfo.MobileInfoDao;
import com.sidc.dao.sits.photo.PhotoDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.shoppingitem.ShoppingItemDao;
import com.sidc.dao.sits.sparecategory.SpareCategoryDao;
import com.sidc.dao.sits.sparecategorylang.SpareCategoryLangDao;
import com.sidc.dao.sits.spareitem.SpareItemDao;
import com.sidc.dao.sits.spareitemlang.SpareItemLangDao;
import com.sidc.dao.sits.spareorderheader.SpareOrderHeaderDao;
import com.sidc.dao.sits.spareorderline.SpareOrderLineDao;
import com.sidc.dao.sits.tokenkey.TokenKeyDao;
import com.sidc.dao.sits.tokenstaffdetail.TokenStaffDetailDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class SpareManager {
	private SpareManager() {
	}

	private static class LazyHolder {
		public static final SpareManager INSTANCE = new SpareManager();
	}

	public static SpareManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public int insertCategory(final int status, final int sequence, final List<SpareLangBean> langList,
			final List<ActivityPhotoUploadBean> photoList) throws SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = SpareCategoryDao.getInstance().insert(conn, status, sequence);

			for (final SpareLangBean entity : langList) {
				SpareCategoryLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
						entity.getDescription());
			}

			// photo 處理
			for (final ActivityPhotoUploadBean photoEntity : photoList) {
				PhotoDao.getInstance().insert(conn, String.valueOf(id),
						"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
						photoEntity.getName() + "." + photoEntity.getExtension(), "1",
						PhotoType.SPARECATEGORY.toString());
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
		return id;
	}

	public int insertItem(final int categoryId, final String status, final int sequence, final float price,
			final int qty, final List<SpareLangBean> langList, final List<ActivityPhotoUploadBean> photoList)
			throws SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = SpareItemDao.getInstance().insert(conn, categoryId, status, sequence, price, qty);

			for (final SpareLangBean entity : langList) {
				SpareItemLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
						entity.getDescription());
			}

			// photo 處理
			for (final ActivityPhotoUploadBean photoEntity : photoList) {
				PhotoDao.getInstance().insert(conn, String.valueOf(id),
						"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
						photoEntity.getName() + "." + photoEntity.getExtension(), "1", PhotoType.SPAREITEM.toString());
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
		return id;
	}

	public boolean isExistByCategoryId(final int id) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = SpareCategoryDao.getInstance().isExist(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean isExistByItemId(final int id) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = ShoppingItemDao.getInstance().isExist(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean isExistAndEnableByItemId(final List<Integer> idList) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final int id : idList) {
				if (!ShoppingItemDao.getInstance().isExist(conn, id, "1")) {
					return false;
				}
			}
			isPass = true;
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean isExistByOrderId(final int id) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = SpareOrderHeaderDao.getInstance().isExist(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	/**
	 * 參數有 token 適用 mobile create order, sits tv create order 另議
	 * 
	 * @throws SiDCException
	 */
	public int orderCreate(final String publicKey, final String privateKey, final String roomNo, final String guestNo,
			final String guestFirstName, final String guestLastName, final String memo,
			final List<SpareOrderItemBean> itemList) throws SQLException, SiDCException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = TokenKeyDao.getInstance().selectMobileInfoId(conn, publicKey, privateKey);

			final String billNo = BillDao.getInstance().searchBillNoWithCheckIn(conn, roomNo);

			float amount = 0;

			id = SpareOrderHeaderDao.getInstance().insert(conn, itemList.size(), amount, memo, roomNo, guestNo,
					guestFirstName, guestLastName, billNo, mobileId);

			for (final SpareOrderItemBean entity : itemList) {
				final SpareItemResponse itemEntity = SpareItemDao.getInstance().select(conn, entity.getItemid());

				// 查庫存量 購不購賣
				if (itemEntity.getQty() - entity.getQty() < 0) {
					throw new SiDCException(APIStatus.SHOP_INSUFFICIENT_STOCK, "item id:" + itemEntity.getItemid());
				} else if (itemEntity.getQty() - entity.getQty() == 0) {
					// 賣光 改狀態 才不會繼續賣
					SpareItemDao.getInstance().updateStatus(conn, itemEntity.getItemid(), "20");
				}

				SpareOrderLineDao.getInstance().insert(conn, id, itemEntity.getCategoryid(), itemEntity.getItemid(),
						itemEntity.getPrice(), entity.getQty());

				SpareItemDao.getInstance().updateQty(conn, itemEntity.getItemid(),
						itemEntity.getQty() - entity.getQty());

				amount += entity.getQty() * itemEntity.getPrice();
			}

			SpareOrderHeaderDao.getInstance().updateAmount(conn, id, amount);

			conn.commit();
		} catch (SiDCException e) {
			conn.rollback();
			throw new SiDCException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return id;
	}

	public void updateOrderStatusDeleteCons(final int orderId, final String status) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			final String billNo = SpareOrderHeaderDao.getInstance().findBillNo(conn, orderId);

			final short consType = Short.valueOf(ConsType.SPARE.getValue());

			ConsDao.getInstance().deleteConsByReferId(conn, billNo, consType, String.valueOf(orderId));

			SpareOrderHeaderDao.getInstance().updateStatus(conn, orderId, status);

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

	public String selectOrderStatus(final int id) throws SQLException {

		Connection conn = null;
		String status = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			status = SpareOrderHeaderDao.getInstance().selectStatus(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return status;
	}

	public void updateCategory(final int categoryId, final int status, final int sequence,
			final List<SpareLangBean> langList, final boolean isPhotoUpdate,
			final List<ActivityPhotoUploadBean> photoList) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			SpareCategoryDao.getInstance().update(conn, categoryId, status, sequence);

			SpareCategoryLangDao.getInstance().delete(conn, categoryId);

			for (final SpareLangBean entity : langList) {
				SpareCategoryLangDao.getInstance().insert(conn, categoryId, entity.getLangcode(), entity.getName(),
						entity.getDescription());
			}

			if (isPhotoUpdate) {
				PhotoDao.getInstance().delete(conn, String.valueOf(categoryId), PhotoType.SPARECATEGORY.toString());
				for (final ActivityPhotoUploadBean photoEntity : photoList) {
					PhotoDao.getInstance().insert(conn, String.valueOf(categoryId),
							"/" + categoryId + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
							photoEntity.getName() + "." + photoEntity.getExtension(), "1",
							PhotoType.SPARECATEGORY.toString());
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
	}

	public void updateOrderStatus(final int orderId, final String status) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			SpareOrderHeaderDao.getInstance().updateStatus(conn, orderId, status);

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

	public void updateOrderStatusCreateCons(final int orderId, final String status) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			String langCode = "en_US";
			final String billNo = SpareOrderHeaderDao.getInstance().findBillNo(conn, orderId);
			final String roomNo = SpareOrderHeaderDao.getInstance().findRoomNo(conn, orderId);
			final String guestNo = SpareOrderHeaderDao.getInstance().findGuestNo(conn, orderId);

			if (StringUtils.isBlank(guestNo)) {
				final List<GuestInfoBean> guestList = GuestDao.getInstance().selectGuestInfo(conn, roomNo);
				// 暫時
				if (!guestList.isEmpty()) {
					langCode = guestList.get(0).getNational();
				}
			} else {
				langCode = GuestDao.getInstance().selectGuest(conn, roomNo, guestNo).getNational();
			}

			final List<SpareOrderLineAmountBean> list = SpareOrderLineDao.getInstance().selectAmount(conn, orderId);

			final short consType = Short.valueOf(ConsType.SPARE.getValue());

			for (final SpareOrderLineAmountBean entity : list) {
				/**
				 * 如果沒資料 用通用的英文來找 ,萬一沒資料就DB任選一筆,還是沒有就拋錯誤... 否則 PMS 那端 沒資料顯示
				 */
				String itemName = SpareItemLangDao.getInstance().selectName(conn, entity.getItemid(), langCode);

				if (StringUtils.isBlank(itemName)) {
					itemName = SpareItemLangDao.getInstance().selectName(conn, entity.getItemid(), "en_US");
					if (StringUtils.isBlank(itemName)) {
						itemName = SpareItemLangDao.getInstance().selectName(conn, entity.getItemid());
					}
				}

				if (StringUtils.isBlank(itemName)) {
					throw new SQLException("not find item name lang data.");
				}

				// 0元不用送cons
				if (entity.getAmount() <= 0) {
					continue;
				}

				ConsDao.getInstance().insertCons(conn, UUID.randomUUID().toString().replace("-", ""), billNo,
						entity.getAmount() * entity.getQty(), consType, itemName,
						ConsDao.getInstance().findMaxSequence(conn) + 1, roomNo, String.valueOf(orderId));
			}

			SpareOrderHeaderDao.getInstance().updateStatus(conn, orderId, status);

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

	public void updateItem(final int itemId, final int categoryId, final String status, final int sequence,
			final float price, final int qty, final List<SpareLangBean> langList,
			final List<ActivityPhotoUploadBean> photoList, final boolean isPhotoUpdate) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			SpareItemDao.getInstance().update(conn, itemId, categoryId, status, sequence, price, qty);

			SpareItemLangDao.getInstance().delete(conn, itemId);

			for (final SpareLangBean entity : langList) {
				SpareItemLangDao.getInstance().insert(conn, itemId, entity.getLangcode(), entity.getName(),
						entity.getDescription());
			}

			if (isPhotoUpdate) {
				PhotoDao.getInstance().delete(conn, String.valueOf(itemId), PhotoType.SPAREITEM.toString());
				// photo 處理
				for (final ActivityPhotoUploadBean photoEntity : photoList) {
					PhotoDao.getInstance().insert(conn, String.valueOf(itemId),
							"/" + itemId + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
							photoEntity.getName() + "." + photoEntity.getExtension(), "1",
							PhotoType.SPAREITEM.toString());
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
	}

	public List<SpareCategoryResponse> selectCategory(final int categoryId, final int status, final String langCode)
			throws SQLException {
		Connection conn = null;
		List<SpareCategoryResponse> list = new ArrayList<SpareCategoryResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = SpareCategoryDao.getInstance().selectCategory(conn, categoryId, status);

			for (SpareCategoryResponse entity : list) {
				// 多語系
				entity.setLangs(SpareCategoryLangDao.getInstance().select(conn, entity.getCategoryid(), langCode));
				// 圖片唷
				entity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn, String.valueOf(entity.getCategoryid()),
						PhotoType.SPARECATEGORY.toString()));
			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<SpareItemResponse> selectItem(final int itemId, final int categoryId, final String status,
			final String langCode) throws SQLException {
		Connection conn = null;
		List<SpareItemResponse> list = new ArrayList<SpareItemResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = SpareItemDao.getInstance().select(conn, itemId, categoryId, status);

			for (SpareItemResponse entity : list) {
				// 多語系
				entity.setLangs(SpareItemLangDao.getInstance().select(conn, entity.getItemid(), langCode));
				// 圖片唷
				entity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn, String.valueOf(entity.getItemid()),
						PhotoType.SPAREITEM.toString()));
			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<SpareOrderResponse> selectOrder(final String deviceid, final int orderId, final String status,
			final String startTime, final String endTime, final String langCode) throws SQLException {

		Connection conn = null;
		List<SpareOrderResponse> list = new ArrayList<SpareOrderResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = MobileInfoDao.getInstance().getId(conn, deviceid);

			list = SpareOrderHeaderDao.getInstance().select(conn, mobileId, orderId, status, startTime, endTime);

			for (SpareOrderResponse entity : list) {
				// 撈 order line 資料
				entity.setItemlist(SpareOrderLineDao.getInstance().select(conn, entity.getId(), langCode));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	/**
	 * step 1:藉由 category,item 去找到 order line 的 order id step 2:再用其他條件下去篩選
	 * 
	 * @throws SQLException
	 */
	public List<SpareBackendOrderResponse> selectOrderWithBackend(final String roomNo, final int orderId,
			final String categoryIdWhereInStr, final String itemIdWhereInStr, final String status,
			final String startTime, final String endTime, final String langCode) throws SQLException {

		Connection conn = null;
		List<SpareBackendOrderResponse> list = new ArrayList<SpareBackendOrderResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			// order id 清單 藉由 category,vendor,item
			final List<Integer> orderIdList = SpareOrderLineDao.getInstance().selectOrderIdByWhereIn(conn,
					categoryIdWhereInStr, itemIdWhereInStr);

			// 沒資料就直接 return 否則 下面會把資料撈出來
			if (orderIdList.isEmpty()) {
				return new ArrayList<SpareBackendOrderResponse>();
			}

			/**
			 * sql where in 整理 不用 setArray 因為 sits MySQL版本太舊... 會出現
			 * SQLFeatureNotSupportedException, MySQL version 5.0 2017/08/07
			 */
			String orderIdWhereInStr = "";
			for (final int id : orderIdList) {
				orderIdWhereInStr += "\'" + String.valueOf(id) + "\',";
			}

			if (!StringUtils.isBlank(orderIdWhereInStr)) {
				orderIdWhereInStr = orderIdWhereInStr.substring(0, orderIdWhereInStr.length() - 1);
			}

			list = SpareOrderHeaderDao.getInstance().select(conn, orderId, orderIdWhereInStr, roomNo, status, startTime,
					endTime);

			for (SpareBackendOrderResponse entity : list) {
				entity.setItemlist(SpareOrderLineDao.getInstance().select(conn, entity.getId(), langCode));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<SpareBackendOrderResponse> selectOrderWithBackend(final String roomNo, final int orderId,
			final String status, final String startTime, final String endTime, final String langCode)
			throws SQLException {

		Connection conn = null;
		List<SpareBackendOrderResponse> list = new ArrayList<SpareBackendOrderResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = SpareOrderHeaderDao.getInstance().select(conn, orderId, null, roomNo, status, startTime, endTime);

			for (SpareBackendOrderResponse entity : list) {
				entity.setItemlist(SpareOrderLineDao.getInstance().select(conn, entity.getId(), langCode));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public void itemUpdateCheck(final int categoryId, final int itemId) throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!SpareCategoryDao.getInstance().isExist(conn, categoryId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find category id).");
			}

			if (!SpareItemDao.getInstance().isExist(conn, itemId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find item id).");
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void orderCreateCheck(final String roomNo, final String guestNo, final List<Integer> itemIdList)
			throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!RoomDao.getInstance().isCheckIn(conn, roomNo)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room not check in.");
			}
			for (final int itemId : itemIdList) {
				if (!SpareItemDao.getInstance().isExist(conn, itemId, "10")) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
							"request illegal(not find item id or item id not enable).");
				}
			}
			if (!StringUtils.isBlank(guestNo)) {
				if (!GuestDao.getInstance().selectByIdGuestNo(conn, guestNo, roomNo)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find guest no.");
				}
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void backendOrderListCheck(final String roomNo, final String token, final String staffId)
			throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final String id = TokenStaffDetailDao.getInstance().selectStaffIdByToken(conn, token);

			if (!id.equals(staffId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "token and staff id is not match.");
			}

			if (!StringUtils.isBlank(roomNo)) {
				if (!RoomDao.getInstance().isExist(conn, roomNo)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(not find room no).");
				}
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public String findOrderMobilePushToken(final int orderId) throws SQLException, SiDCException {

		Connection conn = null;
		String pushToken = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = SpareOrderHeaderDao.getInstance().findMobileId(conn, orderId);

			pushToken = MobileInfoDao.getInstance().getPushToken(conn, mobileId);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return pushToken;
	}
}
