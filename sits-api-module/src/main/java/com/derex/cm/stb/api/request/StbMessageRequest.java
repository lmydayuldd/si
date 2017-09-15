/**
 * 
 */
package com.derex.cm.stb.api.request;

import java.io.Serializable;

/**
 * @author Nandin
 *
 */
public class StbMessageRequest implements Serializable {

    private static final long serialVersionUID = -4830309799299996660L;
    private String roomno;
    private String message;

    public StbMessageRequest(String roomno, String message) {
	super();
	this.roomno = roomno;
	this.message = message;
    }

    public String getRoomno() {
	return roomno;
    }

    public String getMessage() {
	return message;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("StbMessageRequest [roomno=\n");
	builder.append(roomno);
	builder.append(", message=\n");
	builder.append(message);
	builder.append("]");
	return builder.toString();
    }

}
