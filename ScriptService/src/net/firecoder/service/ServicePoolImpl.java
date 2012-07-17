/* ServicePoolImpl.java
 * project: EnvirLims
 */
package net.firecoder.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ��չ�����ʵ��
 * @author �˳�
 * create: 2011-9-6
 */
class ServicePoolImpl implements ServicePool {
	
	private static final Logger log = LoggerFactory.getLogger(ServicePoolImpl.class);
	
	/** ʵ�ʳ� */
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
			log.debug("����չ������л�ȡ����" + serviceName);
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
			throw new Exception("��չ�����Ѿ����ڣ�" + service.getServiceName());
		}
		loadedServices.put(service.getServiceName(), service);
		log.debug("ע����չ����" + service.getServiceName() + 
				"��Ŀǰ������չ��������" + loadedServices.size());
	}

	@Override
	public void clearServices() {
		if (loadedServices != null) {
			log.debug("���������չ���񣬹��ƣ�" + loadedServices.size());
			loadedServices.clear();
		}
	}

}
