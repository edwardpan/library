/* ServicePoolFactory.java
 * project: EnvirLims
 */
package net.firecoder.service;

/**
 * @author ≈À≥¨
 * create: 2011-9-6
 */
public class ServicePoolFactory {
	private static ServicePool pool = null;
	
	public static synchronized ServicePool getServicePool() {
		if (pool == null) {
			pool = new ServicePoolImpl();
		}
		return pool;
	}
}
