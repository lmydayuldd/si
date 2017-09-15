package com.sidc.blackcore.api.sits.bill.request;

import java.io.Serializable;
import java.util.List;

import com.sidc.blackcore.api.sits.bill.response.BillItemResponse;

/**
 * 
 * @author Allen Huang
 *
 */
public class BillReviewList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5436366209269767040L;
	private String roomno;
	private String guestno;
	private String firstname;
	private List<BillItemResponse> items;
	private String balanceamount;
	private String expresscheckoutbalanceamount;

	public BillReviewList(String roomno, String guestno, String firstname, List<BillItemResponse> items,
			String balanceamount, String expresscheckoutbalanceamount) {
		super();
		this.roomno = roomno;
		this.guestno = guestno;
		this.firstname = firstname;
		this.items = items;
		this.balanceamount = balanceamount;
		this.expresscheckoutbalanceamount = expresscheckoutbalanceamount;
	}

	public String getRoomno() {
		return roomno;
	}

	public String getGuestno() {
		return guestno;
	}

	public String getFirstname() {
		return firstname;
	}

	public List<BillItemResponse> getItems() {
		return items;
	}

	public String getBalanceamount() {
		return balanceamount;
	}

	public String getExpresscheckoutbalanceamount() {
		return expresscheckoutbalanceamount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BillReviewList [roomno=");
		builder.append(roomno);
		builder.append(", guestno=");
		builder.append(guestno);
		builder.append(", firstname=");
		builder.append(firstname);
		builder.append(", items=");
		builder.append(items);
		builder.append(", balanceamount=");
		builder.append(balanceamount);
		builder.append(", expresscheckoutbalanceamount=");
		builder.append(expresscheckoutbalanceamount);
		builder.append("]");
		return builder.toString();
	}

}
