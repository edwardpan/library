/* PathUtils.java
 * project: EnvirLims
 */
package net.firecoder.utils;

import java.io.File;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 潘超
 * create: 2011-9-6
 */
public class WebPathUtils {
	
	private static final Logger log = LoggerFactory.getLogger(WebPathUtils.class);
	
	/**
	 * 获取系统根目录所在的真实位置
	 * @return 例：E:\tomcat\webapps\EnvirLims\
	 */
	public static String getRootRealPath() {
		String path = new File("/").getPath();
		try {
			path = WebPathUtils.class.getResource("").toURI().getPath();
			path = path.substring(0, path.indexOf("WEB-INF"));
		} catch (URISyntaxException e) {
			log.warn("系统根目录获取出错，使用默认位置：" + path);
		}
		return path;
	}
}
