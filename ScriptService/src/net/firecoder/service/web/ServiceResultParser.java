/* ServiceResultParser.java
 * project: EnvirLims
 */
package net.firecoder.service.web;

import net.sf.json.JSONArray;

/**
 * @author 潘超
 * create: 2011-9-10
 */
public class ServiceResultParser {
	public static String parserToJson(Object value) {
		String json = "";
		if (value != null) {// 解析服务执行结果
			if (value instanceof String) {// 是字符串
				json = (String) value;
			} else if (value instanceof StringBuffer || 
					value instanceof StringBuilder ||
					value.getClass().getSimpleName().indexOf("String") > -1) {// 是字符串
				json = value.toString();
			} else {// 是对象
				JSONArray arr = JSONArray.fromObject(value);
				json = arr.toString();
			}
		}
		return json;
	}
}
