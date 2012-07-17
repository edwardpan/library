/* Table.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word�ĵ��ı�����
 * 
 * ==========================================================
 * ����: [2010-4-29 ����11:00:04] by �˳� 
 * ============================================================ 
 */

package com.woobsoft.jacobword.table;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.range.HasRange;
import com.woobsoft.jacobword.range.Range;

/**
 * <p>Word�ĵ��ı�����</p>
 *
 * @author �˳�
 * @date 2010-4-29 ����11:00:04
 */
public class Table extends WordElement implements HasRange{
	/**
	 * <p>��ʼ��Word�ĵ�������</p>
	 * @param table Table��Dispatch����
	 * @throws WordException
	 */
	public Table(Dispatch table) throws WordException{
		super(table);
	}
	
	/**
	 * <p>��ȡ����е�һ����Ԫ��</p>
	 * @param rowIndex ��Ԫ����������ţ���1��ʼ
	 * @param columnIndex ��Ԫ����������ţ���1��ʼ
	 * @return ��Ԫ�����
	 * @throws WordException
	 */
	public Cell getCell(int rowIndex, int columnIndex) throws WordException {
		if (rowIndex < 0 || columnIndex < 0){
			return null;
		}
		Cell cell = null;
		try{
			Dispatch dis = Dispatch.call(dispatch, "Cell", 
					new Variant(rowIndex), 
					new Variant(columnIndex)).toDispatch();
			cell = new Cell(dis);
		} catch (Exception ex){
			throw new WordException("��ȡ��Ԫ�����ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		
		return cell;
	}

	@Override
	public Range getRange() throws WordException {
		Dispatch dis = null;
		try{
			dis = Dispatch.get(dispatch, "Range").toDispatch();
		} catch (Exception ex) {
			throw new WordException("��ȡ�������ѡ��Χʧ�ܣ�ԭ��" + ex.getMessage());
		}
		Range range = new Range(dis);
		return range;
	}
	
	/**
	 * <p>��ȡ����м��϶���</p>
	 * @return
	 * @throws WordException
	 */
	public Rows getRows() throws WordException{
		Dispatch dis = null;
		try{
			dis = Dispatch.get(dispatch, "Rows").toDispatch();
		} catch (Exception ex) {
			throw new WordException("��ȡ����м��϶���ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		Rows rows = new Rows(dis);
		return rows;
	}
	
	/**
	 * <p>��ȡ����м��϶���</p>
	 * @return
	 * @throws WordException
	 */
	public Columns getColumns() throws WordException{
		Dispatch dis = null;
		try{
			dis = Dispatch.get(dispatch, "Columns").toDispatch();
		} catch (Exception ex) {
			throw new WordException("��ȡ����м��϶���ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		Columns columns = new Columns(dis);
		return columns;
	}
}
