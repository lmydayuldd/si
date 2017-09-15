package com.sidc.blackcore.api.sits.roomservice.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sidc.blackcore.api.sits.shop.bean.ShopItemInfoBean;

public class RoomServiceInfoBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4674425250594434477L;
	private int catalogueid;
	private int seq;
	private String catalogue;
	private List<ShopItemInfoBean> roomservicelist = new ArrayList<ShopItemInfoBean>();

	public int getCatalogueid() {
		return catalogueid;
	}

	public int getSeq() {
		return seq;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public List<ShopItemInfoBean> getRoomservicelist() {
		return roomservicelist;
	}

	public void setRoomservicelist(List<ShopItemInfoBean> roomservicelist) {
		this.roomservicelist = roomservicelist;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopTypeInfoBean [catalogueid=");
		builder.append(catalogueid);
		builder.append(", seq=");
		builder.append(seq);
		builder.append(", catalogue=");
		builder.append(catalogue);
		builder.append(", roomservicelist=");
		builder.append(roomservicelist);
		builder.append("]");
		return builder.toString();
	}

	public RoomServiceInfoBean(int catalogueid, int seq, String catalogue) {
		super();
		this.catalogueid = catalogueid;
		this.seq = seq;
		this.catalogue = catalogue;
	}

}
