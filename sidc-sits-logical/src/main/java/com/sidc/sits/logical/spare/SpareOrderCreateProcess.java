package com.sidc.sits.logical.spare;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sidc.blackcore.api.sits.spare.bean.SpareOrderItemBean;
import com.sidc.blackcore.api.sits.spare.request.SpareOrderCreateRequest;
import com.sidc.dao.sits.manager.SpareManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

public class SpareOrderCreateProcess extends AbstractAuthAPIProcess {

	private final SpareOrderCreateRequest entity;
	private final String STEP = "1";

	public SpareOrderCreateProcess(final SpareOrderCreateRequest entity) {
		super(entity.getPublickey(), entity.getPrivatekey(), entity.getRoomno());
		this.entity = entity;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		final int id = SpareManager.getInstance().orderCreate(entity.getPublickey(), entity.getPrivatekey(),
				entity.getRoomno(), entity.getGuestno(), entity.getGuestfirstname(), entity.getGuestlastname(),
				entity.getMemo(), entity.getItemlist());
		LogAction.getInstance().debug("step 1/" + STEP + ":insert success(SpareManager|orderCreate).");

		return id;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal.");
		}
		if (StringUtils.isBlank(entity.getPublickey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(public key).");
		}
		if (StringUtils.isBlank(entity.getPrivatekey())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(private key).");
		}
		if (StringUtils.isBlank(entity.getRoomno())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(room no).");
		}
		if (!StringUtils.isBlank(entity.getGuestfirstname()) && entity.getGuestfirstname().length() > 50) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(guest fist name length).");
		}
		if (!StringUtils.isBlank(entity.getGuestlastname()) && entity.getGuestlastname().length() > 50) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(guest last name length).");
		}
		if (!StringUtils.isBlank(entity.getMemo()) && entity.getMemo().length() > 2048) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(memo length).");
		}
		if (entity.getItemlist() == null || entity.getItemlist().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(item list).");
		}
		final List<Integer> itemIdList = new ArrayList<Integer>();
		for (final SpareOrderItemBean itemEntity : entity.getItemlist()) {
			if (itemEntity.getItemid() < 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(item id).");
			}
			if (itemEntity.getQty() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "request illegal(qty).");
			}
			itemIdList.add(itemEntity.getItemid());
		}
		SpareManager.getInstance().orderCreateCheck(entity.getRoomno(), entity.getGuestno(), itemIdList);
	}
}
