/*
 * Test.java
 */
package net.firecoder.service.test;

import net.firecoder.service.GroovyServiceServerEngine;
import net.firecoder.service.Service;
import net.firecoder.service.ServicePoolFactory;
import net.firecoder.service.ServiceServer;

/**
 * @author ≈À≥¨
 * @date Jul 18, 2012
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceServer server = GroovyServiceServerEngine.getInstance();
		try {
			server.start(null);
			Service service = ServicePoolFactory.getServicePool().getService("Test");
			System.out.println(service.call());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
