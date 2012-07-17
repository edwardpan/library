/* HTMLDataPumpImpl.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 描述该文件
 * 
 * ==========================================================
 * 创建: [2011-3-16 下午02:10:12] by 潘超
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
 * <p>HTML数据抽取实现类</p>
 * @author 潘超
 * @date 2011-3-16 下午02:10:12
 */
public class HTMLDataPumpImpl extends AbstractHTMLDataPump {

	@Override
	public void run() throws PumpRunException {
		if (xslStream == null) {
			throw new PumpRunException("必须正确设置存在的XSL样式表文件！");
		}
		if (htmlSource == null || htmlSource.equals("") || !htmlSource.contains("html")) {
			throw new PumpRunException("必须设置用于抽取数据的HTML源");
		}
		if (outputPath == null || outputPath.equals("")) {
			throw new PumpRunException("必须设置用于输出抽取结果的文件路径");
		}
		
		// 为输出的XML数据文件创建目录
		File outFile = new File(outputPath);
		outFile.mkdirs();
		
		if (autoEncoding) {// 检查是否要自动获取HTML文档编码
			getEncoding();
		}
		
		// 格式化HTML源
		HTMLFormater htmlFormater = new HTMLFormater();
		htmlFormater.setInputEncoding(inputEncoding);
		htmlFormater.setOutputEncoding(outputEncoding);
		String xmlSource = "";
		try {
			xmlSource = htmlFormater.format(htmlSource);
		} catch (Exception e) {
			throw new PumpRunException(e);
		}
		
		// 抽取数据
		XSLTransformer former = new XSLTransformer();
		try {
			// 设置HTML源
			former.setXMLString(xmlSource);
			// 设置转换用的XSL
			former.setXSL(xslStream);
			// 设置输出的数据XML路径
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
        	contentEncode = m.group(1);  //第1个group匹配的就是需要的字符编码  
        }
        
        this.inputEncoding = contentEncode;
        this.outputEncoding = contentEncode;
	}
}
