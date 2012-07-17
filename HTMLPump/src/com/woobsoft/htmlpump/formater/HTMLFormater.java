package com.woobsoft.htmlpump.formater;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.w3c.tidy.Tidy;

/**
 * <p>HTML源代码格式化。将其格式化为标准的XML</p>
 * @author 潘超
 * @date 2011-3-16 下午02:12:21
 */
public class HTMLFormater {
	
	private String inputEncoding = "GB2312";
	private String outputEncoding = "GB2312";
	private Tidy tidy = null;
	
	public HTMLFormater() {
		initTidy();
	}
	
	/**
	 * <p>设置输入的HTML源的编码，默认为GB2312</p>
	 * @param encoding
	 */
	public void setInputEncoding(String encoding) {
		this.inputEncoding = encoding;
	}
	
	/**
	 * <p>获取当前设置的输入的HTML源的编码</p>
	 * @return
	 */
	public String getInputEncoding() {
		return this.inputEncoding;
	}
	
	/**
	 * <p>设置输出的XML的编码，默认为GB2312</p>
	 * @param encoding
	 */
	public void setOutputEncoding(String encoding) {
		this.outputEncoding = encoding;
	}
	
	/**
	 * <p>获取当前设置的输出XML的编码</p>
	 * @return
	 */
	public String getOutputEncoding() {
		return this.outputEncoding;
	}

	private void initTidy() {
		tidy = new Tidy();
		tidy.setShowWarnings(false);
		tidy.setXmlOut(true);
		tidy.setDropFontTags(true);
		tidy.setDropEmptyParas(true);
//		tidy.setXmlPi(false);
//		tidy.setDocType("omit");
		tidy.setXHTML(false);
//		tidy.setRawOut(true);
//		tidy.setNumEntities(true);
		tidy.setQuoteNbsp(true);
		tidy.setQuoteMarks(false);
		tidy.setQuoteAmpersand(false);
		tidy.setFixComments(true);
		tidy.setFixBackslash(true);
		tidy.setMakeClean(true);
		tidy.setInputEncoding(inputEncoding);
		tidy.setOutputEncoding(outputEncoding);
	}
	
	/**
	 * <p>格式化HTML源代码为标准的XML</p>
	 * @param htmlSource 需要格式化的HTML源代码内容
	 * @return
	 * @throws Exception 
	 */
	public String format(String htmlSource) throws Exception {
		if (htmlSource == null || htmlSource.equals("") || !htmlSource.contains("html") ) {
			throw new Exception("格式化HTML源代码出错：不是有效的HTML源代码");
		}
		
		InputStream in = null;
		ByteArrayOutputStream byteOut = null; 
		String result = "";
		Document doc = null;
		
		try {
			// 输入的HTML源
			in = new ByteArrayInputStream(htmlSource.getBytes());
			// 存放格式化后的HTML
			byteOut = new ByteArrayOutputStream();
			// 转换
			tidy.parse(in, byteOut);
			result += byteOut.toString();
			
			// 去掉DOCTYPE元素
			result = clearDocType(result);
			// 去掉script元素
			result = clearScript(result);
			// 去掉HTML节点中的Base属性，避免加载为XML的DOM时出错
			result = clearHtmlBase(result);
			
			// 解析为DOM对象，验证XML数据正确性，并在XML头中设置编码
			doc = DocumentHelper.parseText(result);
			doc.setXMLEncoding(outputEncoding);
			result = doc.asXML();
		} catch (Exception e) {
			throw e;
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
		return result;
	}
	
	/**
	 * <p>去掉DOCTYPE元素</p>
	 * @param source
	 * @return
	 */
	private String clearDocType(String source) {
		int startIndex = 0;
		int endIndex = 0;
		if ((startIndex = source.indexOf("<!DOCTYPE")) >= 0) {
			endIndex = source.indexOf(">",startIndex);
			source = source.substring(0,startIndex) + 
			source.substring(endIndex + 1, source.length());
		}
		return source;
	}
	
	/**
	 * <p>去掉script元素</p>
	 * @param source
	 * @return
	 */
	private String clearScript(String source) {
		int startIndex = 0;
		int endIndex = 0;
		while ((startIndex = source.indexOf("<script")) >= 0) {
			endIndex = source.indexOf("</script>");
			source = source.substring(0,startIndex) +
			source.substring(endIndex + 9, source.length());
		}
		return source;
	}
	
	/**
	 * <p>去掉HTML节点中的Base属性，避免加载为XML的DOM时出错</p>
	 * @param source
	 * @return
	 */
	private String clearHtmlBase(String source) {
		String deleteAtt = "";
		String root_tag = "<html\\s*([^>]*)\\s*>";
		Pattern rootPattern = Pattern.compile(root_tag, Pattern.CASE_INSENSITIVE);  
        Matcher rootM = rootPattern.matcher(source);
        if (rootM.find()) {
        	deleteAtt = rootM.group(1);  //第1个group匹配的就是需要的字符编码  
        }
        return source.replace(deleteAtt, "");
	}
}
