package com.sidc.sits.logical.movie;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.movie.bean.MovieListBean;
import com.sidc.blackcore.api.sits.movie.bean.MoviesCatalogueBean;
import com.sidc.blackcore.api.sits.movie.request.MovieListRequest;
import com.sidc.blackcore.api.sits.movie.response.MovieListResponse;
import com.sidc.blackcore.api.sits.room.bean.PayServiceBean;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.MovieManager;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class MovieListProcess extends AbstractAuthAPIProcess {
	private final MovieListRequest entity;
	private final String step = "4";

	public MovieListProcess(final MovieListRequest entity) {
		// TODO Auto-generated constructor stub
		super(entity.getPublickey(), entity.getPrivatekey(), entity.getRoomno());
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
		final String[] systemKeys = { "S21_URL", "PREVIEW_DIR", "CURRENCY" };

		final Map<String, String> propertiesMap = SystemPropertiesManager.getInstance()
				.findPropertiesMessage(systemKeys);

		final String movieUrl = propertiesMap.get("S21_URL");
		final String previewUrl = propertiesMap.get("PREVIEW_DIR");
		final String currency = propertiesMap.get("CURRENCY");
		final String imgUrl = UrlUtils.getUrl(SidcUrlName.MOVIEIMAGE.toString()) + "/";
		LogAction.getInstance()
				.debug("step 1/" + step + ":movieUrl=" + movieUrl + "|previewUrl=" + previewUrl + "|imgUrl=" + imgUrl);

		List<MoviesCatalogueBean> list = MovieManager.getInstance().select(entity.getLangcode(), entity.getRoomno());
		LogAction.getInstance().debug("step 2/" + step + ":get movie list success(MovieManager|select).");

		for (final MoviesCatalogueBean catEntity : list) {
			for (final MovieListBean movieEntity : catEntity.getMovieslist()) {
				movieEntity.setImage(imgUrl + movieEntity.getImage());
			}
		}
		LogAction.getInstance().debug("step 3/" + step + ":update full image link success.");

		final PayServiceBean payEntity = MovieManager.getInstance().getPayServiceInfo("Movie", entity.getRoomno());
		LogAction.getInstance().debug("step 4/" + step + ":get pay service data success.");

		return new MovieListResponse(movieUrl, movieUrl + previewUrl, currency, payEntity.isCharge(),
				payEntity.isPayservice(), payEntity.isAdult(), list);
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
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "lang code is empty.");
		}
		if (StringUtils.isBlank(entity.getHotelid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "hotel is empty.");
		}
		if (!RoomManager.getInstance().isCheckin(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room not check in.");
		}
	}
}
