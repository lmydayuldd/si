package com.sidc.ra.logical.api.rcu.mode;

import org.junit.Test;

import com.google.gson.Gson;
import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuModeSettingDeviceListResponse;

public class asd {

	@Test
	public void test() {
		String aa = "{\"mode\":[{\"catalogue\":\"BULB\",\"devices\":[{\"keycode\":\"BATH\",\"condition\":{\"status\":0}}]}]}";

		Gson gson = new Gson();

		RcuModeSettingDeviceListResponse entity = gson.fromJson(aa, RcuModeSettingDeviceListResponse.class);

		System.out.println(entity);
		System.out.println(gson.toJson(entity));
	}

}
