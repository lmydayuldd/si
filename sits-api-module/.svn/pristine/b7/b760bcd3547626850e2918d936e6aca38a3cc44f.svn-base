package com.sidc.blackcore.api.mobile.activity.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivitySignUpBean;

public class ActivitySignUpRequest implements Serializable {
	private static final long serialVersionUID = 8506225161738746630L;
	private String publickey;
	private String privatekey;
	private int activityid;
	private int repeatid;
	private String roomno;
	private String memo;
	private List<ActivitySignUpBean> list = new ArrayList<ActivitySignUpBean>();
	private String activitydate;

	public String getPublickey() {
		return publickey;
	}

	public String getPrivatekey() {
		return privatekey;
	}

	public int getActivityid() {
		return activityid;
	}

	public int getRepeatid() {
		return repeatid;
	}

	public String getRoomno() {
		return roomno;
	}

	public List<ActivitySignUpBean> getList() {
		return list;
	}

	public String getMemo() {
		return memo;
	}

	public String getActivitydate() {
		return activitydate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivitySignUpRequest [publickey=");
		builder.append(publickey);
		builder.append(", privatekey=");
		builder.append(privatekey);
		builder.append(", activityid=");
		builder.append(activityid);
		builder.append(", repeatid=");
		builder.append(repeatid);
		builder.append(", roomno=");
		builder.append(roomno);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", list=");
		builder.append(list);
		builder.append(", activitydate=");
		builder.append(activitydate);
		builder.append("]");
		return builder.toString();
	}

	public ActivitySignUpRequest(String publickey, String privatekey, int activityid, int repeatid, String roomno,
			String memo, List<ActivitySignUpBean> list, String activitydate) {
		super();
		this.publickey = publickey;
		this.privatekey = privatekey;
		this.activityid = activityid;
		this.repeatid = repeatid;
		this.roomno = roomno;
		this.memo = memo;
		this.list = list;
		this.activitydate = activitydate;
	}

}
