package com.sidc.blackcore.api.sits.bill.response;

import java.io.Serializable;

/**
 * 
 * @author Allen Huang
 *
 */
public class BillItemResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 784309739253169925L;
	private String postingdate;
	private String paymentdesc;
	private String itemamount;
	
	public BillItemResponse(String postingdate, String paymentdesc, String itemamount) {
		super();
		this.postingdate = postingdate;
		this.paymentdesc = paymentdesc;
		this.itemamount = itemamount;
	}

	public String getPostingdate() {
		return postingdate;
	}

	public String getPaymentdesc() {
		return paymentdesc;
	}

	public String getItemamount() {
		return itemamount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BillItemResponse [postingdate=");
		builder.append(postingdate);
		builder.append(", paymentdesc=");
		builder.append(paymentdesc);
		builder.append(", itemamount=");
		builder.append(itemamount);
		builder.append("]");
		return builder.toString();
	}
	
}
