package com.sidc.blackcore.api.mobile.activity.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityLangBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityRepeatInfoBean;
import com.sidc.blackcore.api.mobile.activity.bean.ActivityToFeeBean;

public class ActivityResponse implements Serializable {
	private static final long serialVersionUID = 1679959114433493898L;

	private int id;
	private int type;
	private int status;
	private String repeattype;
	private int lowerlimit;
	private int upperlimit;
	private int target;
	private String registrationstarttime;
	private String registrationendtime;
	private int ischarge;
	private String memo;
	private List<ActivityLangBean> langs = new ArrayList<ActivityLangBean>();
	private List<ActivityToFeeBean> feelist = new ArrayList<ActivityToFeeBean>();
	private List<ActivityRepeatInfoBean> repeatlist = new ArrayList<ActivityRepeatInfoBean>();
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();
	private List<String> repeatfrequentlist = new ArrayList<String>();

	public int getId() {
		return id;
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

	public int getStatus() {
		return status;
	}

	public String getMemo() {
		return memo;
	}

	public List<ActivityToFeeBean> getFeelist() {
		return feelist;
	}

	public List<ActivityRepeatInfoBean> getRepeatlist() {
		return repeatlist;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public void setLowerlimit(int lowerlimit) {
		this.lowerlimit = lowerlimit;
	}

	public void setFeelist(List<ActivityToFeeBean> feelist) {
		this.feelist = feelist;
	}

	public void setRepeatlist(List<ActivityRepeatInfoBean> repeatlist) {
		this.repeatlist = repeatlist;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	public List<String> getRepeatfrequentlist() {
		return repeatfrequentlist;
	}

	public void setRepeatfrequentlist(List<String> repeatfrequentlist) {
		this.repeatfrequentlist = repeatfrequentlist;
	}

	public List<ActivityLangBean> getLangs() {
		return langs;
	}

	public void setLangs(List<ActivityLangBean> langs) {
		this.langs = langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivityResponse [id=");
		builder.append(id);
		builder.append(", type=");
		builder.append(type);
		builder.append(", status=");
		builder.append(status);
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
		builder.append(", langs=");
		builder.append(langs);
		builder.append(", feelist=");
		builder.append(feelist);
		builder.append(", repeatlist=");
		builder.append(repeatlist);
		builder.append(", photolist=");
		builder.append(photolist);
		builder.append(", repeatfrequentlist=");
		builder.append(repeatfrequentlist);
		builder.append("]");
		return builder.toString();
	}

	public ActivityResponse(int id, int type, int status, String repeattype, int lowerlimit, int upperlimit, int target,
			String registrationstarttime, String registrationendtime, int ischarge, String memo,
			List<ActivityLangBean> langs, List<ActivityToFeeBean> feelist, List<ActivityRepeatInfoBean> repeatlist,
			List<ActivityPhotoBean> photolist, List<String> repeatfrequentlist) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.repeattype = repeattype;
		this.lowerlimit = lowerlimit;
		this.upperlimit = upperlimit;
		this.target = target;
		this.registrationstarttime = registrationstarttime;
		this.registrationendtime = registrationendtime;
		this.ischarge = ischarge;
		this.memo = memo;
		this.langs = langs;
		this.feelist = feelist;
		this.repeatlist = repeatlist;
		this.photolist = photolist;
		this.repeatfrequentlist = repeatfrequentlist;
	}

}
