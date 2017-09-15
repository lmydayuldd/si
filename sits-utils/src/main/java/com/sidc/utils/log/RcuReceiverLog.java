/**
 * 
 */
package com.sidc.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nandin Pao
 *
 */
public class RcuReceiverLog {

	private final static Logger logger = LoggerFactory.getLogger(RcuReceiverLog.class);

	private ThreadLocal<ReceiverRcuLogBean> threadLocal = new ThreadLocal<ReceiverRcuLogBean>();

	private static class SingletonHolder {
		private static RcuReceiverLog instance = new RcuReceiverLog();
	}

	private RcuReceiverLog() {
	}

	public static RcuReceiverLog getInstance() {
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
	
	public void setRoomIP(String roomip) {
		this.threadLocal.get().setRoomip(roomip);
	}

	public void setCatalogue(String catalogue) {
		this.threadLocal.get().setCatalogue(catalogue);
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
