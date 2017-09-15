package com.sidc.sits.logical.spare;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.spare.request.SpareItemRequest;
import com.sidc.blackcore.api.sits.spare.response.SpareItemResponse;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.SpareManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class SpareItemProcess extends AbstractAPIProcess {
	private final SpareItemRequest entity;
	private final String STEP = "3";

	public SpareItemProcess(final SpareItemRequest entity) {
		// TODO Auto-generated constructor stub
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		String status = null;
		String langCode = null;

		if (!entity.getStatus().equals("all")) {
			status = entity.getStatus();
		}

		switch (entity.getLangcode()) {
		case "all":
			break;
		default:
			langCode = entity.getLangcode();
			break;
		}

		List<SpareItemResponse> list = SpareManager.getInstance().selectItem(entity.getItemid(), entity.getCategoryid(),
				status, langCode);
		LogAction.getInstance().debug("step 1/" + STEP + ":select success(SpareManager|selectItem).");

		// 取參數
		final String imageUrl = UrlUtils.getUrl(SidcUrlName.IMAGEURL.toString());

		final String photoUrl = SystemPropertiesManager.getInstance().findPropertiesMessage("upload.spare.path")
				+ PhotoType.SPAREITEM.toString();
		LogAction.getInstance().debug("step 2/" + STEP + ":get system url success.");

		for (SpareItemResponse responseEntity : list) {
			responseEntity.setPhotolist(photoProcess(responseEntity.getPhotolist(), imageUrl, photoUrl));
		}
		LogAction.getInstance().debug("step 3/" + STEP + ":format full photo url.");

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (entity.getCategoryid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(category id).");
		}
		if (entity.getItemid() < 0) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(item id).");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(langcode).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(status).");
		}
	}

	/**
	 * 加上url
	 * 
	 * @param photoList
	 * @param sitsUrl
	 * @param photoUrl
	 * @return
	 */
	private List<ActivityPhotoBean> photoProcess(List<ActivityPhotoBean> photoList, final String sitsUrl,
			final String photoUrl) {
		for (ActivityPhotoBean photoEntity : photoList) {
			final String url = photoEntity.getPhoto();
			photoEntity.setPhoto(sitsUrl + photoUrl + url);
		}
		return photoList;
	}

}
