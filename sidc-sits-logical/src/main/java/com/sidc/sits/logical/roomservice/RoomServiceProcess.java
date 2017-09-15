package com.sidc.sits.logical.roomservice;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.shop.request.ShoppingListRequest;
import com.sidc.blackcore.api.sits.shop.request.ShoppingRequest;
import com.sidc.blackcore.api.sits.shop.response.ShoppingResponse;
import com.sidc.dao.sits.cons.ConsumeType;
import com.sidc.dao.sits.manager.RoomManager;
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
public class RoomServiceProcess extends AbstractAuthAPIProcess {

	private ShoppingListRequest enity;

	public RoomServiceProcess(ShoppingListRequest enity) {
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
		// TODO Auto-generated method
		// stubLogAction.getInstance().setUserId(this.enity.getRoomno());

		boolean isCheck = RoomManager.getInstance().isCheckin(this.enity.getRoomno());
		if (!isCheck) {
			throw new SiDCException(APIStatus.FAIL_AUTHENTICATION, "Room is not checkin.");
		}

		String billNo = RoomManager.getInstance().findBillno(this.enity.getRoomno());

		ShoppingResponse resposne = ShopManager.getInstance().insert(enity, billNo, ConsumeType.ROOMSERVICE);
		
		return resposne;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal.");
		}
		if (StringUtils.isBlank(enity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Private key is empty.");
		}
		if (StringUtils.isBlank(enity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Public key is empty.");
		}
		if (StringUtils.isBlank(enity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Room no is empty");
		}
		if (this.enity.getList() == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Data list is empty.");
		}

		for (ShoppingRequest roomService : this.enity.getList()) {
			if (roomService.getId() == 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "ID is 0.");
			}
			if (roomService.getQuantity() == 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Quantity is less than or equal to 0.");
			}
		}
	}

}
