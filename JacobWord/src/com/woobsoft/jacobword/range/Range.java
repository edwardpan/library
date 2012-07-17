/* Range.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word�ĵ�Ԫ�ط�Χ��
 * 
 * ==========================================================
 * ����: [2010-4-29 ����09:50:14] by �˳� 
 * ============================================================ 
 */

package com.woobsoft.jacobword.range;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.inlineshape.HasInLineShapes;
import com.woobsoft.jacobword.inlineshape.InLineShapes;
import com.woobsoft.jacobword.table.HasTables;
import com.woobsoft.jacobword.table.Tables;

/**
 * <p>Word�ĵ�Ԫ�ط�Χ��</p>
 *
 * @author �˳�
 * @date 2010-4-29 ����09:50:14
 */
public class Range extends WordElement implements HasInLineShapes, HasTables{
	/**
	 * <p>��ʼ��Word�ĵ�Ԫ�ط�Χ��</p>
	 *
	 * @param range Word�ĵ�Ԫ�ط�Χ���Dispatch����
	 * @throws WordException
	 */
	public Range(Dispatch range) throws WordException{
		super(range);
	}
	
	/**
	 * <p>��һ��ѡȡ��Χ�ڵ����ݸ��Ƶ���һ��ѡȡ��Χ��</p>
	 * @param source Դѡȡ��Χ
	 * @param target Ŀ¼ѡȡ��Χ
	 * @throws WordException
	 */
	public static void copyRange(HasRange source, HasRange target) throws WordException{
		source.getRange().copy();
		target.getRange().paste();
	}
	
	/**
	 * <p>����Χ�а�����Word�ĵ�Ԫ�ظ��Ƶ�ϵͳճ������</p>
	 * @throws WordException
	 */
	public void copy() throws WordException{
		try {
			Dispatch.call(dispatch, "Copy");
		} catch (Exception ex){
			throw new WordException("����ʧ�ܣ�ԭ��" + ex.getMessage());
		}
	}
	
	/**
	 * <p>��ϵͳճ�����л�ȡ����ճ���ڵ�ǰѡ��Χ��</p>
	 * @throws WordException
	 */
	public void paste() throws WordException{
		try {
			Dispatch.call(dispatch, "Paste");
		} catch (Exception ex){
			throw new WordException("ճ��ʧ�ܣ�ԭ��" + ex.getMessage());
		}
	}
	
	/**
	 * <p>����Χ�а�����Word�ĵ�Ԫ�ؼ��е�ϵͳճ������</p>
	 * @throws WordException
	 */
	public void cut() throws WordException{
		try {
			Dispatch.call(dispatch, "Cut");
		} catch (Exception ex){
			throw new WordException("����ʧ�ܣ�ԭ��" + ex.getMessage());
		}
	}
	
	/**
	 * <p>����Χ�а�����Word�ĵ�Ԫ��ɾ��</p>
	 * @throws WordException
	 */
	public void delete() throws WordException{
		try {
			Dispatch.call(dispatch, "Delete");
		} catch (Exception ex){
			throw new WordException("ɾ��ʧ�ܣ�ԭ��" + ex.getMessage());
		}
	}
	
	/**
	 * <p>Ϊ�÷�Χ�������������ݣ��÷�Χ��ԭ���������ֽ����滻</p>
	 * @throws WordException
	 */
	public void setText(String value) throws WordException{
		try {
			Dispatch.put(dispatch, "Text", value);
		} catch (Exception ex){
			throw new WordException("���÷�Χ�е�����ʧ�ܣ�ԭ��" + ex.getMessage());
		}
	}
	
	/**
	 * <p>��ȡ�÷�Χ�е���������</p>
	 * @throws WordException
	 */
	public String getText() throws WordException{
		String value = "";
		try {
			value = Dispatch.get(dispatch, "Text").getString();
		} catch (Exception ex){
			throw new WordException("��ȡ��Χ�е�����ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return value;
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
