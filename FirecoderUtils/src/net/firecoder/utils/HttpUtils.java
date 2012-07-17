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
 * @author 潘超
 * create: 2011-9-17
 */
public class HttpUtils {
	
	private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
	
	/**
	 * 转换下载文件时要显示到客户端的中文名称编码，使其满足MIME格式，并考虑浏览器的不同
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
	 * 从容器的临时目录中获取指定名字的文件数据
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
			log.error("从容器临时目录中提取文件数据失败！", e);
		} catch (IOException e) {
			log.error("从容器临时目录中提取文件数据失败！", e);
		} finally {
			try {
				input.close();
			} catch (IOException e) {}
		}
		return data;
	}
	
	/**
	 * 保存文件数据到容器的临时目录中
	 * @param data 文件数据
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
			log.error("保存文件数据到容器临时目录失败！", e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {}
		}
		return file;
	}
}
