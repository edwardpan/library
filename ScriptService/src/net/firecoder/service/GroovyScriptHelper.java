/*
 * GroovyServiceHelper.java
 */
package net.firecoder.service;

import groovy.lang.Script;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 潘超
 * @date Jul 18, 2012
 */
public class GroovyScriptHelper {
	private static final Logger log = LoggerFactory.getLogger(GroovyScriptHelper.class);
	private Service service;
	
	public GroovyScriptHelper(Service service) {
		this.service = service;
	}
	
	/**
	 * 引入内部脚本
	 * @param alias 别名
	 * @param scriptName 名称，如：convertDate(.groovy)，不包含括号和括号中的内容
	 * @throws Exception 
	 */
	public void include(String alias, String scriptName) throws Exception {
		if (alias == null || alias.equals("")) {
			alias = "iscript_" + scriptName;
		}
		String scriptEntrance = service.getServiceName() + "/" + scriptName + ".groovy";
		log.debug("引用的脚本：" + scriptEntrance);
		try {
			Script script = service.getGSE().createScript(scriptEntrance, service.getBinding());
			service.putVariable(alias, script);
			log.debug("脚本引用成功：" + scriptEntrance);
		} catch (Exception e) {
			throw new Exception("引用脚本>>" + scriptName + "<<出错！", e);
		}
	}
	
	/**
	 * 引入外部脚本
	 * @param alias 别名
	 * @param service 需要引入的外部脚本所在服务
	 * @param scriptName 需要引入的外部脚本名称，如：convertDate(.groovy)，不包含括号和括号中的内容
	 * @throws Exception 
	 */
	public void include(String alias, String serviceName, String scriptName) throws Exception {
		if (alias == null || alias.equals("")) {
			alias = "oscript_" + serviceName + "_" + scriptName;
		}
		String scriptEntrance = serviceName + "/" + scriptName + ".groovy";
		log.debug("引用的脚本：" + scriptEntrance);
		try {
			Script script = service.getGSE().createScript(scriptEntrance, service.getBinding());
			service.putVariable(alias, script);
			log.debug("脚本引用成功：" + scriptEntrance);
		} catch (Exception e) {
			throw new Exception("引用脚本>>" + serviceName + "." + scriptName + "<<出错！", e);
		}
	}
}
