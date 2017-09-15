package com.sidc.blackcore.api.sits.printer.request;

import java.io.Serializable;
import java.util.Arrays;

public class PrinterRequest implements Serializable {
	private static final long serialVersionUID = 3809453131488844134L;
	private String[] printername;
	private String printdata;
	private String signature;

	public String[] getPrintername() {
		return printername;
	}

	public String getPrintdata() {
		return printdata;
	}

	public String getSignature() {
		return signature;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PrinterRequest [printername=");
		builder.append(Arrays.toString(printername));
		builder.append(", printdata=");
		builder.append(printdata);
		builder.append(", signature=");
		builder.append(signature);
		builder.append("]");
		return builder.toString();
	}

	public PrinterRequest(String[] printername, String printdata, String signature) {
		super();
		this.printername = printername;
		this.printdata = printdata;
		this.signature = signature;
	}

}
