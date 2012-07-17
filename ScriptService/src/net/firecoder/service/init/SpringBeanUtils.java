/* SpringBeanUtils.java
 * project: EnvirLims
 */
package net.firecoder.service.init;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

/**
 * @author 潘超
 * create: 2011-9-9
 */
public class SpringBeanUtils {
	/**
	 * 获取Spring中已初始化的Bean
	 * @param context
	 * @return
	 */
	public static Map<String, Object> getSpringBeans(ApplicationContext context){
		return getExcludeSpringBeans(context, SpringBeanLoadConfig.excludeBeans);
	}
	
	/**
	 * 获取Spring中已初始化的Bean，通过需要获取的Bean的名称
	 * @param context
	 * @param includeBeanNames 需要获取的Bean的名称
	 * @return
	 */
	public static Map<String, Object> getIncludeSpringBeans(
			ApplicationContext context, String[] includeBeanNames){
		Map<String, Object> beans = new LinkedHashMap<String, Object>();
		if (includeBeanNames != null) {
			for (String beanName : includeBeanNames) {
				beans.put(beanName, context.getBean(beanName));
			}
		}
		return beans;
	}
	
	/**
	 * 获取Spring中已初始化的Bean，通过需要排除的Bean的名称
	 * @param context
	 * @param excludeBeanNames 需要排除的Bean的名称
	 * @return
	 */
	public static Map<String, Object> getExcludeSpringBeans(
			ApplicationContext context, String[] excludeBeanNames){
		Map<String, Object> beans = new LinkedHashMap<String, Object>();
		String[] names = context.getBeanDefinitionNames();
		if (names != null) {
			boolean isRemove = false;
			for (String beanName : names) {
				isRemove = false;
				// 查找是否为需要排除的Bean
				if (excludeBeanNames != null) {
					for (String excludeBeanName : excludeBeanNames) {
						if (beanName.equals(excludeBeanName)) {
							isRemove = true;// 排除
							break;
						}
					}
				}
				if (!isRemove) {
					Object bean = context.getBean(beanName);
					beans.put(beanName, bean);
				}
			}
		}
		return beans;
	}
}
