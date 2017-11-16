package com.sidc.blackcore.api.sits.type;

public enum PrintType {
	DOC("DocPrintJob"), PRINTER("PrinterJob");

	private final String value;

	private PrintType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
