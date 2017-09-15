package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;

import com.sidc.blackcore.api.agent.request.AgentPostRequest;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.cons.ConsDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class PostManager {

	private PostManager() {
	}
	
	private static final class lazyHolder {
		public static final PostManager INSTANCE = new PostManager();
	}
	
	public static PostManager getInstance() {
		return lazyHolder.INSTANCE;
	}
	
	public void updateStatus(AgentPostRequest enity) throws SiDCException, SQLException {
		boolean isPass = false;
		
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);
			
			isPass = ConsDao.getInstance().existAgentId(conn, enity.getAgentid());
			
			if (isPass) {
				ConsDao.getInstance().updateStatus(conn, enity);
			} else {
				throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "agent id does not exist.");
			}
			
			conn.commit();			
		} catch (Exception e) {
			// TODO: handle exception
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}
}
