/* Service.java
 * project: EnvirLims
 */
package net.firecoder.service;

import groovy.lang.Binding;

/**
 * ��չ����
 * @author �˳�
 * create: 2011-9-6
 */
public interface Service {
	/**
	 * ��ȡ������
	 * @return
	 */
	public String getServiceName();
	/**
	 * ��ȡ��Ҫ������չ����ű��Ļ�������
	 * @return
	 */
	public Binding getBinding();
	/**
	 * ��ʱ������չ����ű�����ʱ��Ҫ�Ļ�������
	 * @param name ������
	 * @param value �������ݣ�������һ������
	 * @throws Exception
	 */
	public void putVariable(String name, Object value);
	/**
	 * ��ȡ�����õ���չ����ű���������
	 * @param name
	 * @return
	 */
	public Object getVariable(String name);
	/**
	 * ִ����չ����ű�
	 * @return
	 * @throws Exception
	 */
	public Object call() throws Exception;
	/**
	 * ��ʼ������
	 */
	public void initBinding();
}
