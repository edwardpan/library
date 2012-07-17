/* WordElement.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * WordԪ�س�����
 * 
 * ==========================================================
 * ����: [2011-2-17 ����03:42:05] by �˳�
 * ============================================================ 
 */

package com.woobsoft.jacobword;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>WordԪ�س�����</p>
 *
 * @author �˳�
 * @date 2011-2-17 ����03:42:05
 */
public abstract class WordElement {
	protected Dispatch dispatch;
	
	/**
	 * <p>��ʼ��WordԪ�ض��󡣴������WordԪ�ض�������Dispatch����</p>
	 * @param dispatch ԭʼDispatch����
	 * @throws WordException
	 */
	public WordElement(Dispatch dispatch) throws WordException{
		if (dispatch == null){
			throw new WordException("��ʼ��WordԪ�ض���ʧ�ܣ�ԭ��û�л��Dispatch����");
		}
		this.dispatch = dispatch;
	}
	
	/**
	 * <p>��ȡWordԪ�ض����ԭʼDispatch����</p>
	 * @return
	 */
	public Dispatch getDispatch() {
		return dispatch;
	}
}
