/**
 * 
 */
package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcugroupdevice.bean.RcuGroupDeviceBean;
import com.sidc.blackcore.api.ra.rcugroupdevice.bean.RcuGroupDeviceGroupInfoBean;
import com.sidc.blackcore.api.ra.rcugroupdevice.bean.RcuGroupDeviceInfoBean;
import com.sidc.blackcore.api.ra.rcugroupdevice.bean.RcuGroupDeviceInsertBean;
import com.sidc.blackcore.api.ra.rcugroupdevice.bean.RcuGroupInfoBean;
import com.sidc.blackcore.api.ra.response.RcuGroupEnity;
import com.sidc.blackcore.api.sits.langs.bean.LangsBean;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.RCUGroupDao;
import com.sidc.dao.ra.RCUGroupDeviceDao;
import com.sidc.dao.ra.RcuDeviceDao;
import com.sidc.dao.ra.RcuDeviceGroupLangDao;
import com.sidc.dao.ra.RcuDeviceLangDao;
import com.sidc.dao.ra.room.RcuGroupDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public class RcuGroupManager {

	private RcuGroupManager() {
	}

	private static class LazyHolder {
		public static final RcuGroupManager INSTANCE = new RcuGroupManager();
	}

	public static RcuGroupManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void insert(final String roomno, final int rcu_group_id) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RcuGroupDao.getInstance().insert(conn, roomno, rcu_group_id);

			conn.commit();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

	}

	public void update(final String roomno, final int rcu_group_id) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RcuGroupDao.getInstance().update(conn, roomno, rcu_group_id);

			conn.commit();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

	}

	public void delete(final String roomno) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RcuGroupDao.getInstance().delete(conn, roomno);

			conn.commit();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

	}

	public List<RcuGroupEnity> listRoomRCU() throws SQLException {
		List<RcuGroupEnity> result = null;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RcuGroupDao.getInstance().listRcuGroup(conn);

			conn.commit();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return result;
	}

	public boolean existRCUGroup(final int rcu_group_id) throws SQLException {
		boolean result = false;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RcuGroupDao.getInstance().existRCUGroup(conn, rcu_group_id);

			conn.commit();

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return result;
	}

	/**
	 * 新增 group device
	 * 
	 * @param groups
	 * @throws SQLException
	 */
	public void insertGroupDevice(final List<RcuGroupDeviceInsertBean> groups) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final RcuGroupDeviceInsertBean entity : groups) {
				RCUGroupDeviceDao.getInstance().deleteByGrouId(conn, entity.getGroupid());
				for (final int deviceId : entity.getDevices()) {
					RCUGroupDeviceDao.getInstance().insert(conn, entity.getGroupid(), deviceId);
				}
			}
			conn.commit();

		} catch (Exception ex) {
			conn.rollback();
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	/**
	 * 用房型id取的底下device資訊
	 * 
	 * @param groupId
	 * @return List<RcuGroupDeviceBean>
	 * @throws SQLException
	 * @throws SiDCException
	 */
	public List<RcuGroupDeviceBean> findGroupDevice(final int groupId) throws SQLException, SiDCException {
		Connection conn = null;

		List<RcuGroupDeviceBean> result = new ArrayList<RcuGroupDeviceBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			// 用 房型資料找到device
			final List<RcuGroupDeviceGroupInfoBean> devices = RCUGroupDeviceDao.getInstance().selectByRcuGroupid(conn,
					groupId);

			if (devices.isEmpty()) {
				throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find device");
			}

			List<RcuGroupInfoBean> groups = new ArrayList<RcuGroupInfoBean>();
			List<Integer> deviceList = new ArrayList<Integer>();
			// RcuGroupInfoBean
			// 整理 device group資料
			for (final RcuGroupDeviceGroupInfoBean entity : devices) {
				boolean isPass = true;
				for (final RcuGroupInfoBean groupEntity : groups) {
					if (groupEntity.getGroupid() == entity.getGroupid()) {
						isPass = false;
						break;
					}
				}

				if (isPass) {
					groups.add(new RcuGroupInfoBean(entity.getGroupid(), entity.getGroupname()));
				}
				deviceList.add(entity.getDeviceid());
			}

			// format data to output & 語言資料
			for (final RcuGroupInfoBean groupEneity : groups) {
				List<RcuGroupDeviceInfoBean> list = new ArrayList<RcuGroupDeviceInfoBean>();

				for (final RcuGroupDeviceGroupInfoBean deviceEntity : devices) {
					if (groupEneity.getGroupid() == deviceEntity.getGroupid()) {
						final String device = RcuDeviceDao.getInstance().findDevice(conn, deviceEntity.getDeviceid());
						final List<LangsBean> deviceLangs = RcuDeviceLangDao.getInstance().listLang(conn,
								deviceEntity.getDeviceid());
						list.add(new RcuGroupDeviceInfoBean(deviceEntity.getDeviceid(), device, deviceLangs));
					}
				}

				final List<LangsBean> langs = RcuDeviceGroupLangDao.getInstance().listLang(conn,
						groupEneity.getGroupid());
				result.add(new RcuGroupDeviceBean(groupEneity.getGroupid(), groupEneity.getCatalogue(), langs, list));
			}

		} catch (SiDCException e) {
			throw new SiDCException(e.getErrorCode(), e.getMessage());
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return result;
	}

	/**
	 * 新增 房型
	 * 
	 * @param groupName
	 * @param devices
	 * @throws SQLException
	 */
	public void insertGroup(final String groupName, final List<Integer> devices) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int id = RCUGroupDao.getInstance().findId(conn) + 1;

			RCUGroupDao.getInstance().insert(conn, id, groupName);

			for (final int deviceId : devices) {
				RCUGroupDeviceDao.getInstance().insert(conn, id, deviceId);
			}

			conn.commit();

		} catch (Exception ex) {
			conn.rollback();
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	/**
	 * 刪除房型
	 * 
	 * @param groupId
	 * @throws SQLException
	 */
	public void deleteGroup(final int groupId) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RCUGroupDeviceDao.getInstance().deleteByGrouId(conn, groupId);

			RCUGroupDao.getInstance().delete(conn, groupId);

			conn.commit();

		} catch (Exception ex) {
			conn.rollback();
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}
}
