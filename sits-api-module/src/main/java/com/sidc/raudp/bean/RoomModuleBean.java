/**
 * 
 */
package com.sidc.raudp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nandin
 *
 */
public class RoomModuleBean implements Serializable {

	private static final long serialVersionUID = -5872299129304761582L;
	private int id;
	private String name;
	private List<RCUModule> rcu;
	private List<String> rooms;

	public RoomModuleBean() {
		super();
	}

	public RoomModuleBean(int id, String name, List<RCUModule> rcu, List<String> rooms) {
		super();
		this.id = id;
		this.name = name;
		this.rcu = rcu;
		this.rooms = rooms;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RCUModule> getRcu() {
		return rcu;
	}

	public void setRcu(List<RCUModule> rcu) {
		this.rcu = rcu;
	}

	public List<String> getRooms() {
		return rooms;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomModuleBean [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", rcu=");
		builder.append(rcu);
		builder.append(", rooms=");
		builder.append(rooms);
		builder.append("]");
		return builder.toString();
	}

}
