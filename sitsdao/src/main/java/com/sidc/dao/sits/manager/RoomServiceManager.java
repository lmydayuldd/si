package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.guest.bean.GuestInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceBackendOrderInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceCategoryBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemCategoryBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemIdBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderHeaderInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineAmountBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderLineInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderSetBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceOrderSetItemBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetCategoryBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetItemBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetItemInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetItemListBean;
import com.sidc.blackcore.api.sits.roomservice.reponse.RoomServiceCategoryResponse;
import com.sidc.blackcore.api.sits.roomservice.reponse.RoomServiceItemResponse;
import com.sidc.blackcore.api.sits.type.ConsType;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.bill.BillDao;
import com.sidc.dao.sits.cons.ConsDao;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.dao.sits.mobileinfo.MobileInfoDao;
import com.sidc.dao.sits.photo.PhotoDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.roomservicecategory.RoomServiceCategoryDao;
import com.sidc.dao.sits.roomservicecategoryitemlimit.RoomServiceCategoryItemLimitDao;
import com.sidc.dao.sits.roomservicecategorylang.RoomServiceCategoryLangDao;
import com.sidc.dao.sits.roomserviceitem.RoomServiceItemDao;
import com.sidc.dao.sits.roomserviceitemlang.RoomServiceItemLangDao;
import com.sidc.dao.sits.roomserviceitemtoset.RoomServiceItemToSetDao;
import com.sidc.dao.sits.roomserviceorderheader.RoomServiceOrderHeaderDao;
import com.sidc.dao.sits.roomserviceorderline.RoomServiceOrderLineDao;
import com.sidc.dao.sits.tokenkey.TokenKeyDao;
import com.sidc.dao.sits.tokenstaffdetail.TokenStaffDetailDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class RoomServiceManager {
	private RoomServiceManager() {
	}

	private static class LazyHolder {
		public static final RoomServiceManager INSTANCE = new RoomServiceManager();
	}

	public static RoomServiceManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public int insertCategory(final int status, final int referId, final String serviceStartTime,
			final String serviceEndTime, final int sequence, final List<RoomServiceLangBean> langList,
			final List<ActivityPhotoUploadBean> photoList) throws SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = RoomServiceCategoryDao.getInstance().insert(conn, status, referId, serviceStartTime, serviceEndTime,
					sequence);

			// ==0 代表是分類最上層
			if (referId == 0) {
				RoomServiceCategoryDao.getInstance().updateReferId(conn, id, id);
			}

			for (final RoomServiceLangBean langEntity : langList) {
				RoomServiceCategoryLangDao.getInstance().insert(conn, id, langEntity.getLangcode(),
						langEntity.getName(), langEntity.getDescription());
			}

			// photo 處理
			for (final ActivityPhotoUploadBean photoEntity : photoList) {
				PhotoDao.getInstance().insert(conn, String.valueOf(id),
						"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
						photoEntity.getName() + "." + photoEntity.getExtension(), "1",
						PhotoType.ROOMSERVICECATEGORY.toString());
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

	public void updateCategory(final int id, final int status, final int referId, final String serviceStartTime,
			final String serviceEndTime, final int sequence, final List<RoomServiceLangBean> langList,
			final boolean isPhotoUpdate, final List<ActivityPhotoUploadBean> photoList) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RoomServiceCategoryDao.getInstance().update(conn, id, status, referId, serviceStartTime, serviceEndTime,
					sequence);

			RoomServiceCategoryLangDao.getInstance().delete(conn, id);

			for (final RoomServiceLangBean langEntity : langList) {
				RoomServiceCategoryLangDao.getInstance().insert(conn, id, langEntity.getLangcode(),
						langEntity.getName(), langEntity.getDescription());
			}

			if (isPhotoUpdate) {
				PhotoDao.getInstance().delete(conn, String.valueOf(id), PhotoType.ROOMSERVICECATEGORY.toString());
				for (final ActivityPhotoUploadBean photoEntity : photoList) {
					PhotoDao.getInstance().insert(conn, String.valueOf(id),
							"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
							photoEntity.getName() + "." + photoEntity.getExtension(), "1",
							PhotoType.ROOMSERVICECATEGORY.toString());
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

	public boolean isExistByCategoryId(final int id) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = RoomServiceCategoryDao.getInstance().isExist(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean isExistByCategoryId(final List<Integer> list) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final int categoryId : list) {
				if (!RoomServiceCategoryDao.getInstance().isExist(conn, categoryId)) {
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

	public boolean isExistByCategoryReferId(final int referId) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = RoomServiceCategoryDao.getInstance().isExistByReferId(conn, referId);

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

			isPass = RoomServiceOrderHeaderDao.getInstance().isExist(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean isExistBySet(final List<RoomServiceOrderSetBean> list) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final RoomServiceOrderSetBean entity : list) {
				for (final RoomServiceSetItemListBean itemListEntity : entity.getIteminfo()) {
					for (final RoomServiceOrderSetItemBean itemEntity : itemListEntity.getItemlist()) {
						if (!RoomServiceItemToSetDao.getInstance().isExist(conn, entity.getSetitemid(),
								itemEntity.getItemid())) {
							return false;
						}
					}
				}
				isPass = true;
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean isSingleWithItem(final List<Integer> list) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final int id : list) {
				if (RoomServiceItemDao.getInstance().isSet(conn, id)) {
					return false;
				}
				isPass = true;
			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean isEnableByItemId(final List<Integer> list) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final int id : list) {
				if (!RoomServiceItemDao.getInstance().isEnable(conn, id)) {
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

	public boolean isExistByMobileDevice(final String deviceId) throws SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (MobileInfoDao.getInstance().getId(conn, deviceId) > 0) {
				isExist = true;
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isExist;
	}

	public int insertItem(final int categoryId, final int status, final int sequence, final float price,
			final String type, final List<RoomServiceLangBean> langList, final List<RoomServiceSetBean> setList)
			throws SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = RoomServiceItemDao.getInstance().insert(conn, status, categoryId, price, type, sequence);

			for (final RoomServiceLangBean langEntity : langList) {
				RoomServiceItemLangDao.getInstance().insert(conn, id, langEntity.getLangcode(), langEntity.getName(),
						langEntity.getDescription());
			}

			for (final RoomServiceSetBean setEntity : setList) {
				for (final RoomServiceItemIdBean itemEntity : setEntity.getItemidlist()) {
					RoomServiceItemToSetDao.getInstance().insert(conn, id, itemEntity.getItemid());
				}
				if (setEntity.getLimitqty() > 0) {
					RoomServiceCategoryItemLimitDao.getInstance().insert(conn, setEntity.getCategoryid(), id,
							setEntity.getLimitqty());
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
		return id;
	}

	public void updateItem(final int id, final int status, final int sequence, final int categoryId, final float price,
			final String type, final List<RoomServiceLangBean> langList, final List<RoomServiceSetBean> setList,
			final boolean isPhotoUpdate, final List<ActivityPhotoUploadBean> photoList) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RoomServiceItemDao.getInstance().update(conn, id, categoryId, status, price, type, sequence);

			RoomServiceItemLangDao.getInstance().delete(conn, id);

			RoomServiceItemToSetDao.getInstance().delete(conn, id);

			RoomServiceCategoryItemLimitDao.getInstance().delete(conn, id);

			for (final RoomServiceLangBean langEntity : langList) {
				RoomServiceItemLangDao.getInstance().insert(conn, id, langEntity.getLangcode(), langEntity.getName(),
						langEntity.getDescription());
			}

			for (final RoomServiceSetBean setEntity : setList) {
				for (final RoomServiceItemIdBean itemEntity : setEntity.getItemidlist()) {
					RoomServiceItemToSetDao.getInstance().insert(conn, id, itemEntity.getItemid());
				}
				if (setEntity.getLimitqty() > 0) {
					RoomServiceCategoryItemLimitDao.getInstance().insert(conn, setEntity.getCategoryid(), id,
							setEntity.getLimitqty());
				}
			}

			if (isPhotoUpdate) {
				PhotoDao.getInstance().delete(conn, String.valueOf(id), PhotoType.ROOMSERVICEITEM.toString());
				for (final ActivityPhotoUploadBean photoEntity : photoList) {
					PhotoDao.getInstance().insert(conn, String.valueOf(id),
							"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
							photoEntity.getName() + "." + photoEntity.getExtension(), "1",
							PhotoType.ROOMSERVICEITEM.toString());
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

	public boolean isExistByItemId(final int id) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = RoomServiceItemDao.getInstance().isExist(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean isExistByItemId(final List<Integer> list) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final int itemId : list) {
				if (!RoomServiceItemDao.getInstance().isExist(conn, itemId)) {
					return false;
				}
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return true;
	}

	public String selectOrderStatus(final int id) throws SQLException {

		Connection conn = null;
		String status = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			status = RoomServiceOrderHeaderDao.getInstance().selectStatus(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return status;
	}

	/*
	 * 給user查資料的 一定要帶 device
	 */
	public List<RoomServiceOrderHeaderInfoBean> selectOrder(final String deviceid, final int orderId,
			final String status, final String startTime, final String endTime, final String langCode)
			throws SQLException {

		Connection conn = null;
		List<RoomServiceOrderHeaderInfoBean> list = new ArrayList<RoomServiceOrderHeaderInfoBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = MobileInfoDao.getInstance().getId(conn, deviceid);

			list = RoomServiceOrderHeaderDao.getInstance().select(conn, mobileId, orderId, status, startTime, endTime);

			for (RoomServiceOrderHeaderInfoBean entity : list) {
				// 撈 order line 資料
				List<RoomServiceOrderLineInfoBean> itemList = RoomServiceOrderLineDao.getInstance().selectInfo(conn,
						entity.getId(), 0, langCode);
				// 取得Category item 名稱
				for (RoomServiceOrderLineInfoBean itemEntity : itemList) {
					if (RoomServiceItemDao.getInstance().isSet(conn, itemEntity.getItemid())) {
						final List<RoomServiceOrderLineInfoBean> setItemList = RoomServiceOrderLineDao.getInstance()
								.selectInfo(conn, entity.getId(), itemEntity.getId(), langCode);
						itemEntity.setSetitemlist(setItemList);
					}
				}
				entity.setItemlist(itemList);
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<RoomServiceBackendOrderInfoBean> selectOrderWithBackend(final String roomNo, final int orderId,
			final String status, final String startTime, final String endTime, final String langCode)
			throws SQLException {

		Connection conn = null;
		List<RoomServiceBackendOrderInfoBean> list = new ArrayList<RoomServiceBackendOrderInfoBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RoomServiceOrderHeaderDao.getInstance().select(conn, orderId, status, startTime, endTime, roomNo);

			for (RoomServiceBackendOrderInfoBean entity : list) {
				// 撈 order line 資料
				List<RoomServiceOrderLineInfoBean> itemList = RoomServiceOrderLineDao.getInstance().selectInfo(conn,
						entity.getId(), 0, langCode);

				// 取得Category item 名稱
				for (RoomServiceOrderLineInfoBean itemEntity : itemList) {
					if (RoomServiceItemDao.getInstance().isSet(conn, itemEntity.getItemid())) {
						final List<RoomServiceOrderLineInfoBean> setItemList = RoomServiceOrderLineDao.getInstance()
								.selectInfo(conn, entity.getId(), itemEntity.getId(), langCode);
						itemEntity.setSetitemlist(setItemList);
					}
				}
				entity.setItemlist(itemList);
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<RoomServiceCategoryBean> selectCategory(final int status, final String langCode) throws SQLException {

		Connection conn = null;
		List<RoomServiceCategoryBean> list = new ArrayList<RoomServiceCategoryBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RoomServiceCategoryDao.getInstance().selectTopLevelCategory(conn, status, langCode);

			for (RoomServiceCategoryBean categoryEntity : list) {
				// 圖片唷
				categoryEntity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn,
						String.valueOf(categoryEntity.getCategoryid()), PhotoType.ROOMSERVICECATEGORY.toString()));

				List<RoomServiceItemBean> itemList = RoomServiceItemDao.getInstance().selectItem(conn, 1,
						categoryEntity.getCategoryid(), langCode);

				for (RoomServiceItemBean itemEntity : itemList) {
					// set 還要去撈其他資料塞進去
					if (itemEntity.getType().equals("set")) {
						List<RoomServiceSetCategoryBean> setCategoryList = RoomServiceCategoryDao.getInstance()
								.select(conn, itemEntity.getItemid(), 1, langCode);

						for (RoomServiceSetCategoryBean setCategoryEntity : setCategoryList) {
							List<RoomServiceSetItemBean> setItemList = RoomServiceItemToSetDao.getInstance()
									.selectSetItem(conn, itemEntity.getItemid(), setCategoryEntity.getCategoryid(),
											langCode);
							setCategoryEntity.setItemlist(setItemList);
						}
						itemEntity.setSetlist(setCategoryList);
						itemEntity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn,
								String.valueOf(categoryEntity.getCategoryid()), PhotoType.ROOMSERVICEITEM.toString()));
					}
				}

				categoryEntity.setItemlist(itemList);

				List<RoomServiceCategoryBean> subCategoryList = RoomServiceCategoryDao.getInstance()
						.selectCategory(conn, status, categoryEntity.getCategoryid(), langCode);

				// 遞迴搜尋有沒有在下一層
				categoryEntity.setSublist(searchCategory(conn, subCategoryList, langCode, status));

			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<RoomServiceCategoryResponse> selectCategory(final int categoryId, final int referId, final int status,
			final String langCode) throws SQLException {

		Connection conn = null;
		List<RoomServiceCategoryResponse> list = new ArrayList<RoomServiceCategoryResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RoomServiceCategoryDao.getInstance().select(conn, categoryId, referId, status);

			for (RoomServiceCategoryResponse entity : list) {
				entity.setLangs(
						RoomServiceCategoryLangDao.getInstance().select(conn, entity.getCategoryid(), langCode));

				// 圖片唷
				entity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn, String.valueOf(entity.getCategoryid()),
						PhotoType.ROOMSERVICECATEGORY.toString()));

				List<RoomServiceCategoryResponse> subList = RoomServiceCategoryDao.getInstance().selectByReferId(conn,
						entity.getCategoryid());
				entity.setSublist(searchCategory(conn, subList, langCode));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<RoomServiceItemResponse> selectItem(final int categoryId, final int itemId, final int status,
			final String type, final String langCode) throws SQLException {

		Connection conn = null;
		List<RoomServiceItemResponse> list = new ArrayList<RoomServiceItemResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RoomServiceItemDao.getInstance().select(conn, categoryId, itemId, status, type);

			for (RoomServiceItemResponse entity : list) {
				// 多語系
				entity.setLangs(RoomServiceItemLangDao.getInstance().select(conn, entity.getItemid(), langCode));

				// 圖片
				entity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn, String.valueOf(entity.getItemid()),
						PhotoType.ROOMSERVICEITEM.toString()));

				// set 處理
				if (entity.getType().equals("set")) {

					List<RoomServiceItemCategoryBean> categoryList = RoomServiceItemDao.getInstance()
							.selectCategoryByItemId(conn, entity.getItemid());

					for (RoomServiceItemCategoryBean categoryEntity : categoryList) {

						List<RoomServiceSetItemInfoBean> itemList = RoomServiceItemToSetDao.getInstance()
								.selectByCategoryIdSetId(conn, entity.getItemid(), categoryEntity.getCategoryid());

						for (RoomServiceSetItemInfoBean itemEntity : itemList) {
							// 多語系
							itemEntity.setLangs(RoomServiceItemLangDao.getInstance().select(conn,
									itemEntity.getItemid(), langCode));

							// 圖片
							itemEntity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn,
									String.valueOf(itemEntity.getItemid()), PhotoType.ROOMSERVICEITEM.toString()));
						}
						// 多語系
						categoryEntity.setLangs(RoomServiceCategoryLangDao.getInstance().select(conn,
								categoryEntity.getCategoryid(), langCode));
						categoryEntity.setItemlist(itemList);

					}
					entity.setSetlist(categoryList);

				}
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	/**
	 * 參數有 token 適用 mobile create order sits tv create order 另議
	 */
	public int createOrder(final String roomNo, final String guestNo, final String guestFirstName,
			final String guestLastName, final String expectedTime, final String memo,
			final List<RoomServiceOrderLineBean> itemList, final List<RoomServiceOrderSetBean> setList,
			final String publicKey, final String privateKey) throws SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = TokenKeyDao.getInstance().selectMobileInfoId(conn, publicKey, privateKey);

			final String billNo = BillDao.getInstance().searchBillNoWithCheckIn(conn, roomNo);

			id = RoomServiceOrderHeaderDao.getInstance().insert(conn, itemList.size() + setList.size(), 0, expectedTime,
					memo, roomNo, guestNo, guestFirstName, guestLastName, billNo, mobileId);

			float amount = 0;

			// 單一項目
			for (final RoomServiceOrderLineBean entity : itemList) {
				// 取得 item資訊
				final RoomServiceItemInfoBean itemEntity = RoomServiceItemDao.getInstance()
						.selectItemWithOrderLine(conn, entity.getItemid());

				RoomServiceOrderLineDao.getInstance().insert(conn, id, entity.getItemid(), itemEntity.getCategoryid(),
						itemEntity.getPrice(), entity.getQty());

				amount += itemEntity.getPrice() * entity.getQty();
			}

			/**
			 * 處理set項目 一個 set 底下會有多個可選項目 只有 set 主項目 需要寫入價格 非主項目不列金額 且 reder id
			 * 指向 set 代表 選了哪些項目
			 */
			for (final RoomServiceOrderSetBean entity : setList) {
				// 取得 item資訊
				final RoomServiceItemInfoBean itemEntity = RoomServiceItemDao.getInstance()
						.selectItemWithOrderLine(conn, entity.getSetitemid());

				for (final RoomServiceSetItemListBean itemListEntity : entity.getIteminfo()) {
					final int itemId = RoomServiceOrderLineDao.getInstance().insert(conn, id, entity.getSetitemid(),
							itemEntity.getCategoryid(), itemEntity.getPrice(), 1);

					for (final RoomServiceOrderSetItemBean setItemEntity : itemListEntity.getItemlist()) {

						final RoomServiceItemInfoBean ItemInfoEntity = RoomServiceItemDao.getInstance()
								.selectItemWithOrderLine(conn, setItemEntity.getItemid());
						// 將 set底下 所選的商品 放入 order line 但是不寫金額 以最上層 set金額為主
						RoomServiceOrderLineDao.getInstance().insert(conn, id, setItemEntity.getItemid(),
								ItemInfoEntity.getCategoryid(), itemId, 0, 1);
					}
				}

				amount += itemEntity.getPrice() * entity.getQty();
			}

			RoomServiceOrderHeaderDao.getInstance().updateAmount(conn, id, amount, amount);

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

	public void updateOrderStatus(final int orderId, final String status) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RoomServiceOrderHeaderDao.getInstance().updateStatus(conn, orderId, status);

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
			final String billNo = RoomServiceOrderHeaderDao.getInstance().findBillNo(conn, orderId);
			final String roomNo = RoomServiceOrderHeaderDao.getInstance().findRoomNo(conn, orderId);
			final List<GuestInfoBean> guestList = GuestDao.getInstance().selectGuestInfo(conn, roomNo);

			// 暫時
			if (!guestList.isEmpty()) {
				langCode = guestList.get(0).getNational();
			}

			final List<RoomServiceOrderLineAmountBean> list = RoomServiceOrderLineDao.getInstance().selectAmount(conn,
					orderId);

			final short consType = Short.valueOf(ConsType.IN_ROOM_DINING.getValue());

			for (final RoomServiceOrderLineAmountBean entity : list) {
				/**
				 * 如果沒資料 用通用的英文來找 ,萬一沒資料就DB任選一筆,還是沒有就拋錯誤... 否則 PMS 那端 沒資料顯示
				 */
				String itemName = RoomServiceItemLangDao.getInstance().selectName(conn, entity.getItemid(), langCode);

				if (StringUtils.isBlank(itemName)) {
					itemName = RoomServiceItemLangDao.getInstance().selectName(conn, entity.getItemid(), "en_US");
					if (StringUtils.isBlank(itemName)) {
						itemName = RoomServiceItemLangDao.getInstance().selectName(conn, entity.getItemid());
					}
				}

				if (StringUtils.isBlank(itemName)) {
					throw new SQLException("not find item name lang data.");
				}

				ConsDao.getInstance().insertCons(conn, UUID.randomUUID().toString().replace("-", ""), billNo,
						entity.getAmount() * entity.getQty(), consType, itemName,
						ConsDao.getInstance().findMaxSequence(conn), roomNo, String.valueOf(orderId));
			}

			RoomServiceOrderHeaderDao.getInstance().updateStatus(conn, orderId, status);

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

	public void updateOrderStatusDeleteCons(final int orderId, final String status) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			final String billNo = RoomServiceOrderHeaderDao.getInstance().findBillNo(conn, orderId);

			ConsDao.getInstance().deleteConsByReferId(conn, billNo, 15, String.valueOf(orderId));

			RoomServiceOrderHeaderDao.getInstance().updateStatus(conn, orderId, status);

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

	public void orderCreateCheck(final String roomNo, final String guestNo, final List<Integer> itemList)
			throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!RoomDao.getInstance().isCheckIn(conn, roomNo)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(room not check in).");
			}

			if (!StringUtils.isBlank(guestNo)) {
				if (!GuestDao.getInstance().selectByIdGuestNo(conn, guestNo, roomNo)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find guest no.");
				}
			}

			for (final int itemId : itemList) {
				if (!RoomServiceItemDao.getInstance().isExist(conn, itemId)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find item id).");
				}
				if (!RoomServiceItemDao.getInstance().isEnable(conn, itemId)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(item id is no enable).");
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

	public void categoryupdateCheck(final int categoryId, final int referId) throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!RoomServiceCategoryDao.getInstance().isExist(conn, categoryId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find id).");
			}

			if (referId > 0) {
				if (!RoomServiceCategoryDao.getInstance().isExist(conn, referId)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not this refer id).");
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

			final int mobileId = RoomServiceOrderHeaderDao.getInstance().findMobileId(conn, orderId);

			pushToken = MobileInfoDao.getInstance().getPushToken(conn, mobileId);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return pushToken;
	}

	private List<RoomServiceCategoryBean> searchCategory(Connection conn, List<RoomServiceCategoryBean> list,
			final String langCode, final int status) throws SQLException {

		for (RoomServiceCategoryBean categoryEntity : list) {
			// 撈 分類底下的 項目 ex 果汁_蘋果汁
			List<RoomServiceItemBean> itemList = RoomServiceItemDao.getInstance().selectItem(conn, 1,
					categoryEntity.getCategoryid(), langCode);

			for (RoomServiceItemBean itemEntity : itemList) {
				itemEntity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn,
						String.valueOf(categoryEntity.getCategoryid()), PhotoType.ROOMSERVICEITEM.toString()));
			}
			categoryEntity.setItemlist(itemList);

			// 還有下一層類別
			if (categoryEntity.getCategoryid() != categoryEntity.getReferid()) {

				List<RoomServiceCategoryBean> subCategoryList = RoomServiceCategoryDao.getInstance()
						.selectCategory(conn, status, categoryEntity.getCategoryid(), langCode);

				if (subCategoryList.isEmpty()) {
					continue;
				}
				// 遞迴搜尋有沒有在下一層
				categoryEntity.setSublist(searchCategory(conn, subCategoryList, langCode, status));
			}
			// 圖片唷
			categoryEntity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn,
					String.valueOf(categoryEntity.getCategoryid()), PhotoType.ROOMSERVICECATEGORY.toString()));
		}

		return list;
	}

	private List<RoomServiceCategoryResponse> searchCategory(Connection conn, List<RoomServiceCategoryResponse> list,
			final String langCode) throws SQLException {

		for (RoomServiceCategoryResponse categoryEntity : list) {
			categoryEntity.setLangs(
					RoomServiceCategoryLangDao.getInstance().select(conn, categoryEntity.getCategoryid(), langCode));
			// 圖片唷
			categoryEntity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn,
					String.valueOf(categoryEntity.getCategoryid()), PhotoType.ROOMSERVICECATEGORY.toString()));

			// 還有下一層類別
			if (categoryEntity.getCategoryid() != categoryEntity.getReferid()) {
				List<RoomServiceCategoryResponse> subCategoryList = RoomServiceCategoryDao.getInstance()
						.selectByReferId(conn, categoryEntity.getCategoryid());

				if (subCategoryList.isEmpty()) {
					continue;
				}
				// 遞迴搜尋有沒有在下一層
				categoryEntity.setSublist(searchCategory(conn, subCategoryList, langCode));
			}

		}

		return list;
	}

}
