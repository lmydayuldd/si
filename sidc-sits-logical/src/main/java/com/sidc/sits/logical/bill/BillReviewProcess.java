package com.sidc.sits.logical.bill;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.StringEntity;

import com.derex.cm.stb.api.request.APIRequest;
import com.derex.cm.stb.api.response.Apidocument;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sidc.blackcore.api.sits.bill.request.BillReviewRequest;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class BillReviewProcess extends AbstractAuthAPIProcess {

	private BillReviewRequest enity;

	public BillReviewProcess(BillReviewRequest enity) {
		super(enity.getPublickey(), enity.getPrivatekey(), enity.getRoomno());
		this.enity = enity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + enity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());
		
		final APIRequest request = new APIRequest(enity);
		final Gson gson = new Gson();
		final String json = gson.toJson(request);
		final StringEntity strEntity = new StringEntity(json, "UTF-8");
		
		String response = HttpClientUtils.httpSendPost(sitsUrl + PageList.BILL_REVIEW, strEntity);
		LogAction.getInstance().debug("step 1/1 send post bill review success(url:" + PageList.BILL_REVIEW + ").");

		JsonElement apid = new JsonParser().parse(response).getAsJsonObject().get("apidocument");
		Apidocument apidocument = gson.fromJson(apid.getAsJsonObject(), Apidocument.class);
		
		if (apidocument.getData() == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "No bill data");
		}
		
		return apidocument.getData();
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Request is illegal");
		}

		if (StringUtils.isBlank(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Room No. is empty");
		}

		if (StringUtils.isBlank(enity.getGuestno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Guest No. is empty");
		}
	}
	

}
