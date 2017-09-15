package com.sidc.dao.quartz.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sidc.blackcore.api.sits.flight.bean.FlightStatsFlightStatusBean;
import com.sidc.dao.connection.ProxoolConnection;
import com.sidc.dao.sits.flightairline.FlightAirlineDao;
import com.sidc.dao.sits.flightairlinelang.FlightAirlineLangDao;
import com.sidc.dao.sits.flightairport.FlightAirportDao;
import com.sidc.dao.sits.flightairportlang.FlightAirportLangDao;
import com.sidc.dao.sits.flightschedule.FlightScheduleDao;
import com.sidc.dao.sits.flightstatuslang.FlightStatusLangDao;

public class FlightManager {
	private static class LazyHolder {
		public static final FlightManager INSTANCE = new FlightManager();
	}

	public static FlightManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void updateFlightSchedule(final List<FlightStatsFlightStatusBean> list) throws SQLException {
		Connection conn = null;
		try {
			conn = ProxoolConnection.getInstance().connectSiTS();
			conn.setAutoCommit(false);

			// FlightScheduleDao.getInstance().deleteWithRegionIsNull(conn);

			final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			final SimpleDateFormat dateFormatTime = new SimpleDateFormat("HH:mm");

			for (final FlightStatsFlightStatusBean entity : list) {
				String arrivalDate = null;

				// 飛航狀態
				if (entity.getStatus().equals("L")) {
					final Date formatArrivalDate = dateFormat.parse(entity.getArrivalDate().getDateLocal());
					arrivalDate = dateFormatTime.format(formatArrivalDate);
				}

				final String status = FlightStatusLangDao.getInstance().findName(conn, "flightstats",
						entity.getStatus(), "zh_TW");

				// 航空公司資訊
				final int airlineId = FlightAirlineDao.getInstance().selectIdByIataCode(conn,
						entity.getCarrierFsCode());

				final String airlineName = FlightAirlineLangDao.getInstance().findName(conn, airlineId, "zh_TW");

				// 出發地機場資訊
				final int departureAirportId = FlightAirportDao.getInstance().selectIdByIataCode(conn,
						entity.getDepartureAirportFsCode());

				final String departureAirportName = FlightAirportLangDao.getInstance().findName(conn,
						departureAirportId, "zh_TW");

				// 目的地機場資訊
				final int ArrivalAirportId = FlightAirportDao.getInstance().selectIdByIataCode(conn,
						entity.getArrivalAirportFsCode());

				final String ArrivalAirportName = FlightAirportLangDao.getInstance().findName(conn, ArrivalAirportId,
						"zh_TW");

				FlightScheduleDao.getInstance().insert(conn, "", arrivalDate, airlineName, entity.getCarrierFsCode(),
						entity.getFlightNumber(), departureAirportName,
						entity.getAirportResources().getArrivalTerminal(),
						entity.getAirportResources().getArrivalGate(), "Arrivals", status, "zh_TW", ArrivalAirportName);
			}

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new SQLException(e);
		} finally {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
	}

}
