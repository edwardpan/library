/* ServiceResultParser.java
 * project: EnvirLims
 */
package net.firecoder.service.web;

import net.sf.json.JSONArray;

/**
 * @author �˳�
 * create: 2011-9-10
 */
public class ServiceResultParser {
	public static String parserToJson(Object value) {
		String json = "";
		if (value != null) {// ��������ִ�н��
			if (value instanceof String) {// ���ַ���
				json = (String) value;
			} else if (value instanceof StringBuffer || 
					value instanceof StringBuilder ||
					value.getClass().getSimpleName().indexOf("String") > -1) {// ���ַ���
				json = value.toString();
			} else {// �Ƕ���
				JSONArray arr = JSONArray.fromObject(value);
				json = arr.toString();
			}
		}
		return json;
	}
}
