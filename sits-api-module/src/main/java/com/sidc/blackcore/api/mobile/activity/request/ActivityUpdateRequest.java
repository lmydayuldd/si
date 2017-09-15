package com.sidc.blackcore.api.mobile.activity.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityFeeInsertBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityLangBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoUploadBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityRepeatBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityRepeatFrequentBean;

public class ActivityUpdateRequest implements Serializable {
	private static final long serialVersionUID = 400214483011030420L;
	private int id;
	private String token;
	private int type;
	private String repeattype;
	private int lowerlimit;
	private int upperlimit;
	private int target;
	private String registrationstarttime;
	private String registrationendtime;
	private int ischarge;
	private String memo;
	private ActivityRepeatFrequentBean repeatfrequent;
	private List<ActivityLangBean> list = new ArrayList<ActivityLangBean>();
	private List<ActivityFeeInsertBean> feelist = new ArrayList<ActivityFeeInsertBean>();
	private List<ActivityRepeatBean> repeatlist = new ArrayList<ActivityRepeatBean>();
	private List<ActivityPhotoUploadBean> photolist = new ArrayList<ActivityPhotoUploadBean>();
	private boolean photoupdate;

	public int getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public int getType() {
		return type;
	}

	public String getRepeattype() {
		return repeattype;
	}

	public int getLowerlimit() {
		return lowerlimit;
	}

	public int getUpperlimit() {
		return upperlimit;
	}

	public int getTarget() {
		return target;
	}

	public String getRegistrationstarttime() {
		return registrationstarttime;
	}

	public String getRegistrationendtime() {
		return registrationendtime;
	}

	public int getIscharge() {
		return ischarge;
	}

	public String getMemo() {
		return memo;
	}

	public List<ActivityLangBean> getList() {
		return list;
	}

	public List<ActivityFeeInsertBean> getFeelist() {
		return feelist;
	}

	public List<ActivityRepeatBean> getRepeatlist() {
		return repeatlist;
	}

	public List<ActivityPhotoUploadBean> getPhotolist() {
		return photolist;
	}

	public void setFeelist(List<ActivityFeeInsertBean> feelist) {
		this.feelist = feelist;
	}

	public void setPhotolist(List<ActivityPhotoUploadBean> photolist) {
		this.photolist = photolist;
	}

	public ActivityRepeatFrequentBean getRepeatfrequent() {
		return repeatfrequent;
	}

	public void setRepeatfrequent(ActivityRepeatFrequentBean repeatfrequent) {
		this.repeatfrequent = repeatfrequent;
	}

	public boolean isPhotoupdate() {
		return photoupdate;
	}

	public void setRegistrationstarttime(String registrationstarttime) {
		this.registrationstarttime = registrationstarttime;
	}

	public void setRegistrationendtime(String registrationendtime) {
		this.registrationendtime = registrationendtime;
	}

	public void setRepeatlist(List<ActivityRepeatBean> repeatlist) {
		this.repeatlist = repeatlist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityUpdateRequest [id=");
		builder.append(id);
		builder.append(", token=");
		builder.append(token);
		builder.append(", type=");
		builder.append(type);
		builder.append(", repeattype=");
		builder.append(repeattype);
		builder.append(", lowerlimit=");
		builder.append(lowerlimit);
		builder.append(", upperlimit=");
		builder.append(upperlimit);
		builder.append(", target=");
		builder.append(target);
		builder.append(", registrationstarttime=");
		builder.append(registrationstarttime);
		builder.append(", registrationendtime=");
		builder.append(registrationendtime);
		builder.append(", ischarge=");
		builder.append(ischarge);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", repeatfrequent=");
		builder.append(repeatfrequent);
		builder.append(", list=");
		builder.append(list);
		builder.append(", feelist=");
		builder.append(feelist);
		builder.append(", repeatlist=");
		builder.append(repeatlist);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", photoupdate=");
		builder.append(photoupdate);
		builder.append("]");
		return builder.toString();
	}

	public ActivityUpdateRequest(int id, String token, int type, String repeattype, int lowerlimit, int upperlimit,
			int target, String registrationstarttime, String registrationendtime, int ischarge, String memo,
			ActivityRepeatFrequentBean repeatfrequent, List<ActivityLangBean> list, List<ActivityFeeInsertBean> feelist,
			List<ActivityRepeatBean> repeatlist, List<ActivityPhotoUploadBean> photolist, boolean photoupdate) {
		super();
		this.id = id;
		this.token = token;
		this.type = type;
		this.repeattype = repeattype;
		this.lowerlimit = lowerlimit;
		this.upperlimit = upperlimit;
		this.target = target;
		this.registrationstarttime = registrationstarttime;
		this.registrationendtime = registrationendtime;
		this.ischarge = ischarge;
		this.memo = memo;
		this.repeatfrequent = repeatfrequent;
		this.list = list;
		this.feelist = feelist;
		this.repeatlist = repeatlist;
		this.photolist = photolist;
		this.photoupdate = photoupdate;
	}

}
