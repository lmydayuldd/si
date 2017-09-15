package com.sidc.sits.logical.roomservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceItemCategoryBean;
import com.sidc.blackcore.api.sits.roomservice.bean.RoomServiceSetItemInfoBean;
import com.sidc.blackcore.api.sits.roomservice.reponse.RoomServiceItemResponse;
import com.sidc.blackcore.api.sits.roomservice.request.RoomServiceItemRequest;
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

/**
 * 
 * @author Joe
 *
 */
public class RoomServiceItemProcess extends AbstractAPIProcess {
	private final RoomServiceItemRequest entity;
	private final String STEP = "2";

	public RoomServiceItemProcess(final RoomServiceItemRequest entity) {
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
		String type = null;

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

		switch (entity.getType()) {
		case "all":
			type = "";
			break;
		default:
			type = entity.getType();
			break;
		}

		List<RoomServiceItemResponse> list = new ArrayList<RoomServiceItemResponse>();

		list = RoomServiceManager.getInstance().selectItem(entity.getCategoryid(), entity.getItemid(), status, type,
				langCode);
		LogAction.getInstance().debug("step 1/" + STEP + " :select success(RoomServiceManager|selectCategory).");

		final String itemUrl = PhotoType.ROOMSERVICEITEM.toString();
		// 取參數
		final String imageUrl = configure(SidcUrlName.IMAGEURL.toString());

		final String roomServicePhotoUrl = imageUrl
				+ SystemPropertiesManager.getInstance().findPropertiesMessage("upload.roomservice.path");

		for (RoomServiceItemResponse itemEntity : list) {
			itemEntity.setPhotolist(photoUrlProcess(itemEntity.getPhotolist(), roomServicePhotoUrl + itemUrl));
			if (itemEntity.getType().equals("set")) {
				for (RoomServiceItemCategoryBean categoryEntity : itemEntity.getSetlist()) {
					for (RoomServiceSetItemInfoBean setItemEntity : categoryEntity.getItemlist()) {
						setItemEntity.setPhotolist(
								photoUrlProcess(setItemEntity.getPhotolist(), roomServicePhotoUrl + itemUrl));
					}
				}
			}
		}
		LogAction.getInstance().debug("step 2/" + STEP + " :full photo url.");

		return list;
	}

	private List<ActivityPhotoBean> photoUrlProcess(List<ActivityPhotoBean> photoList, final String serviceUrl) {
		for (ActivityPhotoBean photoEntity : photoList) {
			final String url = photoEntity.getPhoto();
			photoEntity.setPhoto(serviceUrl + url);
		}
		return photoList;
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
		if (StringUtils.isBlank(entity.getType())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(type).");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(langcode).");
		}
		if (StringUtils.isBlank(entity.getStatus())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(status).");
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
