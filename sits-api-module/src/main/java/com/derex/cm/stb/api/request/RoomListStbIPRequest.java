package com.derex.cm.stb.api.request;

import java.io.Serializable;

public class RoomListStbIPRequest implements Serializable {

    private static final long serialVersionUID = 4783845704858324003L;
    private String roomno;

    public RoomListStbIPRequest(String roomno) {
	super();
	this.roomno = roomno;
    }

    public String getRoomno() {
	return roomno;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("RoomListStbIPRequest [roomno=\n");
	builder.append(roomno);
	builder.append("]");
	return builder.toString();
    }

}
