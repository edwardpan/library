/* XSLTransformer.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 
 * ==========================================================
 * ����: [2010-6-28 ����01:44:00] by �˳� 
 * ============================================================ 
 */

package com.woobsoft.htmlpump.transformer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * XML+XSL����ΪHTML�ļ��Ĵ�����
 * @author �˳�
 * @date 2010-6-28 ����01:44:00
 */
public class XSLTransformer {
	/** ��Ҫ������XML���� */
	private InputStream xmlStream;
	/** ����ʱʹ�õ�XSL��ʽ�� */
	private InputStream xslStream;
	/** �����������HTML�ļ� */
	private OutputStream htmlStream;
	
	/**
	 * <p>������Ҫ������XML����</p>
	 * @param xmlFile XML���ݵ��ļ�����·��
	 * @throws FileNotFoundException
	 */
	public void setXML(String xmlFile) throws FileNotFoundException{
		if (null == xmlFile || "".equals(xmlFile)){
			return;
		}
		xmlStream = new FileInputStream(xmlFile);
	}
	
	/**
	 * <p>������Ҫ������XML����</p>
	 * @param xmlFile XML������
	 */
	public void setXML(InputStream xmlFile){
		if (null == xmlFile){
			return;
		}
		xmlStream = xmlFile;
	}
	
	/**
	 * <p>������Ҫ������XML����</p>
	 * @param xmlFile XML�����ļ�
	 * @throws FileNotFoundException
	 */
	public void setXML(File xmlFile) throws FileNotFoundException{
		if (null == xmlFile || !xmlFile.exists()){
			return;
		}
		xmlStream = new FileInputStream(xmlFile);
	}
	
	/**
	 * <p>������Ҫ������XML����</p>
	 * @param xmlStr XML���ݵ��ַ�������
	 */
	public void setXMLString(String xmlStr){
		if (null == xmlStr || "".equals(xmlStr)){
			return;
		}
		xmlStream = new ByteArrayInputStream(xmlStr.getBytes());
	}
	
	/**
	 * <p>���ý�����Ҫ��XSL��ʽ��</p>
	 * @param xslFile XSL��ʽ���ļ�������·��
	 * @throws FileNotFoundException
	 */
	public void setXSL(String xslFile) throws FileNotFoundException{
		if (null == xslFile || "".equals(xslFile)){
			return;
		}
		xslStream = new FileInputStream(xslFile);
	}
	
	/**
	 * <p>���ý�����Ҫ��XSL��ʽ��</p>
	 * @param xslFile XSL��ʽ���ļ���
	 */
	public void setXSL(InputStream xslFile) {
		if (null == xslFile){
			return;
		}
		xslStream = xslFile;
	}
	
	/**
	 * <p>���ý�����Ҫ��XSL��ʽ��</p>
	 * @param xslFile XSL��ʽ���ļ�
	 * @throws FileNotFoundException
	 */
	public void setXSL(File xslFile) throws FileNotFoundException{
		if (null == xslFile || !xslFile.exists()){
			return;
		}
		xslStream = new FileInputStream(xslFile);
	}
	
	/**
	 * <p>���ý�����Ҫ��XSL��ʽ��</p>
	 * @param xslStr XSL��ʽ�������ַ���
	 */
	public void setXSLString(String xslStr){
		if (null == xslStr || "".equals(xslStr)){
			return;
		}
		xslStream = new ByteArrayInputStream(xslStr.getBytes());
	}
	
	/**
	 * <p>���ý����������HTML�ļ���λ��</p>
	 * @param htmlFile HTML�ļ�������·��
	 * @throws FileNotFoundException
	 */
	public void setHTML(String htmlFile) throws FileNotFoundException{
		if (null == htmlFile || "".equals(htmlFile)){
			return;
		}
		htmlStream = new FileOutputStream(htmlFile);
	}
	
	/**
	 * <p>���ý����������HTML�ļ���λ��</p>
	 * @param htmlFile HTML�ļ�������·��
	 * @throws FileNotFoundException
	 */
	public void setHTML(OutputStream htmlFile){
		if (null == htmlFile){
			return;
		}
		htmlStream = htmlFile;
	}
	
	/**
	 * <p>���ý����������HTML�ļ���λ��</p>
	 * @param htmlFile HTML�ļ�������·��
	 * @throws FileNotFoundException 
	 * @throws FileNotFoundException
	 */
	public void setHTML(File htmlFile) throws FileNotFoundException{
		if (null == htmlFile){
			return;
		}
		htmlStream = new FileOutputStream(htmlFile);
	}
	
	/**
	 * <p>ʹ��XSL��ʽ�����XML���ݣ���ת��ΪHTML�ļ����浽���趨��Ŀ¼��</p>
	 * @throws Exception
	 */
	public void transform() throws Exception {
		if (xslStream == null || xmlStream == null || htmlStream == null){
			throw new Exception("����ʧ�ܣ�û���ҵ���ؽ�������");
		}
        try {
            TransformerFactory tFac = TransformerFactory.newInstance();
            Source xslSource = new StreamSource(xslStream);
            Transformer t = tFac.newTransformer(xslSource);
            Source source = new StreamSource(xmlStream);
            Result result = new StreamResult(htmlStream);
            t.transform(source, result);
        } catch (TransformerConfigurationException e) {
            throw e;
        } catch (TransformerException e) {
        	throw e;
        } finally {
        	xmlStream.close();
        	xslStream.close();
        	htmlStream.close();
        }
    }
}
