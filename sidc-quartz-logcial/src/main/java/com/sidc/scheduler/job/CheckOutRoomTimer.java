//package com.sidc.scheduler.job;
//
//import java.io.File;
//import java.sql.SQLException;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Unmarshaller;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.sidc.blackcore.api.sits.rcu.request.HvacCommandRequest;
//import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
//import com.sidc.configuration.conf.Env;
//import com.sidc.corejob.IJob;
//import com.sidc.corejob.JobInfo;
//import com.sidc.corejob.JobsAction;
//import com.sidc.dao.quartz.manager.ScheduleManager;
//import com.sidc.dao.sits.manager.RoomManager;
//import com.sidc.quartz.api.request.CheckOutRoomScheduleListRequest;
//import com.sidc.quartz.api.response.CheckOutRoomScheduleResposne;
//import com.sidc.rcu.sdk.command.HVACCommandClient;
//import com.sidc.utils.exception.SiDCException;
//import com.sidc.utils.log.LogAction;
//
//public class CheckOutRoomTimer extends JobsAction implements IJob {
//
//	private int timer;
//	private String className = "";
//	private ScheduleInfoResponse info = null;
//	private SidcUrlsConfiguration sidcConfigure = null;
//	private final ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
//	private final static Logger logger = LoggerFactory.getLogger(CheckOutRoomTimer.class);
//
//	public CheckOutRoomTimer() throws SQLException {
//		className = this.getClass().getSimpleName();
//		ScheduleStatusRequest request = new ScheduleStatusRequest(className.substring(className.lastIndexOf('.') + 1));
//		info = ScheduleManager.getInstance().select(request.getJobname());
//	}
//
//	public void execute(JobExecutionContext context) throws JobExecutionException {
//		// TODO Auto-generated method stub
//		try {
//			sidcConfigure = readSidcUrlsConfiguration(new File(Env.SYSTEM_DEF_PATH + Env.SIDC_URL_PATH));
//			JsonElement jsonElement = new JsonParser().parse(info.getCommands());
//			JsonObject jsonObject = jsonElement.getAsJsonObject();
//			int temperature = Integer.parseInt(jsonObject.get("temperature").toString());
//			timer = Integer.parseInt(jsonObject.get("timer").toString());
//
//			final CheckOutRoomScheduleListRequest rooms = RoomManager.getInstance().listCheckOutRoom();
//			for (CheckOutRoomScheduleResposne room : rooms.getList()) {
//				HvacCommandRequest openHVAC = new HvacCommandRequest(room.getRoomno(), room.getIp(), true, temperature);
//				new HVACCommandClient(sidcConfigure.getUrls().get(1).toString(), openHVAC).execute();
//				logger.debug("Room no：" + room.getRoomno() + " HVAC open success");
//			}
//
//			start();
//		} catch (Exception e) {
//			logger.debug("HVAC open Error： " + e.getMessage());
//		}
//	}
//
//	@Override
//	public JobInfo setJobInfo() {
//		// TODO Auto-generated method stub
//		JobInfo job = new JobInfo();
//		try {
//			job.setJobName(info.getJobname()); // 設置任務名稱
//			job.setJobGroup(info.getGroupname()); // 設置任務分組
//			job.setTriggerType(0); // 設定任務執行計劃
//									// 0：使用cron表達式執行，使用此項時cron表達式必填。
//			job.setCron(info.getExecutiontime());
//			job.setCronDescription(info.getDescription());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			LogAction.getInstance().debug("Check Out job info Error： " + e.getMessage());
//		}
//		return job;
//	}
//
//	protected void start() {
//
//		exec.schedule(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					final CheckOutRoomScheduleListRequest rooms = RoomManager.getInstance().listCheckOutRoom();
//					for (CheckOutRoomScheduleResposne room : rooms.getList()) {
//						HvacCommandRequest request = new HvacCommandRequest(room.getRoomno(), room.getIp(), false);
//						new HVACCommandClient(sidcConfigure.getUrls().get(1).toString(), request).execute();
//						logger.debug("Room no " + room.getRoomno() + " HVAC close success");
//					}
//				} catch (SiDCException e) {
//					// TODO Auto-generated catch block
//					logger.debug("HVAC close Error： " + e.getMessage());
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					logger.debug("HVAC close Error： " + e.getMessage());
//				}
//			}
//		}, timer, TimeUnit.MINUTES);
//	}
//
//	public void shutdown() {
//
//		try {
//			if (!exec.isShutdown()) {
//				exec.shutdown();
//				logger.debug("HVAC close timer shutdown!!");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	protected void initial() throws SiDCException, Exception {
//		// TODO Auto-generated method stub
//		LogAction.getInstance().initial(logger, this.getClass().getCanonicalName());
//	}
//
//	private SidcUrlsConfiguration readSidcUrlsConfiguration(File file) throws Exception {
//
//		JAXBContext jaxbContext = JAXBContext.newInstance(SidcUrlsConfiguration.class);
//
//		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//		SidcUrlsConfiguration config = (SidcUrlsConfiguration) jaxbUnmarshaller.unmarshal(file);
//
//		return config;
//	}
//
//}
