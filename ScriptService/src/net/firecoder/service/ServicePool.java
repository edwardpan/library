/* ServicePool.java
 * project: EnvirLims
 */
package net.firecoder.service;

/**
 * 扩展服务池
 * @author 潘超
 * create: 2011-9-6
 */
public interface ServicePool {
	/**
	 * 注册服务
	 * @param service
	 * @throws Exception
	 */
	public void registerService(Service service) throws Exception;
	
	/**
	 * 清除所有服务，一般用在重启或停止扩展服务引擎
	 */
	public void clearServices();
	
	/**
	 * 从池中获得一个已有扩展服务
	 * @param serviceName 扩展服务名称，区分大小写
	 * @return
	 */
	public Service getService(String serviceName);
}
