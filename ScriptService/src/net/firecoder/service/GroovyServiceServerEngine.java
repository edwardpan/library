/* GroovyServiceServerEngine.java
 * project: EnvirLims
 */
package net.firecoder.service;

import groovy.util.GroovyScriptEngine;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedHashMap;
import java.util.Map;

import net.firecoder.utils.WebPathUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author �˳�
 * create: 2011-9-6
 */
public class GroovyServiceServerEngine implements ServiceServer {

	private static final Logger log = LoggerFactory.getLogger(GroovyServiceServerEngine.class);
	private static ServiceServer server = null;
	
	public static synchronized ServiceServer getInstance() {
		if (server == null) {
			server = new GroovyServiceServerEngine();
			log.debug("��ʼ��Groovy��������");
		}
		return server;
	}
	
	private String scriptType = ".groovy";
	private String[] addonsRoot = null;
	private ServicePool servicePool = null;
	private GroovyScriptEngine gse = null;
	/* ������չ���񶼹��еĻ������� */
	private Map<String, Object> publicBinding = new LinkedHashMap<String, Object>();
	
	private GroovyServiceServerEngine() {
		servicePool = ServicePoolFactory.getServicePool();
		addonsRoot = new String[] { WebPathUtils.getRootRealPath() + "WEB-INF/addons" };
	}
	
	@Override
	public void start(Map<String, Object> publicBinding) throws Exception {
		if (publicBinding != null) {
			publicBinding.putAll(publicBinding);
		}
		try {
			gse = new GroovyScriptEngine(addonsRoot);
		} catch (Exception e) {
			throw new Exception("��̬��չ������������ʧ�ܣ�", e);
		}
		for (String root : addonsRoot) {
			File rootDir = new File(root);
			// ����Ŀ¼
			File[] addonDirs = rootDir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					if (file.isDirectory()) return true;
					return false;
				}
			});
			log.debug("�ҵ���չ���񹲼ƣ�" + addonDirs.length);
			// ����ÿһ����չ�����
			for (File addonDir : addonDirs) {
				File addonScript = new File(addonDir, addonDir.getName() + scriptType);
				if (!addonScript.exists()) {
					log.warn("�ҵ�����չ����" + addonDir.getName() + "����ڽű���ע��ʧ�ܣ�");
					continue;
				}
				servicePool.registerService(
						new GroovyServiceImpl(addonDir.getName(), gse, publicBinding));
				log.debug("ע����չ����" + addonDir.getName() + 
						"����ڽű���" + addonScript.getName());
			}
		}
	}

	@Override
	public void restart(Map<String, Object> publicBinding) throws Exception {
		try{
			stop();
		} catch (Exception e) {}
		start(publicBinding);
	}

	@Override
	public void stop() throws Exception {
		if (gse == null) {
			throw new Exception("��չ�������滹δ������");
		}
		if (publicBinding != null) {
			publicBinding.clear();
		}
		if (servicePool != null) {
			servicePool.clearServices();
		}
		gse = null;
	}

}
