package com.sidc.rcu.engine.behavior;

import org.apache.commons.lang3.StringUtils;

import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.connector.bean.command.RCUCommander;
import com.sidc.rcu.connector.connector.RcuConnector;
import com.sidc.rcu.engine.bean.config.RCUService;
import com.sidc.rcu.engine.bean.config.RCUSetting;
import com.sidc.rcu.engine.conf.RCUManagerKey;
import com.sidc.rcu.engine.conf.SettingKeyword;
import com.sidc.rcu.engine.utils.ConfigurationLoader;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;
import com.sidc.zhongshan.connector.RCUSendConnector;

/**
 * 
 * @author Allen Huang
 *
 */
public class RCUCommandProcess extends AbstractAPIProcess {

	private RCUCommander enity;

	public RCUCommandProcess(RCUCommander enity) {
		// TODO Auto-generated constructor stub
		this.enity = enity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + this.enity);
		LogAction.getInstance().setUserId(this.enity.getRoomno());
		LogAction.getInstance().setContent(this.enity.getKeycode());
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		RCUSetting rcuSetting = (RCUSetting) DataCenter.getInstance().get(RCUManagerKey.CONFIG_RCU);
		RCUService rcuService = ConfigurationLoader.getInstance().readConfig(SettingKeyword.RCU, rcuSetting.getService());

		RcuConnector senderConnector = null;
		try {

			senderConnector = new RCUSendConnector(this.enity.getRoomno(), rcuService.getTarget());
			senderConnector.init();

			senderConnector.send(this.enity);
		} catch (Exception ex) {
			LogAction.getInstance().debug("Room No. " + this.enity.getRoomno() + " is not Command UDP connection.");
		}
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (this.enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(this.enity.getUuid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of UUID.");
		}
		if (StringUtils.isBlank(this.enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room no.");
		}
		if (StringUtils.isBlank(this.enity.getKeycode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of key code.");
		}
	}

}
