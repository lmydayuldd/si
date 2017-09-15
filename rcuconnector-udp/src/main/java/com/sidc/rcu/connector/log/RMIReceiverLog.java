/**
 * 
 */
package com.sidc.rcu.connector.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sidc.utils.log.ReceiverRcuLogBean;

/**
 * @author Nandin Pao
 *
 */
public class RMIReceiverLog {

	private final static Logger logger = LoggerFactory.getLogger(RMIReceiverLog.class);

	private ThreadLocal<ReceiverRcuLogBean> threadLocal = new ThreadLocal<ReceiverRcuLogBean>();

	private static class SingletonHolder {
		private static RMIReceiverLog instance = new RMIReceiverLog();
	}

	private RMIReceiverLog() {
	}

	public static RMIReceiverLog getInstance() {
		return SingletonHolder.instance;
	}

	public void initial(Logger logger, String uuid) {
		ReceiverRcuLogBean logbean = new ReceiverRcuLogBean(logger);
		logbean.setId(uuid);
		threadLocal.set(logbean);
	}

	public void setRoomNo(String roomno) {
		this.threadLocal.get().setRoomno(roomno);
	}

	public void setCatalogue(String catalogue) {
		this.threadLocal.get().setCatalogue(catalogue);
	}

	public void setContent(String content) {
		this.threadLocal.get().setContent(content);
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
