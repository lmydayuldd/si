package com.sidc.sits.logical.roomservice;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceCategoryBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemBean;
import com.sidc.blackcore.api.sits.roomservice.reponse.RoomServiceResponse;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceRequest;
import com.sidc.blackcore.api.sits.type.PhotoType;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.blackcore.SidcUrlsConfiguration;
import com.sidc.configuration.blackcore.SidcUrlsLink;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.RoomServiceManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class RoomServiceListProcess extends AbstractAPIProcess {
	private final RoomServiceRequest entity;
	private final String STEP = "3";

	public RoomServiceListProcess(final RoomServiceRequest entity) {
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
		// TODO Auto-generated method stub

		List<RoomServiceCategoryBean> list = RoomServiceManager.getInstance().selectCategory(1, entity.getLangcode());
		LogAction.getInstance().debug("step 1/" + STEP + ": get list success(RoomServiceManager|selectCategory).");

		// 取參數
		final String imageUrl = configure(SidcUrlName.IMAGEURL.toString());

		final String roomServicePhotoUrl = imageUrl
				+ SystemPropertiesManager.getInstance().findPropertiesMessage("upload.roomservice.path");

		final String categoryUrl = PhotoType.ROOMSERVICECATEGORY.toString();
		final String itemUrl = PhotoType.ROOMSERVICEITEM.toString();
		LogAction.getInstance()
				.debug("step 2/" + STEP + ": photo url=" + roomServicePhotoUrl + categoryUrl + "|" + itemUrl);

		// PhotoType.ROOMSERVICECATEGORY.toString()
		for (RoomServiceCategoryBean categoryEntity : list) {
			for (ActivityPhotoBean categoryPhotoEntity : categoryEntity.getPhotolist()) {
				final String url = categoryPhotoEntity.getPhoto();
				categoryPhotoEntity.setPhoto(roomServicePhotoUrl + categoryUrl + url);
			}
			for (RoomServiceItemBean itemEntity : categoryEntity.getItemlist()) {
				for (ActivityPhotoBean itemPhotoEntity : itemEntity.getPhotolist()) {
					final String url = itemPhotoEntity.getPhoto();
					itemPhotoEntity.setPhoto(roomServicePhotoUrl + itemUrl + url);
				}
			}
		}

		final String currency = SystemPropertiesManager.getInstance().findPropertiesMessage("CURRENCY");
		LogAction.getInstance().debug("step 3/" + STEP
				+ ": get shop currency success(SystemPropertiesManager|findPropertiesMessage|CURRENCY).");

		RoomServiceResponse response = new RoomServiceResponse(currency, list);
		return response;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(lang code).");
		}
	}

	private String configure(final String serverName) throws SiDCException {
		SidcUrlsConfiguration sidcConfigure = (SidcUrlsConfiguration) DataCenter.getInstance()
				.get(SidcUrlName.SITS.toString());
		List<SidcUrlsLink> urlsLinks = sidcConfigure.getUrls();
		String url = null;
		for (SidcUrlsLink sidcUrlsLink : urlsLinks) {
			if (sidcUrlsLink.getName().equalsIgnoreCase(serverName))
				url = sidcUrlsLink.getUrl();
		}
		return url;
	}
}
