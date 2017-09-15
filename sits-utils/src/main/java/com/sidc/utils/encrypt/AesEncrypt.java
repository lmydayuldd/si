package com.sidc.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AesEncrypt {
	private SecretKeySpec secretKey;
	private byte[] key;

	private String decryptedString;
	private String encryptedString;

	public AesEncrypt() {
		// TODO Auto-generated constructor stub
	}

	public AesEncrypt(final String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		setKey(key);
	}

	public String getDecryptedString() {
		return decryptedString;
	}

	public void setDecryptedString(String decryptedString) {
		this.decryptedString = decryptedString;
	}

	public String getEncryptedString() {
		return encryptedString;
	}

	public void setEncryptedString(String encryptedString) {
		this.encryptedString = encryptedString;
	}

	public void setKey(final String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {

		MessageDigest sha = null;

		key = myKey.getBytes("UTF-8");

		sha = MessageDigest.getInstance("SHA-1");

		key = sha.digest(key);

		key = Arrays.copyOf(key, 16); // use only first 128 bit

		secretKey = new SecretKeySpec(key, "AES");
	}

	public String encrypt(final String strToEncrypt) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		setEncryptedString(Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
		return this.getEncryptedString();

	}

	public String decrypt(final String strToDecrypt) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt))));

		return this.getDecryptedString();
	}
}
