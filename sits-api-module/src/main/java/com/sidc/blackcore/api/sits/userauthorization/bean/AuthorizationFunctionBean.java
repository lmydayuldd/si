/**
 * 
 */
package com.sidc.blackcore.api.sits.userauthorization.bean;

import java.io.Serializable;

/**
 * @author Joe
 *
 */
public class AuthorizationFunctionBean implements Serializable {
	private static final long serialVersionUID = -5414670273563320335L;
	private String funcitonid;
	private String url;
	private String functiondesc;
	private String module;
	private String crudgroup;

	public String getFuncitonid() {
		return funcitonid;
	}

	public String getUrl() {
		return url;
	}

	public String getFunctiondesc() {
		return functiondesc;
	}

	public String getModule() {
		return module;
	}

	public String getCrudgroup() {
		return crudgroup;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthorizationFunctionBean [funcitonid=");
		builder.append(funcitonid);
		builder.append(", url=");
		builder.append(url);
		builder.append(", functiondesc=");
		builder.append(functiondesc);
		builder.append(", module=");
		builder.append(module);
		builder.append(", crudgroup=");
		builder.append(crudgroup);
		builder.append("]");
		return builder.toString();
	}

	public AuthorizationFunctionBean(String funcitonid, String url, String functiondesc, String module,
			String crudgroup) {
		super();
		this.funcitonid = funcitonid;
		this.url = url;
		this.functiondesc = functiondesc;
		this.module = module;
		this.crudgroup = crudgroup;
	}

}
