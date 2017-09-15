package com.sidc.blackcore.api.mobile.message.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FcmResponseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 649185818531954445L;
	private String multicast_id;
	private String success;
	private String failure;
	private String canonical_ids;
	private List<FcmResultsBean> results = new ArrayList<FcmResultsBean>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FcmResponseBean [multicast_id=");
		builder.append(multicast_id);
		builder.append(", success=");
		builder.append(success);
		builder.append(", failure=");
		builder.append(failure);
		builder.append(", canonical_ids=");
		builder.append(canonical_ids);
		builder.append(", results=");
		builder.append(results);
		builder.append("]");
		return builder.toString();
	}

	public FcmResponseBean(String multicast_id, String success, String failure, String canonical_ids,
			List<FcmResultsBean> results) {
		super();
		this.multicast_id = multicast_id;
		this.success = success;
		this.failure = failure;
		this.canonical_ids = canonical_ids;
		this.results = results;
	}

	public String getMulticast_id() {
		return multicast_id;
	}

	public String getSuccess() {
		return success;
	}

	public String getFailure() {
		return failure;
	}

	public String getCanonical_ids() {
		return canonical_ids;
	}

	public List<FcmResultsBean> getResults() {
		return results;
	}
}
