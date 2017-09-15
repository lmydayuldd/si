package com.sidc.sits.logical.message;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.agent.request.MessageRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.MessageManager;
import com.sidc.dao.sits.manager.RoomManager;
import com.sidc.dao.sits.manager.StbListManager;
import com.sidc.dao.sits.manager.SystemPropertiesManager;
import com.sidc.sits.logical.parameter.PageList;
import com.sidc.sits.logical.parameter.SiTSPropertiesInfo;
import com.sidc.sits.logical.utils.HttpClientUtils;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class MessageInsertProcess extends AbstractAPIProcess {

	private MessageRequest enity;

	public MessageInsertProcess(MessageRequest enity) {
		// TODO Auto-generated constructor stub
		this.enity = enity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + enity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		LogAction.getInstance().setUserId(this.enity.getRoomno());
		MessageManager.getInstance().insertMessage(enity);
		String msg = SystemPropertiesManager.getInstance().findPropertiesMessage(SiTSPropertiesInfo.MESSAGE);
		// relay
		HttpClientUtils.sendMsgToSTB(PageList.MSG_LIST_PAGE, enity.getRoomno(), msg + enity.getText());

		LogAction.getInstance().debug("step message to STB success");
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of room no.");
		}
		if (StringUtils.isBlank(enity.getText())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of text.");
		}

		/*
		 * 2016/11/15 金旭測試 @Nowy Bai 有發現來電者、聯絡人user有可能不輸入，所以調整文件將來電者、聯絡人修改程非必要欄位
		 * 
		 * if (StringUtils.isBlank(enity.getReceiver())) { throw new
		 * SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of receiver."); }
		 */
		if (!RoomManager.getInstance().isCheckin(enity.getRoomno())) {
			throw new SiDCException(APIStatus.FAIL_AUTHENTICATION, "Room is not checkin.");
		}
	}

}
