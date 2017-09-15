package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.guest.bean.GuestInfoBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryClassBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryItemBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryItemToReturnTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryItemToWashTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryLangBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryOrderItemBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryOrderLineBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryReturnTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryWashTypeBean;
import com.sidc.blackcore.api.mobile.laundry.bean.LaundryWashTypeStatusBean;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryBackendOrderListResponse;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryClassResponse;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryItemResponse;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryOrderListResponse;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryReturnTypeResponse;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryTypeResponse;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryWashTypeResponse;
import com.sidc.blackcore.api.sits.type.ConsType;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.bill.BillDao;
import com.sidc.dao.sits.cons.ConsDao;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.dao.sits.laundryclass.LaundryClassDao;
import com.sidc.dao.sits.laundryclasslang.LaundryClassLangDao;
import com.sidc.dao.sits.laundryitem.LaundryItemDao;
import com.sidc.dao.sits.laundryitemlang.LaundryItemLangDao;
import com.sidc.dao.sits.laundryitemtoreturntype.LaundryItemToReturnTypeDao;
import com.sidc.dao.sits.laundryitemtorewashtype.LaundryItemToWashTypeDao;
import com.sidc.dao.sits.laundryorderheader.LaundryOrderHeaderDao;
import com.sidc.dao.sits.laundryorderline.LaundryOrderLineDao;
import com.sidc.dao.sits.laundryreturntype.LaundryReturnTypeDao;
import com.sidc.dao.sits.laundryreturntypelang.LaundryReturnTypeLangDao;
import com.sidc.dao.sits.laundrytype.LaundryTypeDao;
import com.sidc.dao.sits.laundrytypelang.LaundryTypeLangDao;
import com.sidc.dao.sits.laundrywashtype.LaundryWashTypeDao;
import com.sidc.dao.sits.laundrywashtypelang.LaundryWashTypeLangDao;
import com.sidc.dao.sits.mobileinfo.MobileInfoDao;
import com.sidc.dao.sits.photo.PhotoDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.tokenkey.TokenKeyDao;
import com.sidc.dao.sits.tokenstaffdetail.TokenStaffDetailDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class LaundryServiceManager {

	private LaundryServiceManager() {
	}

	private static class lazyHolder {
		public static final LaundryServiceManager INSTANCE = new LaundryServiceManager();
	}

	public static LaundryServiceManager getInstance() {
		return lazyHolder.INSTANCE;
	}

	public int insertClass(final int status, final float serviceCharge, final List<LaundryLangBean> langList)
			throws SiDCException, SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = LaundryClassDao.getInstance().insert(conn, status, serviceCharge);

			for (final LaundryLangBean entity : langList) {
				LaundryClassLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
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

	public int insertWashType(final int status, final List<LaundryLangBean> langList)
			throws SiDCException, SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = LaundryWashTypeDao.getInstance().insert(conn, status);

			for (final LaundryLangBean entity : langList) {
				LaundryWashTypeLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
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

	public int insertReturnType(final int status, final List<LaundryLangBean> langList)
			throws SiDCException, SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = LaundryReturnTypeDao.getInstance().insert(conn, status);

			for (final LaundryLangBean entity : langList) {
				LaundryReturnTypeLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
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

	public int insertType(final int status, final List<LaundryLangBean> langList,
			final List<ActivityPhotoUploadBean> photoList) throws SiDCException, SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = LaundryTypeDao.getInstance().insert(conn, status);

			for (final LaundryLangBean entity : langList) {
				LaundryTypeLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
						entity.getDescription());
			}

			// photo 處理
			for (final ActivityPhotoUploadBean photoEntity : photoList) {
				PhotoDao.getInstance().insert(conn, String.valueOf(id),
						"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
						photoEntity.getName() + "." + photoEntity.getExtension(), "1",
						PhotoType.LAUNDRYTYPE.toString());
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

	public int insertItem(final int status, final int typeId, final List<LaundryLangBean> langList,
			final List<LaundryItemToWashTypeBean> washTypeList, final List<LaundryItemToReturnTypeBean> returnTypelist,
			final List<ActivityPhotoUploadBean> photoList) throws SiDCException, SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = LaundryItemDao.getInstance().insert(conn, typeId, status);

			for (final LaundryLangBean entity : langList) {
				LaundryItemLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
						entity.getDescription());
			}
			for (final LaundryItemToWashTypeBean entity : washTypeList) {
				LaundryItemToWashTypeDao.getInstance().insert(conn, id, entity.getWashtypeid(), entity.getPrice());
			}
			for (final LaundryItemToReturnTypeBean entity : returnTypelist) {
				LaundryItemToReturnTypeDao.getInstance().insert(conn, id, entity.getReturntypeid());
			}

			// photo 處理
			for (final ActivityPhotoUploadBean photoEntity : photoList) {
				PhotoDao.getInstance().insert(conn, String.valueOf(id),
						"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
						photoEntity.getName() + "." + photoEntity.getExtension(), "1",
						PhotoType.LAUNDRYITEM.toString());
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

	/**
	 * 參數有 token 適用 mobile create order, sits tv create order 另議
	 * 
	 * @throws SiDCException
	 */
	public int createOrder(final String publicKey, final String privateKey, final String roomNo, final String guestNo,
			final String guestFirstName, final String guestLastName, final String receiveTime, final int classId,
			final String memo, final List<LaundryOrderItemBean> orderItemList) throws SiDCException, SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = TokenKeyDao.getInstance().selectMobileInfoId(conn, publicKey, privateKey);

			final String billNo = BillDao.getInstance().searchBillNoWithCheckIn(conn, roomNo);

			if (StringUtils.isBlank(billNo)) {
				throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "room not check in(not find bill no).");
			}

			// 服務費
			final float serviceCharge = LaundryClassDao.getInstance().selectServiceCharge(conn, classId);

			id = LaundryOrderHeaderDao.getInstance().insert(conn, "0", billNo, roomNo, guestNo, guestFirstName,
					guestLastName, receiveTime, classId, serviceCharge, memo, mobileId);

			int totalPieces = 0;
			int subTotal = 0;// 小計 未加上服務費

			for (final LaundryOrderItemBean entity : orderItemList) {

				final LaundryItemBean itemEntity = LaundryItemDao.getInstance().selectByEnable(conn,
						entity.getItemid());

				if (!LaundryItemToWashTypeDao.getInstance().isExist(conn, entity.getItemid(), entity.getWashtypeid())) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
							"illegal of item id and wash type id(no relationship).");
				}

				final float price = LaundryItemToWashTypeDao.getInstance().selectPrice(conn, entity.getItemid(),
						entity.getWashtypeid());

				if (entity.getReturntypeid() > 0) {
					if (!LaundryItemToReturnTypeDao.getInstance().isExist(conn, entity.getItemid(),
							entity.getReturntypeid())) {
						throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
								"illegal of item id and return type id(no relationship).");
					}
					LaundryOrderLineDao.getInstance().insert(conn, id, itemEntity.getTypeid(), entity.getItemid(),
							entity.getWashtypeid(), entity.getReturntypeid(), entity.getPiece(), price);
				} else {
					LaundryOrderLineDao.getInstance().insert(conn, id, itemEntity.getTypeid(), entity.getItemid(),
							entity.getWashtypeid(), entity.getPiece(), price);
				}

				totalPieces += entity.getPiece();
				subTotal += price * entity.getPiece();
			}

			float serviceFee = (float) Math.round(serviceCharge * subTotal);

			LaundryOrderHeaderDao.getInstance().update(conn, id, totalPieces, subTotal, subTotal + serviceFee);

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

	public void updateOrderStatus(final int id, final String status) throws SiDCException, SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			LaundryOrderHeaderDao.getInstance().updateWithStatus(conn, id, status);
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

	public void updateOrderReceiveTime(final int id, final String receiveTime) throws SiDCException, SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			LaundryOrderHeaderDao.getInstance().updateWithReceive(conn, id, receiveTime);
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

	public List<LaundryClassResponse> selectClassByStatus(final int status, final String langCode)
			throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryClassResponse> list = new ArrayList<LaundryClassResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryClassBean> classList = LaundryClassDao.getInstance().selectByStatus(conn, status);

			for (final LaundryClassBean entity : classList) {
				final List<LaundryLangBean> langList = LaundryClassLangDao.getInstance().select(conn, entity.getId(),
						langCode);
				list.add(new LaundryClassResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryClassResponse> selectClassByStatus(final int status) throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryClassResponse> list = new ArrayList<LaundryClassResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryClassBean> classList = LaundryClassDao.getInstance().selectByStatus(conn, status);

			for (final LaundryClassBean entity : classList) {
				final List<LaundryLangBean> langList = LaundryClassLangDao.getInstance().select(conn, entity.getId());
				list.add(new LaundryClassResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryClassResponse> selectClass() throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryClassResponse> list = new ArrayList<LaundryClassResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryClassBean> classList = LaundryClassDao.getInstance().select(conn);

			for (final LaundryClassBean entity : classList) {
				final List<LaundryLangBean> langList = LaundryClassLangDao.getInstance().select(conn, entity.getId());
				list.add(new LaundryClassResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryClassResponse> selectClass(final String langCode) throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryClassResponse> list = new ArrayList<LaundryClassResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryClassBean> classList = LaundryClassDao.getInstance().select(conn);

			for (final LaundryClassBean entity : classList) {
				final List<LaundryLangBean> langList = LaundryClassLangDao.getInstance().select(conn, entity.getId(),
						langCode);
				list.add(new LaundryClassResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryTypeResponse> selectTypeByStatus(final int status, final String langCode)
			throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryTypeResponse> list = new ArrayList<LaundryTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryTypeBean> typeList = LaundryTypeDao.getInstance().selectByStatus(conn, status);

			for (final LaundryTypeBean entity : typeList) {
				final List<LaundryLangBean> langList = LaundryTypeLangDao.getInstance().select(conn, entity.getId(),
						langCode);

				// 圖片唷
				final List<ActivityPhotoBean> photoList = PhotoDao.getInstance().selectByReferid(conn,
						String.valueOf(entity.getId()), PhotoType.LAUNDRYTYPE.toString());

				list.add(new LaundryTypeResponse(entity.getId(), entity.getStatus(), langList, photoList));
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

	public List<LaundryTypeResponse> selectTypeByStatus(final int status) throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryTypeResponse> list = new ArrayList<LaundryTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryTypeBean> typeList = LaundryTypeDao.getInstance().selectByStatus(conn, status);

			for (final LaundryTypeBean entity : typeList) {
				final List<LaundryLangBean> langList = LaundryTypeLangDao.getInstance().select(conn, entity.getId());
				// 圖片唷
				final List<ActivityPhotoBean> photoList = PhotoDao.getInstance().selectByReferid(conn,
						String.valueOf(entity.getId()), PhotoType.LAUNDRYTYPE.toString());

				list.add(new LaundryTypeResponse(entity.getId(), entity.getStatus(), langList, photoList));
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

	public List<LaundryTypeResponse> selectType() throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryTypeResponse> list = new ArrayList<LaundryTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryTypeBean> typeList = LaundryTypeDao.getInstance().select(conn);

			for (final LaundryTypeBean entity : typeList) {
				final List<LaundryLangBean> langList = LaundryTypeLangDao.getInstance().select(conn, entity.getId());
				// 圖片唷
				final List<ActivityPhotoBean> photoList = PhotoDao.getInstance().selectByReferid(conn,
						String.valueOf(entity.getId()), PhotoType.LAUNDRYTYPE.toString());

				list.add(new LaundryTypeResponse(entity.getId(), entity.getStatus(), langList, photoList));
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

	public List<LaundryTypeResponse> selectType(final String langCode) throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryTypeResponse> list = new ArrayList<LaundryTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryTypeBean> typeList = LaundryTypeDao.getInstance().select(conn);

			for (final LaundryTypeBean entity : typeList) {
				final List<LaundryLangBean> langList = LaundryTypeLangDao.getInstance().select(conn, entity.getId(),
						langCode);

				// 圖片唷
				final List<ActivityPhotoBean> photoList = PhotoDao.getInstance().selectByReferid(conn,
						String.valueOf(entity.getId()), PhotoType.LAUNDRYTYPE.toString());

				list.add(new LaundryTypeResponse(entity.getId(), entity.getStatus(), langList, photoList));
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

	public List<LaundryWashTypeResponse> selectWashTypeByStatus(final int status, final String langCode)
			throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryWashTypeResponse> list = new ArrayList<LaundryWashTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryWashTypeStatusBean> washList = LaundryWashTypeDao.getInstance().selectByStatus(conn, status);

			for (final LaundryWashTypeStatusBean entity : washList) {
				final List<LaundryLangBean> langList = LaundryWashTypeLangDao.getInstance().select(conn, entity.getId(),
						langCode);
				list.add(new LaundryWashTypeResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryWashTypeResponse> selectWashTypeByStatus(final int status) throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryWashTypeResponse> list = new ArrayList<LaundryWashTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryWashTypeStatusBean> washList = LaundryWashTypeDao.getInstance().selectByStatus(conn, status);

			for (final LaundryWashTypeStatusBean entity : washList) {
				final List<LaundryLangBean> langList = LaundryWashTypeLangDao.getInstance().select(conn,
						entity.getId());
				list.add(new LaundryWashTypeResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryWashTypeResponse> selectWashType() throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryWashTypeResponse> list = new ArrayList<LaundryWashTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryWashTypeStatusBean> washList = LaundryWashTypeDao.getInstance().select(conn);

			for (final LaundryWashTypeStatusBean entity : washList) {
				final List<LaundryLangBean> langList = LaundryWashTypeLangDao.getInstance().select(conn,
						entity.getId());
				list.add(new LaundryWashTypeResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryWashTypeResponse> selectWashType(final String langCode) throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryWashTypeResponse> list = new ArrayList<LaundryWashTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryWashTypeStatusBean> washList = LaundryWashTypeDao.getInstance().select(conn);

			for (final LaundryWashTypeStatusBean entity : washList) {
				final List<LaundryLangBean> langList = LaundryWashTypeLangDao.getInstance().select(conn, entity.getId(),
						langCode);
				list.add(new LaundryWashTypeResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryReturnTypeResponse> selectReturnTypeByStatus(final int status, final String langCode)
			throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryReturnTypeResponse> list = new ArrayList<LaundryReturnTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryReturnTypeBean> returnTypeList = LaundryReturnTypeDao.getInstance().selectByStatus(conn,
					status);

			for (final LaundryReturnTypeBean entity : returnTypeList) {
				final List<LaundryLangBean> langList = LaundryReturnTypeLangDao.getInstance().select(conn,
						entity.getId(), langCode);
				list.add(new LaundryReturnTypeResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryReturnTypeResponse> selectReturnTypeByStatus(final int status)
			throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryReturnTypeResponse> list = new ArrayList<LaundryReturnTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryReturnTypeBean> returnTypeList = LaundryReturnTypeDao.getInstance().selectByStatus(conn,
					status);

			for (final LaundryReturnTypeBean entity : returnTypeList) {
				final List<LaundryLangBean> langList = LaundryReturnTypeLangDao.getInstance().select(conn,
						entity.getId());
				list.add(new LaundryReturnTypeResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryReturnTypeResponse> selectReturnType() throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryReturnTypeResponse> list = new ArrayList<LaundryReturnTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryReturnTypeBean> returnTypeList = LaundryReturnTypeDao.getInstance().select(conn);

			for (final LaundryReturnTypeBean entity : returnTypeList) {
				final List<LaundryLangBean> langList = LaundryReturnTypeLangDao.getInstance().select(conn,
						entity.getId());
				list.add(new LaundryReturnTypeResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryReturnTypeResponse> selectReturnType(final String langCode) throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryReturnTypeResponse> list = new ArrayList<LaundryReturnTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryReturnTypeBean> returnTypeList = LaundryReturnTypeDao.getInstance().select(conn);

			for (final LaundryReturnTypeBean entity : returnTypeList) {
				final List<LaundryLangBean> langList = LaundryReturnTypeLangDao.getInstance().select(conn,
						entity.getId(), langCode);
				list.add(new LaundryReturnTypeResponse(entity.getId(), entity.getStatus(), langList));
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

	public List<LaundryItemResponse> selectItemByStatus(final int status, final String langCode)
			throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryItemResponse> list = new ArrayList<LaundryItemResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryItemBean> itemList = LaundryItemDao.getInstance().selectByStatus(conn, status);

			for (final LaundryItemBean entity : itemList) {
				list.add(itemResponseProcessWithlangCode(conn, entity.getId(), entity.getStatus(), langCode));
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

	public List<LaundryItemResponse> selectItemByStatus(final int status) throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryItemResponse> list = new ArrayList<LaundryItemResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryItemBean> itemList = LaundryItemDao.getInstance().selectByStatus(conn, status);

			for (final LaundryItemBean entity : itemList) {
				list.add(itemResponseProcess(conn, entity.getId(), entity.getStatus()));
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

	public List<LaundryItemResponse> selectItem() throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryItemResponse> list = new ArrayList<LaundryItemResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryItemBean> itemList = LaundryItemDao.getInstance().select(conn);

			for (final LaundryItemBean entity : itemList) {
				list.add(itemResponseProcess(conn, entity.getId(), entity.getStatus()));
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

	public List<LaundryItemResponse> selectItem(final String langCode) throws SiDCException, SQLException {

		Connection conn = null;

		List<LaundryItemResponse> list = new ArrayList<LaundryItemResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<LaundryItemBean> itemList = LaundryItemDao.getInstance().select(conn);

			for (final LaundryItemBean entity : itemList) {
				list.add(itemResponseProcessWithlangCode(conn, entity.getId(), entity.getStatus(), langCode));
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

	/**
	 * 給mobile user查資料的 一定要帶 device
	 * 
	 * @param deviceid
	 * @param orderId
	 * @param status
	 * @param classId
	 * @param startTime
	 * @param endTime
	 * @param langCode
	 * @return
	 * @throws SQLException
	 */
	public List<LaundryOrderListResponse> selectOrder(final String deviceid, final int orderId, final String status,
			final int classId, final String startTime, final String endTime, final String langCode)
			throws SQLException {

		Connection conn = null;
		List<LaundryOrderListResponse> list = new ArrayList<LaundryOrderListResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = MobileInfoDao.getInstance().getId(conn, deviceid);

			list = LaundryOrderHeaderDao.getInstance().select(conn, mobileId, orderId, status, classId, startTime,
					endTime, langCode);

			for (LaundryOrderListResponse entity : list) {
				entity.setItemlist(LaundryOrderLineDao.getInstance().selectInfo(conn, entity.getOrderid(), langCode));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<LaundryBackendOrderListResponse> selectOrderWithBackend(final String roomNo, final int orderId,
			final String status, final int classId, final String startTime, final String endTime, final String langCode)
			throws SQLException {

		Connection conn = null;
		List<LaundryBackendOrderListResponse> list = new ArrayList<LaundryBackendOrderListResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = LaundryOrderHeaderDao.getInstance().select(conn, orderId, status, roomNo, classId, startTime,
					endTime, langCode);

			for (LaundryBackendOrderListResponse entity : list) {
				entity.setItemlist(LaundryOrderLineDao.getInstance().selectInfo(conn, entity.getOrderid(), langCode));
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public String selectOrderStatus(final int id) throws SQLException {

		Connection conn = null;
		String status = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			status = LaundryOrderHeaderDao.getInstance().selectStatus(conn, id);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return status;
	}

	public void updateClass(final int id, final int status, final float serviceCharge,
			final List<LaundryLangBean> langList) throws SiDCException, SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			LaundryClassDao.getInstance().update(conn, id, status, serviceCharge);

			LaundryClassLangDao.getInstance().delete(conn, id);

			for (final LaundryLangBean entity : langList) {
				LaundryClassLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
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

	public void updateWashType(final int id, final int status, final List<LaundryLangBean> langList)
			throws SiDCException, SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			LaundryWashTypeDao.getInstance().update(conn, id, status);

			LaundryWashTypeLangDao.getInstance().delete(conn, id);

			for (final LaundryLangBean entity : langList) {
				LaundryWashTypeLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
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

	public void updateReturnType(final int id, final int status, final List<LaundryLangBean> langList)
			throws SiDCException, SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			LaundryReturnTypeDao.getInstance().update(conn, id, status);

			LaundryReturnTypeLangDao.getInstance().delete(conn, id);

			for (final LaundryLangBean entity : langList) {
				LaundryReturnTypeLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
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

	public void updateType(final int id, final int status, final List<LaundryLangBean> langList,
			final boolean isPhotoUpdate, final List<ActivityPhotoUploadBean> photoList)
			throws SiDCException, SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			LaundryTypeDao.getInstance().update(conn, id, status);

			LaundryTypeLangDao.getInstance().delete(conn, id);

			for (final LaundryLangBean entity : langList) {
				LaundryTypeLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
						entity.getDescription());
			}

			if (isPhotoUpdate) {
				PhotoDao.getInstance().delete(conn, String.valueOf(id), PhotoType.LAUNDRYTYPE.toString());
				for (final ActivityPhotoUploadBean photoEntity : photoList) {
					PhotoDao.getInstance().insert(conn, String.valueOf(id),
							"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
							photoEntity.getName() + "." + photoEntity.getExtension(), "1",
							PhotoType.LAUNDRYTYPE.toString());
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

	public void updateItem(final int id, final int status, final int typeId, final List<LaundryLangBean> langList,
			final List<LaundryItemToWashTypeBean> washTypeList, final List<LaundryItemToReturnTypeBean> returnTypelist,
			final boolean isPhotoUpdate, final List<ActivityPhotoUploadBean> photoList)
			throws SiDCException, SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			LaundryItemDao.getInstance().update(conn, typeId, status, typeId);

			LaundryItemLangDao.getInstance().delete(conn, id);
			LaundryItemToWashTypeDao.getInstance().delete(conn, id);
			LaundryItemToReturnTypeDao.getInstance().delete(conn, id);

			for (final LaundryLangBean entity : langList) {
				LaundryItemLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
						entity.getDescription());
			}
			for (final LaundryItemToWashTypeBean entity : washTypeList) {
				LaundryItemToWashTypeDao.getInstance().insert(conn, id, entity.getWashtypeid(), entity.getPrice());
			}
			for (final LaundryItemToReturnTypeBean entity : returnTypelist) {
				LaundryItemToReturnTypeDao.getInstance().insert(conn, id, entity.getReturntypeid());
			}

			if (isPhotoUpdate) {
				PhotoDao.getInstance().delete(conn, String.valueOf(id), PhotoType.LAUNDRYITEM.toString());
				for (final ActivityPhotoUploadBean photoEntity : photoList) {
					PhotoDao.getInstance().insert(conn, String.valueOf(id),
							"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
							photoEntity.getName() + "." + photoEntity.getExtension(), "1",
							PhotoType.LAUNDRYITEM.toString());
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

	public boolean isExistOfHeaderId(final int headerId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = LaundryOrderHeaderDao.getInstance().isExist(conn, headerId);
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

	public boolean isExistOfClassId(final int classId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = LaundryClassDao.getInstance().isExist(conn, classId);
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

	public boolean isExistAndEnableOfClassId(final int classId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = LaundryClassDao.getInstance().isExist(conn, classId, 1);
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

	public boolean isExistOfWashTypeId(final int washTypeId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = LaundryWashTypeDao.getInstance().isExist(conn, washTypeId);
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

	public boolean isExistOfWashTypeId(final List<Integer> list) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			for (final int id : list) {
				if (!LaundryWashTypeDao.getInstance().isExist(conn, id)) {
					return false;
				}
			}
			isExist = true;
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

	public boolean isExistAndEnableOfWashTypeId(final List<Integer> list) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			for (final int id : list) {
				if (!LaundryWashTypeDao.getInstance().isExist(conn, id, 1)) {
					return false;
				}
			}
			isExist = true;
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

	public boolean isExistOfReturnTypeId(final int returnTypeId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = LaundryReturnTypeDao.getInstance().isExist(conn, returnTypeId);
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

	public boolean isExistOfReturnTypeId(final List<Integer> list) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final int id : list) {
				if (!LaundryReturnTypeDao.getInstance().isExist(conn, id)) {
					return false;
				}
			}
			isExist = true;

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

	public boolean isExistAndEnableOfReturnTypeId(final List<Integer> list) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final int id : list) {
				if (id == 0) {
					continue;
				}
				if (!LaundryReturnTypeDao.getInstance().isExist(conn, id, 1)) {
					return false;
				}
			}
			isExist = true;

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

	public boolean isExistOfTypeId(final int typeId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = LaundryTypeDao.getInstance().isExist(conn, typeId);
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

	public boolean isExistOfTypeId(final List<Integer> list) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			for (final int id : list) {
				if (!LaundryTypeDao.getInstance().isExist(conn, id)) {
					return false;
				}
			}
			isExist = true;
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

	public boolean isExistAndEnableOfTypeId(final List<Integer> list) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			for (final int id : list) {
				if (!LaundryTypeDao.getInstance().isExist(conn, id, 1)) {
					return false;
				}
			}
			isExist = true;
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

	public boolean isExistOfItemId(final int itemId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = LaundryItemDao.getInstance().isExist(conn, itemId);
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

	public boolean isExistOfItemId(final List<Integer> list) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			for (final int id : list) {
				if (!LaundryItemDao.getInstance().isExist(conn, id)) {
					return false;
				}
			}
			isExist = true;
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

	public boolean isExistAndEnableOfItemId(final List<Integer> list) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			for (final int id : list) {
				if (!LaundryItemDao.getInstance().isExist(conn, id, 1)) {
					return false;
				}
			}
			isExist = true;
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

	public void updateOrderStatusCreateCons(final int orderId, final String status) throws SQLException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			String langCode = "en_US";
			final String billNo = LaundryOrderHeaderDao.getInstance().findBillNo(conn, orderId);
			final String roomNo = LaundryOrderHeaderDao.getInstance().findRoomNo(conn, orderId);
			final List<GuestInfoBean> guestList = GuestDao.getInstance().selectGuestInfo(conn, roomNo);

			// 暫時
			if (!guestList.isEmpty()) {
				langCode = guestList.get(0).getNational();
			}

			final List<LaundryOrderLineBean> list = LaundryOrderLineDao.getInstance().selectItemPrice(conn, orderId);

			final short consType = Short.valueOf(ConsType.LAUNDRY.getValue());

			for (final LaundryOrderLineBean entity : list) {
				/**
				 * 如果沒資料 用通用的英文來找 ,萬一沒資料就DB任選一筆,還是沒有就拋錯誤... 否則 PMS 那端 沒資料顯示
				 */
				String itemName = LaundryItemLangDao.getInstance().selectName(conn, entity.getItemid(), langCode);

				if (StringUtils.isBlank(itemName)) {
					itemName = LaundryItemLangDao.getInstance().selectName(conn, entity.getItemid(), "en_US");
					if (StringUtils.isBlank(itemName)) {
						itemName = LaundryItemLangDao.getInstance().selectName(conn, entity.getItemid());
					}
				}

				if (StringUtils.isBlank(itemName)) {
					throw new SQLException("not find item name lang data.");
				}

				ConsDao.getInstance().insertCons(conn, UUID.randomUUID().toString().replace("-", ""), billNo,
						entity.getPrice() * entity.getPiece(), consType, itemName,
						ConsDao.getInstance().findMaxSequence(conn), roomNo, String.valueOf(orderId));
			}

			LaundryOrderHeaderDao.getInstance().updateWithStatus(conn, orderId, status);

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
			final String billNo = LaundryOrderHeaderDao.getInstance().findBillNo(conn, orderId);

			ConsDao.getInstance().deleteConsByReferId(conn, billNo, 15, String.valueOf(orderId));

			LaundryOrderHeaderDao.getInstance().updateWithStatus(conn, orderId, status);

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

	public String findOrderMobilePushToken(final int orderId) throws SQLException, SiDCException {

		Connection conn = null;
		String pushToken = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = LaundryOrderHeaderDao.getInstance().findMobileId(conn, orderId);

			pushToken = MobileInfoDao.getInstance().getPushToken(conn, mobileId);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return pushToken;
	}

	private LaundryItemResponse itemResponseProcessWithlangCode(Connection conn, final int id, final int status,
			final String langCode) throws Exception {
		final List<LaundryLangBean> langList = LaundryItemLangDao.getInstance().select(conn, id, langCode);

		// wash type,只列出狀態啟用的資料
		List<LaundryWashTypeBean> washTypeList = LaundryItemToWashTypeDao.getInstance().selectWashType(conn, id);
		for (final LaundryWashTypeBean washTypeEntity : washTypeList) {
			washTypeEntity.setLangs(
					LaundryWashTypeLangDao.getInstance().select(conn, washTypeEntity.getWashtypeid(), langCode));
		}

		// return type,只列出狀態啟用的資料
		List<Integer> returnTypeIdList = LaundryItemToReturnTypeDao.getInstance().selectReturnType(conn, id);

		List<LaundryReturnTypeResponse> returnTypeList = new ArrayList<LaundryReturnTypeResponse>();

		for (final int returnTypeId : returnTypeIdList) {
			returnTypeList.add(new LaundryReturnTypeResponse(returnTypeId, 1,
					LaundryReturnTypeLangDao.getInstance().select(conn, returnTypeId, langCode)));
		}
		// 圖片唷
		final List<ActivityPhotoBean> photoList = PhotoDao.getInstance().selectByReferid(conn, String.valueOf(id),
				PhotoType.LAUNDRYITEM.toString());

		return new LaundryItemResponse(id, status, langList, returnTypeList, washTypeList, photoList);
	}

	private LaundryItemResponse itemResponseProcess(Connection conn, final int id, final int status) throws Exception {

		final List<LaundryLangBean> langList = LaundryItemLangDao.getInstance().select(conn, id);

		// wash type,只列出狀態啟用的資料
		List<LaundryWashTypeBean> washTypeList = LaundryItemToWashTypeDao.getInstance().selectWashType(conn, id);
		for (final LaundryWashTypeBean washTypeEntity : washTypeList) {
			washTypeEntity.setLangs(LaundryWashTypeLangDao.getInstance().select(conn, washTypeEntity.getWashtypeid()));
		}

		// return type,只列出狀態啟用的資料
		List<Integer> returnTypeIdList = LaundryItemToReturnTypeDao.getInstance().selectReturnType(conn, id);

		List<LaundryReturnTypeResponse> returnTypeList = new ArrayList<LaundryReturnTypeResponse>();

		for (final int returnTypeId : returnTypeIdList) {
			returnTypeList.add(new LaundryReturnTypeResponse(returnTypeId, 1,
					LaundryReturnTypeLangDao.getInstance().select(conn, returnTypeId)));
		}

		// 圖片唷
		final List<ActivityPhotoBean> photoList = PhotoDao.getInstance().selectByReferid(conn, String.valueOf(id),
				PhotoType.LAUNDRYITEM.toString());
		return new LaundryItemResponse(id, status, langList, returnTypeList, washTypeList, photoList);
	}

	public void orderCreateCheck(final String roomNo, final int classId, final String guestNo,
			final List<Integer> itemIdList, final List<Integer> returnTypeIdList, final List<Integer> washTypeIdList)
			throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!RoomDao.getInstance().isCheckIn(conn, roomNo)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room not check in.");
			}
			if (!LaundryClassDao.getInstance().isExist(conn, classId, 1)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "class id is not exist.");
			}
			for (final int id : itemIdList) {
				if (!LaundryItemDao.getInstance().isExist(conn, id, 1)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "item id is not exist.");
				}
			}
			for (final int id : returnTypeIdList) {
				if (id == 0) {
					continue;
				}
				if (!LaundryReturnTypeDao.getInstance().isExist(conn, id, 1)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "return type id is not exist.");
				}
			}
			for (final int id : washTypeIdList) {
				if (!LaundryWashTypeDao.getInstance().isExist(conn, id, 1)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "wash type id is not exist.");
				}
			}
			if (!StringUtils.isBlank(guestNo)) {
				if (!GuestManager.getInstance().isExist(roomNo, guestNo)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find guest no.");
				}
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void backendOrderCheck(final String roomNo, final String token, final String requestStaffId)
			throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!StringUtils.isBlank(roomNo)) {
				if (!RoomDao.getInstance().isExist(conn, roomNo)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(not find room no).");
				}
			}

			final String staffId = TokenStaffDetailDao.getInstance().selectStaffIdByToken(conn, token);

			if (requestStaffId.equals(staffId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "token and staff id is not match.");
			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void itemInsertCheck(final int typeId, final List<Integer> returnTypeList, final List<Integer> washTypeList)
			throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!LaundryTypeDao.getInstance().isExist(conn, typeId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find type id.");
			}

			for (final int id : returnTypeList) {
				if (!LaundryReturnTypeDao.getInstance().isExist(conn, id)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find return type id.");
				}
			}

			for (final int id : washTypeList) {
				if (!LaundryWashTypeDao.getInstance().isExist(conn, id)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find wash type id.");
				}
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void itemUpdateCheck(final int itemId, final int typeId, final List<Integer> returnTypeList,
			final List<Integer> washTypeList) throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!LaundryItemDao.getInstance().isExist(conn, itemId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find item id.");
			}

			if (!LaundryTypeDao.getInstance().isExist(conn, typeId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find type id.");
			}

			for (final int id : returnTypeList) {
				if (!LaundryReturnTypeDao.getInstance().isExist(conn, id)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find return type id.");
				}
			}

			for (final int id : washTypeList) {
				if (!LaundryWashTypeDao.getInstance().isExist(conn, id)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find wash type id.");
				}
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

}
