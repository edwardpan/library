package net.firecoder.expressionhelper.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>�Զ����ʱ�䴦����</p>
 *
 * @author �˳�
 * @date 2011-3-23 ����09:13:20
 */
public class DateFunctions {
	private Logger logger = LoggerFactory.getLogger(DateFunctions.class);
	
	private List<String> dateFormatList = new ArrayList<String>();
	
	public DateFunctions(){
		dateFormatList.add("yyyy-MM-dd");
		dateFormatList.add("yyyy-MM-dd HH:mm:ss");
		dateFormatList.add("yyyy-MM-dd HH:mm:ss.SSS");
		dateFormatList.add("yyyy-MM-dd HH:mm:ss:SSS");
		dateFormatList.add("yyyy-M-d");
		dateFormatList.add("yyyy-M-d HH:mm:ss");
		dateFormatList.add("yyyy-M-d HH:mm:ss.SSS");
		dateFormatList.add("yyyy-M-d HH:mm:ss:SSS");
		dateFormatList.add("yyyy/MM/dd");
		dateFormatList.add("yyyy/MM/dd HH:mm:ss");
		dateFormatList.add("yyyy/MM/dd HH:mm:ss.SSS");
		dateFormatList.add("yyyy/MM/dd HH:mm:ss:SSS");
		dateFormatList.add("yyyy/M/d");
		dateFormatList.add("yyyy/M/d HH:mm:ss");
		dateFormatList.add("yyyy/M/d HH:mm:ss.SSS");
		dateFormatList.add("yyyy/M/d HH:mm:ss:SSS");
		dateFormatList.add("yyyyMMdd");
		dateFormatList.add("yyyyMMdd HH:mm:ss");
		dateFormatList.add("yyyyMMdd HH:mm:ss.SSS");
		dateFormatList.add("yyyyMMdd HH:mm:ss:SSS");
		dateFormatList.add("yyyyMd");
		dateFormatList.add("yyyyMd HH:mm:ss");
		dateFormatList.add("yyyyMd HH:mm:ss.SSS");
		dateFormatList.add("yyyyMd HH:mm:ss:SSS");
		dateFormatList.add("yyyy-MM-dd-HH:mm:ss");
		dateFormatList.add("yyyy-MM-dd-HH:mm:ss.SSS");
		dateFormatList.add("yyyy-MM-dd-HH:mm:ss:SSS");
		dateFormatList.add("yyyy-MM-dd-HH-mm-ss");
		dateFormatList.add("yyyy-MM-dd-HH-mm-ss.SSS");
		dateFormatList.add("yyyy-MM-dd-HH-mm-ss:SSS");
		dateFormatList.add("yyyy-M-d-HH:mm:ss");
		dateFormatList.add("yyyy-M-d-HH:mm:ss.SSS");
		dateFormatList.add("yyyy-M-d-HH:mm:ss:SSS");
		dateFormatList.add("yyyy-M-d-HH-mm-ss");
		dateFormatList.add("yyyy-M-d-HH-mm-ss.SSS");
		dateFormatList.add("yyyy-M-d-HH-mm-ss:SSS");
		dateFormatList.add("yyyyMMddHH:mm:ss");
		dateFormatList.add("yyyyMMddHH:mm:ss.SSS");
		dateFormatList.add("yyyyMMddHH:mm:ss:SSS");
	}
	
	/**
	 * <p>IKExpression���ʽ�������ĺ��������ڸ�ʽ�任</p>
	 * @param dateStr ԭ�����ַ���
	 * @param oldFormatStr �ɵĸ�ʽ
	 * @param newFormatStr �µĸ�ʽ
	 * @return
	 */
	public String convertDateFormat(String dateStr, String oldFormatStr, String newFormatStr) {
		SimpleDateFormat format = new SimpleDateFormat(oldFormatStr);
		Date date = null;
		String newDateStr = dateStr;
		if (dateStr != null && !dateStr.trim().equals("")) {
			try{
				logger.debug("��ʼʶ�������ʱ���ʽ");
				date = format.parse(String.valueOf(dateStr));
				format = new SimpleDateFormat(newFormatStr);
				logger.debug("��ʼ������ʱ�����ת��Ϊ�¸�ʽ");
				newDateStr = format.format(date);
			} catch (ParseException ex){
				logger.warn("���ڸ�ʽת������ִ��ʧ�ܣ�" + ex.getMessage() + "����ʽ��" + oldFormatStr + " �� " + newFormatStr);
			}
		}
		return newDateStr;
	}
	
	/**
	 * <p>IKExpression���ʽ�������ĺ��������ַ���������ʱ�����Ϊ����</p>
	 * @param dateStr ��Ҫʶ�𲢽���������ʱ���ַ���
	 * @return ����ʱ�����java.util.Date
	 */
	public Date convertStrToDate(String dateStr){
		Date date = null;
		SimpleDateFormat format = null;
		
		logger.debug("ʶ������ʱ���ַ�����ʽ��");
		String formatStr = readDateFormat(dateStr);
		logger.debug("ʶ������ʱ���ַ�����ʽΪ��" + formatStr);
		try {
			format = new SimpleDateFormat(formatStr);
			date = format.parse(dateStr);
			logger.debug("����ʱ���ַ��������ɹ����ַ�����" + dateStr + "����ʽ��" + format);
		} catch (ParseException ex){
			logger.warn("����ʱ���ַ�������ʧ�ܣ�" + ex.getMessage());
		}
		
		return date;
	}
	
	/**
	 * <p>����ƥ������ʱ���ַ����ĸ�ʽ</p>
	 * @param dateStr ��Ҫʶ��/���ҵ�����ʱ���ַ���
	 * @return ��ʽ���磺yyyy-MM-dd HH:mm:ss
	 */
	public String readDateFormat(String dateStr){
		String realFormatStr = "yyyy-MM-dd HH:mm:ss";
		boolean success = false;
		SimpleDateFormat format = null;
		for (String formatStr : dateFormatList) {
			logger.debug("ƥ������ʱ���ʽ��" + formatStr);
			try {
				format = new SimpleDateFormat(formatStr);
				format.parse(dateStr);
				success = true;
			} catch (ParseException ex){
				success = false;
			}
			if (success){
				logger.debug("����ʱ���ʽƥ��ɹ���" + formatStr);
				realFormatStr = formatStr;
				break;
			}
		}
		return realFormatStr;
	}
	
	/**
	 * IKExpression���ʽ�������ĺ���������ת��Ϊ�ַ�������
	 * @param date ���ڶ���
	 * @param format ��Ҫת��Ϊ���ַ�����ʽ������SimpleDateFormat���й��ڸ�ʽ��˵��
	 * @return
	 */
	public String convertDateToString(Date date, String format) {
		if (date == null) {
			logger.debug("����ʱ�����Ϊ�գ�δת��");
			return "";
		}
		logger.debug("��ʼת�����ڶ��󣬸�ʽ��" + format);
		SimpleDateFormat dateFormater = new SimpleDateFormat(format);
		return dateFormater.format(date);
	}
}
