/**
 * 
 */
package com.sidc.ra.logical.abs;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import com.sidc.configuration.blackcore.AllowDomainConfiguration;
import com.sidc.configuration.blackcore.AllowDomainIP;
import com.sidc.configuration.conf.Env;
import com.sidc.dao.sits.manager.AuthorizeManager;
import com.sidc.utils.common.DataCenter;
import com.sidc.utils.exception.SiDCException;
import com.sidc.utils.status.APIStatus;

/**
 * @author Nandin
 *
 */
public abstract class TokenAuthorization implements RCUAuthorization {

	private String token;
	private String roomNo;
	private String address;
	private String privateKey;

	public TokenAuthorization(final String token, final String privateKey, final String roomNo, final String address) {
		this.token = token;
		this.roomNo = roomNo;
		this.address = address;
		this.privateKey = privateKey;
	}

	public TokenAuthorization(final String token, final String roomNo, final String address) {
		this.token = token;
		this.roomNo = roomNo;
		this.address = address;
	}

	public TokenAuthorization(final String roomNo, final String address) {
		this.roomNo = roomNo;
		this.address = address;
	}

	public void MobileAuthorize() throws Exception {

		if (!isAllowDomainCheck(InetAddress.getByName(this.address))) {
			throw new SiDCException(APIStatus.IP_NOT_ALLOWED, "The IP is not allowed(" + this.address + ")");
		}

		String token = null;
		String privateKey = null;
		try {
			// AesEncrypt encrypt = new AesEncrypt("sidc");
			// token = encrypt.decrypt(this.token);
			token = this.token;
			//
			// privateKey = encrypt.decrypt(this.privateKey);
			privateKey = this.privateKey;
		} catch (Exception e) {
			throw new SiDCException(APIStatus.TOKEN_ILLEGAL, "illegal token.");
		}

		AuthorizeManager.getInstance().mobileAuthorize(roomNo, token, privateKey, 0);

	}

	@Override
	public void authorize() throws SiDCException {
		try {
			if (!isAllowDomainCheck(InetAddress.getByName(this.address))) {
				throw new SiDCException(APIStatus.IP_NOT_ALLOWED, "The IP is not allowed(" + this.address + ")");
			}
			String token = null;
			try {
				// AesEncrypt encrypt = new AesEncrypt("sidc");
				// token = encrypt.decrypt(this.token);
				token = this.token;
			} catch (Exception e) {
				throw new SiDCException(APIStatus.TOKEN_ILLEGAL, "illegal token.");
			}

			AuthorizeManager.getInstance().mobileAuthorize(roomNo, token, 0);
		} catch (SQLException e) {
			throw new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
		} catch (UnknownHostException e) {
			throw new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
		}
	}

	@Override
	public void domainAuthorize() throws SiDCException {

		try {
			if (!isAllowDomainCheck(InetAddress.getByName(this.address))) {
				throw new SiDCException(APIStatus.IP_NOT_ALLOWED, "The IP is not allowed(" + this.address + ")");
			}

		} catch (UnknownHostException e) {
			throw new SiDCException(APIStatus.GENERAL_ERROR, e.getMessage(), e);
		}
	}

	private boolean isAllowDomainCheck(final InetAddress ip) throws UnknownHostException, SiDCException {
		boolean isAllow = false;

		if (!ip.equals(InetAddress.getByName("127.0.0.1"))) {
			String domain = "";
			for (int i = 0; i < 2; i++) {
				if (!domain.equals(""))
					domain += ".";
				domain += (ip.getAddress()[i] < 0 ? (int) ip.getAddress()[i] + 256 : (int) ip.getAddress()[i]) << (256
						* i);
			}

			final AllowDomainConfiguration configuration = (AllowDomainConfiguration) DataCenter.getInstance()
					.get(Env.DOMAINCONFIGURATION);
			List<AllowDomainIP> domainIPs = configuration.getList();
			for (AllowDomainIP allowDomainIP : domainIPs) {

				List<String> list = allowDomainIP.getValue();
				if (list.contains(domain) || list.contains("*")) {
					isAllow = true;
					break;
				}
			}
		} else {
			isAllow = true;
		}
		return isAllow;
	}
}
