/* HasMore.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * �������ļ�
 * 
 * ==========================================================
 * ����: [2011-2-17 ����03:12:56] by �˳�
 * ============================================================ 
 */

package com.woobsoft.jacobword;

import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>ʵ�ָýӿڵĶ����ʾ��Ϊһ�ּ��϶������ж��Ԫ�ء�</p>
 *
 * @author �˳�
 * @date 2011-2-17 ����03:12:56
 */
public interface HasMore {
	
	/**
	 * <p>��ȡ�ü��϶�����Ԫ�صĸ���</p>
	 * @return
	 */
	public int getCount() throws WordException;
}
