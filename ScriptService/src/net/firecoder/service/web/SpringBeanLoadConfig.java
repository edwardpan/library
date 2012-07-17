/* SpringBeanLoadConfig.java
 * project: EnvirLims
 */
package net.firecoder.service.web;

/**
 * ���ó�ʼ���ű�����ʱ��Ҫ�������ų���spring�е�bean
 * @author �˳�
 * create: 2011-9-9
 */
public class SpringBeanLoadConfig {
	public static final String[] includeBeans = new String[] {
		"dictManager"
	};
	
	public static final String[] excludeBeans = new String[] {
		"hibernateDao", "transactionBase"
	};
}
