/**
 * 
 */
package com.sidc.ra.logical.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import com.sidc.blackcore.api.ra.rcugroup.bean.RoomRcuInsertBean;
import com.sidc.blackcore.api.ra.request.RoomModuleRequest;
import com.sidc.common.framework.abs.AbstractAPIProcess;
import com.sidc.dao.bean.RoomModuelDevice;
import com.sidc.dao.ra.manager.RCUManager;
import com.sidc.dao.ra.response.DeviceCatalogue;
import com.sidc.dao.ra.response.DeviceEnity;
import com.sidc.dao.ra.response.RcuModule;
import com.sidc.dao.ra.response.RoomRcuEnity;
import com.sidc.ra.logical.common.CommonDataKey;
import com.sidc.raudp.bean.Device;
import com.sidc.raudp.bean.RCUModule;
import com.sidc.raudp.bean.RoomModuleBean;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public class ZhongshanRcuModuleInitialProcess extends AbstractAPIProcess {
	private final String step = "4";
	private RoomModuleRequest enity;

	public ZhongshanRcuModuleInitialProcess(RoomModuleRequest enity) {
		this.enity = enity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.ra.logical.api.AbstractAPIProcess#init()
	 */
	@Override
	protected void init() throws SiDCException, Exception {
		LogAction.getInstance().debug("Request:" + enity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.ra.logical.api.AbstractAPIProcess#process()
	 */
	@Override
	protected Object process() throws SiDCException, Exception {

		if (enity.isInitial()) {
			handleRcuData(this.enity.getModules());
		}
		LogAction.getInstance()
				.debug("Step 1/" + step + ":insert rcu data success(initial enable:" + enity.isInitial() + ").");

		List<RoomRcuEnity> roomRcuEnities = RCUManager.getInstance().listRoomRCU();
		LogAction.getInstance().debug("Step 2/" + step + ":get room rcu data success.");

		initial(roomRcuEnities);
		LogAction.getInstance().debug("Step 3/" + step + ":data format success.");

		storeToMemory(roomRcuEnities);
		LogAction.getInstance().debug("Step 4/" + step + ":store to memory success.");

		return null;
	}

	private void initial(List<RoomRcuEnity> roomRcuEnities) {
		for (RoomRcuEnity enity : roomRcuEnities) {
			List<DeviceCatalogue> cats = enity.getCatalogues();
			for (DeviceCatalogue cat : cats) {
				List<DeviceEnity> devices = cat.getDevices();
				for (DeviceEnity device : devices) {
					append(this.enity.getModules(), enity.getRoomType(), device, cat);
				}
			}
		}
	}

	private void storeToMemory(List<RoomRcuEnity> roomRcuEnities) {
		Ignite ignite = Ignition.ignite();
		IgniteCache<String, RoomRcuEnity> roomRCUStatusCache = ignite.getOrCreateCache("RoomRCUStatus");
		roomRCUStatusCache.clear();

		for (RoomRcuEnity enity : roomRcuEnities) {
			if (StringUtils.isBlank(enity.getRoomno())) {
				LogAction.getInstance().warn("RoomRcuEnity is null.");
				continue;
			}
			roomRCUStatusCache.put(enity.getRoomno(), enity);
		}
	}

	private void append(List<RoomModuleBean> sources, String roomType, DeviceEnity target, DeviceCatalogue targetCat) {

		for (RoomModuleBean module : sources) {
			if (module.getName() != null && module.getName().equals(roomType)) {
				for (RCUModule rcuModule : module.getRcu()) {
					for (Device device : rcuModule.getDevices()) {
						if (device.getKeycode() != null && device.getKeycode().equals(target.getKeycode())) {
							target.setAppender(device.getPosition());
							targetCat.setCommand(rcuModule.getCommand());
						}
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sidc.ra.logical.api.AbstractAPIProcess#check()
	 */
	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (enity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (enity.getModules() == null || enity.getModules().isEmpty()) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(modules).");
		}

		List<String> rooms = new ArrayList<String>();
		for (final RoomModuleBean moduleEntity : enity.getModules()) {
			if (moduleEntity.getId() <= 0) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(id).");
			}
			if (StringUtils.isBlank(moduleEntity.getName())) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(name).");
			}
			if (moduleEntity.getRcu() == null || moduleEntity.getRcu().isEmpty()) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(rcu).");
			}
			if (moduleEntity.getRooms() == null) {
				throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(rooms).");
			}
			for (final String roomNo : moduleEntity.getRooms()) {
				if (rooms.contains(roomNo)) {
					throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(roomno repeat).");
				}
				rooms.add(roomNo);
			}
		}

	}

	private void handleRcuData(List<RoomModuleBean> roomModuleBeans) {
		List<RoomModuelDevice> modules = new ArrayList<RoomModuelDevice>();
		List<RoomRcuInsertBean> rcuRooms = new ArrayList<RoomRcuInsertBean>();
		for (RoomModuleBean enity : roomModuleBeans) {

			for (RCUModule module : enity.getRcu()) {
				for (Device device : module.getDevices()) {
					int deviceId = -1;
					try {
						deviceId = findDeviceID(device.getKeycode());
					} catch (SiDCException e) {
						LogAction.getInstance().debug(e.getMessage());
					}
					if (deviceId == -1) {
						continue;
					}
					modules.add(new RoomModuelDevice(enity.getId(), enity.getName(), deviceId));
				}
			}
			rcuRooms.add(new RoomRcuInsertBean(enity.getId(), enity.getRooms()));
		}

		try {
			// RCUManager.getInstance().insertRCU(modules);
			RCUManager.getInstance().insertRCU(modules, rcuRooms);
		} catch (SQLException e) {
			LogAction.getInstance().error(e.getMessage(), e);
		}
	}

	private int findDeviceID(String name) throws SiDCException {

		@SuppressWarnings("unchecked")
		List<RcuModule> rcuModules = (List<RcuModule>) DataCenter.getInstance().get(CommonDataKey.DEVICE_LIST);

		if (rcuModules == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "Device is not found");
		}
		for (RcuModule rcuModule : rcuModules) {
			if (rcuModule.getDevice().equals(name)) {
				return rcuModule.getId();
			}
		}

		return -1;
	}

}
