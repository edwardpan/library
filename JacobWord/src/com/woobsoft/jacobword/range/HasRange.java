/* HasRange.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * �������ļ�
 * 
 * ==========================================================
 * ����: [2010-4-29 ����09:45:30] by �˳� 
 * �޸ģ�[2010-3-1 21:01] by other
 *    �޸����ݣ�(�޸��������������Զ���)
 * ============================================================ 
 */

package com.woobsoft.jacobword.range;

import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>����ӵ��ѡ��Χ</p>
 *
 * @author �˳�
 * @date 2010-4-29 ����09:45:30
 */
public interface HasRange {
	/**
	 * <p>��ȡѡ��Χ����</p>
	 * @return
	 * @throws WordException
	 */
	public Range getRange() throws WordException;
}
