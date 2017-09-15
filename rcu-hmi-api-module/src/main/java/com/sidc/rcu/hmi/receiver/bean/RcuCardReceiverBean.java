package com.sidc.rcu.hmi.receiver.bean;

import java.io.Serializable;
import java.util.Date;

import com.sidc.rcu.hmi.abs.AbstractRcuBean;

public class RcuCardReceiverBean extends AbstractRcuBean implements Serializable {
	private static final long serialVersionUID = 527524528586798776L;
	private String status;
	private String authorization;
	private String cardType;
	private Date time;

	public String getStatus() {
		return status;
	}

	public String getAuthorization() {
		return authorization;
	}

	public String getCardType() {
		return cardType;
	}

	public Date getTime() {
		return time;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuCardReceiverBean [status=");
		builder.append(status);
		builder.append(", authorization=");
		builder.append(authorization);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", time=");
		builder.append(time);
		builder.append("]");
		return builder.toString();
	}

	public RcuCardReceiverBean(String status, String authorization, String cardType) {
		super();
		this.status = status;
		this.authorization = authorization;
		this.cardType = cardType;
		this.time = new Date();
	}

}
