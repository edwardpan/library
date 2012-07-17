package net.firecoder.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * ��ȫ�����࣬���������㷨
 * @author �˳�
 *
 */
public class Security {
	/** 16������������ */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * MD5�����㷨�����ش�д�ַ���
	 * @param str ��Ҫ���ܵ��ַ���
	 * @return �Ѽ��ܵ��ַ���
	 */
	public static String MD5Encrypt(String str) {
		String resultString = null;
		try {
			resultString = new String(str);
			MessageDigest md = MessageDigest.getInstance("MD5");
			//���ܣ���ת��Ϊ16����
			resultString = byteArrayToString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	/**
	 * ���ַ�����ת��Ϊ16���Ƶļ������ݽ��
	 * @param b �ַ�����
	 * @return ���ܲ�ת����ɺ������
	 */
	public static String byteArrayToString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		//ת��Ϊ16���Ƶļ��ܽ������������ĸ��ϵ���ʽ
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * ת��16����
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * Base64���ܷ���
	 * @param value ��Ҫ���ܵ�ֵ
	 * @return ���ؼ��ܺ��ֵ
	 */
	public static String BASE64Encrypt(String value) {
		try {
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(value.getBytes("GBK"));
		} catch (UnsupportedEncodingException ex) {
			return null;
		}
	}

	/**
	 * Base64���ܷ���
	 * @param value ��Ҫ���ܵ�ֵ
	 * @return ���ؽ��ܺ��ֵ
	 */
	public static String BASE64Decoder(String value) {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytes = decoder.decodeBuffer(value);
			return new String(bytes, "GBK");
		} catch (IOException ex) {
			return null;
		}
	}
}
