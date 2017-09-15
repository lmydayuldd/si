/**
 * 
 */
package com.derex.cm.stb.api.request;

import java.io.Serializable;

import com.sidc.blackcore.api.sits.token.bean.InfoBean;

/**
 * @author Nandin
 *
 */
public class SystemUserLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6179185749828955680L;
	private String user;
	private String password;
	private InfoBean info;

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public InfoBean getInfo() {
		return info;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SystemUserLogin [user=");
		builder.append(user);
		builder.append(", password=");
		builder.append(password);
		builder.append(", info=");
		builder.append(info);
		builder.append("]");
		return builder.toString();
	}

	public SystemUserLogin(String user, String password, InfoBean info) {
		super();
		this.user = user;
		this.password = password;
		this.info = info;
	}

}
