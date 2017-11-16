package com.sidc.corejob.common;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.matchers.GroupMatcher;

import com.sidc.dao.quartz.manager.ScheduleManager;
import com.sidc.quartz.api.response.ScheduleStatusResponse;

/**
 * 
 * @author Allen Huang
 *
 */
public class ReflectInterface {

	/**
	 * 獲取同一路徑下所有子類或接口實現類別
	 * 
	 * @param intf
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
	
	public List<Class<?>> getAllAssignedClass(Class<?> cal) throws IOException, ClassNotFoundException, SQLException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Class<?> c : getClasses(cal)) {

			if (cal.isAssignableFrom(c) && !cal.equals(c)) {
				classes.add(c);
			}
		}
		return classes;
	}

	/**
	 * 取得當前類路徑下的所有類別
	 * 
	 * @param cls
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
	public static List<Class<?>> getClasses(Class<?> cls) throws IOException, ClassNotFoundException, SQLException {
		String resourcePath = ReflectInterface.class.getResource("").getPath();
		resourcePath = resourcePath.substring(0, resourcePath.indexOf("!"));
		URL url = new URL(resourcePath);
		
		return getClasses(url);
	}

	/**
	 * 疊代尋找類別
	 * 
	 * @param dir
	 * @param pk
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException 
	 * @throws SQLException 
	 */
	@SuppressWarnings("resource")
	private static List<Class<?>> getClasses(URL url) throws ClassNotFoundException, IOException, SQLException {

		String pk = "com.sidc.scheduler.job";
		String path = pk.replace('.', '/');
		List<Class<?>> classes = new ArrayList<Class<?>>();
		
		JarFile jarFile = new JarFile(url.getPath());
		for (Enumeration<JarEntry> entries = jarFile.entries() ; entries.hasMoreElements() ;)
	    {
	        JarEntry jarEntry = entries.nextElement();
			if (jarEntry.isDirectory()) continue;
			if (jarEntry.getName().indexOf(path) < 0) continue;
	        String file = jarEntry.getName();
	        if (file.endsWith(".class"))
	        {
	        	String classname = file.replace('/', '.').substring(0, file.length() - 6);
	        	Class<?> c = Class.forName(classname);
	        	if (c.getSimpleName().equals("")) continue;
	        	final ScheduleStatusResponse infoEntity = ScheduleManager.getInstance().isEnabled(c.getSimpleName());
				if (infoEntity.isEnabled()) {
					classes.add(c);
				}
	        }
	    }
		
		return classes;
	}

	/**
	 * 判斷此工作是否在執行中
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @return
	 */
	public static boolean ckeckRunJob(Scheduler scheduler, String jobName, String jobGroup) {
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);

			for (JobKey jobKey : jobKeys) {
				if (jobName.equals(jobKey.getName()) && jobGroup.equals(jobKey.getGroup())) {
					// 執行中
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 毫秒數轉化為中文顯示
	 * 
	 * @param mss
	 * @return
	 */
	public static String formatDuring(long mss) {
		String returnValue = "";
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		if (days != 0l) {
			returnValue += days + "天";
		}
		if (hours != 0l) {
			returnValue += hours + "小時";
		}
		if (minutes != 0l) {
			returnValue += minutes + "分";
		}
		if (seconds != 0l) {
			returnValue += seconds + "秒";
		}
		return returnValue;
	}

	public static boolean isEmpty(Object obj) {
		if (obj == null || obj.toString().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
}