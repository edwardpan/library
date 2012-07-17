/* PathUtils.java
 * project: EnvirLims
 */
package net.firecoder.utils;

import java.io.File;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author �˳�
 * create: 2011-9-6
 */
public class WebPathUtils {
	
	private static final Logger log = LoggerFactory.getLogger(WebPathUtils.class);
	
	/**
	 * ��ȡϵͳ��Ŀ¼���ڵ���ʵλ��
	 * @return ����E:\tomcat\webapps\EnvirLims\
	 */
	public static String getRootRealPath() {
		String path = new File("/").getPath();
		try {
			path = WebPathUtils.class.getResource("").toURI().getPath();
			path = path.substring(0, path.indexOf("WEB-INF"));
		} catch (URISyntaxException e) {
			log.warn("ϵͳ��Ŀ¼��ȡ����ʹ��Ĭ��λ�ã�" + path);
		}
		return path;
	}
}
