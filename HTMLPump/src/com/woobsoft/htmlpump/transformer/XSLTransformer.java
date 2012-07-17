/* XSLTransformer.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 
 * ==========================================================
 * 创建: [2010-6-28 下午01:44:00] by 潘超 
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
 * XML+XSL解析为HTML文件的处理类
 * @author 潘超
 * @date 2010-6-28 下午01:44:00
 */
public class XSLTransformer {
	/** 需要解析的XML数据 */
	private InputStream xmlStream;
	/** 解析时使用的XSL样式表 */
	private InputStream xslStream;
	/** 解析后输出的HTML文件 */
	private OutputStream htmlStream;
	
	/**
	 * <p>设置需要解析的XML数据</p>
	 * @param xmlFile XML数据的文件完整路径
	 * @throws FileNotFoundException
	 */
	public void setXML(String xmlFile) throws FileNotFoundException{
		if (null == xmlFile || "".equals(xmlFile)){
			return;
		}
		xmlStream = new FileInputStream(xmlFile);
	}
	
	/**
	 * <p>设置需要解析的XML数据</p>
	 * @param xmlFile XML数据流
	 */
	public void setXML(InputStream xmlFile){
		if (null == xmlFile){
			return;
		}
		xmlStream = xmlFile;
	}
	
	/**
	 * <p>设置需要解析的XML数据</p>
	 * @param xmlFile XML数据文件
	 * @throws FileNotFoundException
	 */
	public void setXML(File xmlFile) throws FileNotFoundException{
		if (null == xmlFile || !xmlFile.exists()){
			return;
		}
		xmlStream = new FileInputStream(xmlFile);
	}
	
	/**
	 * <p>设置需要解析的XML数据</p>
	 * @param xmlStr XML数据的字符串内容
	 */
	public void setXMLString(String xmlStr){
		if (null == xmlStr || "".equals(xmlStr)){
			return;
		}
		xmlStream = new ByteArrayInputStream(xmlStr.getBytes());
	}
	
	/**
	 * <p>设置解析需要的XSL样式表</p>
	 * @param xslFile XSL样式表文件的完整路径
	 * @throws FileNotFoundException
	 */
	public void setXSL(String xslFile) throws FileNotFoundException{
		if (null == xslFile || "".equals(xslFile)){
			return;
		}
		xslStream = new FileInputStream(xslFile);
	}
	
	/**
	 * <p>设置解析需要的XSL样式表</p>
	 * @param xslFile XSL样式表文件流
	 */
	public void setXSL(InputStream xslFile) {
		if (null == xslFile){
			return;
		}
		xslStream = xslFile;
	}
	
	/**
	 * <p>设置解析需要的XSL样式表</p>
	 * @param xslFile XSL样式表文件
	 * @throws FileNotFoundException
	 */
	public void setXSL(File xslFile) throws FileNotFoundException{
		if (null == xslFile || !xslFile.exists()){
			return;
		}
		xslStream = new FileInputStream(xslFile);
	}
	
	/**
	 * <p>设置解析需要的XSL样式表</p>
	 * @param xslStr XSL样式表内容字符串
	 */
	public void setXSLString(String xslStr){
		if (null == xslStr || "".equals(xslStr)){
			return;
		}
		xslStream = new ByteArrayInputStream(xslStr.getBytes());
	}
	
	/**
	 * <p>设置解析后输出的HTML文件的位置</p>
	 * @param htmlFile HTML文件的完整路径
	 * @throws FileNotFoundException
	 */
	public void setHTML(String htmlFile) throws FileNotFoundException{
		if (null == htmlFile || "".equals(htmlFile)){
			return;
		}
		htmlStream = new FileOutputStream(htmlFile);
	}
	
	/**
	 * <p>设置解析后输出的HTML文件的位置</p>
	 * @param htmlFile HTML文件的完整路径
	 * @throws FileNotFoundException
	 */
	public void setHTML(OutputStream htmlFile){
		if (null == htmlFile){
			return;
		}
		htmlStream = htmlFile;
	}
	
	/**
	 * <p>设置解析后输出的HTML文件的位置</p>
	 * @param htmlFile HTML文件的完整路径
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
	 * <p>使用XSL样式表解析XML数据，并转换为HTML文件保存到已设定的目录中</p>
	 * @throws Exception
	 */
	public void transform() throws Exception {
		if (xslStream == null || xmlStream == null || htmlStream == null){
			throw new Exception("解析失败！没有找到相关解析数据");
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
