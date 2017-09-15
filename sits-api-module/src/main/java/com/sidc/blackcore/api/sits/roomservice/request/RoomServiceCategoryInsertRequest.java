package com.sidc.blackcore.api.sits.roomservice.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceLangBean;

public class RoomServiceCategoryInsertRequest implements Serializable {
	private static final long serialVersionUID = -5581200726453157339L;
	private String token;
	private int referid;
	private int status;
	private String startime;
	private String endtime;
	private int sequence;
	private List<RoomServiceLangBean> list = new ArrayList<RoomServiceLangBean>();
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();

	public String getToken() {
		return token;
	}

	public int getReferid() {
		return referid;
	}

	public int getStatus() {
		return status;
	}

	public String getStartime() {
		return startime;
	}

	public String getEndtime() {
		return endtime;
	}

	public List<RoomServiceLangBean> getList() {
		return list;
	}

	public List<ActivityPhotoUploadBean> getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List<ActivityPhotoUploadBean> photolist) {
		this.photolist = photolist;
	}

	public int getSequence() {
		return sequence;
	}

	public RoomServiceCategoryInsertRequest(String token, int referid, int status, String startime, String endtime,
			int sequence, List<RoomServiceLangBean> list, List<ActivityPhotoUploadBean> photolist) {
		super();
		this.token = token;
		this.referid = referid;
		this.status = status;
		this.startime = startime;
		this.endtime = endtime;
		this.sequence = sequence;
		this.list = list;
		this.photolist = photolist;
	}

	public RoomServiceCategoryInsertRequest(String token, int referid, int status, String startime, String endtime,
			int sequence, List<RoomServiceLangBean> list) {
		super();
		this.token = token;
		this.referid = referid;
		this.status = status;
		this.startime = startime;
		this.endtime = endtime;
		this.sequence = sequence;
		this.list = list;
	}

}
