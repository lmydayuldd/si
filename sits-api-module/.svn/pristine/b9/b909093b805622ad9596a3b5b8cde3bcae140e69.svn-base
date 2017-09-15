package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

import com.derex.cm.stb.api.display.LogFlags;
import com.derex.cm.stb.api.display.Resolution;
import com.derex.cm.stb.api.display.ShowChannelOsd;
import com.derex.cm.stb.api.display.Volcontrol;

/**
 * 
 * @author Allen Huang
 *
 */
public class StbSystemIniTemplateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 275486243172020425L;
	private List<String> stbip;
	private Resolution resolution;
	private int displayscalesize;
	private int volume;
	private ShowChannelOsd showchannelosd;
	private LogFlags logflags;
	private Volcontrol volcontrol;

	public StbSystemIniTemplateRequest(List<String> stbip, Resolution resolution, 
			int displayscalesize, int volume, ShowChannelOsd showchannelosd, 
			LogFlags logflags, Volcontrol volcontrol) {
		this.stbip = stbip;
		this.resolution = resolution;
		this.displayscalesize = displayscalesize;
		this.volume = volume;
		this.showchannelosd = showchannelosd;
		this.logflags = logflags;
		this.volcontrol = volcontrol;
	}

	public List<String> getStbip() {
		return stbip;
	}

	public String getResolution() {
		return resolution.getResolution();
	}

	public int getDisplayscalesize() {
		return displayscalesize;
	}

	public int getVolume() {
		return volume;
	}

	public String getShowchannelosd() {
		return showchannelosd.toString();
	}

	public String getLogflags() {
		return logflags.toString();
	}

	public String getVolcontrol() {
		return volcontrol.toString();
	}
	
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("StbSystemIniTemplateRequest [stbip=\n");
		builder.append(stbip);
		builder.append(", resolution=\n");
		builder.append(resolution.getResolution());
		builder.append(", displayscalesize=\n");
		builder.append(displayscalesize);
		builder.append(", volume=\n");
		builder.append(volume);
		builder.append(", showchannelosd=\n");
		builder.append(showchannelosd.toString());
		builder.append(", logflags=\n");
		builder.append(logflags.toString());
		builder.append(", volcontrol=\n");
		builder.append(volcontrol);
		builder.append("]");
		return builder.toString();
	}
}
