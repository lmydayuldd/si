/**
 * 
 */
package com.sidc.rcu.engine.behavior;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import com.sidc.rcu.engine.abs.AbstractChainOfBehavior;
import com.sidc.utils.exception.SiDCException;

/**
 * @author Nandin
 *
 */
public class RCUMemoryBehavior extends AbstractChainOfBehavior {

	public final static String RCU_CACHE = "rcu_cache";

	public RCUMemoryBehavior() {
	}

	@Override
	protected void process() throws SiDCException {

		Ignite ignite = Ignition.ignite();
		IgniteCache<String, String> cache = ignite.getOrCreateCache(RCU_CACHE);

		
	}

}
