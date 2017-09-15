/**
 * 
 */
package com.sidc.rcu.connector.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.utils.log.SenderRcuLogBean;

/**
 * @author Nandin Pao
 *
 */
public class RASenderLog {

	private final static Logger logger = LoggerFactory.getLogger(RASenderLog.class);

	private ThreadLocal<SenderRcuLogBean> threadLocal = new ThreadLocal<SenderRcuLogBean>();

	private static class SingletonHolder {
		private static RASenderLog instance = new RASenderLog();
	}

	private RASenderLog() {
	}

	public static RASenderLog getInstance() {
		return SingletonHolder.instance;
	}

	public void initial(Logger logger, String uuid) {
		SenderRcuLogBean logbean = new SenderRcuLogBean(logger);
		logbean.setId(uuid);
		threadLocal.set(logbean);
	}

	public void setRoomNo(String roomno) {
		this.threadLocal.get().setRoomno(roomno);
	}

	public void setKeycode(String keycode) {
		this.threadLocal.get().setKeycode(keycode);
	}

	public void writeRecord() {
		this.threadLocal.get()
				.setPerformance(String.valueOf(System.currentTimeMillis() - this.threadLocal.get().getStarttime()));
		logger.info(this.threadLocal.get().toString());
	}

	public void setUUID(String id) {
		this.threadLocal.get().setId(id);
	}

	public void warn(String str) {
		threadLocal.get().getLogger().warn(threadLocal.get().getId() + "|" + str);
	}

	public void info(String str) {
		if (threadLocal.get().getLogger().isInfoEnabled()) {
			threadLocal.get().getLogger().info(threadLocal.get().getId() + "|" + str);
		}
	}

	public void debug(String str) {
		if (threadLocal.get().getLogger().isDebugEnabled()) {
			threadLocal.get().getLogger().debug(threadLocal.get().getId() + "|" + str);
		}
	}

	public void error(String str, Throwable e) {
		threadLocal.get().getLogger().error(threadLocal.get().getId() + "|" + str, e);
	}

	public void trace(String str) {
		if (threadLocal.get().getLogger().isTraceEnabled()) {
			threadLocal.get().getLogger().trace(threadLocal.get().getId() + "|" + str);
		}
	}

}
