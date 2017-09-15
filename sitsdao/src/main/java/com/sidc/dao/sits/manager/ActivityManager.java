package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeInsertBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeLangBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityLangBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityRepeatBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivitySignUpBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityTypeBean;
import com.sidc.blackcore.api.mobile.activity.response.ActivityFeeResponse;
import com.sidc.blackcore.api.mobile.activity.response.ActivityOrderListResponse;
import com.sidc.blackcore.api.mobile.activity.response.ActivityTypeResponse;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.activityfee.ActivityFeeDao;
import com.sidc.dao.sits.activityfeelang.ActivityFeeLangDao;
import com.sidc.dao.sits.activityheader.ActivityHeaderDao;
import com.sidc.dao.sits.activityheaderlang.ActivityHeaderLangDao;
import com.sidc.dao.sits.activityline.ActivityOrderLineDao;
import com.sidc.dao.sits.activityorderheader.ActivityOrderHeaderDao;
import com.sidc.dao.sits.activityrepeat.ActivityRepeatDao;
import com.sidc.dao.sits.activitytofee.ActivityToFeeDao;
import com.sidc.dao.sits.activitytype.ActivityTypeDao;
import com.sidc.dao.sits.activitytypelang.ActivityTypeLangDao;
import com.sidc.dao.sits.bill.BillDao;
import com.sidc.dao.sits.guest.GuestDao;
import com.sidc.dao.sits.mobileinfo.MobileInfoDao;
import com.sidc.dao.sits.photo.PhotoDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.tokenkey.TokenKeyDao;
import com.sidc.dao.sits.tokenstaffdetail.TokenStaffDetailDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class ActivityManager {

	private ActivityManager() {
	}

	private static class lazyHolder {
		public static final ActivityManager INSTANCE = new ActivityManager();
	}

	public static ActivityManager getInstance() {
		return lazyHolder.INSTANCE;
	}

	public int insertFee(final int status, final List<ActivityFeeLangBean> langList)
			throws SiDCException, SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = ActivityFeeDao.getInstance().insert(conn, status);

			for (final ActivityFeeLangBean entity : langList) {
				ActivityFeeLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName());
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

	public int insertType(final int status, final List<ActivityTypeBean> langList) throws SiDCException, SQLException {

		Connection conn = null;
		int id = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = ActivityTypeDao.getInstance().insert(conn, status);

			for (final ActivityTypeBean entity : langList) {
				ActivityTypeLangDao.getInstance().insert(conn, id, entity.getLangcode(), entity.getName(),
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

	public List<ActivityFeeResponse> selectFee(final String langCode) throws SiDCException, SQLException {

		Connection conn = null;

		List<ActivityFeeResponse> list = new ArrayList<ActivityFeeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<Integer> feeList = ActivityFeeDao.getInstance().select(conn);

			for (final int id : feeList) {
				final List<ActivityFeeLangBean> feeLangList = ActivityFeeLangDao.getInstance().select(conn, id,
						langCode);
				list.add(new ActivityFeeResponse(id, feeLangList));
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

	public List<ActivityFeeResponse> selectFee() throws SiDCException, SQLException {

		Connection conn = null;

		List<ActivityFeeResponse> list = new ArrayList<ActivityFeeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<Integer> feeList = ActivityFeeDao.getInstance().select(conn);

			for (final int id : feeList) {
				final List<ActivityFeeLangBean> feeLangList = ActivityFeeLangDao.getInstance().select(conn, id);
				list.add(new ActivityFeeResponse(id, feeLangList));
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

	public List<ActivityFeeResponse> selectFeeByStatus(final String langCode, final int status)
			throws SiDCException, SQLException {

		Connection conn = null;

		List<ActivityFeeResponse> list = new ArrayList<ActivityFeeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<Integer> feeList = ActivityFeeDao.getInstance().selectByStatus(conn, status);

			for (final int id : feeList) {
				final List<ActivityFeeLangBean> feeLangList = ActivityFeeLangDao.getInstance().select(conn, id,
						langCode);
				list.add(new ActivityFeeResponse(id, feeLangList));
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

	public List<ActivityFeeResponse> selectFeeByStatus(final int status) throws SiDCException, SQLException {

		Connection conn = null;

		List<ActivityFeeResponse> list = new ArrayList<ActivityFeeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<Integer> feeList = ActivityFeeDao.getInstance().selectByStatus(conn, status);

			for (final int id : feeList) {
				final List<ActivityFeeLangBean> feeLangList = ActivityFeeLangDao.getInstance().select(conn, id);
				list.add(new ActivityFeeResponse(id, feeLangList));
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

	public List<ActivityTypeResponse> selectType(final String langCode) throws SiDCException, SQLException {

		Connection conn = null;

		List<ActivityTypeResponse> list = new ArrayList<ActivityTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<Integer> typeList = ActivityTypeDao.getInstance().select(conn);

			for (final int id : typeList) {
				final List<ActivityTypeBean> typeLangList = ActivityTypeLangDao.getInstance().selectByLangCode(conn, id,
						langCode);
				list.add(new ActivityTypeResponse(id, typeLangList));
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

	public List<ActivityTypeResponse> selectType() throws SiDCException, SQLException {

		Connection conn = null;

		List<ActivityTypeResponse> list = new ArrayList<ActivityTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<Integer> typeList = ActivityTypeDao.getInstance().select(conn);

			for (final int id : typeList) {
				final List<ActivityTypeBean> typeLangList = ActivityTypeLangDao.getInstance().selectById(conn, id);
				list.add(new ActivityTypeResponse(id, typeLangList));
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

	public List<ActivityTypeResponse> selectTypeByStatus(final String langCode, final int status)
			throws SiDCException, SQLException {

		Connection conn = null;

		List<ActivityTypeResponse> list = new ArrayList<ActivityTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<Integer> typeList = ActivityTypeDao.getInstance().selectByStatus(conn, status);

			for (final int id : typeList) {
				final List<ActivityTypeBean> typeLangList = ActivityTypeLangDao.getInstance().selectByLangCode(conn, id,
						langCode);
				list.add(new ActivityTypeResponse(id, typeLangList));
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

	public List<ActivityTypeResponse> selectTypeByStatus(final int status) throws SiDCException, SQLException {

		Connection conn = null;

		List<ActivityTypeResponse> list = new ArrayList<ActivityTypeResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			List<Integer> typeList = ActivityTypeDao.getInstance().selectByStatus(conn, status);

			for (final int id : typeList) {
				final List<ActivityTypeBean> typeLangList = ActivityTypeLangDao.getInstance().selectById(conn, id);
				list.add(new ActivityTypeResponse(id, typeLangList));
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

	public int insert(final int typeId, final String repeatType, final int lowerLimit, final int upperLimit,
			final int status, final int target, final String registrationStartTime, final String registrationEndTime,
			final int isCharge, final String memo, final List<ActivityLangBean> list,
			final List<ActivityFeeInsertBean> feeList, final List<ActivityRepeatBean> repeatList,
			final List<ActivityPhotoUploadBean> photoList, final String repeatFrequentJson)
			throws SiDCException, SQLException {

		Connection conn = null;
		int id = -1;
		try {

			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = ActivityHeaderDao.getInstance().insert(conn, typeId, repeatType, lowerLimit, upperLimit, status,
					target, registrationStartTime, registrationEndTime, isCharge, memo, repeatFrequentJson);

			for (final ActivityLangBean langEntity : list) {
				ActivityHeaderLangDao.getInstance().insert(conn, id, langEntity.getLangcode(), langEntity.getTitle(),
						langEntity.getContent(), langEntity.getLocation());
			}

			for (final ActivityFeeInsertBean feeEntity : feeList) {
				ActivityToFeeDao.getInstance().insert(conn, id, feeEntity.getFeeid(), feeEntity.getPrice());
			}

			for (final ActivityRepeatBean repeatEntity : repeatList) {
				ActivityRepeatDao.getInstance().insert(conn, id, repeatEntity.getStarttime(), repeatEntity.getEndtime(),
						repeatEntity.getDescription());
			}

			for (final ActivityPhotoUploadBean photoEntity : photoList) {
				PhotoDao.getInstance().insert(conn, String.valueOf(id),
						"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
						photoEntity.getName() + "." + photoEntity.getExtension(), "1", PhotoType.ACTIVITY.toString());
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

	public List<ActivityBean> selectActivity(final String langCode, final int activiyId, final int type,
			final String repeatType, final int status, final int target, final int charge)
			throws SiDCException, SQLException {

		Connection conn = null;

		List<ActivityBean> list = new ArrayList<ActivityBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = ActivityHeaderDao.getInstance().select(conn, activiyId, type, repeatType, status, target, charge);

			list = this.activityDataProcess(conn, list, langCode);

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

	public String selectRepeatStarTime(final int activityId, final int repeatId) throws SiDCException, SQLException {

		Connection conn = null;

		String starTime = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			starTime = ActivityRepeatDao.getInstance().selectStartTime(conn, activityId, repeatId);
		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return starTime;
	}

	public String selectRepeatFrequent(final int activiyId) throws SiDCException, SQLException {

		Connection conn = null;

		String repeatFrequent = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			repeatFrequent = ActivityHeaderDao.getInstance().selectRepeatFrequent(conn, activiyId);

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return repeatFrequent;
	}

	public void updateFee(final int feeId, final int status, final List<ActivityFeeLangBean> langList)
			throws SiDCException, SQLException {

		Connection conn = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			ActivityFeeDao.getInstance().update(conn, feeId, status);
			ActivityFeeLangDao.getInstance().delete(conn, feeId);

			for (final ActivityFeeLangBean entity : langList) {
				ActivityFeeLangDao.getInstance().insert(conn, feeId, entity.getLangcode(), entity.getName());
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

	public void updateType(final int typeId, final int status, final List<ActivityTypeBean> langList)
			throws SiDCException, SQLException {

		Connection conn = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			ActivityTypeDao.getInstance().update(conn, typeId, status);
			ActivityTypeLangDao.getInstance().delete(conn, typeId);

			for (final ActivityTypeBean entity : langList) {
				ActivityTypeLangDao.getInstance().insert(conn, typeId, entity.getLangcode(), entity.getName(),
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

	public void update(final int id, final int typeId, final String repeatType, final int lowerLimit,
			final int upperLimit, final int status, final int target, final String registrationStartTime,
			final String registrationEndTime, final int isCharge, final String memo, final List<ActivityLangBean> list,
			final List<ActivityFeeInsertBean> feeList, final List<ActivityRepeatBean> repeatList,
			final List<ActivityPhotoUploadBean> photoList, final boolean isPhotoUpdate, final String repeatFrequentJson)
			throws SiDCException, SQLException {

		Connection conn = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			ActivityHeaderDao.getInstance().update(conn, id, typeId, repeatType, lowerLimit, upperLimit, status, target,
					registrationStartTime, registrationEndTime, isCharge, memo, repeatFrequentJson);

			ActivityHeaderLangDao.getInstance().delete(conn, id);
			for (final ActivityLangBean langEntity : list) {
				ActivityHeaderLangDao.getInstance().insert(conn, id, langEntity.getLangcode(), langEntity.getTitle(),
						langEntity.getContent(), langEntity.getLocation());
			}

			ActivityToFeeDao.getInstance().delete(conn, id);
			for (final ActivityFeeInsertBean feeEntity : feeList) {
				ActivityToFeeDao.getInstance().insert(conn, id, feeEntity.getFeeid(), feeEntity.getPrice());
			}

			ActivityRepeatDao.getInstance().delete(conn, id);
			for (final ActivityRepeatBean repeatEntity : repeatList) {
				ActivityRepeatDao.getInstance().insert(conn, id, repeatEntity.getStarttime(), repeatEntity.getEndtime(),
						repeatEntity.getDescription());
			}

			if (isPhotoUpdate) {
				PhotoDao.getInstance().delete(conn, String.valueOf(id), PhotoType.ACTIVITY.toString());
				for (final ActivityPhotoUploadBean photoEntity : photoList) {
					PhotoDao.getInstance().insert(conn, String.valueOf(id),
							"/" + id + "/" + photoEntity.getName() + "." + photoEntity.getExtension(),
							photoEntity.getName() + "." + photoEntity.getExtension(), "1",
							PhotoType.ACTIVITY.toString());
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

	public boolean isExistOfFeeID(final int feeId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = ActivityFeeDao.getInstance().isExist(conn, feeId);

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

	public boolean isExistOfFeeID(final int activityId, final List<Integer> feeList)
			throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			for (final int id : feeList) {
				if (!ActivityToFeeDao.getInstance().isExist(conn, activityId, id)) {
					return false;
				}
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

	public boolean isExistOfTypeID(final int typeId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = ActivityTypeDao.getInstance().selectById(conn, typeId);

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

	public boolean isExistOfActivityID(final int activityId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = ActivityHeaderDao.getInstance().selectById(conn, activityId);
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

	public String getRepeatType(final int activityId) throws SiDCException, SQLException {

		Connection conn = null;
		String repeatType = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!ActivityHeaderDao.getInstance().selectById(conn, activityId)) {
				return null;
			}

			repeatType = ActivityHeaderDao.getInstance().selectRepeatType(conn, activityId);

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return repeatType;
	}

	public boolean isExistOfOrderID(final int orderId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = ActivityOrderHeaderDao.getInstance().selectById(conn, orderId);
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

	public boolean isExistOfRepeatType(final int activityId, final int repeatId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = ActivityRepeatDao.getInstance().isExist(conn, activityId, repeatId);
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

	public boolean isExistOfIdentity(final int activityId, final int repeadId, final List<String> list)
			throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final String id : list) {
				if (ActivityOrderLineDao.getInstance().isExistOfIdentity(conn, activityId, repeadId, id)) {
					return true;
				}
				isExist = false;
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

	public boolean isExistOfPassport(final int activityId, final int repeadId, final List<String> list)
			throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final String passport : list) {
				if (ActivityOrderLineDao.getInstance().isExistOfPassport(conn, activityId, repeadId, passport)) {
					return true;
				}
				isExist = false;
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

	public boolean isApprovedOfOrderStatus(final int orderId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final String status = ActivityOrderHeaderDao.getInstance().selectStatusById(conn, orderId);

			if (status.equals("10")) {
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

	public boolean isOverLimit(final int activityId, final int repeatId, final int count)
			throws SiDCException, SQLException {

		Connection conn = null;
		boolean isExist = true;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isExist = ActivityHeaderDao.getInstance().isOverLimit(conn, activityId, repeatId, count);

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

	public boolean isCharge(final int activityId) throws SiDCException, SQLException {

		Connection conn = null;
		boolean isCharge = false;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			isCharge = ActivityHeaderDao.getInstance().isCharge(conn, activityId);

		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return isCharge;
	}

	private List<ActivityBean> activityDataProcess(Connection conn, List<ActivityBean> list, final String langCode)
			throws SiDCException, SQLException {
		try {
			for (ActivityBean entity : list) {

				// 語系
				entity.setLangs(ActivityHeaderLangDao.getInstance().selectByLangCode(conn, entity.getId(), langCode));

				// 費用
				entity.setFeelist(ActivityToFeeDao.getInstance().selectByActivityId(conn, entity.getId()));

				// 日期
				entity.setRepeatlist(ActivityRepeatDao.getInstance().selectByActivityId(conn, entity.getId()));

				// photo
				entity.setPhotolist(PhotoDao.getInstance().selectByReferid(conn, String.valueOf(entity.getId()),
						PhotoType.ACTIVITY.toString()));

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
	 * 參數有 token 適用 mobile create ,sits tv create 另議
	 * 
	 * @throws SiDCException
	 */
	public int signUp(final String publicKey, final String privateKey, final String roomNo,
			final List<ActivitySignUpBean> list, final int id, final int repeatId, final String memo,
			final String activityDate) throws SQLException, SiDCException {

		Connection conn = null;
		int orderId = -1;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = TokenKeyDao.getInstance().selectMobileInfoId(conn, publicKey, privateKey);
			// int mobileId = 1;
			String startTime = ActivityRepeatDao.getInstance().selectStartTime(conn, id, repeatId);

			final String charge = String.valueOf(ActivityHeaderDao.getInstance().isCharge(conn, id) ? 10 : 0);// 是否收費

			final String billNo = BillDao.getInstance().searchBillNoWithCheckIn(conn, roomNo);

			final List<ActivityBean> activityList = ActivityHeaderDao.getInstance().select(conn, id, 0, null, -1, -1,
					-1);

			final String repeatType = activityList.get(0).getRepeattype();

			if (repeatType.equals("3")) {
				startTime = activityDate + startTime.substring(10);
			}

			orderId = ActivityOrderHeaderDao.getInstance().insert(conn, id, repeatId, charge, billNo, roomNo, mobileId,
					startTime, memo);

			int qty = 0;// 人數
			float amount = 0;// 收費金額
			for (final ActivitySignUpBean entity : list) {
				if (!StringUtils.isBlank(entity.getGuestno())) {
					if (GuestDao.getInstance().isOverDepartureDate(conn, roomNo, entity.getGuestno(), startTime)) {
						throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "activity date over departure date.");
					}
				}

				ActivityOrderLineDao.getInstance().insert(conn, orderId, entity.getFirstname(), entity.getLastname(),
						entity.getSex(), entity.getIdentitytype(), entity.getIdentity(), entity.getPassport(),
						entity.getPhone(), entity.getGuestno(), entity.getEmail(), entity.getFeeid());

				amount += Float.valueOf(ActivityToFeeDao.getInstance().selectPrice(conn, id, entity.getFeeid()));
				++qty;
			}

			ActivityOrderHeaderDao.getInstance().updateAmountQty(conn, orderId, amount, qty);

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
		return orderId;
	}

	public List<ActivityOrderListResponse> selectOrder(final String deviceid, final int orderId, final int activityId,
			final String status, final String paymentStatus, final String startTime, final String endTime,
			final String langCode) throws SiDCException, SQLException {

		Connection conn = null;
		List<ActivityOrderListResponse> list = new ArrayList<ActivityOrderListResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = MobileInfoDao.getInstance().getId(conn, deviceid);

			list = ActivityOrderHeaderDao.getInstance().select(conn, orderId, activityId, status, paymentStatus,
					startTime, endTime, mobileId, langCode);

			for (ActivityOrderListResponse entity : list) {
				final List<ActivityLangBean> langList = ActivityHeaderLangDao.getInstance().selectByLangCode(conn,
						entity.getActivityid(), langCode);

				if (ActivityHeaderDao.getInstance().selectRepeatType(conn, entity.getActivityid()).equals("3")) {
					entity.setStarttime(entity.getActivitydate() + entity.getStarttime().substring(10));
					entity.setEndtime(entity.getActivitydate() + entity.getEndtime().substring(10));
				}

				if (!langList.isEmpty()) {
					ActivityLangBean langEntity = langList.get(0);
					entity.setActivitycontent(langEntity.getContent());
					entity.setActivitylocation(langEntity.getLocation());
					entity.setActivitytitle(langEntity.getTitle());
				}

				entity.setList(ActivityOrderLineDao.getInstance().select(conn, entity.getOrderid(), langCode));
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

	public List<ActivityOrderListResponse> selectOrderWithBackend(final int activityId, final int activityRepeatId,
			final int orderId, final String status, final String paymentStatus, final String roomNo,
			final String startTime, final String endTime, final String langCode) throws SiDCException, SQLException {

		Connection conn = null;
		List<ActivityOrderListResponse> list = new ArrayList<ActivityOrderListResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = orderListProcess(conn, orderId, null, activityId, activityRepeatId, status, paymentStatus, roomNo,
					startTime, endTime, langCode);

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
	 * step 1:藉由 fee id,id type 去找到 order line 的 order id. step 2:再用其他條件下去篩選
	 * 
	 * @throws SQLException
	 */
	public List<ActivityOrderListResponse> selectOrderWithBackend(final int activityId, final int activityRepeatId,
			final int orderId, final String status, final String paymentStatus, final String roomNo,
			final String startTime, final String endTime, final String langCode, final String feeIdWhereInStr,
			final String identityTypeWhereInStr) throws SiDCException, SQLException {

		Connection conn = null;
		List<ActivityOrderListResponse> list = new ArrayList<ActivityOrderListResponse>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final List<Integer> orderIdList = ActivityOrderLineDao.getInstance().selectOrderIdByWhereIn(conn,
					feeIdWhereInStr, identityTypeWhereInStr);

			// 沒資料就直接 return 否則 下面會把資料撈出來
			if (orderIdList.isEmpty()) {
				return new ArrayList<ActivityOrderListResponse>();
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

			list = orderListProcess(conn, orderId, orderIdWhereInStr, activityId, activityRepeatId, status,
					paymentStatus, roomNo, startTime, endTime, langCode);

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

	public void updateOrderStatus(final int id, final String status) throws SiDCException, SQLException {

		Connection conn = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			ActivityOrderHeaderDao.getInstance().updateStatus(conn, id, status);

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

	public void updateOrderPaymentStatus(final int id, final String paymentStatus) throws SiDCException, SQLException {

		Connection conn = null;

		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			ActivityOrderHeaderDao.getInstance().updatePaymentStatus(conn, id, paymentStatus);

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

	public boolean isOverRoomDepartureDate(final int id, final int repeatId, final String roomNo) throws SQLException {
		Connection conn = null;
		boolean isOver = true;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			final String date = ActivityRepeatDao.getInstance().selectStartTime(conn, id, repeatId);
			isOver = GuestDao.getInstance().isOverDepartureDate(conn, roomNo, date);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return isOver;
	}

	public String findOrderMobilePushToken(final int orderId) throws SQLException, SiDCException {

		Connection conn = null;
		String pushToken = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int mobileId = ActivityOrderHeaderDao.getInstance().findMobileId(conn, orderId);

			pushToken = MobileInfoDao.getInstance().getPushToken(conn, mobileId);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return pushToken;
	}

	public void backendOrderCheck(final String roomNo, final String token, final String reqeustStaffId)
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

			if (reqeustStaffId.equals(staffId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "token and staff id is not match.");
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void orderCreateCheck(final String roomNo, final int activityId, final int repeatId, final int count,
			final List<Integer> feeList, final List<String> idList, final List<String> passportList)
			throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!RoomDao.getInstance().isCheckIn(conn, roomNo)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room not check in.");
			}
			if (!ActivityHeaderDao.getInstance().selectById(conn, activityId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "activity id not existed.");
			}
			if (!ActivityRepeatDao.getInstance().isExist(conn, activityId, repeatId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of request(repeat id not belong activity id).");
			}
			if (ActivityHeaderDao.getInstance().isOverLimit(conn, activityId, repeatId, count)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(over the upper limit).");
			}
			for (final int id : feeList) {
				if (!ActivityToFeeDao.getInstance().isExist(conn, activityId, id)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "fee id not existed.");
				}
			}
			for (final String id : idList) {
				if (ActivityOrderLineDao.getInstance().isExistOfIdentity(conn, activityId, repeatId, id)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "identity already existed.");
				}
			}
			for (final String passport : passportList) {
				if (ActivityOrderLineDao.getInstance().isExistOfPassport(conn, activityId, repeatId, passport)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "passprot already existed.");
				}
			}
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void paymentStatusUpdateCheck(final int orderId) throws SQLException, SiDCException {

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (!ActivityOrderHeaderDao.getInstance().selectById(conn, orderId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "order id not existed.");
			}

			final String status = ActivityOrderHeaderDao.getInstance().selectStatusById(conn, orderId);

			if (!status.equals("10")) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "order id not yet approved.");
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	private List<ActivityOrderListResponse> orderListProcess(Connection conn, final int orderId,
			final String orderIdWhereInStr, final int activityId, final int activityRepeatId, final String status,
			final String paymentStatus, final String roomNo, final String startTime, final String endTime,
			final String langCode) throws SQLException {

		List<ActivityOrderListResponse> list = ActivityOrderHeaderDao.getInstance().select(conn, orderId,
				orderIdWhereInStr, activityId, activityRepeatId, status, paymentStatus, roomNo, startTime, endTime,
				langCode);

		for (ActivityOrderListResponse entity : list) {
			final List<ActivityLangBean> langList = ActivityHeaderLangDao.getInstance().selectByLangCode(conn,
					entity.getActivityid(), langCode);

			if (ActivityHeaderDao.getInstance().selectRepeatType(conn, entity.getActivityid()).equals("3")) {
				entity.setStarttime(entity.getActivitydate() + entity.getStarttime().substring(10));
				entity.setEndtime(entity.getActivitydate() + entity.getEndtime().substring(10));
			}

			if (!langList.isEmpty()) {
				ActivityLangBean langEntity = langList.get(0);
				entity.setActivitycontent(langEntity.getContent());
				entity.setActivitylocation(langEntity.getLocation());
				entity.setActivitytitle(langEntity.getTitle());
			}

			entity.setList(ActivityOrderLineDao.getInstance().select(conn, entity.getOrderid(), langCode));
		}
		return list;
	}
}
