/**
 * 
 */
package com.sidc.blackcore.api.sits.userauthorization.request;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class AuthorizationListRequest implements Serializable {
	private static final long serialVersionUID = 7081807155131022575L;
	private String token;

	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthorizationListRequest [token=");
		builder.append(token);
		builder.append("]");
		return builder.toString();
	}

	public AuthorizationListRequest(String token) {
		super();
		this.token = token;
	}

}
