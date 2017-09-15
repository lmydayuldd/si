/**
 * 
 */
package com.derex.cm.stb.api.request;

import java.io.Serializable;
import java.util.List;

/**
 * @author Joe
 *
 */
public class StbAskVersionRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 9210602959985743011L;
    private List<String> stbip;

    public StbAskVersionRequest(List<String> stbip) {
	super();
	this.stbip = stbip;
    }

    public List<String> getStbip() {
	return stbip;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StbAskVersionRequest [stbip=\n");
	builder.append(stbip);
	builder.append("]");
	return builder.toString();
    }

}
