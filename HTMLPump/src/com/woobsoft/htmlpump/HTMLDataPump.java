/* HTMLDataPump.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 描述该文件
 * 
 * ==========================================================
 * 创建: [2011-3-16 下午01:03:50] by 潘超
 * 修改：[2010-3-1 21:01] by other
 *    修改内容：(修改内容描述，可以多行)
 * ============================================================ 
 */

package com.woobsoft.htmlpump;

import java.io.InputStream;

import com.woobsoft.htmlpump.exception.PumpRunException;

/**
 * <p>HTML数据抽取器，从指定的HTML源中按XSL的定义抽取数据为XML。</p>
 * @author 潘超
 * @date 2011-3-16 下午01:03:50
 */
public interface HTMLDataPump {
	/**
	 * <p>设置输入的HTML源的编码，默认为GB2312</p>
	 * @param encoding
	 */
	public void setInputEncoding(String encoding);
	
	/**
	 * <p>获取当前设置的输入的HTML源的编码</p>
	 * @return
	 */
	public String getInputEncoding();
	
	/**
	 * <p>设置输出的HTML源及XML数据的编码，默认为GB2312</p>
	 * @param encoding
	 */
	public void setOutputEncoding(String encoding);
	
	/**
	 * <p>获取当前设置的输出的HTML源及XML数据的编码</p>
	 * @return
	 */
	public String getOutputEncoding();
	
	/**
	 * <p>设置是否根据HTML源的Meta信息自动判断编码，设置此项后手动设置的编码将失效。</p>
	 * @param autoEncoding
	 */
	public void setAutoEncoding(boolean autoEncoding);
	
	/**
	 * <p>是否根据HTML源的Meta信息自动判断编码</p>
	 * @return
	 */
	public boolean isAutoEncoding();
	
	/**
	 * <p>设置需要抽取数据的HTML源代码内容</p>
	 * @param htmlSource HTML源代码内容
	 */
	public void setHTMLSource(String htmlSource);
	
	/**
	 * <p>获取当前需要抽取数据的HTML源代码内容</p>
	 * @return
	 */
	public String getHTMLSource();
	
	/**
	 * <p>设置抽取数据时使用的XSL样式表文件的位置</p>
	 * @param path
	 */
	public void setXSLFile(String path);
	
	/**
	 * <p>设置抽取数据时使用的XSL样式表文件的流</p>
	 * @param in
	 */
	public void setXSLFile(InputStream in);
	
	/**
	 * <p>设置抽取数据后输出的文件位置</p>
	 * @param path 文件的完整路径
	 * @throws PumpRunException
	 */
	public void setOutputFile(String path);
	
	/**
	 * <p>获取当前抽取数据后输出的文件位置</p>
	 * @return
	 */
	public String getOutputFile();
	
	/**
	 * <p>开始按照给定的XSL样式表从HTML源代码中抽取数据</p>
	 * @throws PumpRunException
	 */
	public void run() throws PumpRunException;
}
