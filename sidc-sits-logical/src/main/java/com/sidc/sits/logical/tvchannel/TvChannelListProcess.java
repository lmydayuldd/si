package com.sidc.sits.logical.tvchannel;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.tvchannel.bean.TvChannelBean;
import com.sidc.blackcore.api.sits.tvchannel.bean.TvListBean;
import com.sidc.blackcore.api.sits.tvchannel.request.TvChannelListRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.dao.sits.manager.TvChannelManager;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class TvChannelListProcess extends AbstractAPIProcess {
	final TvChannelListRequest entity;

	public TvChannelListProcess(final TvChannelListRequest entity) {
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

		List<TvListBean> list = TvChannelManager.getInstance().selectAllChannel(entity.getLangcode());
		LogAction.getInstance().debug("step 1/2 get channel list success(TvChannelManager|selectAllChannel)");

		final String imageUrl = UrlUtils.getUrl(SidcUrlName.IMAGEURL.toString());

		final String photoUrl = SystemPropertiesManager.getInstance()
				.findPropertiesMessage("upload.tv.background.path");

		for (TvListBean tvEntity : list) {
			for (TvChannelBean channelEntity : tvEntity.getChannellist()) {
				if (!StringUtils.isBlank(channelEntity.getImage())) {
					channelEntity.setImage(imageUrl + photoUrl + channelEntity.getImage());
					continue;
				}
			}
		}
		LogAction.getInstance().debug("step 2/2 format image url.");

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room no is empty.");
		}
		if (StringUtils.isBlank(entity.getHotelid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "hotel id is empty.");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "language is empty.");
		}
	}
}
