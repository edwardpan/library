/* ServicePoolImpl.java
 * project: EnvirLims
 */
package net.firecoder.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 扩展服务池实现
 * @author 潘超
 * create: 2011-9-6
 */
class ServicePoolImpl implements ServicePool {
	
	private static final Logger log = LoggerFactory.getLogger(ServicePoolImpl.class);
	
	/** 实际池 */
	private Map<String, Service> loadedServices = null;
	
	ServicePoolImpl() {
		loadedServices = new LinkedHashMap<String, Service>();
	}

	@Override
	public Service getService(String serviceName) {
		if (serviceName == null) {
			return null;
		}
		Service temp = loadedServices.get(serviceName);
		if (temp != null) {
			log.debug("从扩展服务池中获取服务：" + serviceName);
		}
		return temp;
	}

	@Override
	public void registerService(Service service) throws Exception {
		if (loadedServices == null) {
			return;
		}
		
		Service temp = loadedServices.get(service.getServiceName());
		if (temp != null) {
			throw new Exception("扩展服务已经存在：" + service.getServiceName());
		}
		loadedServices.put(service.getServiceName(), service);
		log.debug("注册扩展服务：" + service.getServiceName() + 
				"。目前池内扩展服务数：" + loadedServices.size());
	}

	@Override
	public void clearServices() {
		if (loadedServices != null) {
			log.debug("清除所有扩展服务，共计：" + loadedServices.size());
			loadedServices.clear();
		}
	}

}
