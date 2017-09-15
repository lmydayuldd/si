package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

public class StbMoviePlayRequest implements Serializable {
	private static final long serialVersionUID = -6673600138829213445L;

	private List<String> stbip;
	private String type;
	private String ip;
	private String port;
	private String filepath;
	private String langfilepath;
	private boolean isavailable;
	private int bookmarktime;
	private String categoryid;
	private int delayposttime;

	public List<String> getStbip() {
		return stbip;
	}

	public String getType() {
		return type;
	}

	public String getIp() {
		return ip;
	}

	public String getPort() {
		return port;
	}

	public String getFilepath() {
		return filepath;
	}

	public String getLangfilepath() {
		return langfilepath;
	}

	public boolean isIsavailable() {
		return isavailable;
	}

	public int getBookmarktime() {
		return bookmarktime;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public int getDelayposttime() {
		return delayposttime;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void setLangfilepath(String langfilepath) {
		this.langfilepath = langfilepath;
	}

	public void setBookmarktime(int bookmarktime) {
		this.bookmarktime = bookmarktime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StbMoviePlayRequest [stbip=");
		builder.append(stbip);
		builder.append(", type=");
		builder.append(type);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", port=");
		builder.append(port);
		builder.append(", filepath=");
		builder.append(filepath);
		builder.append(", langfilepath=");
		builder.append(langfilepath);
		builder.append(", isavailable=");
		builder.append(isavailable);
		builder.append(", bookmarktime=");
		builder.append(bookmarktime);
		builder.append(", categoryid=");
		builder.append(categoryid);
		builder.append(", delayposttime=");
		builder.append(delayposttime);
		builder.append("]");
		return builder.toString();
	}

	public StbMoviePlayRequest(List<String> stbip, String type, String ip, String port, String filepath,
			String langfilepath, boolean isavailable, int bookmarktime, String categoryid, int delayposttime) {
		super();
		this.stbip = stbip;
		this.type = type;
		this.ip = ip;
		this.port = port;
		this.filepath = filepath;
		this.langfilepath = langfilepath;
		this.isavailable = isavailable;
		this.bookmarktime = bookmarktime;
		this.categoryid = categoryid;
		this.delayposttime = delayposttime;
	}

	public StbMoviePlayRequest(List<String> stbip, String filepath, String langfilepath, boolean isavailable,
			int bookmarktime, String categoryid, int delayposttime) {
		super();
		this.stbip = stbip;
		this.filepath = filepath;
		this.langfilepath = langfilepath;
		this.isavailable = isavailable;
		this.bookmarktime = bookmarktime;
		this.categoryid = categoryid;
		this.delayposttime = delayposttime;
	}

}
