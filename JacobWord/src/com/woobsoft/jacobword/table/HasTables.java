/* HasTables.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * �������ļ�
 * 
 * ==========================================================
 * ����: [2011-2-17 ����03:01:55] by �˳�
 * ============================================================ 
 */

package com.woobsoft.jacobword.table;

import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>ӵ�б�񼯶���</p>
 *
 * @author �˳�
 * @date 2011-2-17 ����03:01:55
 */
public interface HasTables {
	
	/**
	 * <p>��ȡ��񼯶����ڿ��ܰ���������</p>
	 * @return 
	 * @throws WordException
	 */
	public Tables getTables() throws WordException;
}
