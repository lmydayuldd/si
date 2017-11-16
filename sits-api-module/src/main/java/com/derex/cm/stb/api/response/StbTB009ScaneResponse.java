package com.derex.cm.stb.api.response;

import java.io.Serializable;

/**
 * 
 * @author Allen Huang
 *
 */
public class StbTB009ScaneResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6586576605611534230L;
	private String freqnumber;
	private String tvmode;
	
	public StbTB009ScaneResponse(String freqnumber, String tvmode) {
		super();
		this.freqnumber = freqnumber;
		this.tvmode = tvmode;
	}
	public String getFreqnumber() {
		return freqnumber;
	}
	public String getTvmode() {
		return tvmode;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StbTB009ScaneResponse [freqnumber=");
		builder.append(freqnumber);
		builder.append(", tvmode=");
		builder.append(tvmode);
		builder.append("]");
		return builder.toString();
	}
	
}
