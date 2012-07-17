package net.firecoder.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ReflectionUtils {
	private static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

	public static void setFieldValue(Object target, String fname, Class<?> ftype, Object fvalue) {
		setFieldValue(target, target.getClass(), fname, ftype, fvalue);
	}

	public static void setFieldValue(Object target, Class<?> clazz, String fname, Class<?> ftype, Object fvalue) {
		if (target == null || fname == null || "".equals(fname)
				|| (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
			return;
		}

		try {
			Method method = clazz.getDeclaredMethod(
					"set" + Character.toUpperCase(fname.charAt(0)) + fname.substring(1), ftype);
			//if (!Modifier.isPublic(method.getModifiers())) {
			method.setAccessible(true);
			//}
			method.invoke(target, fvalue);
		} catch (Exception me) {
			if (logger.isDebugEnabled()) {
				logger.debug("", me);
			}
			try {
				Field field = clazz.getDeclaredField(fname);
				//if (!Modifier.isPublic(field.getModifiers())) {
				field.setAccessible(true);
				//}
				field.set(target, fvalue);
			} catch (Exception fe) {
				if (logger.isDebugEnabled()) {
					logger.debug("", fe);
				}
			}
		}
	}

	public static Object getFieldValue(Object target, String fname) {
		return getFieldValue(target, target.getClass(), fname);
	}

	public static Object getFieldValue(Object target, Class<?> clazz, String fname) {
		if (target == null || fname == null || "".equals(fname)) {
			return null;
		}

		boolean exCatched = false;
		try {
			String methodname = "get" + StringUtils.capitalize(fname);
			Method method = clazz.getDeclaredMethod(methodname);
			//if (!Modifier.isPublic(method.getModifiers())) {
			method.setAccessible(true);
			//}
			return method.invoke(target);
		} catch (NoSuchMethodException e) {
			exCatched = true;
		} catch (InvocationTargetException e) {
			exCatched = true;
		} catch (IllegalAccessException e) {
			exCatched = true;
		}

		if (exCatched) {
			try {
				Field field = clazz.getDeclaredField(fname);
				//if (!Modifier.isPublic(field.getModifiers())) {
				field.setAccessible(true);
				//}
				return field.get(target);
			} catch (Exception fe) {
				if (logger.isDebugEnabled()) {
					logger.debug("", fe);
				}
			}
		}
		return null;
	}

}
