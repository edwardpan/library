/* Rows.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * �������ļ�
 * 
 * ==========================================================
 * ����: [2011-2-17 ����04:31:37] by �˳�
 * �޸ģ�[2010-3-1 21:01] by other
 *    �޸����ݣ�(�޸��������������Զ���)
 * ============================================================ 
 */

package com.woobsoft.jacobword.table;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.HasMore;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>����еļ��϶���</p>
 *
 * @author �˳�
 * @date 2011-2-17 ����04:31:37
 */
public class Columns extends WordElement implements HasMore {

	/**
	 * <p>��ʼ������м��϶���</p>
	 * @param dispatch
	 * @throws WordException
	 */
	public Columns(Dispatch dispatch) throws WordException {
		super(dispatch);
	}

	@Override
	public int getCount() throws WordException{
		int num = 0;
		try {
			num = Dispatch.get(dispatch, "Count").getInt();
		} catch (Exception ex) {
			throw new WordException("��ȡ��������ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return num;
	}
}
