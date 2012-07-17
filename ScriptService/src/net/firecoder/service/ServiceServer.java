/* ServiceServer.java
 * project: EnvirLims
 */
package net.firecoder.service;

import java.util.Map;

/**
 * ��չ��������ӿ�
 * @author �˳�
 * create: 2011-9-6
 */
public interface ServiceServer {
	/**
	 * ��������
	 * @throws Exception
	 */
	public void start(Map<String, Object> publicBinding) throws Exception;
	/**
	 * ����װ�����棬����װ��������չ����
	 * @throws Exception
	 */
	public void restart(Map<String, Object> publicBinding) throws Exception;
	/**
	 * ֹͣ����
	 * @throws Exception
	 */
	public void stop() throws Exception;
}
