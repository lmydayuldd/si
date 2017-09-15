package com.derex.cm.stb.api.display;

/**
 * 
 * @author Allen Huang
 *
 */
public enum Resolution {
	R720p("720p"), R1080p("1080p");
	
	private String resolution;
	
	private Resolution(String resolution) {
        this.resolution = resolution;
    }

    public String getResolution() {
        return resolution;
    }
}
