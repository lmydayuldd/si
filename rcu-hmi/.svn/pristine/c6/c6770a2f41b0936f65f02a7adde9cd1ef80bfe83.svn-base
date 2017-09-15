package com.sidc.rcu.hmi.api.parser;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.time.JsonDateDeserializer;
import com.sidc.utils.time.TimeStandardSetting;

public class UDPParser<T> {

	private final static Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer())
			.setDateFormat(TimeStandardSetting.STANDARD_TIMESTAMP).create();

	private UDPParser() {
	}

	private static class LazyHolder {
		public static final UDPParser INSTANCE = new UDPParser();
	}

	public static UDPParser getInstance() {
		return LazyHolder.INSTANCE;
	}

	public List parse(Object obj, Type type) throws SiDCException {
		return gson.fromJson(gson.toJson(obj), type);
	}

	public <T> T parse(String str, Class<T> clazz) throws SiDCException {
		return gson.fromJson(str, clazz);
	}
	
	public String toJson(Object obj){
		return gson.toJson(obj);
	}
}
