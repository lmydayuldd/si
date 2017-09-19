package com.sidc.rcu.hmi.commander;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.sidc.dao.rcu.command.response.RcuRoomMode;
import com.sidc.rcu.hmi.api.parser.UDPParser;
import com.sidc.rcu.hmi.common.CommonDataKey;
import com.sidc.rcu.hmi.common.DataCenter;
import com.sidc.rcu.hmi.framework.abs.AbstractAPIProcess;
import com.sidc.rcu.hmi.systeminitial.bean.BlackcoreInitialBean;
import com.sidc.rcu.hmi.udp.intf.RcuCommandIntf;
import com.sidc.rcu.hmi.websocket.bean.RoomControlCommandBean;
import com.sidc.sdk.blackcore.rcu.command.RcuCommandClient;
import com.sidc.sdk.blackcore.rcu.command.RcuHvacCommandClient;
import com.sidc.sdk.blackcore.rcu.mode.RcuModeClient;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public class RcuCommand extends AbstractAPIProcess {

	private RoomControlCommandBean entity;
	private BlackcoreInitialBean blackcoreSetting;

	public RcuCommand(final RoomControlCommandBean entity) throws SiDCException {
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		this.blackcoreSetting = (BlackcoreInitialBean) DataCenter.getInstance().get(CommonDataKey.BLACKCORE_SETTING);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub

		entity.setUuid(UUID.randomUUID().toString());

		RoomControlCommandBean masterEntity = new RoomControlCommandBean(entity.getUuid(), entity.getRoomno(), "MASTER",
				"\"value\":1");

		if (entity.getKeycode().equals("TV")) {
			new RcuModeClient(blackcoreSetting.getUrl(), new RcuRoomMode(entity.getRoomno(), "WATCHTV")).execute();
		} else if (entity.getKeycode().equals("SLEEP")) {
			new RcuModeClient(blackcoreSetting.getUrl(), new RcuRoomMode(entity.getRoomno(), "SLEEP")).execute();
		} else if (entity.getKeycode().equals("READING")) {
			new RcuModeClient(blackcoreSetting.getUrl(), new RcuRoomMode(entity.getRoomno(), "READING")).execute();
		} else if (entity.getKeycode().equals("WELCOME")) {
			new RcuModeClient(blackcoreSetting.getUrl(), new RcuRoomMode(entity.getRoomno(), "WELCOME")).execute();
		} else if (entity.getKeycode().equals("BLUB_ALL_OPEN")) {
			new RcuModeClient(blackcoreSetting.getUrl(), new RcuRoomMode(entity.getRoomno(), "FULLOPEN")).execute();
		} else if (entity.getKeycode().equals("BLUB_ALL_CLOSE") || entity.getKeycode().equals("MASTER")) {
			new RcuModeClient(blackcoreSetting.getUrl(), new RcuRoomMode(entity.getRoomno(), "ALLOFF")).execute();
		} else if (entity.getKeycode().equals("BATH")) {

			RoomControlCommandBean testEntity = new RoomControlCommandBean(entity.getUuid(), entity.getRoomno(),
					"BATH");

			testEntity.setData(entity.getData());

			new RcuHvacCommandClient(blackcoreSetting.getUrl(), UDPParser.getInstance().toJsonByContent(testEntity))
					.execute();

			testEntity = new RoomControlCommandBean(entity.getUuid(), entity.getRoomno(), "MIRROR");
			testEntity.setData(entity.getData());
			new RcuHvacCommandClient(blackcoreSetting.getUrl(), UDPParser.getInstance().toJsonByContent(testEntity))
					.execute();

			testEntity = new RoomControlCommandBean(entity.getUuid(), entity.getRoomno(), "SINK");
			testEntity.setData(entity.getData());
			new RcuHvacCommandClient(blackcoreSetting.getUrl(), UDPParser.getInstance().toJsonByContent(testEntity))
					.execute();

		} else {
			// new RcuHvacCommandClient(blackcoreSetting.getUrl(),
			// UDPParser.getInstance().toJsonByContent(entity)).execute();
			new RcuHvacCommandClient(blackcoreSetting.getUrl(), UDPParser.getInstance().toJsonByContent(entity))
					.execute();
		}

		RoomControlCommandBean askEntity = new RoomControlCommandBean(entity.getUuid(), entity.getRoomno(), "ASK");
		new RcuCommandClient(blackcoreSetting.getUrl(), RcuCommandIntf.ASK,
				UDPParser.getInstance().toJsonByContent(askEntity)).execute();

		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "entity illegal.");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "roomno is null.");
		}
		if (StringUtils.isBlank(entity.getKeycode())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "keycode null.");
		}
		if (blackcoreSetting == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "blackcoreSetting is null(DataCenter).");
		}
	}
}
