package com.sidc.authorization;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.sidc.utils.exception.SiDCException;

/**
 * 
 */

/**
 * @author Nandin
 *
 */
public class SystemLoginCryption {

	private final static byte[] key = ">Y@y`7C]:2/d_>6>".getBytes();

	/**
	 * This method encrypts the input byte[] using the AES Key byte [].
	 * 
	 * @param byte
	 *            []
	 * @param byte
	 *            []
	 * @return byte []
	 * @throws Exception
	 * @throws SiDCException
	 */
	public static String encrypt(String text) throws SiDCException {

		String content = "";
		try {
			SecretKeySpec spec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, spec);
			byte[] bytes = cipher.doFinal(text.getBytes("UTF-8"));

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] md5Str = md.digest(bytes);

			content = Base64.encodeBase64String(md5Str);

		} catch (NoSuchAlgorithmException e) {
			throw new SiDCException(e);
		} catch (NoSuchPaddingException e) {
			throw new SiDCException(e);
		} catch (InvalidKeyException e) {
			throw new SiDCException(e);
		} catch (IllegalBlockSizeException e) {
			throw new SiDCException(e);
		} catch (BadPaddingException e) {
			throw new SiDCException(e);
		} catch (UnsupportedEncodingException e) {
			throw new SiDCException(e);
		}

		return content;
	}

	/**
	 * This method decrypts the input byte [] using AES Key byte []
	 * 
	 * @param byte
	 *            []
	 * @param byte
	 *            []
	 * @return byte []
	 * @throws Exception
	 * @throws SiDCException
	 */
	public static String decrypt(String text) throws SiDCException {
		String content = "";

		try {
			SecretKeySpec spec = new SecretKeySpec(key, "AES");
			byte[] decodeBase64 = Base64.decodeBase64(text.getBytes());
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, spec);
			byte[] bytes = cipher.doFinal(decodeBase64);
			content = new String(bytes, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new SiDCException(e);
		} catch (NoSuchPaddingException e) {
			throw new SiDCException(e);
		} catch (InvalidKeyException e) {
			throw new SiDCException(e);
		} catch (IllegalBlockSizeException e) {
			throw new SiDCException(e);
		} catch (BadPaddingException e) {
			throw new SiDCException(e);
		} catch (UnsupportedEncodingException e) {
			throw new SiDCException(e);
		}
		return content;
	}

}
