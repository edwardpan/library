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
 * @author �˳�
 * @date Jul 18, 2012
 */
public class GroovyScriptHelper {
	private static final Logger log = LoggerFactory.getLogger(GroovyScriptHelper.class);
	private Service service;
	
	public GroovyScriptHelper(Service service) {
		this.service = service;
	}
	
	/**
	 * �����ڲ��ű�
	 * @param alias ����
	 * @param scriptName ���ƣ��磺convertDate(.groovy)�����������ź������е�����
	 * @throws Exception 
	 */
	public void include(String alias, String scriptName) throws Exception {
		if (alias == null || alias.equals("")) {
			alias = "iscript_" + scriptName;
		}
		String scriptEntrance = service.getServiceName() + "/" + scriptName + ".groovy";
		log.debug("���õĽű���" + scriptEntrance);
		try {
			Script script = service.getGSE().createScript(scriptEntrance, service.getBinding());
			service.putVariable(alias, script);
			log.debug("�ű����óɹ���" + scriptEntrance);
		} catch (Exception e) {
			throw new Exception("���ýű�>>" + scriptName + "<<����", e);
		}
	}
	
	/**
	 * �����ⲿ�ű�
	 * @param alias ����
	 * @param service ��Ҫ������ⲿ�ű����ڷ���
	 * @param scriptName ��Ҫ������ⲿ�ű����ƣ��磺convertDate(.groovy)�����������ź������е�����
	 * @throws Exception 
	 */
	public void include(String alias, String serviceName, String scriptName) throws Exception {
		if (alias == null || alias.equals("")) {
			alias = "oscript_" + serviceName + "_" + scriptName;
		}
		String scriptEntrance = serviceName + "/" + scriptName + ".groovy";
		log.debug("���õĽű���" + scriptEntrance);
		try {
			Script script = service.getGSE().createScript(scriptEntrance, service.getBinding());
			service.putVariable(alias, script);
			log.debug("�ű����óɹ���" + scriptEntrance);
		} catch (Exception e) {
			throw new Exception("���ýű�>>" + serviceName + "." + scriptName + "<<����", e);
		}
	}
}
