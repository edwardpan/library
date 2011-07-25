/* Term.java
 * project: FireCoder
 */
package net.firecoder.test.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ʵ�������Կ���Ϊ��ѯ������ע��
 * @author �˳�
 * create: 2011-7-25
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Term {
	/** ��ѯ�ȶ�ʱ��Ӧ�����ݿ��ֶ����������ָ����Ĭ��ʹ�ñ�ע�͵�ʵ�������� */
	String field() default "";
}
