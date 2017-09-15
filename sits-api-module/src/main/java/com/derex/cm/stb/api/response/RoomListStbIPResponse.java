/**
 * 
 */
package com.derex.cm.stb.api.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nandin
 *
 */
public class RoomListStbIPResponse implements Serializable {

    private static final long serialVersionUID = 976330146340141611L;
    private boolean isCheckin;
    private List<StbStatusConnectionResponse> stb;

    public RoomListStbIPResponse(boolean isCheckin, List<StbStatusConnectionResponse> stb) {
	super();
	this.isCheckin = isCheckin;
	this.stb = stb;
    }

    public boolean isCheckin() {
	return isCheckin;
    }

    public List<StbStatusConnectionResponse> getStb() {
	return stb;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("RoomListStbIPResponse [isCheckin=\n");
	builder.append(isCheckin);
	builder.append(", stb=\n");
	builder.append(stb);
	builder.append("]");
	return builder.toString();
    }

}
