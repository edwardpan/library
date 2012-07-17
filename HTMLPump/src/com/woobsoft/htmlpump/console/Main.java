package com.woobsoft.htmlpump.console;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.woobsoft.htmlpump.HTMLDataPump;
import com.woobsoft.htmlpump.exception.PumpRunException;
import com.woobsoft.htmlpump.impl.HTMLDataPumpImpl;

public class Main {

	private static String contentType = "text/html";
	
	public static void main(String[] args) {
		if (args == null || args.length < 3) {
			System.out.println("示例：java -jar HTMLPump.jar <Url> <XslPath> <DataXmlPath>");
			System.out.println("  <Url>         下载HTML源的http连接，例：http://www.woobsoft.com/");
			System.out.println("  <XslPath>     用于抽取数据的XSL样式表路径，例：toXml.xsl");
			System.out.println("  <DataXmlPath> 保存抽取到的数据的路径，例：data.xml");
			return;
		}
		
		String contentEncode = "GB2312";
		String htmlSource = downloadHTMLSource(args[0]);
		
		HTMLDataPump pump = new HTMLDataPumpImpl();
		pump.setInputEncoding(contentEncode);
		pump.setOutputEncoding(contentEncode);
		pump.setAutoEncoding(false);
		
		pump.setHTMLSource(htmlSource);
		pump.setXSLFile(args[1]);
		pump.setOutputFile(args[2]);
		
		try {
			pump.run();
		} catch (PumpRunException e) {
			System.out.println(e.getMessage());
		}
	}

	private static String downloadHTMLSource(String urlStr) {
		String htmlSource = "";
		URL url = null;
		URLConnection conn = null;
		InputStream in = null;
		ByteArrayOutputStream byteOut = null; 
		try {
			url = new URL(urlStr);
			conn = url.openConnection();
			conn.setRequestProperty("Accept-Language", "zh-CN");
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; CIBA)");
			conn.connect();
			contentType = conn.getContentType();
			if (!contentType.equals(conn.getContentType())) {
				return "";
			}
			in = conn.getInputStream();
			byteOut = new ByteArrayOutputStream();
			byte[] cache = new byte[16384];
			int length = 0;
			while ((length = in.read(cache)) > 0) {
				for (int i = 0; i < cache.length; i++) {
					byte b = cache[i];
					// Remove binary below space except tab and newline
					if (b < 32 && b != 10 && b != 13 && b != 9) {
						b = 32;
					}
				}
				byteOut.write(cache, 0, length);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {}
			}
			if (byteOut != null) {
				try {
					byteOut.close();
				} catch (IOException e) {}
			}
		}
		htmlSource = byteOut.toString();
		return htmlSource;
	}
}
