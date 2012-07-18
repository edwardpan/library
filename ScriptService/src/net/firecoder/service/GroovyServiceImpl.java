/* GroovyServiceImpl.java
 * project: EnvirLims
 */
package net.firecoder.service;

import groovy.lang.Binding;
import groovy.lang.Script;
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
	
	/**
	 * 构造脚本服务包
	 * @param serviceName 服务包的名称
	 * @param gse 脚本引擎
	 * @param publicBinding 默认绑定的资源
	 */
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
		
		// 初始化构造服务时传入的资源
		Iterator<String> it = publicBinding.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			binding.setVariable(key, publicBinding.get(key));
		}
		
		// 初始化其他资源
		binding.setVariable("SCRIPT", new GroovyScriptHelper(this));
	}
	
	@Override
	public void putVariable(String name, Object value) {
		if ("SCRIPT".equals(name)) {
			return;
		}
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
				Script script = gse.createScript(scriptEntrance, binding);
				result = script.run();
				log.debug("扩展服务" + serviceName + "运行结果：" + result);
			} catch (Exception e) {
				log.debug(e.getMessage(), e);
				throw new Exception("扩展服务" + serviceName + "执行出错！", e);
			} finally {
				// 服务调用完毕后设置初始化状态为true，表示清空之前服务运行时被注入的所有资源
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

	@Override
	public GroovyScriptEngine getGSE() {
		return gse;
	}
}
