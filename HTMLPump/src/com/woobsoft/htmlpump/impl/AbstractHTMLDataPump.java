/* DataPump.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 描述该文件
 * 
 * ==========================================================
 * 创建: [2011-3-16 上午11:21:39] by 潘超
 * 修改：[2010-3-1 21:01] by other
 *    修改内容：(修改内容描述，可以多行)
 * ============================================================ 
 */

package com.woobsoft.htmlpump.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.woobsoft.htmlpump.HTMLDataPump;

/**
 * <p>HTML数据抽取器</p>
 * @author 潘超
 * @date 2011-3-16 上午11:21:39
 */
abstract class AbstractHTMLDataPump implements HTMLDataPump {
	protected String inputEncoding = "GB2312";
	protected String outputEncoding = "GB2312";
	protected boolean autoEncoding = false;
	protected String htmlSource = null;
	protected InputStream xslStream = null;
	protected String outputPath = null;
	protected OutputStream outputFile = null;
	
	@Override
	public void setInputEncoding(String encoding) {
		this.inputEncoding = encoding;
	}
	
	@Override
	public String getInputEncoding() {
		return this.inputEncoding;
	}
	
	@Override
	public void setOutputEncoding(String encoding) {
		this.outputEncoding = encoding;
	}
	
	@Override
	public String getOutputEncoding() {
		return this.outputEncoding;
	}
	
	@Override
	public void setAutoEncoding(boolean autoEncoding) {
		this.autoEncoding = autoEncoding;
	}
	
	@Override
	public boolean isAutoEncoding() {
		return this.autoEncoding;
	}
	
	@Override
	public void setHTMLSource(String htmlSource) {
		this.htmlSource = htmlSource;
	}
	
	@Override
	public String getHTMLSource() {
		return this.htmlSource;
	}
	
	@Override
	public void setXSLFile(String path) {
		if (path == null || path.equals("") || !path.endsWith(".xsl")) {
			return;
		}
		try {
			this.xslStream = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			this.xslStream = null;
		}
	}
	
	@Override
	public void setXSLFile(InputStream in) {
		this.xslStream = in;
	}
	
	@Override
	public void setOutputFile(String path) {
		if (path == null || path.equals("") || !path.endsWith(".xml")) {
			return;
		}
		this.outputPath = path;
		try {
			this.outputFile = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			this.outputFile = null;
		}
	}
	
	@Override
	public String getOutputFile() {
		return this.outputPath;
	}
}
