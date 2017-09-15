package com.sidc.sits.logical.roomservice;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.request.ShoppingListUpdateRequest;
import com.sidc.blackcore.api.sits.shop.request.ShoppingRequest;
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
public class RoomServiceUpdateProcess extends AbstractAuthAPIProcess {

	private ShoppingListUpdateRequest enity;
	
	public RoomServiceUpdateProcess(ShoppingListUpdateRequest enity) {
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
		
		boolean isUnpost = ShopManager.getInstance().update(enity);
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
		if (this.enity.getList() == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Data list is empty.");
		}

		for (ShoppingRequest shop : this.enity.getList()) {
			if (shop.getId() == 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "ID is 0.");
			}
			if (shop.getQuantity() == 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Quantity is less than or equal to 0.");
			}
		}
	}

}
