package com.sidc.sits.logical.utils;

import java.util.List;

import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsLink;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;

public class UrlUtils {

	public static String getUrl(final String serverName) throws SiDCException {

		SidcUrlsConfiguration sidcConfigure = (SidcUrlsConfiguration) DataCenter.getInstance()
				.get(SidcUrlName.SITS.toString());
		List<SidcUrlsLink> urlsLinks = sidcConfigure.getUrls();
		String url = null;
		for (SidcUrlsLink sidcUrlsLink : urlsLinks) {
			if (sidcUrlsLink.getName().equalsIgnoreCase(serverName)) {
				url = sidcUrlsLink.getUrl();
				break;
			}
		}
		return url;
	}
}
