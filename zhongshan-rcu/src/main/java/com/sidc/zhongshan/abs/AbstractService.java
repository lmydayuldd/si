package com.sidc.zhongshan.abs;

import com.sidc.utils.exception.SiDCException;

/**
 * 
 * @author Allen Huang
 *
 */
public abstract class AbstractService {

	private byte[] bytes;
	private String device;
	private int value;
	private AbstractService next;	
	public AbstractService(byte[] bytes, String device, int value) {
		// TODO Auto-generated constructor stub
		this.bytes = bytes;
		this.device = device;
		this.value = value;
	}

	public void setNext(AbstractService next) {
		this.next = next;
	}

	public void process() throws SiDCException {
		handle();
		
		doNext();
	}
	
	protected abstract byte[] handle() throws SiDCException;

	private void doNext() throws SiDCException {
		if (next != null) {
	    	next.handle();
		}
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public String getDevice() {
		return device;
	}

	public int getValue() {
		return value;
	}
}
