/* HttpUtils.java
 * project: EnvirLims
 */
package net.firecoder.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * @author �˳�
 * create: 2011-9-17
 */
public class HttpUtils {
	
	private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
	
	/**
	 * ת�������ļ�ʱҪ��ʾ���ͻ��˵��������Ʊ��룬ʹ������MIME��ʽ��������������Ĳ�ͬ
	 * @param filename
	 * @param request
	 * @return
	 */
	public static String getFileName(String filename, HttpServletRequest request){
        String agent = request.getHeader("USER-AGENT");
        if (null != agent && -1 != agent.indexOf("MSIE")){
            try {
				filename = URLEncoder.encode(filename, "UTF-8");
			} catch (UnsupportedEncodingException e) {}
        } else if(null != agent && -1 != agent.indexOf("Mozilla")){
            try {
				filename = MimeUtility.encodeText(filename, "UTF-8", "B");
			} catch (UnsupportedEncodingException e) {}
        }
        return filename;
	}
	
	/**
	 * ����������ʱĿ¼�л�ȡָ�����ֵ��ļ�����
	 * @param tmpFileName
	 * @param request
	 * @return
	 */
	public static byte[] getServletContextTempFile(String tmpFileName, HttpServletRequest request) {
		byte[] data = null;
		InputStream input = null;
		try {
			ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
			input = new FileInputStream(
					new File(
							request.getServletContext().getAttribute(ServletContext.TEMPDIR) + 
							"/" + tmpFileName));
			int length = 0;
			byte[] buffer = new byte[2048];
			while ( (length = input.read(buffer)) > -1 ){
				byteArrOut.write(buffer, 0, length);
			}
			data = byteArrOut.toByteArray();
		} catch (FileNotFoundException e) {
			log.error("��������ʱĿ¼����ȡ�ļ�����ʧ�ܣ�", e);
		} catch (IOException e) {
			log.error("��������ʱĿ¼����ȡ�ļ�����ʧ�ܣ�", e);
		} finally {
			try {
				input.close();
			} catch (IOException e) {}
		}
		return data;
	}
	
	/**
	 * �����ļ����ݵ���������ʱĿ¼��
	 * @param data �ļ�����
	 * @param request
	 * @return
	 */
	public static File saveServletContextTempFile(byte[] data, HttpServletRequest request) {
		Object tempDir = request.getServletContext().getAttribute(ServletContext.TEMPDIR);
		File file = new File(tempDir + "/" + UUID.randomUUID().toString());
		OutputStream out = null;
		try{
			out = new FileOutputStream(file);
			out.write(data);
		} catch (Exception e) {
			log.error("�����ļ����ݵ�������ʱĿ¼ʧ�ܣ�", e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {}
		}
		return file;
	}
}
