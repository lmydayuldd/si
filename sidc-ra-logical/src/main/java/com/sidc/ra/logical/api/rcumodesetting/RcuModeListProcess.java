package com.sidc.ra.logical.api.rcumodesetting;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sidc.blackcore.api.ra.rcumodesetting.response.RcuDefaultModeResponse;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.ra.manager.RcuModeManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;

public class RcuModeListProcess extends AbstractAPIProcess {

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		List<RcuDefaultModeResponse> list = new ArrayList<RcuDefaultModeResponse>();

		list = RcuModeManager.getInstance().selectDefaultMode();

		for (RcuDefaultModeResponse entity : list) {
			entity.setContent(formatStringProcess(entity.getContent()));
		}

		LogAction.getInstance().debug("step 1/1: get list success(RcuModeManager|selectMode).");

		return list;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 清除空白、換行符號等
	 * 
	 * @param str
	 * @return str
	 */
	private String formatStringProcess(final String str) {
		String strData = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			strData = m.replaceAll("");
		}
		return strData;
	}
}
