package com.sidc.dao.sits.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.movie.bean.MovieBean;
import com.sidc.blackcore.api.sits.movie.bean.MovieCategoryBean;
import com.sidc.blackcore.api.sits.movie.bean.MovieListBean;
import com.sidc.blackcore.api.sits.movie.bean.MoviePlayBean;
import com.sidc.blackcore.api.sits.movie.bean.MoviesCatalogueBean;
import com.sidc.blackcore.api.sits.movie.bean.VideoServerInfoBean;
import com.sidc.blackcore.api.sits.room.bean.PayServiceBean;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.bill.BillDao;
import com.sidc.dao.sits.cons.ConsDao;
import com.sidc.dao.sits.movie.MovieDao;
import com.sidc.dao.sits.moviebookmarkrecord.MovieBookMarkRecordDao;
import com.sidc.dao.sits.movielanguage.MovieLanguageDao;
import com.sidc.dao.sits.movieplayinglist.MoviePlayingListDao;
import com.sidc.dao.sits.movieratinglanguage.MovieRatingLanguageDao;
import com.sidc.dao.sits.movietype.MovieTypeDao;
import com.sidc.dao.sits.movietypelanguage.MovieTypeLanguageDao;
import com.sidc.dao.sits.movievolume.MovieVolumeDao;
import com.sidc.dao.sits.room.RoomDao;
import com.sidc.dao.sits.stb.StbDao;
import com.sidc.dao.sits.systemfrontfunction.SystemFrontFunctionDao;
import com.sidc.dao.sits.systemfrontmenu.SystemFrontMenuDao;
import com.sidc.dao.sits.tvpetomovie.TypeToMovieDao;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class MovieManager {
	private MovieManager() {
	}

	private static class LazyHolder {
		public static final MovieManager INSTANCE = new MovieManager();
	}

	public static MovieManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public List<MovieCategoryBean> select() throws SQLException {

		Connection conn = null;
		List<MovieCategoryBean> list = new ArrayList<MovieCategoryBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			list = MovieTypeDao.getInstance().select(conn);

			for (MovieCategoryBean entity : list) {
				entity.setLangs(MovieTypeLanguageDao.getInstance().selectByTypeid(conn, entity.getType_id()));

				List<MovieBean> movieList = MovieDao.getInstance().selectByTypeid(conn, entity.getType_id());

				for (MovieBean movieEntity : movieList) {

					movieEntity.setMovieLangs(
							MovieLanguageDao.getInstance().selectByMovieid(conn, movieEntity.getMovie_id()));

					movieEntity.setGradeLangs(
							MovieRatingLanguageDao.getInstance().selectByGrade(conn, movieEntity.getGrade()));
				}

				entity.setMovieInfo(movieList);
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public List<MoviesCatalogueBean> select(final String langCode, final String roomNo)
			throws SQLException, SiDCException {

		Connection conn = null;
		List<MoviesCatalogueBean> list = new ArrayList<MoviesCatalogueBean>();
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final List<String> stbIps = StbDao.getInstance().selectStbIp(conn, roomNo);
			final String billNo = BillDao.getInstance().searchBillNoWithCheckIn(conn, roomNo);

			// 資料檢查
			if (stbIps.isEmpty()) {
				throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find stb ip.");
			}
			if (StringUtils.isBlank(billNo)) {
				throw new SiDCException(APIStatus.DATA_DOES_NOT_EXIST, "not find bill no.");
			}

			list = MovieTypeDao.getInstance().select(conn, langCode);

			for (MoviesCatalogueBean catEntity : list) {
				List<MovieListBean> movieList = MovieDao.getInstance().select(conn, catEntity.getCatalogueid(),
						langCode);
				for (MovieListBean movieEntity : movieList) {
					movieEntity = MovieLanguageDao.getInstance().select(conn, movieEntity, langCode);

					final String movieName = MovieLanguageDao.getInstance().findMovieName(conn, movieEntity.getId(),
							"en_US");

					final boolean isExistInCons = ConsDao.getInstance().findMovieCons(conn, billNo, roomNo, movieName);
					final boolean isExistInBookmark = MovieBookMarkRecordDao.getInstance().isExistByMovie(conn,
							stbIps.get(0), roomNo, movieEntity.getMoviefile());

					// 如果有收看過 才給資料
					if (isExistInCons && isExistInBookmark) {
						final int bookmark = MovieBookMarkRecordDao.getInstance().findTimeStamp(conn, stbIps.get(0),
								roomNo, movieEntity.getMoviefile());

						final int playTime = Integer.valueOf(movieEntity.getPlaytime());

						if (bookmark > 0) {
							movieEntity.setBookmarktime(computeRemainingTime(playTime, bookmark));
						}
						movieEntity.setBookmark(true);
					}
				}

				catEntity.setMovieslist(movieList);
			}

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return list;
	}

	public MoviePlayBean selectPlayInfoById(final String movieId, final String langCode) throws SQLException {

		Connection conn = null;

		MoviePlayBean entity = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final String sourceName = MovieDao.getInstance().selectSourceNameById(conn, movieId);
			final String typeId = TypeToMovieDao.getInstance().selectTypeID(conn, movieId);
			final String langSourceName = MovieLanguageDao.getInstance().selectSourceByMovieId(conn, movieId, langCode);

			entity = new MoviePlayBean(sourceName, langSourceName, typeId);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return entity;
	}

	public VideoServerInfoBean insertPlayingListWithLoadBalance(final String stbIp, final String movieId)
			throws SQLException {

		Connection conn = null;
		VideoServerInfoBean entity = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			MoviePlayingListDao.getInstance().deleteByStbIp(conn, stbIp);

			final String volumeId = MovieVolumeDao.getInstance().selectVolumeIdWithBalance(conn);

			// 沒有撥放中的
			if (StringUtils.isBlank(volumeId)) {
				entity = MovieVolumeDao.getInstance().selectInfo(conn);
			} else {
				entity = MovieVolumeDao.getInstance().selectInfo(conn, volumeId);
			}

			MoviePlayingListDao.getInstance().insert(conn, volumeId, stbIp, movieId);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return entity;
	}

	public VideoServerInfoBean selectVideoServerInfo() throws SQLException {

		Connection conn = null;
		VideoServerInfoBean entity = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			entity = MovieVolumeDao.getInstance().selectInfo(conn);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return entity;
	}

	public PayServiceBean getPayServiceInfo(final String functionName, final String roomNo)
			throws SQLException, SiDCException {

		Connection conn = null;
		PayServiceBean entity = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			final int functionId = SystemFrontFunctionDao.getInstance().selectFunctionId(conn, functionName);

			// 對於飯店來說 這個功能是不是要收費
			final boolean isPayService = SystemFrontMenuDao.getInstance().isPayService(conn, functionId);

			// 對於房間來說 可不可以使用付費服務
			entity = RoomDao.getInstance().selectPayServiceInfo(conn, roomNo);

			entity.setisCharge(isPayService);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return entity;
	}

	public int findBookmarkTime(final String stpIp, final String roomNo, final String fileName) throws SQLException {

		Connection conn = null;

		int bookarkTime = 0;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			bookarkTime = MovieBookMarkRecordDao.getInstance().findTimeStamp(conn, stpIp, roomNo, fileName);

		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return bookarkTime;
	}

	private int computeRemainingTime(int playTime, final int timeStamp) {

		int value = (int) Math.ceil(Double.valueOf(timeStamp) / 60);// 秒換算成分鐘
		if (value < 0) {
			value = 0;
		}
		playTime -= value;

		if (playTime < 1) {
			playTime = 1;
		}
		return playTime;
	}
}
