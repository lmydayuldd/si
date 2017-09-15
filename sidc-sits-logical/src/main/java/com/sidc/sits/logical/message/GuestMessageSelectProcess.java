package com.sidc.sits.logical.message;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.mobile.message.bean.ChatMettingHistoricalMessageBean;
import com.sidc.blackcore.api.mobile.message.bean.ChatMettingMessageBean;
import com.sidc.blackcore.api.mobile.message.request.GuestMessageHistoryInfoRequest;
import com.sidc.dao.sits.manager.MessageManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class GuestMessageSelectProcess extends AbstractAuthAPIProcess {

	private final GuestMessageHistoryInfoRequest entity;
	private final String STEP = "2";

	public GuestMessageSelectProcess(final GuestMessageHistoryInfoRequest entity) {
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

		final List<ChatMettingMessageBean> list = MessageManager.getInstance()
				.selectGuestMessageList(entity.getRoomno(), entity.getDeviceid());
		LogAction.getInstance()
				.debug("step 1/" + STEP + ":" + "get data success.(MessageManager|selectGuestMessageList)");

		List<ChatMettingHistoricalMessageBean> historyList = new ArrayList<ChatMettingHistoricalMessageBean>();

		for (final ChatMettingMessageBean messageEntity : list) {

			ChatMettingHistoricalMessageBean historyEntity = new ChatMettingHistoricalMessageBean(
					messageEntity.getMessageid(), messageEntity.getContent(), messageEntity.getCreationtime());

			if (messageEntity.getReceivertype() == 0 && messageEntity.getSendertype() == 1) {
				historyEntity.setType(0);
				historyEntity.setStaffid(messageEntity.getReceivername());
			} else if (messageEntity.getReceivertype() == 1 && messageEntity.getSendertype() == 0) {
				historyEntity.setType(1);
				historyEntity.setStaffid(messageEntity.getSendername());
			} else {
				historyEntity.setType(-999);
			}

			historyList.add(historyEntity);
		}

		return historyList;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of private key.");
		}
		if (StringUtils.isBlank(entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of public key.");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room no.");
		}
		if (StringUtils.isBlank(entity.getDeviceid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of device id.");
		}

	}
}
