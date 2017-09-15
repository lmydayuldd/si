/**
 * 
 */
package com.sidc.sits.logical.abs;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sidc.blackcore.api.sits.userauthorization.bean.AuthorizationFunctionBean;
import com.sidc.blackcore.api.sits.userauthorization.bean.AuthorizationFunctionNameBean;
import com.sidc.configuration.conf.Env;
import com.sidc.dao.sits.manager.AuthorizeManager;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

public abstract class TokenAuthorization implements Authorization {

	private String token;
	private String privateKey;
	private String roomNo;

	public TokenAuthorization(final String token) {
		this.token = token;
	}

	public TokenAuthorization(final String token, final String privateKey, final String roomNo) {
		this.token = token;
		this.privateKey = privateKey;
		this.roomNo = roomNo;
	}

	@Override
	public void authorize() throws SiDCException {
		String token = null;
		try {
			// AesEncrypt encrypt = new AesEncrypt("sidc"); // token =
			// encrypt.decrypt(this.token);

			token = this.token;
		} catch (Exception e) {
			throw new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
		}

		try {
			AuthorizeManager.getInstance().StaffChatAuthorize(token);
		} catch (SQLException e) {
			throw new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage());
		}
	}

	public void authorizeByFuncitonList(final String url) throws SiDCException {
		try {
			// AesEncrypt encrypt = new AesEncrypt("sidc");
			// token = encrypt.decrypt(this.token);

			@SuppressWarnings("unchecked")
			List<AuthorizationFunctionBean> authFunctionList = (List<AuthorizationFunctionBean>) DataCenter
					.getInstance().get(Env.AUTHORIZATION_FUNCTION_LIST);

			if (authFunctionList == null) {
				authFunctionList = authDataProcess();
				DataCenter.getInstance().put(Env.AUTHORIZATION_FUNCTION_LIST, authFunctionList);
			}

			String functionId = null;

			for (final AuthorizationFunctionBean entity : authFunctionList) {
				if (entity.getUrl().equals(url)) {
					functionId = entity.getFuncitonid();
					break;
				}
			}

			if (StringUtils.isBlank(functionId)) {
				throw new SiDCException(APIStatus.FAIL_OPERATE_PERMISSION,
						"Can not use this api(not find function id).");
			}

			AuthorizeManager.getInstance().APIAuthorize(token, functionId);

		} catch (SiDCException e) {
			throw new SiDCException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			throw new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage());
		}

	}

	// mobile user
	public void MobileAuthorize() throws Exception {
		String publicKey = null;
		String privateKey = null;
		try {
			// AesEncrypt encrypt = new AesEncrypt("sidc");
			// publicKey = encrypt.decrypt(token);
			//
			// encrypt.decrypt(this.privateKey);
			// privateKey = encrypt.getDecryptedString();
			publicKey = this.token;
			privateKey = this.privateKey;

		} catch (Exception e) {
			throw new SiDCException(APIStatus.TOKEN_ILLEGAL, e.getMessage());
		}

		AuthorizeManager.getInstance().mobileAuthorize(roomNo, publicKey, privateKey, 0);

	}

	private List<AuthorizationFunctionBean> authDataProcess() throws Exception {

		final File file = new File(Env.SYSTEM_DEF_PATH + Env.AUTHORIZATION_FUNCTION_CONFIGUATION_PATH);

		final String json = FileUtils.readFileToString(file, CharEncoding.UTF_8);

		final List<AuthorizationFunctionNameBean> list = new Gson().fromJson(json,
				new TypeToken<List<AuthorizationFunctionNameBean>>() {
				}.getType());

		List<String> codeList = new ArrayList<String>();

		for (final AuthorizationFunctionNameBean functionEntity : list) {
			codeList.add(functionEntity.getFunctioncode());
		}

		return AuthorizeManager.getInstance().selectFunction(codeList);
	}

}
