package com.derex.cm.stb.api.response;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author Allen Huang
 *
 */
public class MiddlewareVersionResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2428983327524487137L;
	private String versionId;
	private String memo;
	private int verIndex;
	private Date createDate;
    private boolean suspend;
	
	public MiddlewareVersionResponse(String versionId, String memo, int verIndex, Date createDate,
			boolean suspend) {
		// TODO Auto-generated constructor stub
		super();
		this.versionId = versionId;
		this.memo = memo;
		this.verIndex = verIndex;
		this.createDate = createDate;
		this.suspend = suspend;
	}

	public String getVersionId() {
		return versionId;
	}

	public String getMemo() {
		return memo;
	}

	public long getVerIndex() {
		return verIndex;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public boolean getSuspend() {
		return suspend;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MiddlewareVersionResponse versionId=\n");
		builder.append(versionId);
		builder.append(", memo=\n");
		builder.append(memo);
		builder.append(", verIndex=\n");
		builder.append(verIndex);
		builder.append(", createDate=\n");
		builder.append(createDate);
		builder.append(", suspend=\n");
		builder.append(suspend);
		builder.append("]");
		return builder.toString();
	}
}
