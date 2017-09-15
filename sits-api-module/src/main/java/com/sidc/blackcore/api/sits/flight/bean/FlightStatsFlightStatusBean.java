package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;
import java.util.Comparator;

public class FlightStatsFlightStatusBean
		implements Serializable, Comparator<FlightStatsFlightStatusBean>, Comparable<FlightStatsFlightStatusBean> {
	private static final long serialVersionUID = 2978915824127720180L;
	private String flightId;
	private String carrierFsCode;
	private String flightNumber;
	private String departureAirportFsCode;
	private String arrivalAirportFsCode;
	private FlightStatsCommonDateBean departureDate;
	private FlightStatsCommonDateBean arrivalDate;
	private String status;
	private FlightStatsAirportInfoBean airportResources;
	private FlightStatsDelayInfoBean delays;
	private FlightStatsFlightDurationsBean flightDurations;

	public String getFlightId() {
		return flightId;
	}

	public String getCarrierFsCode() {
		return carrierFsCode;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getDepartureAirportFsCode() {
		return departureAirportFsCode;
	}

	public String getArrivalAirportFsCode() {
		return arrivalAirportFsCode;
	}

	public FlightStatsCommonDateBean getDepartureDate() {
		return departureDate;
	}

	public FlightStatsCommonDateBean getArrivalDate() {
		return arrivalDate;
	}

	public String getStatus() {
		return status;
	}

	public FlightStatsAirportInfoBean getAirportResources() {
		return airportResources;
	}

	public FlightStatsDelayInfoBean getDelays() {
		return delays;
	}

	public FlightStatsFlightDurationsBean getFlightDurations() {
		return flightDurations;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightStatsFlightStatusBean [flightId=");
		builder.append(flightId);
		builder.append(", carrierFsCode=");
		builder.append(carrierFsCode);
		builder.append(", flightNumber=");
		builder.append(flightNumber);
		builder.append(", departureAirportFsCode=");
		builder.append(departureAirportFsCode);
		builder.append(", arrivalAirportFsCode=");
		builder.append(arrivalAirportFsCode);
		builder.append(", departureDate=");
		builder.append(departureDate);
		builder.append(", arrivalDate=");
		builder.append(arrivalDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", airportResources=");
		builder.append(airportResources);
		builder.append(", delays=");
		builder.append(delays);
		builder.append(", flightDurations=");
		builder.append(flightDurations);
		builder.append("]");
		return builder.toString();
	}

	public FlightStatsFlightStatusBean(String flightId, String carrierFsCode, String flightNumber,
			String departureAirportFsCode, String arrivalAirportFsCode, FlightStatsCommonDateBean departureDate,
			FlightStatsCommonDateBean arrivalDate, String status, FlightStatsAirportInfoBean airportResources,
			FlightStatsDelayInfoBean delays, FlightStatsFlightDurationsBean flightDurations) {
		super();
		this.flightId = flightId;
		this.carrierFsCode = carrierFsCode;
		this.flightNumber = flightNumber;
		this.departureAirportFsCode = departureAirportFsCode;
		this.arrivalAirportFsCode = arrivalAirportFsCode;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.status = status;
		this.airportResources = airportResources;
		this.delays = delays;
		this.flightDurations = flightDurations;
	}

	public FlightStatsFlightStatusBean() {
		super();
	}

	public int compare(FlightStatsFlightStatusBean o1, FlightStatsFlightStatusBean o2) {
		return (o1.departureDate.getDateLocal()).compareTo(o2.departureDate.getDateLocal());
	}

	public int compareTo(FlightStatsFlightStatusBean entity) {
		return (this.arrivalDate.getDateLocal()).compareTo(entity.arrivalDate.getDateLocal());
	}

}
