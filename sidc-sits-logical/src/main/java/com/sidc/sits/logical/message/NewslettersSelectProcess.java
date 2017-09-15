package com.sidc.sits.logical.message;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.activity.bean.ActivityPhotoBean;
import com.sidc.blackcore.api.mobile.message.bean.NewslettersBean;
import com.sidc.blackcore.api.mobile.message.request.NewslettersSelectRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.MessageManager;
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
public class NewslettersSelectProcess extends AbstractAPIProcess {

	private final NewslettersSelectRequest entity;
	private final String STEP = "2";

	public NewslettersSelectProcess(final NewslettersSelectRequest entity) {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {

		final List<NewslettersBean> list = MessageManager.getInstance().selectNewslettersList(entity.getNewslettersid(),
				entity.getDeviceid());
		LogAction.getInstance()
				.debug("step 1/" + STEP + ":" + "get data success.(MessageManager|selectNewslettersList)");

		// 取參數
		final String imageUrl = UrlUtils.getUrl(SidcUrlName.IMAGEURL.toString());

		String photoUrl = SystemPropertiesManager.getInstance().findPropertiesMessage("upload.newsletters.path");
		photoUrl = photoUrl.substring(0, photoUrl.length() - 1);

		for (NewslettersBean newsEntity : list) {
			for (ActivityPhotoBean photoEntity : newsEntity.getPhotolist()) {
				final String url = photoEntity.getPhoto();
				photoEntity.setPhoto(imageUrl + photoUrl + url);
			}
		}
		LogAction.getInstance().debug("step 2/" + STEP + ":" + "modify photo full url success.");

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getDeviceid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(device id).");
		}
		if (!MessageManager.getInstance().isExistByDevice(entity.getDeviceid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(not find device id).");
		}
	}
}
