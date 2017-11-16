package com.sidc.configuration;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.sidc.blackcore.bean.configuration.BlackcoreConfiguration;
import com.sidc.configuration.blackcore.AllowDomainConfiguration;
import com.sidc.configuration.blackcore.AppFcmKeyConfiguration;
import com.sidc.configuration.blackcore.RCUServiceConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;

@SuppressWarnings("restriction")
public class Configuration {

	public static BlackcoreConfiguration readMainConfiguration(File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(BlackcoreConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		BlackcoreConfiguration config = (BlackcoreConfiguration) jaxbUnmarshaller.unmarshal(file);

		return config;

	}

	public static AllowDomainConfiguration readDomainConfiguration(File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(AllowDomainConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		AllowDomainConfiguration config = (AllowDomainConfiguration) jaxbUnmarshaller.unmarshal(file);

		return config;
	}

	public static SidcUrlsConfiguration readSidcUrlsConfiguration(File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(SidcUrlsConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		SidcUrlsConfiguration config = (SidcUrlsConfiguration) jaxbUnmarshaller.unmarshal(file);

		return config;
	}

	public static AppFcmKeyConfiguration readAppFcmKeyConfiguration(File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(AppFcmKeyConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		AppFcmKeyConfiguration config = (AppFcmKeyConfiguration) jaxbUnmarshaller.unmarshal(file);

		return config;
	}

	public static RCUServiceConfiguration readRCUServiceConfiguration(File file) throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(RCUServiceConfiguration.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		RCUServiceConfiguration config = (RCUServiceConfiguration) jaxbUnmarshaller.unmarshal(file);

		return config;
	}
}
