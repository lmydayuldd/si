package com.sidc.blackcore.api.ra.rcumodesetting.response;

public class RcuModeSettingDevicesEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5112733832038333370L;
	private String keycode;
	private RcuModeSettingConditionEntity condition;

	public String getKeycode() {
		return keycode;
	}

	public RcuModeSettingConditionEntity getCondition() {
		return condition;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RcuModeSettingDevicesEntity [keycode=");
		builder.append(keycode);
		builder.append(", condition=");
		builder.append(condition);
		builder.append("]");
		return builder.toString();
	}

	public RcuModeSettingDevicesEntity(String keycode, RcuModeSettingConditionEntity condition) {
		super();
		this.keycode = keycode;
		this.condition = condition;
	}

}
