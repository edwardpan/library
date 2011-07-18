package net.firecoder.expressionhelper.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>�Զ�����ַ���������</p>
 * @author �˳�
 * @date 2011-3-24 ����09:45:41
 */
public class StringFunctions {
	private Logger logger = LoggerFactory.getLogger(DateFunctions.class);
	
	/**
	 * <p>IKExpression���ʽ�������ĺ������滻�ַ����е�����</p>
	 * @param str �������ַ���
	 * @param replaceStr ��Ҫ���滻��ֵ
	 * @param replaceNewStr ���滻���ݵ���ֵ
	 * @return
	 */
	public String replace(String str, String replaceStr, String replaceNewStr) {
		if (str == null) {
			return "";
		}
		logger.debug("��ʼ�滻�ַ���" + str + "�е�" + replaceStr + "Ϊ" + replaceNewStr);
		return str.replace(replaceStr, replaceNewStr);
	}
	
	/**
	 * <p>IKExpression���ʽ�������ĺ������滻�ַ����е�һ�������ֵ�����</p>
	 * @param str �������ַ���
	 * @param replaceStr ��Ҫ���滻��ֵ
	 * @param replaceNewStr ���滻���ݵ���ֵ
	 * @return
	 */
	public String replaceFirst(String str, String replaceStr, String replaceNewStr) {
		if (str == null) {
			return "";
		}
		logger.debug("��ʼ�滻�ַ���" + str + "�������ĵ�һ��" + replaceStr + "Ϊ" + replaceNewStr);
		return str.replaceFirst(replaceStr, replaceNewStr);
	}
	
	/**
	 * <p>IKExpression���ʽ�������ĺ�����ȥ���ַ���ǰ��Ŀո�</p>
	 * @param str �������ַ���
	 * @return
	 */
	public String trim(String str) {
		if (str == null) {
			return "";
		}
		logger.debug("��ʼȥ���ַ���" + str + "ǰ��Ŀո�");
		return str.trim();
	}
	
	/**
	 * <p>IKExpression���ʽ�������ĺ�������ȡ�ַ���</p>
	 * @param str �������ַ���
	 * @param beginIndex ��ʼ��ȡ������
	 * @param endIndex ������ȡ������
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
		logger.debug("��ʼ���ַ���" + str + "�н�ȡ�ַ�������" + beginIndex + "��" + endIndex);
		return str.substring(beginIndex, endIndex);
	}
	
	/**
	 * <p>IKExpression���ʽ�������ĺ������ַ����е���ĸתСд����</p>
	 * @param str �������ַ���
	 * @return
	 */
	public String toLowerCase(String str) {
		if (str == null) {
			return "";
		}
		logger.debug("��ʼ���ַ����е���ĸת��ΪСд");
		return str.toLowerCase();
	}
	
	/**
	 * <p>IKExpression���ʽ�������ĺ������ַ����е���ĸת��д����</p>
	 * @param str �������ַ���
	 * @return
	 */
	public String toUpperCase(String str) {
		if (str == null) {
			return "";
		}
		logger.debug("��ʼ���ַ����е���ĸת��Ϊ��д");
		return str.toUpperCase();
	}
}
