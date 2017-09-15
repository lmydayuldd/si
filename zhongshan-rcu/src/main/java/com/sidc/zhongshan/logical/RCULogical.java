package com.sidc.zhongshan.logical;

import java.io.Serializable;
import java.util.List;

import com.sidc.utils.exception.SiDCException;

/**
 * 
 * @author Allen Huang
 *
 */
public interface RCULogical {

	public List<Serializable> execute() throws SiDCException;
}
