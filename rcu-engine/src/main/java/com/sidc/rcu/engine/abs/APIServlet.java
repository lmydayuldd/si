/**
 * 
 */
package com.sidc.rcu.engine.abs;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.derex.cm.stb.api.response.Apidocument;
import com.google.gson.Gson;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.log.LogAction;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public abstract class APIServlet extends HttpServlet {

	private static final long serialVersionUID = -709781345825499314L;
	private final static Logger logger = LoggerFactory.getLogger(APIServlet.class);

	public APIServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());

		String error = "HTTP Method Fail";

		Apidocument apiDoc = new Apidocument(APIStatus.HTTP_METHOD_FAIL, error);

		if (apiDoc != null) {
			try {
				resp.getWriter().write(new Gson().toJson(apiDoc));
			} catch (IOException e) {
			} finally {
				LogAction.getInstance().writeRecord(APIStatus.HTTP_METHOD_FAIL, error);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		resp.setContentType("text/html; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		SiDCException ex = null;
		int status = APIStatus.SUCCESS;
		Object obj = null;
		Apidocument apiDoc = null;
		try {

			initial(req);

			if (!LogAction.getInstance().isSettingLog()) {
				apiDoc = new Apidocument(APIStatus.LOG_NOT_SETTING, "Log is not initial");

				resp.getWriter().write(new Gson().toJson(apiDoc));

				return;
			}

			String apiRequest = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);

			obj = execute(apiRequest);

			apiDoc = new Apidocument(APIStatus.SUCCESS, APIStatus.MSG_SUCCESS, obj);

		} catch (SiDCException e) {
			ex = e;
			LogAction.getInstance().error(e.getMessage(), e);
		} catch (Exception e) {
			ex = new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
			LogAction.getInstance().error(e.getMessage(), e);
		} catch (Throwable e) {
			ex = new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
			LogAction.getInstance().error(e.getMessage(), e);
		} finally {

			LogAction.getInstance().setRemoteIP(req.getRemoteAddr());

			if (ex != null) {
				status = ex.getErrorCode();
				apiDoc = new Apidocument(ex.getErrorCode(), ex.getMessage());

				LogAction.getInstance().writeRecord(status, ex.getMessage());
			} else {
				LogAction.getInstance().writeRecord(status, APIStatus.MSG_SUCCESS);
			}

			if (apiDoc != null) {
				try {
					resp.getWriter().write(new Gson().toJson(apiDoc));
				} catch (IOException e) {
				}
			}

		}

	}

	protected abstract Object execute(String apiRequest) throws SiDCException, Exception;

	protected abstract void initial(HttpServletRequest req) throws SiDCException, Exception;

}
