/* ServicePool.java
 * project: EnvirLims
 */
package net.firecoder.service;

/**
 * ��չ�����
 * @author �˳�
 * create: 2011-9-6
 */
public interface ServicePool {
	/**
	 * ע�����
	 * @param service
	 * @throws Exception
	 */
	public void registerService(Service service) throws Exception;
	
	/**
	 * ������з���һ������������ֹͣ��չ��������
	 */
	public void clearServices();
	
	/**
	 * �ӳ��л��һ��������չ����
	 * @param serviceName ��չ�������ƣ����ִ�Сд
	 * @return
	 */
	public Service getService(String serviceName);
}
