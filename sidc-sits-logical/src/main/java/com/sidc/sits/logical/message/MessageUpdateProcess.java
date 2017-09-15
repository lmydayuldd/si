package com.sidc.sits.logical.message;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.agent.request.MessageRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.sits.manager.MessageManager;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class MessageUpdateProcess extends AbstractAPIProcess {

	private MessageRequest enity;
	public MessageUpdateProcess(MessageRequest enity) {
		this.enity = enity;
	}
	
	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + enity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		MessageManager.getInstance().updateMessage(enity);
		
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
	}

}
