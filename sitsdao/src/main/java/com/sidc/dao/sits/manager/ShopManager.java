package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.guest.bean.GuestInfoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceInfoBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopItemBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopItemInfoBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopItemLangBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopLangBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopOrderItemBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopOrderLineAmountBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopPhotoUploadBean;
import com.sidc.blackcore.api.sits.shop.bean.ShopTypeInfoBean;
import com.sidc.blackcore.api.sits.shop.request.ShoppingListRequest;
import com.sidc.blackcore.api.sits.shop.request.ShoppingListUpdateRequest;
import com.sidc.blackcore.api.sits.shop.request.ShoppingRequest;
import com.sidc.blackcore.api.sits.shop.response.ShoppingBackendOrderResponse;
import com.sidc.blackcore.api.sits.shop.response.ShoppingCategoryResponse;
import com.sidc.blackcore.api.sits.shop.response.ShoppingItemResponse;
import com.sidc.blackcore.api.sits.shop.response.ShoppingOrderResponse;
import com.sidc.blackcore.api.sits.shop.response.ShoppingPriceResponse;
import com.sidc.blackcore.api.sits.shop.response.ShoppingResponse;
import com.sidc.blackcore.api.sits.shop.response.ShoppingVendorResponse;
import com.sidc.blackcore.api.sits.type.ConsType;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.bill.BillDao;
import com.sidc.dao.sits.cons.ConsDao;
import com.sidc.dao.sits.cons.ConsumeType;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.dao.sits.mobileinfo.MobileInfoDao;
import com.sidc.dao.sits.photo.PhotoDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.shop.ShopTypeDao;
import com.sidc.dao.sits.shoplanguage.ShopLanguageDao;
import com.sidc.dao.sits.shoporder.ShopOrderDao;
import com.sidc.dao.sits.shoppingcategory.ShoppingCategoryDao;
import com.sidc.dao.sits.shoppingcategorylang.ShoppingCategoryLangDao;
import com.sidc.dao.sits.shoppingitem.ShoppingItemDao;
import com.sidc.dao.sits.shoppingitemlang.ShoppingItemLangDao;
import com.sidc.dao.sits.shoppingvendor.ShoppingVendorDao;
import com.sidc.dao.sits.shoppingvendorlang.ShoppingVendorLangDao;
import com.sidc.dao.sits.shopppingorderheader.ShoppingOrderHeaderDao;
import com.sidc.dao.sits.shopppingorderline.ShoppingOrderLineDao;
import com.sidc.dao.sits.tokenkey.TokenKeyDao;
import com.sidc.dao.sits.tokenstaffdetail.TokenStaffDetailDao;
import com.sidc.dao.sits.typetoshop.TypeToShopDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class ShopManager {
	private ShopManager() {
	}

	private static class LazyHolder {
		public static final ShopManager INSTANCE = new ShopManager();
	}

	public static ShopManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public List<ShopTypeInfoBean> selectShop(final String langCode) throws SQLException {

		Connection conn = null;

		List<ShopTypeInfoBean> list = new ArrayList<ShopTypeInfoBean>();

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = ShopTypeDao.getInstance().selectShop(conn, langCode);

			for (ShopTypeInfoBean entity : list) {

				List<ShopItemInfoBean> itemInfoList = TypeToShopDao.getInstance().select(conn, entity.getCatalogueid(),
						langCode);

				if (!itemInfoList.isEmpty())
					entity.setShoppinglist(itemInfoList);
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<RoomServiceInfoBean> selectMeal(final String langCode) throws SQLException {

		Connection conn = null;

		List<RoomServiceInfoBean> list = new ArrayList<RoomServiceInfoBean>();

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = ShopTypeDao.getInstance().selectMeal(conn, langCode);

			for (RoomServiceInfoBean entity : list) {

				List<ShopItemInfoBean> itemInfoList = TypeToShopDao.getInstance().select(conn, entity.getCatalogueid(),
						langCode);

				if (!itemInfoList.isEmpty())
					entity.setRoomservicelist(itemInfoList);
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	private int findMaxSequence() throws SQLException {

		int sequence = 0;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			sequence = ConsDao.getInstance().findMaxSequence(conn);
			sequence += 1;
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return sequence;
	}

	private ShoppingResponse doInsertShop(final Connection conn, final ShoppingListRequest enity, final String billNo,
			final short type) throws SQLException {

		String orderId = "";
		float amounts = 0f;
		try {
			List<ShoppingPriceResponse> list = new ArrayList<ShoppingPriceResponse>();
			for (ShoppingRequest shop : enity.getList()) {
				list = ShopLanguageDao.getInstance().searchShopPrice(conn, shop.getId());
				for (ShoppingPriceResponse resp : list) {
					amounts += (resp.getPrice() * shop.getQuantity());
				}
			}

			String consId = UUID.randomUUID().toString().replace("-", "");
			ConsDao.getInstance().insertCons(conn, consId, billNo, amounts, type, ConsumeType.SHOP_CHARGE,
					findMaxSequence(), enity.getRoomno(), null);

			orderId = UUID.randomUUID().toString().replace("-", "");
			ShopOrderDao.getInstance().insertShopOrder(conn, orderId, consId, amounts, type);

			for (ShoppingRequest shop : enity.getList()) {
				ShopOrderDao.getInstance().insertShopOrderDetail(conn, orderId, shop.getId(), shop.getQuantity());
			}

		} catch (Exception e) {
			throw new SQLException(e);
		}

		return new ShoppingResponse(orderId, amounts);
	}

	public ShoppingResponse insert(final ShoppingListRequest enity, final String billNo, final short type)
			throws SQLException {

		ShoppingResponse response;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			response = doInsertShop(conn, enity, billNo, type);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return response;
	}

	public boolean doUpdateShop(final Connection conn, final ShoppingListUpdateRequest enity) throws SQLException {

		float amounts = 0f;
		try {
			String consId = ShopOrderDao.getInstance().findConsId(conn, enity.getOrderid());
			String status = ConsDao.getInstance().findConsStatus(conn, consId);

			if (status.equals(ConsumeType.POSTED))
				return false;
			List<ShoppingPriceResponse> list = new ArrayList<ShoppingPriceResponse>();
			for (ShoppingRequest shop : enity.getList()) {
				list = ShopLanguageDao.getInstance().searchShopPrice(conn, shop.getId());
				for (ShoppingPriceResponse resp : list) {
					amounts += (resp.getPrice() * shop.getQuantity());
				}
			}

			ShopOrderDao.getInstance().updateShopOrder(conn, enity.getOrderid(), amounts);
			ConsDao.getInstance().updateConsPrice(conn, consId, amounts);

			ShopOrderDao.getInstance().deleteShopOrderDetail(conn, enity.getOrderid());
			for (ShoppingRequest shop : enity.getList()) {
				ShopOrderDao.getInstance().insertShopOrderDetail(conn, enity.getOrderid(), shop.getId(),
						shop.getQuantity());
			}
		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		}

		return true;
	}

	public boolean update(final ShoppingListUpdateRequest enity) throws SQLException {

		boolean isUnpost = true;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			isUnpost = doUpdateShop(conn, enity);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return isUnpost;
	}

	private boolean doDeleteShop(final Connection conn, final String orderId) throws SQLException {

		try {
			String consId = ShopOrderDao.getInstance().findConsId(conn, orderId);
			String status = ConsDao.getInstance().findConsStatus(conn, consId);
			if (status.equals(ConsumeType.POSTED))
				return false;

			ShopOrderDao.getInstance().deleteShopOrderDetail(conn, orderId);
			ShopOrderDao.getInstance().deleteShopOrder(conn, orderId);
			ConsDao.getInstance().deleteCons(conn, consId);
		} catch (Exception e) {
			throw new SQLException(e);
		}

		return true;
	}

	public boolean delete(final String orderId) throws SQLException {

		boolean isUnpost = true;

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			isUnpost = doDeleteShop(conn, orderId);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return isUnpost;
	}

	public int insertCategory(final int status, final int sequence, final int referId,
			final List<ShopLangBean> langList, final List<ActivityPhotoUploadBean> photoList) throws SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = ShoppingCategoryDao.getInstance().insert(conn, status, referId, sequence);

			// 0 等於最上層 帶自己id
			if (referId == 0) {
				ShoppingCategoryDao.getInstance().updateReferId(conn, id, id);
			}

			for (final ShopLangBean entity : langList) {
				ShoppingCategoryLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
						entity.getDescription());
			}

			// photo 處理
			for (final ActivityPhotoUploadBean photoEntity : photoList) {
				PhotoDao.getInstance().insert(conn, String.valueOf(id),
						"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
						photoEntity.getName() + "." + photoEntity.getExtension(), "1",
						PhotoType.SHOPCATEGORY.toString());
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

	public int insertVendor(final int status, final String tex, final String email, final String address,
			final List<ShopLangBean> langList) throws SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = ShoppingVendorDao.getInstance().insert(conn, status, tex, email, address);

			for (final ShopLangBean entity : langList) {
				ShoppingVendorLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
						entity.getDescription());
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

	public int insertItem(final int categoryId, final int vendorId, final String status, final int sequence,
			final float price, final float sellingPrice, final int qty, final float weight,
			final List<ShopItemLangBean> langList, final List<ShopPhotoUploadBean> photoList) throws SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = ShoppingItemDao.getInstance().insert(conn, "1", categoryId, vendorId, status, sequence, price,
					sellingPrice, qty, weight);

			// 商品編號 待討論...
			ShoppingItemDao.getInstance().updateDocno(conn, id, String.valueOf(id));

			for (final ShopItemLangBean entity : langList) {
				ShoppingItemLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
						entity.getDescription(), entity.getIntroduction());
			}

			// photo 處理
			for (final ShopPhotoUploadBean photoEntity : photoList) {
				PhotoDao.getInstance().insert(conn, String.valueOf(id),
						"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
						photoEntity.getName() + "." + photoEntity.getExtension(), "1", PhotoType.SHOPITEM.toString());
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

			isPass = ShoppingCategoryDao.getInstance().isExist(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean isExistByCategoryReferId(final int id) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = ShoppingCategoryDao.getInstance().isExistByReferId(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isPass;
	}

	public boolean isExistByVendorId(final int id) throws SQLException {

		Connection conn = null;
		boolean isPass = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isPass = ShoppingVendorDao.getInstance().isExist(conn, id);

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

			isPass = ShoppingOrderHeaderDao.getInstance().isExist(conn, id);

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
			final List<ShopOrderItemBean> itemList) throws SQLException, SiDCException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = TokenKeyDao.getInstance().selectMobileInfoId(conn, publicKey, privateKey);
			// int mobileId = 1;
			final String billNo = BillDao.getInstance().searchBillNoWithCheckIn(conn, roomNo);

			float amount = 0;

			id = ShoppingOrderHeaderDao.getInstance().insert(conn, itemList.size(), amount, memo, roomNo, guestNo,
					guestFirstName, guestLastName, billNo, mobileId);

			for (final ShopOrderItemBean entity : itemList) {
				final ShopItemBean itemEntity = ShoppingItemDao.getInstance().selectItemInfo(conn, entity.getItemid());

				// 查庫存量 購不購賣
				if (itemEntity.getQty() - entity.getQty() < 0) {
					throw new SiDCException(APIStatus.SHOP_INSUFFICIENT_STOCK, "item id:" + itemEntity.getItemid());
				} else if (itemEntity.getQty() - entity.getQty() == 0) {
					// 賣光 改狀態 才不會繼續賣
					ShoppingItemDao.getInstance().updateStatus(conn, itemEntity.getItemid(), "2");
				}

				ShoppingOrderLineDao.getInstance().insert(conn, id, itemEntity.getCategoryid(),
						itemEntity.getVendoryid(), itemEntity.getItemid(), itemEntity.getSellingPrice(),
						entity.getQty());

				ShoppingItemDao.getInstance().updateQty(conn, itemEntity.getItemid(),
						itemEntity.getQty() - entity.getQty());

				amount += entity.getQty() * itemEntity.getSellingPrice();
			}

			ShoppingOrderHeaderDao.getInstance().updateAmount(conn, id, amount, amount);

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
			final short consType = Short.valueOf(ConsType.SHOP.getValue());

			final String billNo = ShoppingOrderHeaderDao.getInstance().findBillNo(conn, orderId);

			ConsDao.getInstance().deleteConsByReferId(conn, billNo, consType, String.valueOf(orderId));

			ShoppingOrderHeaderDao.getInstance().updateStatus(conn, orderId, status);

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

			status = ShoppingOrderHeaderDao.getInstance().selectStatus(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return status;
	}

	public void updateCategory(final int categoryId, final int status, final int sequence, final int referId,
			final List<ShopLangBean> langList, final boolean isPhotoUpdate,
			final List<ActivityPhotoUploadBean> photoList) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			ShoppingCategoryDao.getInstance().update(conn, categoryId, status, referId, sequence);

			// 0 等於最上層 帶自己id
			if (referId == 0) {
				ShoppingCategoryDao.getInstance().updateReferId(conn, categoryId, categoryId);
			}

			ShoppingCategoryLangDao.getInstance().delete(conn, categoryId);

			for (final ShopLangBean entity : langList) {
				ShoppingCategoryLangDao.getInstance().insert(conn, categoryId, entity.getLangcode(), entity.getName(),
						entity.getDescription());
			}

			if (isPhotoUpdate) {
				PhotoDao.getInstance().delete(conn, String.valueOf(categoryId), PhotoType.SHOPCATEGORY.toString());
				for (final ActivityPhotoUploadBean photoEntity : photoList) {
					PhotoDao.getInstance().insert(conn, String.valueOf(categoryId),
							"/" + categoryId + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
							photoEntity.getName() + "." + photoEntity.getExtension(), "1",
							PhotoType.SHOPCATEGORY.toString());
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

	public void updateVendor(final int vendorId, final int status, final String tex, final String email,
			final String address, final List<ShopLangBean> langList) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			ShoppingVendorDao.getInstance().update(conn, vendorId, status, tex, email, address);

			ShoppingVendorLangDao.getInstance().delete(conn, vendorId);
			for (final ShopLangBean entity : langList) {
				ShoppingVendorLangDao.getInstance().insert(conn, vendorId, entity.getLangcode(), entity.getName(),
						entity.getDescription());
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

			ShoppingOrderHeaderDao.getInstance().updateStatus(conn, orderId, status);

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
			final String billNo = ShoppingOrderHeaderDao.getInstance().findBillNo(conn, orderId);
			final String roomNo = ShoppingOrderHeaderDao.getInstance().findRoomNo(conn, orderId);
			final String guestNo = ShoppingOrderHeaderDao.getInstance().findGuestNo(conn, orderId);

			if (StringUtils.isBlank(guestNo)) {
				final List<GuestInfoBean> guestList = GuestDao.getInstance().selectGuestInfo(conn, roomNo);

				// 暫時
				if (!guestList.isEmpty()) {
					langCode = guestList.get(0).getNational();
				}
			} else {
				langCode = GuestDao.getInstance().selectGuest(conn, roomNo, guestNo).getNational();
			}

			final List<ShopOrderLineAmountBean> list = ShoppingOrderLineDao.getInstance().selectAmount(conn, orderId);

			final short consType = Short.valueOf(ConsType.SHOP.getValue());

			for (final ShopOrderLineAmountBean entity : list) {
				/**
				 * 如果沒資料 用通用的英文來找 ,萬一沒資料就DB任選一筆,還是沒有就拋錯誤... 否則 PMS 那端 沒資料顯示
				 */
				String itemName = ShoppingItemLangDao.getInstance().selectName(conn, entity.getItemid(), langCode);

				if (StringUtils.isBlank(itemName)) {
					itemName = ShoppingItemLangDao.getInstance().selectName(conn, entity.getItemid(), "en_US");
					if (StringUtils.isBlank(itemName)) {
						itemName = ShoppingItemLangDao.getInstance().selectName(conn, entity.getItemid());
					}
				}

				if (StringUtils.isBlank(itemName)) {
					throw new SQLException("not find item name lang data.");
				}

				ConsDao.getInstance().insertCons(conn, UUID.randomUUID().toString().replace("-", ""), billNo,
						entity.getAmount() * entity.getQty(), consType, itemName,
						ConsDao.getInstance().findMaxSequence(conn) + 1, roomNo, String.valueOf(orderId));
			}

			ShoppingOrderHeaderDao.getInstance().updateStatus(conn, orderId, status);

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

	public void updateItem(final int itemId, final int categoryId, final int vendorId, final String status,
			final int sequence, final float price, final float sellingPrice, final int qty, final float weight,
			final List<ShopItemLangBean> langList, final List<ShopPhotoUploadBean> photoList,
			final boolean isPhotoUpdate) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			ShoppingItemDao.getInstance().update(conn, itemId, categoryId, vendorId, status, sequence, price,
					sellingPrice, qty, weight);

			ShoppingItemLangDao.getInstance().delete(conn, itemId);

			for (final ShopItemLangBean entity : langList) {
				ShoppingItemLangDao.getInstance().insert(conn, itemId, entity.getLangcode(), entity.getName(),
						entity.getDescription(), entity.getIntroduction());
			}

			if (isPhotoUpdate) {
				PhotoDao.getInstance().delete(conn, String.valueOf(itemId), PhotoType.SHOPITEM.toString());
				// photo 處理
				for (final ShopPhotoUploadBean photoEntity : photoList) {
					PhotoDao.getInstance().insert(conn, String.valueOf(itemId),
							"/" + itemId + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
							photoEntity.getName() + "." + photoEntity.getExtension(), "1",
							PhotoType.SHOPITEM.toString());
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

	public List<ShoppingCategoryResponse> selectCategory(final int categoryId, final int status, final int referId,
			final String langCode) throws SQLException {
		Connection conn = null;
		List<ShoppingCategoryResponse> list = new ArrayList<ShoppingCategoryResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = ShoppingCategoryDao.getInstance().selectCategory(conn, categoryId, status, referId);

			for (ShoppingCategoryResponse entity : list) {
				// 多語系
				entity.setLangs(ShoppingCategoryLangDao.getInstance().select(conn, entity.getCategoryid(), langCode));
				// 圖片唷
				entity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn, String.valueOf(entity.getCategoryid()),
						PhotoType.SHOPCATEGORY.toString()));

				entity.setSublist(searchCategory(conn, entity.getCategoryid(), langCode));

			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<ShoppingItemResponse> selectItem(final int itemId, final int categoryId, final String status,
			final int vendorId, final String langCode) throws SQLException {
		Connection conn = null;
		List<ShoppingItemResponse> list = new ArrayList<ShoppingItemResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = ShoppingItemDao.getInstance().select(conn, itemId, categoryId, vendorId, status);

			for (ShoppingItemResponse entity : list) {
				// 多語系
				entity.setLangs(ShoppingItemLangDao.getInstance().select(conn, entity.getItemid(), langCode));
				// 圖片唷
				entity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn, String.valueOf(entity.getItemid()),
						PhotoType.SHOPITEM.toString()));
			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	private List<ShoppingCategoryResponse> searchCategory(Connection conn, final int categoryId, final String langCode)
			throws SQLException {

		List<ShoppingCategoryResponse> list = ShoppingCategoryDao.getInstance().selectByReferId(conn, categoryId);

		if (!list.isEmpty()) {
			for (ShoppingCategoryResponse responseEntity : list) {
				// 多語系
				responseEntity.setLangs(
						ShoppingCategoryLangDao.getInstance().select(conn, responseEntity.getCategoryid(), langCode));
				// 圖片唷
				responseEntity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn,
						String.valueOf(responseEntity.getCategoryid()), PhotoType.SHOPCATEGORY.toString()));

				// 表示還有下一層
				if (responseEntity.getReferid() != responseEntity.getCategoryid()) {
					responseEntity.setSublist(searchCategory(conn, responseEntity.getCategoryid(), langCode));
				}
			}
		}
		return list;
	}

	public List<ShoppingVendorResponse> selectVendor(final int vendorId, final int status, final String langCode)
			throws SQLException {
		Connection conn = null;
		List<ShoppingVendorResponse> list = new ArrayList<ShoppingVendorResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = ShoppingVendorDao.getInstance().selectVendor(conn, vendorId, status);

			for (ShoppingVendorResponse responseEntity : list) {
				// 多語系
				responseEntity.setLangs(
						ShoppingVendorLangDao.getInstance().select(conn, responseEntity.getVendorid(), langCode));
			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	/**
	 * 針對 vendor name like查詢
	 * 
	 * @param conn
	 * @param vendorId
	 * @param status
	 * @param vendorName
	 * @param langCode
	 * @return
	 * @throws SQLException
	 */
	public List<ShoppingVendorResponse> selectVendor(final int vendorId, final int status, final String vendorName,
			final String langCode) throws SQLException {
		Connection conn = null;
		List<ShoppingVendorResponse> list = new ArrayList<ShoppingVendorResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = ShoppingVendorDao.getInstance().selectVendor(conn, vendorId, status, langCode, vendorName);

			for (ShoppingVendorResponse responseEntity : list) {
				// 多語系
				responseEntity.setLangs(
						ShoppingVendorLangDao.getInstance().select(conn, responseEntity.getVendorid(), langCode));
			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<ShoppingOrderResponse> selectOrder(final String deviceid, final int orderId, final String status,
			final String startTime, final String endTime, final String langCode) throws SQLException {

		Connection conn = null;
		List<ShoppingOrderResponse> list = new ArrayList<ShoppingOrderResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = MobileInfoDao.getInstance().getId(conn, deviceid);

			list = ShoppingOrderHeaderDao.getInstance().select(conn, mobileId, orderId, status, startTime, endTime);

			for (ShoppingOrderResponse entity : list) {
				// 撈 order line 資料
				entity.setItemlist(ShoppingOrderLineDao.getInstance().select(conn, entity.getId(), langCode));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	/**
	 * step 1:藉由 category,vendor,item 去找到 order line 的 order id step
	 * 2:再用其他條件下去篩選
	 * 
	 * @throws SQLException
	 */
	public List<ShoppingBackendOrderResponse> selectOrderWithBackend(final String roomNo, final int orderId,
			final String categoryIdWhereInStr, final String vendorIdWhereInStr, final String itemIdWhereInStr,
			final String status, final String startTime, final String endTime, final String langCode)
			throws SQLException {

		Connection conn = null;
		List<ShoppingBackendOrderResponse> list = new ArrayList<ShoppingBackendOrderResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			// order id 清單 藉由 category,vendor,item
			final List<Integer> orderIdList = ShoppingOrderLineDao.getInstance().selectOrderIdByWhereIn(conn,
					categoryIdWhereInStr, vendorIdWhereInStr, itemIdWhereInStr);

			// 沒資料就直接 return 否則 下面會把資料撈出來
			if (orderIdList.isEmpty()) {
				return new ArrayList<ShoppingBackendOrderResponse>();
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

			list = ShoppingOrderHeaderDao.getInstance().select(conn, orderId, orderIdWhereInStr, roomNo, status,
					startTime, endTime);

			for (ShoppingBackendOrderResponse entity : list) {
				entity.setItemlist(ShoppingOrderLineDao.getInstance().select(conn, entity.getId(), langCode));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<ShoppingBackendOrderResponse> selectOrderWithBackend(final String roomNo, final int orderId,
			final String status, final String startTime, final String endTime, final String langCode)
			throws SQLException {

		Connection conn = null;
		List<ShoppingBackendOrderResponse> list = new ArrayList<ShoppingBackendOrderResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = ShoppingOrderHeaderDao.getInstance().select(conn, orderId, null, roomNo, status, startTime, endTime);

			for (ShoppingBackendOrderResponse entity : list) {
				entity.setItemlist(ShoppingOrderLineDao.getInstance().select(conn, entity.getId(), langCode));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public String findOrderMobilePushToken(final int orderId) throws SQLException, SiDCException {

		Connection conn = null;
		String pushToken = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = ShoppingOrderHeaderDao.getInstance().findMobileId(conn, orderId);

			pushToken = MobileInfoDao.getInstance().getPushToken(conn, mobileId);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return pushToken;
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
			for (final int id : itemIdList) {
				if (!ShoppingItemDao.getInstance().isExist(conn, id, "1")) {
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

	public void backendOrderCheck(final String roomNo, final String token, final String reqeustStaffId)
			throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final String staffId = TokenStaffDetailDao.getInstance().selectStaffIdByToken(conn, token);

			if (reqeustStaffId.equals(staffId)) {
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

	public void itemInsertCheck(final int categoryId, final int vendorId) throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!ShoppingCategoryDao.getInstance().isExist(conn, categoryId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find category id).");
			}
			if (!ShoppingVendorDao.getInstance().isExist(conn, vendorId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find vendor id).");
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void itemUpdateCheck(final int itemId, final int categoryId, final int vendorId)
			throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!ShoppingItemDao.getInstance().isExist(conn, itemId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find item id).");
			}
			if (!ShoppingCategoryDao.getInstance().isExist(conn, categoryId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find category id).");
			}
			if (!ShoppingVendorDao.getInstance().isExist(conn, vendorId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(not find vendor id).");
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}
}
