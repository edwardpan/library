/* Selection.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word�ĵ��������
 * 
 * ==========================================================
 * ����: [2010-4-28 ����04:59:28] by �˳� 
 * �޸ģ�[2010-3-1 21:01] by other
 *    �޸����ݣ�(�޸��������������Զ���)
 * ============================================================ 
 */

package com.woobsoft.jacobword;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.inlineshape.HasInLineShapes;
import com.woobsoft.jacobword.inlineshape.InLineShapes;
import com.woobsoft.jacobword.range.HasRange;
import com.woobsoft.jacobword.range.Range;
import com.woobsoft.jacobword.table.HasTables;
import com.woobsoft.jacobword.table.Tables;

/**
 * <p>Word�ĵ��������</p>
 *
 * @author �˳�
 * @date 2010-4-28 ����04:59:28
 */
public class Selection extends WordElement implements HasRange, HasTables, HasInLineShapes {
	
	/**
	 * <p>��ʼ��Word�ĵ�������ࡣ</p>
	 * @param dis Selection��Dispatch����
	 */
	public Selection(Dispatch dis) throws WordException{
		super(dis);
	}

	@Override
	public Range getRange() throws WordException {
		Dispatch dis = null;
		try{
			dis = Dispatch.get(dispatch, "Range").toDispatch();
		} catch (Exception ex) {
			throw new WordException("��ȡWord�ĵ����������ʶ��ѡ��Χʧ�ܣ�ԭ��" + ex.getMessage());
		}
		Range range = new Range(dis);
		return range;
	}

	@Override
	public InLineShapes getInLineShapes() throws WordException{
		InLineShapes shapes = null;
		try {
			Dispatch shapesDis = Dispatch.get(dispatch, "InLineShapes").toDispatch();
			shapes = new InLineShapes(shapesDis);
		} catch (Exception ex){
			throw new WordException("��ȡ��Ƕͼ�ζ���ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return shapes;
	}
	
	@Override
	public Tables getTables() throws WordException{
		Tables tables = null;
		try {
			Dispatch tablesDis = Dispatch.get(dispatch, "Tables").toDispatch();
			tables = new Tables(tablesDis);
		} catch (Exception ex){
			throw new WordException("��ȡ��񼯶���ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return tables;
	}
}
