/**
 * 
 */
package com.sidc.blackcore.api.sits.account.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;

/**
 * @author Joe
 *
 */
public class SaffInfoBean implements Serializable {
	private static final long serialVersionUID = 2893020831937996076L;
	private String id;
	private String staffcode;
	private String name;
	private List<ActivityPhotoBean> photolist = new ArrayList<ActivityPhotoBean>();

	public String getId() {
		return id;
	}

	public String getStaffcode() {
		return staffcode;
	}

	public String getName() {
		return name;
	}

	public void setPhotolist(List<ActivityPhotoBean> photolist) {
		this.photolist = photolist;
	}

	public List<ActivityPhotoBean> getPhotolist() {
		return photolist;
	}

	public SaffInfoBean(String id, String staffcode, String name, List<ActivityPhotoBean> photolist) {
		super();
		this.id = id;
		this.staffcode = staffcode;
		this.name = name;
		this.photolist = photolist;
	}

	public SaffInfoBean(String id, String staffcode, String name) {
		super();
		this.id = id;
		this.staffcode = staffcode;
		this.name = name;
	}

}
