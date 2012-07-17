/* GroovyServiceImpl.java
 * project: EnvirLims
 */
package net.firecoder.service;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 潘超
 * create: 2011-9-6
 */
public class GroovyServiceImpl implements Service {

	private static final Logger log = LoggerFactory.getLogger(GroovyServiceImpl.class);
	private String serviceName = null;
	private Map<String, Object> publicBinding = null;
	private Binding binding = null;
	private GroovyScriptEngine gse = null;
	private String scriptEntrance = null;
	private boolean isInitBinding = false;
	
	public GroovyServiceImpl(String serviceName, GroovyScriptEngine gse, Map<String, Object> publicBinding) {
		this.serviceName = serviceName;
		this.gse = gse;
		this.publicBinding = publicBinding;
		initBinding();
		this.scriptEntrance = serviceName + "/" + serviceName + ".groovy";
		log.debug("扩展服务初始化：" + serviceName);
	}
	
	@Override
	public void initBinding() {
		this.binding = new Binding();
		Iterator<String> it = publicBinding.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			binding.setVariable(key, publicBinding.get(key));
		}
	}
	
	@Override
	public void putVariable(String name, Object value) {
		if (isInitBinding) {
			initBinding();
			isInitBinding = false;
		}
		binding.setVariable(name, value);
	}

	@Override
	public Object getVariable(String name) {
		return binding.getVariable(name);
	}

	@Override
	public Object call() throws Exception {
		Object result = null;
		if (gse != null) {
			try {
				log.debug("运行扩展服务：" + serviceName);
				result = gse.run(scriptEntrance, binding);
				log.debug("扩展服务" + serviceName + "运行结果：" + result);
			} catch (Exception e) {
				throw new Exception("扩展服务" + serviceName + "执行出错！", e);
			} finally {
				isInitBinding = true;
			}
		}
		return result;
	}

	@Override
	public String getServiceName() {
		return serviceName;
	}

	@Override
	public Binding getBinding() {
		return binding;
	}
}
