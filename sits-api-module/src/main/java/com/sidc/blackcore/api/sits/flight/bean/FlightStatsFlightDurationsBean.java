package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;

public class FlightStatsFlightDurationsBean implements Serializable {
	private static final long serialVersionUID = 5398628695453225838L;
	private long scheduledBlockMinutes;
	private long blockMinutes;
	private long airMinutes;
	private long taxiOutMinutes;
	private long taxiInMinutes;

	public long getScheduledBlockMinutes() {
		return scheduledBlockMinutes;
	}

	public long getBlockMinutes() {
		return blockMinutes;
	}

	public long getAirMinutes() {
		return airMinutes;
	}

	public long getTaxiOutMinutes() {
		return taxiOutMinutes;
	}

	public long getTaxiInMinutes() {
		return taxiInMinutes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightStatsFlightDurationsBean [scheduledBlockMinutes=");
		builder.append(scheduledBlockMinutes);
		builder.append(", blockMinutes=");
		builder.append(blockMinutes);
		builder.append(", airMinutes=");
		builder.append(airMinutes);
		builder.append(", taxiOutMinutes=");
		builder.append(taxiOutMinutes);
		builder.append(", taxiInMinutes=");
		builder.append(taxiInMinutes);
		builder.append("]");
		return builder.toString();
	}

	public FlightStatsFlightDurationsBean(long scheduledBlockMinutes, long blockMinutes, long airMinutes,
			long taxiOutMinutes, long taxiInMinutes) {
		super();
		this.scheduledBlockMinutes = scheduledBlockMinutes;
		this.blockMinutes = blockMinutes;
		this.airMinutes = airMinutes;
		this.taxiOutMinutes = taxiOutMinutes;
		this.taxiInMinutes = taxiInMinutes;
	}

}
