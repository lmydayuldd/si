/**
 * 
 */
package com.sidc.ra.logical.abs;

import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public interface RCUAuthorization {

	void authorize() throws SiDCException;

	void domainAuthorize() throws SiDCException;
}
