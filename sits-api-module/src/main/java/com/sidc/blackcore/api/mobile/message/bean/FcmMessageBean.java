package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;

public class FcmMessageBean implements Serializable {
	private static final long serialVersionUID = -7809703620541846559L;
	private String messageid;
	private String message;
	private String roomno;
	private String name;
	private String staffid;
	private String sendertype;
	private String date;
	private List<ActivityPhotoBean> imagelist = new ArrayList<ActivityPhotoBean>();

	public FcmMessageBean(String messageid, String message, String roomno, String name, String staffid,
			String sendertype, String date, List<ActivityPhotoBean> imagelist) {
		super();
		this.messageid = messageid;
		this.message = message;
		this.roomno = roomno;
		this.name = name;
		this.staffid = staffid;
		this.sendertype = sendertype;
		this.date = date;
		this.imagelist = imagelist;
	}

	public String getMessageid() {
		return messageid;
	}

	public String getMessage() {
		return message;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getName() {
		return name;
	}

	public String getStaffid() {
		return staffid;
	}

	public String getSendertype() {
		return sendertype;
	}

	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FcmMessageBean [messageid=");
		builder.append(messageid);
		builder.append(", message=");
		builder.append(message);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", name=");
		builder.append(name);
		builder.append(", staffid=");
		builder.append(staffid);
		builder.append(", sendertype=");
		builder.append(sendertype);
		builder.append(", date=");
		builder.append(date);
		builder.append(", imagelist=");
		builder.append(imagelist);
		builder.append("]");
		return builder.toString();
	}

	public FcmMessageBean(String messageid, String message, String roomno, String name, String staffid,
			String sendertype, String date) {
		super();
		this.messageid = messageid;
		this.message = message;
		this.roomno = roomno;
		this.name = name;
		this.staffid = staffid;
		this.sendertype = sendertype;
		this.date = date;
	}

}
