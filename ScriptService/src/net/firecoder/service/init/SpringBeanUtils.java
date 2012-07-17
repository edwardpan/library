/* SpringBeanUtils.java
 * project: EnvirLims
 */
package net.firecoder.service.init;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

/**
 * @author �˳�
 * create: 2011-9-9
 */
public class SpringBeanUtils {
	/**
	 * ��ȡSpring���ѳ�ʼ����Bean
	 * @param context
	 * @return
	 */
	public static Map<String, Object> getSpringBeans(ApplicationContext context){
		return getExcludeSpringBeans(context, SpringBeanLoadConfig.excludeBeans);
	}
	
	/**
	 * ��ȡSpring���ѳ�ʼ����Bean��ͨ����Ҫ��ȡ��Bean������
	 * @param context
	 * @param includeBeanNames ��Ҫ��ȡ��Bean������
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
	 * ��ȡSpring���ѳ�ʼ����Bean��ͨ����Ҫ�ų���Bean������
	 * @param context
	 * @param excludeBeanNames ��Ҫ�ų���Bean������
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
				// �����Ƿ�Ϊ��Ҫ�ų���Bean
				if (excludeBeanNames != null) {
					for (String excludeBeanName : excludeBeanNames) {
						if (beanName.equals(excludeBeanName)) {
							isRemove = true;// �ų�
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
