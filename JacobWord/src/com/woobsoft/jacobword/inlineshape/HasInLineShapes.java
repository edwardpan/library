/* HasInLineShapes.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * �������ļ�
 * 
 * ==========================================================
 * ����: [2011-2-17 ����03:05:23] by �˳�
 * �޸ģ�[2010-3-1 21:01] by other
 *    �޸����ݣ�(�޸��������������Զ���)
 * ============================================================ 
 */

package com.woobsoft.jacobword.inlineshape;

import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>ӵ��ͼ�μ�����</p>
 *
 * @author �˳�
 * @date 2011-2-17 ����03:05:23
 */
public interface HasInLineShapes {
	
	/**
	 * <p>��ȡͼ�μ������ڿ����ж��ͼ��</p>
	 * @return
	 * @throws WordException
	 */
	public InLineShapes getInLineShapes() throws WordException;
}
