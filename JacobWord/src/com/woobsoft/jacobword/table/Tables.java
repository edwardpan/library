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
import com.woobsoft.jacobword.HasMore;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.range.Range;

/**
 * <p>Word�ĵ��ı�񼯶���</p>
 *
 * @author �˳�
 * @date 2010-4-29 ����11:00:04
 */
public class Tables extends WordElement implements HasMore {
	/**
	 * <p>��ʼ��Word�ĵ���񼯶���</p>
	 * @param tables Table��Dispatch����
	 * @throws WordException
	 */
	public Tables(Dispatch tables) throws WordException{
		super(tables);
	}
	
	/**
	 * ����һ���±��
	 * @param range ��Ҫ�����������
	 * @param rowNum ��Ҫ����ı������
	 * @param columnNum ��Ҫ����ı������
	 * @param tableBehavior ����һ��ֵ��ָ�� Microsoft Word �Ƿ�Ҫ���ݵ�Ԫ��������Զ��������Ԫ��Ĵ�С��
	 * 			�������Ϊ��ֹ�Զ�������������ʾ�߿򣻷�����ʾ��
	 * 			ͨ��������TableBehavior�����øò�����
	 * @param autoFitBehavior �������� Word ��������С�ġ��Զ�����������ͨ��������AutoFitBehavior�����øò���
	 * @return �²���ı�����Table
	 * @throws WordException
	 */
	public Table add(Range range, int rowNum, int columnNum, int tableBehavior, int autoFitBehavior) throws WordException{
		Table table = null;
		try{
			Dispatch dis = Dispatch.call(dispatch, "Add", 
					range.getDispatch(), 
					new Variant(rowNum), 
					new Variant(columnNum),
					new Variant(tableBehavior),
					new Variant(autoFitBehavior))
					.toDispatch();
			table = new Table(dis);
		} catch (Exception ex){
			throw new WordException("������ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		
		return table;
	}
	
	/**
	 * <p>������Ż�ȡ�ĵ��еı�����</p>
	 * <p>����Word�ĵ��еı�������ɾ�������в������ĵ���ʵ�ʵı�������ѷ����仯��
	 * �����Ž�����ȷ����ʹ��ʱ��Ҫע�⡣</p>
	 * @param index ���������ţ���1��ʼ������
	 * @return ������
	 * @throws WordException
	 */
	public Table getTable(int index) throws WordException{
		if (index < 1){
			return null;
		}
		Table table = null;
		try {
			Dispatch tableItem = Dispatch.call(dispatch, "Item", new Variant(index)).toDispatch();
			table = new Table(tableItem);
		} catch (Exception ex){
			throw new WordException("��ȡ������ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return table;
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
