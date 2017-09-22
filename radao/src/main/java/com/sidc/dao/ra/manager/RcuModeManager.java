package com.sidc.dao.ra.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuDefaultModeResponse;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.ra.RcuGroupModeDao;
import com.sidc.dao.ra.RcuModeDao;
import com.sidc.dao.ra.RcuModeDeviceDao;
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

	public StringBuilder selectCommonMode(final String keyname) throws SQLException {

		StringBuilder result = new StringBuilder();
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			result = RcuModeDao.getInstance().selectCommonMode(conn, keyname);

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

	public void updateGroupMode(final int groupId, final int modeId, final String content) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			RcuGroupModeDao.getInstance().update(conn, groupId, modeId, content);

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

			id = RcuModeDao.getInstance().insert(conn, keyName, content);

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

}
