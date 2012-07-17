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
 * <p>HTMLԴ�����ʽ���������ʽ��Ϊ��׼��XML</p>
 * @author �˳�
 * @date 2011-3-16 ����02:12:21
 */
public class HTMLFormater {
	
	private String inputEncoding = "GB2312";
	private String outputEncoding = "GB2312";
	private Tidy tidy = null;
	
	public HTMLFormater() {
		initTidy();
	}
	
	/**
	 * <p>���������HTMLԴ�ı��룬Ĭ��ΪGB2312</p>
	 * @param encoding
	 */
	public void setInputEncoding(String encoding) {
		this.inputEncoding = encoding;
	}
	
	/**
	 * <p>��ȡ��ǰ���õ������HTMLԴ�ı���</p>
	 * @return
	 */
	public String getInputEncoding() {
		return this.inputEncoding;
	}
	
	/**
	 * <p>���������XML�ı��룬Ĭ��ΪGB2312</p>
	 * @param encoding
	 */
	public void setOutputEncoding(String encoding) {
		this.outputEncoding = encoding;
	}
	
	/**
	 * <p>��ȡ��ǰ���õ����XML�ı���</p>
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
	 * <p>��ʽ��HTMLԴ����Ϊ��׼��XML</p>
	 * @param htmlSource ��Ҫ��ʽ����HTMLԴ��������
	 * @return
	 * @throws Exception 
	 */
	public String format(String htmlSource) throws Exception {
		if (htmlSource == null || htmlSource.equals("") || !htmlSource.contains("html") ) {
			throw new Exception("��ʽ��HTMLԴ�������������Ч��HTMLԴ����");
		}
		
		InputStream in = null;
		ByteArrayOutputStream byteOut = null; 
		String result = "";
		Document doc = null;
		
		try {
			// �����HTMLԴ
			in = new ByteArrayInputStream(htmlSource.getBytes());
			// ��Ÿ�ʽ�����HTML
			byteOut = new ByteArrayOutputStream();
			// ת��
			tidy.parse(in, byteOut);
			result += byteOut.toString();
			
			// ȥ��DOCTYPEԪ��
			result = clearDocType(result);
			// ȥ��scriptԪ��
			result = clearScript(result);
			// ȥ��HTML�ڵ��е�Base���ԣ��������ΪXML��DOMʱ����
			result = clearHtmlBase(result);
			
			// ����ΪDOM������֤XML������ȷ�ԣ�����XMLͷ�����ñ���
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
	 * <p>ȥ��DOCTYPEԪ��</p>
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
	 * <p>ȥ��scriptԪ��</p>
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
	 * <p>ȥ��HTML�ڵ��е�Base���ԣ��������ΪXML��DOMʱ����</p>
	 * @param source
	 * @return
	 */
	private String clearHtmlBase(String source) {
		String deleteAtt = "";
		String root_tag = "<html\\s*([^>]*)\\s*>";
		Pattern rootPattern = Pattern.compile(root_tag, Pattern.CASE_INSENSITIVE);  
        Matcher rootM = rootPattern.matcher(source);
        if (rootM.find()) {
        	deleteAtt = rootM.group(1);  //��1��groupƥ��ľ�����Ҫ���ַ�����  
        }
        return source.replace(deleteAtt, "");
	}
}
