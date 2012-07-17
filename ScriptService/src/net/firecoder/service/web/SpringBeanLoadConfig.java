/* SpringBeanLoadConfig.java
 * project: EnvirLims
 */
package net.firecoder.service.web;

/**
 * 配置初始化脚本服务时需要包含或排除的spring中的bean
 * @author 潘超
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
