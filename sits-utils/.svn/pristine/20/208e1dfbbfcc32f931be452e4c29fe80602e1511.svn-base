/**
 * 
 */
package com.sidc.utils.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nandin
 *
 */
public class DataCenter {

	private Map<String, Object> data = new HashMap<String, Object>();

	private DataCenter() {
	}

	private static class LazyHolder {
		public static final DataCenter INSTANCE = new DataCenter();
	}

	public static DataCenter getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void put(String key, Object value) {
		data.put(key, value);
	}

	public Object get(String key) {
		if (data == null || data.isEmpty()) {
			return null;
		}

		if (data.containsKey(key)) {
			return data.get(key);
		}
		return null;
	}

	public void remove(String key) {
		this.data.remove(key);
	}

	public void clear() {
		this.data.clear();
	}
}
