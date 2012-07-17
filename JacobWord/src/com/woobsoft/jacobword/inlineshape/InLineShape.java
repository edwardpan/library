/* InLineShape.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word�ĵ���Ƕͼ��
 * 
 * ==========================================================
 * ����: [2010-4-29 ����09:20:25] by �˳� 
 * ============================================================ 
 */

package com.woobsoft.jacobword.inlineshape;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.range.HasRange;
import com.woobsoft.jacobword.range.Range;

/**
 * <p>Word�ĵ��е�����Ƕͼ��</p>
 *
 * @author �˳�
 * @date 2010-4-29 ����09:20:25
 */
public class InLineShape extends WordElement implements HasRange {
	/**
	 * <p>��ʼ��Word�ĵ���Ƕͼ�ζ���</p>
	 * @param shape Word�ĵ���Ƕͼ�ε�Dispatch����
	 * @throws WordException
	 */
	public InLineShape(Dispatch shape) throws WordException{
		super(shape);
	}

	@Override
	public Range getRange() throws WordException {
		Dispatch dis = null;
		try{
			dis = Dispatch.get(dispatch, "Range").toDispatch();
		} catch (Exception ex) {
			throw new WordException("��ȡͼ�ζ����ѡ��Χʧ�ܣ�ԭ��" + ex.getMessage());
		}
		Range range = new Range(dis);
		return range;
	}
}
