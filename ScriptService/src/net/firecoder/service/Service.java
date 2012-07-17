/* Service.java
 * project: EnvirLims
 */
package net.firecoder.service;

import groovy.lang.Binding;

/**
 * 扩展服务
 * @author 潘超
 * create: 2011-9-6
 */
public interface Service {
	/**
	 * 获取服务名
	 * @return
	 */
	public String getServiceName();
	/**
	 * 获取将要传入扩展服务脚本的环境变量
	 * @return
	 */
	public Binding getBinding();
	/**
	 * 临时设置扩展服务脚本运行时需要的环境变量
	 * @param name 变量名
	 * @param value 变量内容，可能是一个对象
	 * @throws Exception
	 */
	public void putVariable(String name, Object value);
	/**
	 * 获取已设置的扩展服务脚本环境变量
	 * @param name
	 * @return
	 */
	public Object getVariable(String name);
	/**
	 * 执行扩展服务脚本
	 * @return
	 * @throws Exception
	 */
	public Object call() throws Exception;
	/**
	 * 初始化变量
	 */
	public void initBinding();
}
