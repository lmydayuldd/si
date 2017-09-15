package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;

public class FlightStatsCommonDateBean implements Serializable {
	private static final long serialVersionUID = -2053813557815092895L;
	private String dateLocal;
	private String dateUtc;

	public String getDateLocal() {
		return dateLocal;
	}

	public String getDateUtc() {
		return dateUtc;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightStatsCommonDateBean [dateLocal=");
		builder.append(dateLocal);
		builder.append(", dateUtc=");
		builder.append(dateUtc);
		builder.append("]");
		return builder.toString();
	}

	public FlightStatsCommonDateBean(String dateLocal, String dateUtc) {
		super();
		this.dateLocal = dateLocal;
		this.dateUtc = dateUtc;
	}

}
