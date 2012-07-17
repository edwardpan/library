/* Cell.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word�ĵ��б��ĵ�Ԫ�����
 * 
 * ==========================================================
 * ����: [2010-4-29 ����11:10:49] by �˳� 
 * ============================================================ 
 */

package com.woobsoft.jacobword.table;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.range.HasRange;
import com.woobsoft.jacobword.range.Range;

/**
 * <p>Word�ĵ��б��ĵ�Ԫ�����</p>
 *
 * @author �˳�
 * @date 2010-4-29 ����11:10:49
 */
public class Cell extends WordElement implements HasRange, HasTables{
	/**
	 * <p>��ʼ��Word�ĵ���Ԫ�����</p>
	 * @param dis ��Ԫ���Dispatch����
	 * @throws WordException
	 */
	public Cell(Dispatch dis) throws WordException{
		super(dis);
	}

	@Override
	public Range getRange() throws WordException {
		Dispatch dis = null;
		try{
			dis = Dispatch.get(dispatch, "Range").toDispatch();
		} catch (Exception ex) {
			throw new WordException("��ȡWord�ĵ��е�Ԫ������ѡ��Χʧ�ܣ�ԭ��" + ex.getMessage());
		}
		Range range = new Range(dis);
		return range;
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
