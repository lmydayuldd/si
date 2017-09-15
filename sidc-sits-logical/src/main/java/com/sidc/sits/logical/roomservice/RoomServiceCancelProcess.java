package com.sidc.sits.logical.roomservice;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.request.ShoppingDeleteRequest;
import com.sidc.dao.sits.manager.ShopManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Allen Huang
 *
 */
public class RoomServiceCancelProcess extends AbstractAuthAPIProcess {

	private ShoppingDeleteRequest enity;
	
	public RoomServiceCancelProcess(ShoppingDeleteRequest enity) {
		super(enity.getPublickey(), enity.getPrivatekey(), enity.getRoomno());
		this.enity = enity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + enity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().setUserId(this.enity.getRoomno());
		
		boolean isUnpost = ShopManager.getInstance().delete(enity.getOrderid());
		if (!isUnpost) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Order has been set up, In the meal.");
		}
		
		return null;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal.");
		}
		if (StringUtils.isBlank(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Room No is empty");
		}
		if (StringUtils.isBlank(enity.getOrderid())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Order Id is empty.");
		}
		if (StringUtils.isBlank(enity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Private key is empty.");
		}
		if (StringUtils.isBlank(enity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Public key is empty.");
		}
	}

}
