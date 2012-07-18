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
 * @author �˳�
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
	 * ����ű������
	 * @param serviceName �����������
	 * @param gse �ű�����
	 * @param publicBinding Ĭ�ϰ󶨵���Դ
	 */
	public GroovyServiceImpl(String serviceName, GroovyScriptEngine gse, Map<String, Object> publicBinding) {
		this.serviceName = serviceName;
		this.gse = gse;
		this.publicBinding = publicBinding;
		initBinding();
		this.scriptEntrance = serviceName + "/" + serviceName + ".groovy";
		log.debug("��չ�����ʼ����" + serviceName);
	}
	
	@Override
	public void initBinding() {
		this.binding = new Binding();
		
		// ��ʼ���������ʱ�������Դ
		Iterator<String> it = publicBinding.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			binding.setVariable(key, publicBinding.get(key));
		}
		
		// ��ʼ��������Դ
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
				log.debug("������չ����" + serviceName);
				Script script = gse.createScript(scriptEntrance, binding);
				result = script.run();
				log.debug("��չ����" + serviceName + "���н����" + result);
			} catch (Exception e) {
				log.debug(e.getMessage(), e);
				throw new Exception("��չ����" + serviceName + "ִ�г���", e);
			} finally {
				// ���������Ϻ����ó�ʼ��״̬Ϊtrue����ʾ���֮ǰ��������ʱ��ע���������Դ
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
