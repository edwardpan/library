package net.firecoder.expressionhelper.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>自定义的字符串处理函数</p>
 * @author 潘超
 * @date 2011-3-24 上午09:45:41
 */
public class StringFunctions {
	private Logger logger = LoggerFactory.getLogger(DateFunctions.class);
	
	/**
	 * <p>IKExpression表达式解析器的函数：替换字符串中的内容</p>
	 * @param str 操作的字符串
	 * @param replaceStr 需要被替换的值
	 * @param replaceNewStr 被替换部份的新值
	 * @return
	 */
	public String replace(String str, String replaceStr, String replaceNewStr) {
		if (str == null) {
			return "";
		}
		logger.debug("开始替换字符串" + str + "中的" + replaceStr + "为" + replaceNewStr);
		return str.replace(replaceStr, replaceNewStr);
	}
	
	/**
	 * <p>IKExpression表达式解析器的函数：替换字符串中第一个被发现的内容</p>
	 * @param str 操作的字符串
	 * @param replaceStr 需要被替换的值
	 * @param replaceNewStr 被替换部份的新值
	 * @return
	 */
	public String replaceFirst(String str, String replaceStr, String replaceNewStr) {
		if (str == null) {
			return "";
		}
		logger.debug("开始替换字符串" + str + "中遇到的第一个" + replaceStr + "为" + replaceNewStr);
		return str.replaceFirst(replaceStr, replaceNewStr);
	}
	
	/**
	 * <p>IKExpression表达式解析器的函数：去掉字符串前后的空格</p>
	 * @param str 操作的字符串
	 * @return
	 */
	public String trim(String str) {
		if (str == null) {
			return "";
		}
		logger.debug("开始去除字符串" + str + "前后的空格");
		return str.trim();
	}
	
	/**
	 * <p>IKExpression表达式解析器的函数：截取字符串</p>
	 * @param str 操作的字符串
	 * @param beginIndex 开始截取的索引
	 * @param endIndex 结束截取的索引
	 * @return
	 */
	public String substring(String str, int beginIndex, int endIndex) {
		if (str == null) {
			return "";
		}
		if (beginIndex > str.length() - 1 || beginIndex < 0) {
			beginIndex = 0;
		}
		if (endIndex > str.length() - 1 || endIndex < 0) {
			endIndex = str.length();
		}
		logger.debug("开始从字符串" + str + "中截取字符串，从" + beginIndex + "到" + endIndex);
		return str.substring(beginIndex, endIndex);
	}
	
	/**
	 * <p>IKExpression表达式解析器的函数：字符串中的字母转小写函数</p>
	 * @param str 操作的字符串
	 * @return
	 */
	public String toLowerCase(String str) {
		if (str == null) {
			return "";
		}
		logger.debug("开始将字符串中的字母转换为小写");
		return str.toLowerCase();
	}
	
	/**
	 * <p>IKExpression表达式解析器的函数：字符串中的字母转大写函数</p>
	 * @param str 操作的字符串
	 * @return
	 */
	public String toUpperCase(String str) {
		if (str == null) {
			return "";
		}
		logger.debug("开始将字符串中的字母转换为大写");
		return str.toUpperCase();
	}
}
