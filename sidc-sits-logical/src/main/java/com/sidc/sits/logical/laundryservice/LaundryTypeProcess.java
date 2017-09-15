package com.sidc.sits.logical.laundryservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.mobile.laundry.request.LaundryTypeRequest;
import com.sidc.blackcore.api.mobile.laundry.response.LaundryTypeResponse;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.LaundryServiceManager;
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
public class LaundryTypeProcess extends AbstractAPIProcess {

	private final LaundryTypeRequest entity;
	private final String STEP = "1";

	public LaundryTypeProcess(final LaundryTypeRequest entity) {
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
		List<LaundryTypeResponse> list = new ArrayList<LaundryTypeResponse>();

		if (entity.getStatus().equals("all")) {
			if (entity.getLangcode().equals("all")) {
				list = LaundryServiceManager.getInstance().selectType();
			} else {
				list = LaundryServiceManager.getInstance().selectType(entity.getLangcode());
			}
			LogAction.getInstance().debug("step 1/" + STEP + " :select success(LaundryServiceManager|selectType).");
		} else {
			int status = -1;

			switch (entity.getStatus()) {
			case "enabled":
				status = 1;
				break;
			case "disable":
				status = 0;
				break;
			}

			if (entity.getLangcode().equals("all")) {
				list = LaundryServiceManager.getInstance().selectTypeByStatus(status);
			} else {
				list = LaundryServiceManager.getInstance().selectTypeByStatus(status, entity.getLangcode());
			}
			LogAction.getInstance()
					.debug("step 1/" + STEP + " :select success(LaundryServiceManager|selectTypeByStatus).");
		}

		// 取參數
		final String imageUrl = UrlUtils.getUrl(SidcUrlName.IMAGEURL.toString());

		final String photoUrl = SystemPropertiesManager.getInstance().findPropertiesMessage("upload.laundry.path")
				+ PhotoType.LAUNDRYTYPE.toString();

		for (LaundryTypeResponse itemEntity : list) {
			itemEntity.setPhotolist(photoProcess(itemEntity.getPhotolist(), imageUrl, photoUrl));
		}

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of status.");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of lang code.");
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
