/**
 * 
 */
package com.sidc.utils.exception;

/**
 * @author Nandin
 *
 */
public class SiDCException extends Exception {

	private static final long serialVersionUID = -8841150147818428678L;
	private int errorCode;

	public SiDCException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @param cause
	 */
	public SiDCException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * @param cause
	 */
	public SiDCException(Throwable cause) {
		super(cause.getMessage(), cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

}
