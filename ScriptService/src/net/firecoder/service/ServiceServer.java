/* ServiceServer.java
 * project: EnvirLims
 */
package net.firecoder.service;

import java.util.Map;

/**
 * 扩展服务引擎接口
 * @author 潘超
 * create: 2011-9-6
 */
public interface ServiceServer {
	/**
	 * 启动引擎
	 * @throws Exception
	 */
	public void start(Map<String, Object> publicBinding) throws Exception;
	/**
	 * 重新装载引擎，重新装载所有扩展服务
	 * @throws Exception
	 */
	public void restart(Map<String, Object> publicBinding) throws Exception;
	/**
	 * 停止引擎
	 * @throws Exception
	 */
	public void stop() throws Exception;
}
