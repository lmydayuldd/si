package com.sidc.blackcore.api.mobile.laundry.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LaundryWashTypeBean implements Serializable {
	private static final long serialVersionUID = -4796921175584775322L;
	private int washtypeid;
	private int price;
	private List<LaundryLangBean> langs = new ArrayList<LaundryLangBean>();

	public int getWashtypeid() {
		return washtypeid;
	}

	public int getPrice() {
		return price;
	}

	public List<LaundryLangBean> getLangs() {
		return langs;
	}

	public void setLangs(List<LaundryLangBean> langs) {
		this.langs = langs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LaundryWashTypeBean [washtypeid=");
		builder.append(washtypeid);
		builder.append(", price=");
		builder.append(price);
		builder.append(", langs=");
		builder.append(langs);
		builder.append("]");
		return builder.toString();
	}

	public LaundryWashTypeBean(int washtypeid, int price) {
		super();
		this.washtypeid = washtypeid;
		this.price = price;
	}

}
