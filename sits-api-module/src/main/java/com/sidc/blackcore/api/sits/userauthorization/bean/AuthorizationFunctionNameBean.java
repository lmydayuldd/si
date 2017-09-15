/**
 * 
 */
package com.sidc.blackcore.api.sits.userauthorization.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joe
 *
 */
public class AuthorizationFunctionNameBean implements Serializable {
	private static final long serialVersionUID = -6412552707193982667L;
	private String functionname;
	private String functioncode;
	private List<AuthorizationFunctionInfoBean> list = new ArrayList<AuthorizationFunctionInfoBean>();

	public String getFunctionname() {
		return functionname;
	}

	public String getFunctioncode() {
		return functioncode;
	}

	public List<AuthorizationFunctionInfoBean> getList() {
		return list;
	}

	public void setList(List<AuthorizationFunctionInfoBean> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthorizationFunctionNameBean [functionname=");
		builder.append(functionname);
		builder.append(", functioncode=");
		builder.append(functioncode);
		builder.append(", list=");
		builder.append(list);
		builder.append("]");
		return builder.toString();
	}

	public AuthorizationFunctionNameBean(String functionname, String functioncode) {
		super();
		this.functionname = functionname;
		this.functioncode = functioncode;
	}

}
