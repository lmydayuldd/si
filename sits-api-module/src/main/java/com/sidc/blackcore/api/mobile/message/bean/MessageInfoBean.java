package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;

public class MessageInfoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5008815004994992623L;
	private String roomno;
	private String groupid;
	private String pushtoken;
	private String title;
	private String message;
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();

	public String getRoomno() {
		return roomno;
	}

	public String getGroupid() {
		return groupid;
	}

	public String getPushtoken() {
		return pushtoken;
	}

	public String getMessage() {
		return message;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public List<ActivityPhotoUploadBean> getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List<ActivityPhotoUploadBean> photolist) {
		this.photolist = photolist;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageInfoBean [roomno=");
		builder.append(roomno);
		builder.append(", groupid=");
		builder.append(groupid);
		builder.append(", pushtoken=");
		builder.append(pushtoken);
		builder.append(", title=");
		builder.append(title);
		builder.append(", message=");
		builder.append(message);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append("]");
		return builder.toString();
	}

	public MessageInfoBean(String roomno, String groupid, String pushtoken, String message) {
		super();
		this.roomno = roomno;
		this.groupid = groupid;
		this.pushtoken = pushtoken;
		this.message = message;
	}

	public MessageInfoBean(String roomno, String groupid, String pushtoken, String message,
			List<ActivityPhotoUploadBean> photolist) {
		super();
		this.roomno = roomno;
		this.groupid = groupid;
		this.pushtoken = pushtoken;
		this.message = message;
		this.photolist = photolist;
	}

	public MessageInfoBean(String roomno, String groupid, String pushtoken, String title, String message,
			List<ActivityPhotoUploadBean> photolist) {
		super();
		this.roomno = roomno;
		this.groupid = groupid;
		this.pushtoken = pushtoken;
		this.title = title;
		this.message = message;
		this.photolist = photolist;
	}

	public MessageInfoBean(String roomno, String groupid, String pushtoken, String title, String message) {
		super();
		this.roomno = roomno;
		this.groupid = groupid;
		this.pushtoken = pushtoken;
		this.title = title;
		this.message = message;
	}

}
