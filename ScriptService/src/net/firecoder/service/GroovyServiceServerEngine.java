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
 * @author 潘超
 * create: 2011-9-6
 */
public class GroovyServiceServerEngine implements ServiceServer {

	private static final Logger log = LoggerFactory.getLogger(GroovyServiceServerEngine.class);
	private static ServiceServer server = null;
	
	public static synchronized ServiceServer getInstance() {
		if (server == null) {
			server = new GroovyServiceServerEngine();
			log.debug("初始化Groovy服务引擎");
		}
		return server;
	}
	
	private String scriptType = ".groovy";
	private String[] addonsRoot = null;
	private ServicePool servicePool = null;
	private GroovyScriptEngine gse = null;
	/* 所有扩展服务都共有的环境变量 */
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
			throw new Exception("动态扩展服务引擎启动失败！", e);
		}
		for (String root : addonsRoot) {
			File rootDir = new File(root);
			// 查找目录
			File[] addonDirs = rootDir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					if (file.isDirectory()) return true;
					return false;
				}
			});
			log.debug("找到扩展服务共计：" + addonDirs.length);
			// 遍历每一个扩展服务包
			for (File addonDir : addonDirs) {
				File addonScript = new File(addonDir, addonDir.getName() + scriptType);
				if (!addonScript.exists()) {
					log.warn("找到不扩展服务" + addonDir.getName() + "的入口脚本，注册失败！");
					continue;
				}
				servicePool.registerService(
						new GroovyServiceImpl(addonDir.getName(), gse, publicBinding));
				log.debug("注册扩展服务：" + addonDir.getName() + 
						"，入口脚本：" + addonScript.getName());
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
			throw new Exception("扩展服务引擎还未启动！");
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
