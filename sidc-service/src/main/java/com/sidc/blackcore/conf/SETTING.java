/**
 * 
 */
package com.sidc.blackcore.conf;

import java.io.File;

/**
 * @author Nandin
 *
 */
public interface SETTING {
	
	//com.sidc.blackcore.bean.configuration.BlackcoreConfiguration
	public final static String CONFIGURATION = "CONFIGURATION";
	
	public final static String SYSTEM_DEF_PATH = System.getProperty("sidc.config.path").replace("\\", File.separator)
			.replace("/", File.separator);
}
