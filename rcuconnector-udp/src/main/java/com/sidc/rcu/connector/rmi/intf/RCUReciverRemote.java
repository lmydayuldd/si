/**
 * 
 */
package com.sidc.rcu.connector.rmi.intf;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * @author Nandin
 *
 */
public interface RCUReciverRemote extends Remote {

	public void notice(Serializable object) throws Exception;

}
