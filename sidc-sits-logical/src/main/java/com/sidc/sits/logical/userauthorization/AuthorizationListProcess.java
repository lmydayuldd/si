package com.sidc.sits.logical.userauthorization;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.sits.userauthorization.bean.AuthorizationFunctionBean;
import com.sidc.blackcore.api.sits.userauthorization.bean.AuthorizationFunctionNameBean;
import com.sidc.blackcore.api.sits.userauthorization.request.AuthorizationListRequest;
import com.sidc.blackcore.api.sits.userauthorization.response.UserAuthorizationResponse;
import com.sidc.configuration.conf.Env;
import com.sidc.dao.sits.manager.AuthorizeManager;
import com.sidc.sits.logical.abs.AbstractAuthAPIProcess;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * 
 * @author Joe
 *
 */
public class AuthorizationListProcess extends AbstractAuthAPIProcess {

	private final AuthorizationListRequest entity;
	private final String STEP = "4";

	public AuthorizationListProcess(final AuthorizationListRequest entity) {
		// TODO Auto-generated constructor stub
		super(entity.getToken());
		this.entity = entity;
	}

	@Override
	protected void init() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().debug("Request:" + entity);
	}

	@Override
	protected Object process() throws SiDCException, Exception {
		LogAction.getInstance().debug(
				"step 1/" + STEP + " :file path " + Env.SYSTEM_DEF_PATH + Env.AUTHORIZATION_FUNCTION_CONFIGUATION_PATH);

		final List<AuthorizationFunctionNameBean> list = readFunctionName(
				new File(Env.SYSTEM_DEF_PATH + Env.AUTHORIZATION_FUNCTION_CONFIGUATION_PATH));
		LogAction.getInstance().debug("step 2/" + STEP + " :read file success.");

		List<String> codeList = new ArrayList<String>();
		for (final AuthorizationFunctionNameBean functionNameEntity : list) {
			codeList.add(functionNameEntity.getFunctioncode());
		}

		final List<String> functionIdList = AuthorizeManager.getInstance().selectFunctionId(entity.getToken());

		final List<AuthorizationFunctionBean> authFunctionList = AuthorizeManager.getInstance()
				.selectFunction(codeList);
		LogAction.getInstance().debug("step 3/" + STEP + " :get function list success.");

		List<UserAuthorizationResponse> responseList = new ArrayList<UserAuthorizationResponse>();

		for (final AuthorizationFunctionNameBean authEntity : list) {
			boolean write = false;
			boolean read = false;
			for (final String functionid : functionIdList) {
				if (functionid.indexOf(authEntity.getFunctioncode(), 0) >= 0) {
					for (final AuthorizationFunctionBean authFunctionEntity : authFunctionList) {
						if (authFunctionEntity.getFuncitonid().equals(functionid)) {
							switch (authFunctionEntity.getCrudgroup()) {
							case "select":
								read = true;
								break;
							case "insert":
								write = true;
								break;
							case "update":
								write = true;
								break;
							case "delete":
								write = true;
								break;
							}
							break;
						}
					}
				}
			}
			responseList.add(new UserAuthorizationResponse(authEntity.getFunctionname(), authEntity.getFunctioncode(),
					read, write));
		}
		LogAction.getInstance().debug("step 4/" + STEP + " :format auth data success.");

		return responseList;
	}

	@Override
	protected void check() throws SiDCException, Exception {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request.");
		}
		if (StringUtils.isBlank(entity.getToken())) {
			throw new SiDCException(APIStatus.ILLEGAL_ARGUMENT, "illegal of request(token).");
		}
	}

	private List<AuthorizationFunctionNameBean> readFunctionName(final File file) throws Exception {

		final String json = FileUtils.readFileToString(file, CharEncoding.UTF_8);

		final List<AuthorizationFunctionNameBean> list = new Gson().fromJson(json,
				new TypeToken<List<AuthorizationFunctionNameBean>>() {
				}.getType());

		return list;
	}
}
