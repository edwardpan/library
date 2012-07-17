package test.com.woobsoft.htmlpump;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

import com.woobsoft.htmlpump.HTMLDataPump;
import com.woobsoft.htmlpump.exception.PumpRunException;
import com.woobsoft.htmlpump.impl.HTMLDataPumpImpl;

public class HTMLDataPumpTest {

	@Test
	public void testRun() {
		URL url = null;
		URLConnection conn = null;
		InputStream in = null;
		ByteArrayOutputStream byteOut = null; 
		String contentType = "text/html";
		String contentEncode = "GB2312";
		
		try {
			url = new URL("http://search.51job.com/list/%2B,%2B,%2B,%2B,%2B,%2B,%25B3%25C9%25B6%25BC,2,%2B.html?lang=c&stype=1&image_x=0&image_y=0");
			conn = url.openConnection();
			conn.setRequestProperty("Accept-Language", "zh-CN");
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; CIBA)");
			conn.connect();
			contentType = conn.getContentType();
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
		
		HTMLDataPump pump = new HTMLDataPumpImpl();
		pump.setInputEncoding(contentEncode);
		pump.setOutputEncoding(contentEncode);
		pump.setAutoEncoding(false);
		
		pump.setHTMLSource(byteOut.toString());
		pump.setXSLFile("E:/temp/jobToXmlTest.xsl");
		pump.setOutputFile("E:/temp/jobDataTest.xml");
		
		try {
			pump.run();
		} catch (PumpRunException e) {
			e.printStackTrace();
		}
	}

}
