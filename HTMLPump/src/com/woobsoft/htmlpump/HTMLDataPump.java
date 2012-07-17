/* HTMLDataPump.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * �������ļ�
 * 
 * ==========================================================
 * ����: [2011-3-16 ����01:03:50] by �˳�
 * �޸ģ�[2010-3-1 21:01] by other
 *    �޸����ݣ�(�޸��������������Զ���)
 * ============================================================ 
 */

package com.woobsoft.htmlpump;

import java.io.InputStream;

import com.woobsoft.htmlpump.exception.PumpRunException;

/**
 * <p>HTML���ݳ�ȡ������ָ����HTMLԴ�а�XSL�Ķ����ȡ����ΪXML��</p>
 * @author �˳�
 * @date 2011-3-16 ����01:03:50
 */
public interface HTMLDataPump {
	/**
	 * <p>���������HTMLԴ�ı��룬Ĭ��ΪGB2312</p>
	 * @param encoding
	 */
	public void setInputEncoding(String encoding);
	
	/**
	 * <p>��ȡ��ǰ���õ������HTMLԴ�ı���</p>
	 * @return
	 */
	public String getInputEncoding();
	
	/**
	 * <p>���������HTMLԴ��XML���ݵı��룬Ĭ��ΪGB2312</p>
	 * @param encoding
	 */
	public void setOutputEncoding(String encoding);
	
	/**
	 * <p>��ȡ��ǰ���õ������HTMLԴ��XML���ݵı���</p>
	 * @return
	 */
	public String getOutputEncoding();
	
	/**
	 * <p>�����Ƿ����HTMLԴ��Meta��Ϣ�Զ��жϱ��룬���ô�����ֶ����õı��뽫ʧЧ��</p>
	 * @param autoEncoding
	 */
	public void setAutoEncoding(boolean autoEncoding);
	
	/**
	 * <p>�Ƿ����HTMLԴ��Meta��Ϣ�Զ��жϱ���</p>
	 * @return
	 */
	public boolean isAutoEncoding();
	
	/**
	 * <p>������Ҫ��ȡ���ݵ�HTMLԴ��������</p>
	 * @param htmlSource HTMLԴ��������
	 */
	public void setHTMLSource(String htmlSource);
	
	/**
	 * <p>��ȡ��ǰ��Ҫ��ȡ���ݵ�HTMLԴ��������</p>
	 * @return
	 */
	public String getHTMLSource();
	
	/**
	 * <p>���ó�ȡ����ʱʹ�õ�XSL��ʽ���ļ���λ��</p>
	 * @param path
	 */
	public void setXSLFile(String path);
	
	/**
	 * <p>���ó�ȡ����ʱʹ�õ�XSL��ʽ���ļ�����</p>
	 * @param in
	 */
	public void setXSLFile(InputStream in);
	
	/**
	 * <p>���ó�ȡ���ݺ�������ļ�λ��</p>
	 * @param path �ļ�������·��
	 * @throws PumpRunException
	 */
	public void setOutputFile(String path);
	
	/**
	 * <p>��ȡ��ǰ��ȡ���ݺ�������ļ�λ��</p>
	 * @return
	 */
	public String getOutputFile();
	
	/**
	 * <p>��ʼ���ո�����XSL��ʽ���HTMLԴ�����г�ȡ����</p>
	 * @throws PumpRunException
	 */
	public void run() throws PumpRunException;
}
