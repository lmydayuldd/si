/**
 * 
 */
package com.sidc.utils.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

/**
 * @author Nandin
 *
 */
public class FileUtils {

	public static String readFile(String projectPath) {

		StringBuilder sCurrentLine = new StringBuilder();

		BufferedReader br = null;
		FileReader reader = null;
		try {
			reader = new FileReader(projectPath);
			br = new BufferedReader(reader);

			String line = "";
			while ((line = br.readLine()) != null) {
				sCurrentLine.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
			IOUtils.closeQuietly(reader);
		}

		return sCurrentLine.toString();
	}
}
