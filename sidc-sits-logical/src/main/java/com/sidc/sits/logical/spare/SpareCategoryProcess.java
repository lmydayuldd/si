package com.sidc.sits.logical.spare;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.spare.request.SpareCategoryRequest;
import com.sidc.blackcore.api.sits.spare.response.SpareCategoryResponse;
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
public class SpareCategoryProcess extends AbstractAPIProcess {
	private final SpareCategoryRequest entity;
	private final String STEP = "2";

	public SpareCategoryProcess(final SpareCategoryRequest entity) {
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

		int status = -999;
		String langCode = null;

		switch (entity.getStatus()) {
		case "enable":
			status = 1;
			break;
		case "disable":
			status = 0;
			break;
		case "all":
			status = -1;
			break;
		}

		switch (entity.getLangcode()) {
		case "all":
			langCode = "";
			break;
		default:
			langCode = entity.getLangcode();
			break;
		}

		List<SpareCategoryResponse> list = SpareManager.getInstance().selectCategory(entity.getCategoryid(), status,
				langCode);
		LogAction.getInstance().debug("step 1/" + STEP + " :select success(SpareManager|selectCategory).");

		// 取參數
		final String imageUrl = UrlUtils.getUrl(SidcUrlName.IMAGEURL.toString());

		final String photoUrl = SystemPropertiesManager.getInstance().findPropertiesMessage("upload.spare.path")
				+ PhotoType.SPARECATEGORY.toString();

		for (SpareCategoryResponse responseEntity : list) {
			responseEntity.setPhotolist(photoProcess(responseEntity.getPhotolist(), imageUrl, photoUrl));
		}
		LogAction.getInstance().debug("step 1/" + STEP + ":format full photo url.");

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
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(langcode).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(status).");
		}
	}

	private List<ActivityPhotoBean> photoProcess(List<ActivityPhotoBean> photoList, final String sitsUrl,
			final String photoUrl) {
		for (ActivityPhotoBean photoEntity : photoList) {
			final String url = photoEntity.getPhoto();
			photoEntity.setPhoto(sitsUrl + photoUrl + url);
		}
		return photoList;
	}

}
