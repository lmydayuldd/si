package com.sidc.blackcore.sits.test;

import org.junit.Test;

import com.sidc.utils.encrypt.AesEncrypt;

public class AesEncryptTest {

	@Test
	public void test2() throws Exception {
		String key = "03dd1c41-af0c-470d-b1af-09dcd3143418";
		AesEncrypt encrypt = new AesEncrypt("sidc");

//		System.out.println(": " + encrypt.decrypt(key));
		System.out.println("+: " + encrypt.encrypt(key));

	}

}
