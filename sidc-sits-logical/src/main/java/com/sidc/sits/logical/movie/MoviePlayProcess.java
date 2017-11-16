package com.sidc.sits.logical.movie;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.derex.cm.stb.api.request.StbMoviePlayRequest;
import com.sidc.blackcore.api.sits.movie.bean.MoviePlayBean;
import com.sidc.blackcore.api.sits.movie.bean.VideoServerInfoBean;
import com.sidc.blackcore.api.sits.movie.request.MoviePlayRequest;
import com.sidc.configuration.conf.SidcUrlName;
import com.sidc.dao.sits.manager.MovieManager;
import com.sidc.dao.sits.manager.StbListManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.sits.logical.utils.UrlUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class MoviePlayProcess extends AbstractAuthAPIProcess {
	private final MoviePlayRequest entity;
	private final String STEP = "6";

	public MoviePlayProcess(final MoviePlayRequest entity) {
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

		final List<String> stbipList = StbListManager.getInstance().listStbIp(entity.getRoomno());
		LogAction.getInstance().debug("step 1/" + STEP + " get stbip success.");

		final MoviePlayBean movieEntity = MovieManager.getInstance().selectPlayInfoById(entity.getMovieid(),
				entity.getLangcode());
		LogAction.getInstance().debug("step 2/" + STEP + " movie play info:" + movieEntity);

		int bookmarkTime = 0;
		if (entity.isBookmark()) {
			bookmarkTime = MovieManager.getInstance().findBookmarkTime(stbipList.get(0), entity.getRoomno(),
					movieEntity.getFilepath());
		}
		LogAction.getInstance().debug("step 3/" + STEP + " bookmark:" + bookmarkTime);

		StbMoviePlayRequest moviePlayEntity = new StbMoviePlayRequest(stbipList, movieEntity.getFilepath(),
				movieEntity.getFilepath(), true, 0, movieEntity.getCategoryid(), 0);

		VideoServerInfoBean videoInfoEntity = null;

		if ("Y".equals(SystemPropertiesManager.getInstance().findPropertiesMessage("enable.movie.load.balance"))) {
			videoInfoEntity = MovieManager.getInstance().insertPlayingListWithLoadBalance(entity.getIp(),
					entity.getMovieid());
			LogAction.getInstance()
					.debug("step 4/" + STEP + " movie load balance, video server info :" + videoInfoEntity);
		} else {
			videoInfoEntity = MovieManager.getInstance().selectVideoServerInfo();

			final String s21Url = SystemPropertiesManager.getInstance().findPropertiesMessage("S21_URL");
			final String tmpUrl = s21Url.replace("rtsp://", "");
			final String path[] = StringUtils.split(tmpUrl, "/");

			if (path.length > 0) {
				int charIndex = path[0].indexOf(":");
				if (charIndex != -1) {
					videoInfoEntity.setHost(path[0].substring(0, charIndex));
					videoInfoEntity.setPort(path[0].substring(charIndex + 1));
				} else {
					videoInfoEntity.setHost(path[0]);
					videoInfoEntity.setPort("554");
				}
			} else {
				videoInfoEntity.setHost("10.60.1.199");
				videoInfoEntity.setPort("554");
			}
			LogAction.getInstance().debug("step 4/" + STEP + " video server info :" + videoInfoEntity);
		}

		moviePlayEntity.setIp(videoInfoEntity.getHost());
		moviePlayEntity.setPort(videoInfoEntity.getPort());
		moviePlayEntity.setType(videoInfoEntity.getType());
		moviePlayEntity.setFilepath(videoInfoEntity.getVolume() + "/" + movieEntity.getFilepath());
		moviePlayEntity.setBookmarktime(bookmarkTime);

		// 不一定會有字幕...2017/09/06
		String langFilePath = "";
		if (!StringUtils.isBlank(movieEntity.getLangfilepath())) {
			langFilePath = videoInfoEntity.getVolume() + "/" + movieEntity.getLangfilepath();
		}
		moviePlayEntity.setLangfilepath(langFilePath);
		LogAction.getInstance().debug("step 5/" + STEP + " Movie Play Entity:" + moviePlayEntity);

		final String sitsUrl = UrlUtils.getUrl(SidcUrlName.SITS.toString());
		HttpClientUtils.sendPostWithMoviePlay(sitsUrl + PageList.STB_MOVIE_PLAY, moviePlayEntity);
		LogAction.getInstance()
				.debug("step 6/" + STEP + " send post to sits success, url:" + sitsUrl + PageList.STB_MOVIE_PLAY);

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "private key is empty.");
		}
		if (StringUtils.isBlank(entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "public key is empty.");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "room no is empty.");
		}
		if (StringUtils.isBlank(entity.getLangcode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "lang code is empty.");
		}
		if (StringUtils.isBlank(entity.getMovieid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "movie id is empty.");
		}
		if (StringUtils.isBlank(entity.getIp())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "ip is empty.");
		}
	}
}
