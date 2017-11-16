package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialDevicesBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialDevicesInsertBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialGroupBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialGroupInsertBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialInsertBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeInitialModuleBean;
import com.sidc.blackcore.api.ra.rcumode.bean.ModeLangBean;
import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuDefaultModeResponse;
import com.sidc.blackcore.api.ra.response.RcuDeviceEnity;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.RCUGroupDao;
import com.sidc.dao.ra.RCUGroupDeviceDao;
import com.sidc.dao.ra.RcuDeviceDao;
import com.sidc.dao.ra.RcuGroupModeDao;
import com.sidc.dao.ra.RcuModeDao;
import com.sidc.dao.ra.RcuModeDeviceDao;
import com.sidc.dao.ra.RcuModeLangDao;
import com.sidc.dao.ra.RoomRcuDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class RcuModeManager {

	private RcuModeManager() {
	}

	private static class LazyHolder {
		public static final RcuModeManager INSTANCE = new RcuModeManager();
	}

	public static RcuModeManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public StringBuilder selectMode(final String roomno, final String keyname) throws SQLException {

		StringBuilder result = new StringBuilder();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RcuModeDao.getInstance().selectMode(conn, roomno, keyname);

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return result;
	}

	public StringBuilder selectCommonMode(final String keyname) throws SQLException {

		StringBuilder result = new StringBuilder();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RcuModeDao.getInstance().selectCommonMode(conn, keyname);
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return result;
	}

	public String selectModeCentent(final String keyName, final String roomNo) throws SQLException {

		String result = null;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int groupId = RoomRcuDao.getInstance().findGroupId(conn, roomNo);

			final int modeId = RcuModeDao.getInstance().searchIdByKeyName(conn, keyName);

			result = RcuGroupModeDao.getInstance().findContent(conn, groupId, modeId);

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return result;
	}

	public String searchRcuModel(final int id) throws SQLException {
		String result = null;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RcuModeDao.getInstance().searchById(conn, id);

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

	public List<RcuDefaultModeResponse> selectDefaultMode() throws SQLException {
		List<RcuDefaultModeResponse> list = new ArrayList<RcuDefaultModeResponse>();

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RcuModeDao.getInstance().searchDefaultMode(conn);

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return list;
	}

	public List<RcuDefaultModeResponse> selectAllMode() throws SQLException {
		List<RcuDefaultModeResponse> list = new ArrayList<RcuDefaultModeResponse>();

		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = RcuModeDao.getInstance().searchAllMode(conn);

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return list;
	}

	public String findContend(final int groupId, final int modeId) throws SQLException {
		String result = null;
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RcuGroupModeDao.getInstance().findContent(conn, groupId, modeId);
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}

		return result;
	}

	public void insertGroupMode(final int groupId, final int modeId, final String content) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RcuGroupModeDao.getInstance().insert(conn, groupId, modeId, content);

			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void groupModeInitial(final List<ModeInitialInsertBean> modes) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RcuGroupModeDao.getInstance().deleteAll(conn);
			RcuModeDeviceDao.getInstance().delete(conn);
			RcuModeLangDao.getInstance().deleteAll(conn);
			RcuModeDao.getInstance().deleteAll(conn);

			for (final ModeInitialInsertBean entity : modes) {
				RcuModeDao.getInstance().insertInitial(conn, entity.getModeid(), entity.getKeyname(),
						entity.getTimer());

				for (final ModeLangBean langEntity : entity.getLangs()) {
					RcuModeLangDao.getInstance().insert(conn, entity.getModeid(), langEntity.getLang(),
							langEntity.getName(), langEntity.getDescription());
				}

				for (final ModeInitialModuleBean moduleEntity : entity.getModules()) {
					RcuGroupModeDao.getInstance().insert(conn, moduleEntity.getGroupid(), entity.getModeid(),
							moduleEntity.getContent());

					for (final int deviceId : moduleEntity.getDeviceid()) {
						if (RcuModeDeviceDao.getInstance().isExist(conn, entity.getModeid(), deviceId)) {
							continue;
						}
						RcuModeDeviceDao.getInstance().insert(conn, entity.getModeid(), deviceId);
					}
				}
			}

			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public void updateGroupMode(final int groupId, final int modeId, final String content) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (RcuGroupModeDao.getInstance().isExist(conn, groupId, modeId)) {
				RcuGroupModeDao.getInstance().update(conn, groupId, modeId, content);
			} else {
				RcuGroupModeDao.getInstance().insert(conn, groupId, modeId, content);
			}

			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	public int insertMode(final String keyName, final String content, final List<Integer> devices) throws SQLException {
		Connection conn = null;
		int id = -9999;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			id = RcuModeDao.getInstance().findLastId(conn) + 1;

			RcuModeDao.getInstance().insert(conn, id, keyName, content);

			for (final int device : devices) {
				RcuModeDeviceDao.getInstance().insert(conn, id, device);
			}

			conn.commit();
		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return id;
	}

	public void deleteMode(final int modeId) throws SQLException, SiDCException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			if (RcuModeDao.getInstance().isDefault(conn, modeId)) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "default mode cant not delete.");
			}

			RcuModeDeviceDao.getInstance().delete(conn, modeId);

			RcuGroupModeDao.getInstance().delete(conn, modeId);

			RcuModeDao.getInstance().delete(conn, modeId);

			conn.commit();
		} catch (SiDCException ex) {
			conn.rollback();
			throw new SiDCException(ex.getErrorCode(), ex.getMessage());
		} catch (Exception ex) {
			conn.rollback();
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

	/*
	 * public void modeInitial(final List<ModeInitialBean> modes) throws
	 * SQLException { Connection conn = null; try { conn =
	 * ProxoolConnection.getInstance().connectSiTS(); conn.setAutoCommit(false);
	 * 
	 * RcuGroupModeDao.getInstance().deleteAll(conn);
	 * RcuModeDeviceDao.getInstance().delete(conn);
	 * RcuModeDao.getInstance().deleteAll(conn);
	 * 
	 * for (final ModeInitialBean entity : modes) {
	 * RcuModeDao.getInstance().insertInitial(conn, entity.getId(),
	 * entity.getKeyname(), entity.getTimer());
	 * 
	 * for (final ModeInitialGroupBean groupEntity : entity.getGroups()) { for
	 * (final ModeInitialDevicesBean deviceEntity : groupEntity.getDevices()) {
	 * final int deviceId = RcuDeviceDao.getInstance().findId(conn,
	 * deviceEntity.getKeycode());
	 * 
	 * if (RCUGroupDeviceDao.getInstance().isExist(conn,
	 * groupEntity.getGroupid(), deviceId)) {
	 * 
	 * } } } }
	 * 
	 * } catch (Exception ex) { throw new SQLException(ex); } finally { if (conn
	 * != null && !conn.isClosed()) { conn.close(); } } }
	 */
	public List<ModeInitialGroupInsertBean> modeInitialDeviceInfo(final List<ModeInitialGroupBean> groups)
			throws SQLException {
		Connection conn = null;

		List<ModeInitialGroupInsertBean> list = new ArrayList<ModeInitialGroupInsertBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final ModeInitialGroupBean entity : groups) {
				List<ModeInitialDevicesInsertBean> devices = new ArrayList<ModeInitialDevicesInsertBean>();
				for (final ModeInitialDevicesBean deviceEntity : entity.getDevices()) {
					final int deviceId = RcuDeviceDao.getInstance().findId(conn, deviceEntity.getKeycode());
					if (RCUGroupDeviceDao.getInstance().isExist(conn, entity.getGroupid(), deviceId)) {
						final List<RcuDeviceEnity> deviceInfo = RcuDeviceDao.getInstance().searchByDevice(conn,
								deviceEntity.getKeycode());

						if (!deviceInfo.isEmpty()) {
							devices.add(new ModeInitialDevicesInsertBean(deviceId, deviceInfo.get(0).getGouprName(),
									deviceEntity.getKeycode(), deviceEntity.getStatus(), deviceEntity.isPower(),
									deviceEntity.getFunction(), deviceEntity.getTemperature(), deviceEntity.getSpeed(),
									deviceEntity.getTimer()));
						}
					}
				}

				list.add(new ModeInitialGroupInsertBean(entity.getGroupid(), devices));
			}

		} catch (Exception ex) {
			throw new SQLException(ex);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public void modeInitialCheck(final List<Integer> groups, final List<String> keycodes)
			throws SQLException, SiDCException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			for (final int groupId : groups) {
				if (!RCUGroupDao.getInstance().isExist(conn, groupId)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find group id.");
				}
			}

			for (final String keycode : keycodes) {
				if (!RcuDeviceDao.getInstance().isExist(conn, keycode)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "not find keycode.");
				}
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
	}

}
