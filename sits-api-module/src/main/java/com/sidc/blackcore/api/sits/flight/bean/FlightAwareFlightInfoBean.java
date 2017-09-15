package com.sidc.blackcore.api.sits.flight.bean;

import java.io.Serializable;

public class FlightAwareFlightInfoBean implements Serializable {
	private static final long serialVersionUID = -7837565222993811475L;
	private String ident;
	private String faFlightID;
	private String airline;
	private String flightnumber;
	private String tailnumber;
	private String type;
	private boolean blocked;
	private boolean diverted;
	private boolean cancelled;
	private FlightAwareArrivalOriginAirportBean origin;
	private FlightAwareTimeBean filed_departure_time;
	private FlightAwareTimeBean estimated_departure_time;
	private FlightAwareTimeBean actual_departure_time;
	private int departure_delay;
	private FlightAwareTimeBean filed_arrival_time;
	private FlightAwareTimeBean estimated_arrival_time;
	private FlightAwareTimeBean actual_arrival_time;
	private int arrival_delay;
	private String status;
	private int progress_percent;
	private String aircrafttype;
	private String full_aircrafttype;
	private boolean adhoc;

	public String getIdent() {
		return ident;
	}

	public String getFaFlightID() {
		return faFlightID;
	}

	public String getAirline() {
		return airline;
	}

	public String getFlightnumber() {
		return flightnumber;
	}

	public String getTailnumber() {
		return tailnumber;
	}

	public String getType() {
		return type;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public boolean isDiverted() {
		return diverted;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public FlightAwareArrivalOriginAirportBean getOrigin() {
		return origin;
	}

	public FlightAwareTimeBean getFiled_departure_time() {
		return filed_departure_time;
	}

	public FlightAwareTimeBean getEstimated_departure_time() {
		return estimated_departure_time;
	}

	public FlightAwareTimeBean getActual_departure_time() {
		return actual_departure_time;
	}

	public int getDeparture_delay() {
		return departure_delay;
	}

	public FlightAwareTimeBean getFiled_arrival_time() {
		return filed_arrival_time;
	}

	public FlightAwareTimeBean getEstimated_arrival_time() {
		return estimated_arrival_time;
	}

	public FlightAwareTimeBean getActual_arrival_time() {
		return actual_arrival_time;
	}

	public int getArrival_delay() {
		return arrival_delay;
	}

	public String getStatus() {
		return status;
	}

	public int getProgress_percent() {
		return progress_percent;
	}

	public String getAircrafttype() {
		return aircrafttype;
	}

	public String getFull_aircrafttype() {
		return full_aircrafttype;
	}

	public boolean isAdhoc() {
		return adhoc;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightAwareFlightInfoBean [ident=");
		builder.append(ident);
		builder.append(", faFlightID=");
		builder.append(faFlightID);
		builder.append(", airline=");
		builder.append(airline);
		builder.append(", flightnumber=");
		builder.append(flightnumber);
		builder.append(", tailnumber=");
		builder.append(tailnumber);
		builder.append(", type=");
		builder.append(type);
		builder.append(", blocked=");
		builder.append(blocked);
		builder.append(", diverted=");
		builder.append(diverted);
		builder.append(", cancelled=");
		builder.append(cancelled);
		builder.append(", origin=");
		builder.append(origin);
		builder.append(", filed_departure_time=");
		builder.append(filed_departure_time);
		builder.append(", estimated_departure_time=");
		builder.append(estimated_departure_time);
		builder.append(", actual_departure_time=");
		builder.append(actual_departure_time);
		builder.append(", departure_delay=");
		builder.append(departure_delay);
		builder.append(", filed_arrival_time=");
		builder.append(filed_arrival_time);
		builder.append(", estimated_arrival_time=");
		builder.append(estimated_arrival_time);
		builder.append(", actual_arrival_time=");
		builder.append(actual_arrival_time);
		builder.append(", arrival_delay=");
		builder.append(arrival_delay);
		builder.append(", status=");
		builder.append(status);
		builder.append(", progress_percent=");
		builder.append(progress_percent);
		builder.append(", aircrafttype=");
		builder.append(aircrafttype);
		builder.append(", full_aircrafttype=");
		builder.append(full_aircrafttype);
		builder.append(", adhoc=");
		builder.append(adhoc);
		builder.append("]");
		return builder.toString();
	}

	public FlightAwareFlightInfoBean(String ident, String faFlightID, String airline, String flightnumber,
			String tailnumber, String type, boolean blocked, boolean diverted, boolean cancelled,
			FlightAwareArrivalOriginAirportBean origin, FlightAwareTimeBean filed_departure_time,
			FlightAwareTimeBean estimated_departure_time, FlightAwareTimeBean actual_departure_time,
			int departure_delay, FlightAwareTimeBean filed_arrival_time, FlightAwareTimeBean estimated_arrival_time,
			FlightAwareTimeBean actual_arrival_time, int arrival_delay, String status, int progress_percent,
			String aircrafttype, String full_aircrafttype, boolean adhoc) {
		super();
		this.ident = ident;
		this.faFlightID = faFlightID;
		this.airline = airline;
		this.flightnumber = flightnumber;
		this.tailnumber = tailnumber;
		this.type = type;
		this.blocked = blocked;
		this.diverted = diverted;
		this.cancelled = cancelled;
		this.origin = origin;
		this.filed_departure_time = filed_departure_time;
		this.estimated_departure_time = estimated_departure_time;
		this.actual_departure_time = actual_departure_time;
		this.departure_delay = departure_delay;
		this.filed_arrival_time = filed_arrival_time;
		this.estimated_arrival_time = estimated_arrival_time;
		this.actual_arrival_time = actual_arrival_time;
		this.arrival_delay = arrival_delay;
		this.status = status;
		this.progress_percent = progress_percent;
		this.aircrafttype = aircrafttype;
		this.full_aircrafttype = full_aircrafttype;
		this.adhoc = adhoc;
	}

}
