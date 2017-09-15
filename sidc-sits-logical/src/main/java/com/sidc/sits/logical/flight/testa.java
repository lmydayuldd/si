package com.sidc.sits.logical.flight;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.google.gson.Gson;
import com.sidc.blackcore.api.sits.flight.bean.FlightAwareFlightInfoBean;
import com.sidc.blackcore.api.sits.flight.bean.FlightStatsBean;
import com.sidc.blackcore.api.sits.flight.bean.FlightStatsFlightStatusBean;
import com.sidc.blackcore.api.sits.flight.response.FlightAwareResponse;
import com.sidc.sits.logical.utils.HttpClientUtils;

public class testa {

	@Test
	public void test() throws Exception {
		File file = new File("D:\\workspace\\blackcore\\blackcore\\blackcore\\flightTest.json");
		String json = FileUtils.readFileToString(file, CharEncoding.UTF_8);

		Gson gson = new Gson();
		FlightAwareResponse entity = gson.fromJson(json, FlightAwareResponse.class);
		// System.out.println(entity);

		for (FlightAwareFlightInfoBean bean : entity.getAirportBoardsResult().getArrivals().getFlights()) {
			// System.out.println(bean.getStatus());
		}

		final List<NameValuePair> params = new LinkedList<NameValuePair>();
		params.add(new BasicNameValuePair("appId", "a90d806a"));
		params.add(new BasicNameValuePair("appKey", "37bb13d47c40a5b2b1e2b93372230577"));
		params.add(new BasicNameValuePair("numHours", "1"));
		params.add(new BasicNameValuePair("languageCode", "en"));
		final String paramString = URLEncodedUtils.format(params, "utf-8");

		// arr抵達 dep 去
		URI uri = new URIBuilder(
				"https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/status/TPE/arr/2017/9/9/10?"
						+ paramString).build();

		String response = HttpClientUtils.httpGet(uri.toString());
		
		FlightStatsBean enti = gson.fromJson(response, FlightStatsBean.class);
		System.out.println(gson.toJson(enti));
		Collections.sort(enti.getFlightStatuses());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");

		for (final FlightStatsFlightStatusBean flightStatsEntity : enti.getFlightStatuses()) {
			Date date = formatter.parse(flightStatsEntity.getArrivalDate().getDateLocal());
//			System.out.println(formatter.format(date));
//			System.out.println(formatter2.format(date));
			
			//來的機場名稱
//			System.out.println(flightStatsEntity.getDepartureAirportFsCode());
		}

	}
}
