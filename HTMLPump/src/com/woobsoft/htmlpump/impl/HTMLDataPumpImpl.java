/* HTMLDataPumpImpl.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * �������ļ�
 * 
 * ==========================================================
 * ����: [2011-3-16 ����02:10:12] by �˳�
 * ============================================================ 
 */

package com.woobsoft.htmlpump.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.woobsoft.htmlpump.exception.PumpRunException;
import com.woobsoft.htmlpump.formater.HTMLFormater;
import com.woobsoft.htmlpump.transformer.XSLTransformer;


/**
 * <p>HTML���ݳ�ȡʵ����</p>
 * @author �˳�
 * @date 2011-3-16 ����02:10:12
 */
public class HTMLDataPumpImpl extends AbstractHTMLDataPump {

	@Override
	public void run() throws PumpRunException {
		if (xslStream == null) {
			throw new PumpRunException("������ȷ���ô��ڵ�XSL��ʽ���ļ���");
		}
		if (htmlSource == null || htmlSource.equals("") || !htmlSource.contains("html")) {
			throw new PumpRunException("�����������ڳ�ȡ���ݵ�HTMLԴ");
		}
		if (outputPath == null || outputPath.equals("")) {
			throw new PumpRunException("�����������������ȡ������ļ�·��");
		}
		
		// Ϊ�����XML�����ļ�����Ŀ¼
		File outFile = new File(outputPath);
		outFile.mkdirs();
		
		if (autoEncoding) {// ����Ƿ�Ҫ�Զ���ȡHTML�ĵ�����
			getEncoding();
		}
		
		// ��ʽ��HTMLԴ
		HTMLFormater htmlFormater = new HTMLFormater();
		htmlFormater.setInputEncoding(inputEncoding);
		htmlFormater.setOutputEncoding(outputEncoding);
		String xmlSource = "";
		try {
			xmlSource = htmlFormater.format(htmlSource);
		} catch (Exception e) {
			throw new PumpRunException(e);
		}
		
		// ��ȡ����
		XSLTransformer former = new XSLTransformer();
		try {
			// ����HTMLԴ
			former.setXMLString(xmlSource);
			// ����ת���õ�XSL
			former.setXSL(xslStream);
			// �������������XML·��
			former.setHTML(outputFile);
			former.transform();
		} catch (FileNotFoundException e) {
			throw new PumpRunException(e);
		} catch (Exception e) {
			throw new PumpRunException(e);
		}
	}

	private void getEncoding() {
		String contentEncode = "GB2312";
		String encoding_tag = "<meta[^;]+;\\s*charset\\s*=\\s*([^\"\\s]+)[^>]*>";
		Pattern encodingPattern = Pattern.compile(encoding_tag, Pattern.CASE_INSENSITIVE);  
        Matcher m = encodingPattern.matcher(htmlSource);
        if (m.find()) {  
        	contentEncode = m.group(1);  //��1��groupƥ��ľ�����Ҫ���ַ�����  
        }
        
        this.inputEncoding = contentEncode;
        this.outputEncoding = contentEncode;
	}
}
