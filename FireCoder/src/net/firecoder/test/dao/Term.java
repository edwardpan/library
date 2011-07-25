/* Term.java
 * project: FireCoder
 */
package net.firecoder.test.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体类属性可作为查询条件的注释
 * @author 潘超
 * create: 2011-7-25
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Term {
	/** 查询比对时对应的数据库字段名，如果不指定则默认使用被注释的实体类属性 */
	String field() default "";
}
