package net.firecoder.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 安全处理类，包括加密算法
 * @author 潘超
 *
 */
public class Security {
	/** 16进制数据数组 */
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * MD5加密算法，返回大写字符串
	 * @param str 需要加密的字符串
	 * @return 已加密的字符串
	 */
	public static String MD5Encrypt(String str) {
		String resultString = null;
		try {
			resultString = new String(str);
			MessageDigest md = MessageDigest.getInstance("MD5");
			//加密，并转换为16进制
			resultString = byteArrayToString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	/**
	 * 将字符数组转换为16进制的加密数据结果
	 * @param b 字符数组
	 * @return 加密并转换完成后的数据
	 */
	public static String byteArrayToString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		//转换为16进制的加密结果，即数字字母混合的形式
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 转换16进制
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
	 * Base64加密方法
	 * @param value 需要加密的值
	 * @return 返回加密后的值
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
	 * Base64解密方法
	 * @param value 需要解密的值
	 * @return 返回解密后的值
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
