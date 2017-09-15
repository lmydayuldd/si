package com.sidc.quartz.logical.sits.api;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.sidc.blackcore.api.sits.flight.bean.FlightStatsBean;
import com.sidc.blackcore.api.sits.flight.bean.FlightStatsFlightStatusBean;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.configuration.quartz.FlightStatsApiMethod;
import com.sidc.configuration.quartz.FlightStatsConfiguration;
import com.sidc.configuration.quartz.FlightStatsKeys;
import com.sidc.quartz.logical.parameter.Env;
import com.sidc.quartz.logical.utils.HttpClientUtils;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class FlightInfoProcess extends AbstractAPIProcess {
	private String flightSourceConfigure;

	public FlightInfoProcess() {
	}

	@Override
	protected void init() throws SiDCException, Exception {
		flightSourceConfigure = (String) DataCenter.getInstance().get(Env.FLIGHT_SOURCE_CONFIG);
		LogAction.getInstance().debug("source configure:" + flightSourceConfigure);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		switch (flightSourceConfigure) {
		case "flightstats":
			flightStatsProcess();
			break;
		}

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		if (StringUtils.isBlank(flightSourceConfigure)) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of flight configure.");
		}
	}

	/**
	 * FlightStats API 設定檔 檢查
	 * 
	 * @param configure
	 * @throws SiDCException
	 */
	private void flightStatsCheck(final FlightStatsConfiguration configure) throws SiDCException {
		if (configure == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of flightstats configure.");
		}
		if (StringUtils.isBlank(configure.getUrl())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of flightstats configure(url).");
		}
		if (StringUtils.isBlank(configure.getApplicationid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of flightstats configure(applicationid).");
		}
		if (configure.getKeys().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of flightstats configure(keys).");
		}
		if (configure.getMethods().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of flightstats configure(method).");
		}

		for (final FlightStatsKeys keyEntity : configure.getKeys()) {
			if (StringUtils.isBlank(keyEntity.getValue())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of flightstats configure(keys value).");
			}
		}

		for (final FlightStatsApiMethod methodEntity : configure.getMethods()) {
			if (StringUtils.isBlank(methodEntity.getMethod())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of flightstats configure(method).");
			}
			if (StringUtils.isBlank(methodEntity.getApiname())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of flightstats configure(method apiname).");
			}
			if (StringUtils.isBlank(methodEntity.getArrival())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of flightstats configure(method arrival).");
			}
			if (StringUtils.isBlank(methodEntity.getDeparture())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT,
						"illegal of flightstats configure(method departure).");
			}
		}
	}

	private void flightStatsProcess() throws SiDCException, Exception {
		final String step = "";

		final FlightStatsConfiguration flightStatsConfigure = (FlightStatsConfiguration) DataCenter.getInstance()
				.get(Env.FLIGHT_STATS_CONFIG);
		LogAction.getInstance().debug("flightstats step 1/" + step + ":configure=" + flightStatsConfigure);

		flightStatsCheck(flightStatsConfigure);
		LogAction.getInstance().debug("flightstats step 2/" + step + ":check success.");

		final String url = flightStatsParameterProcess(flightStatsConfigure);
		LogAction.getInstance().debug("flightstats step 3/" + step + ":api url=" + url + ".");

		final String response = HttpClientUtils.httpGet(url);
		LogAction.getInstance().debug("flightstats step 4/" + step + ":http get success,response=" + response + ".");

	}

	private void dataProcess(final String response) throws SiDCException {
		if (StringUtils.isBlank(response)) {
			throw new SiDCException(APIStatus.HTTP_METHOD_FAIL, "flightstats response fail.");
		}

		final Gson gson = new Gson();
		final FlightStatsBean flightEntity = gson.fromJson(response, FlightStatsBean.class);

		Collections.sort(flightEntity.getFlightStatuses());

		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		final SimpleDateFormat dateFormatTime = new SimpleDateFormat("HH:mm");

		for (final FlightStatsFlightStatusBean flightStatsEntity : flightEntity.getFlightStatuses()) {

		}
	}

	/**
	 * format api parameters
	 * 
	 * @param flightEntity
	 * @return
	 * @throws URISyntaxException
	 */
	private String flightStatsParameterProcess(final FlightStatsConfiguration flightEntity) throws URISyntaxException {
		final List<NameValuePair> parameters = new LinkedList<NameValuePair>();
		parameters.add(new BasicNameValuePair("appId", flightEntity.getApplicationid()));
		parameters.add(new BasicNameValuePair("appKey", flightEntity.getKeys().get(0).getValue()));
		// parameters.add(new BasicNameValuePair("numHours", "1"));
		parameters.add(new BasicNameValuePair("languageCode", "en"));

		String apiUrl = flightEntity.getUrl();
		for (final FlightStatsApiMethod methodEntity : flightEntity.getMethods()) {
			if (methodEntity.getMethod().equals("airportstatus")) {
				apiUrl += methodEntity.getApiname() + "TPE" + methodEntity.getArrival() + "/2017/9/5/1?";
				break;
			}
		}

		return new URIBuilder(apiUrl + URLEncodedUtils.format(parameters, "utf-8")).build().toString();
	}

}
