/* Table.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word文档的表格对象
 * 
 * ==========================================================
 * 创建: [2010-4-29 上午11:00:04] by 潘超 
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
 * <p>Word文档的表格集对象</p>
 *
 * @author 潘超
 * @date 2010-4-29 上午11:00:04
 */
public class Tables extends WordElement implements HasMore {
	/**
	 * <p>初始化Word文档表格集对象。</p>
	 * @param tables Table的Dispatch对象
	 * @throws WordException
	 */
	public Tables(Dispatch tables) throws WordException{
		super(tables);
	}
	
	/**
	 * 插入一张新表格
	 * @param range 需要插入表格的区域
	 * @param rowNum 需要插入的表格行数
	 * @param columnNum 需要插入的表格列数
	 * @param tableBehavior 设置一个值来指定 Microsoft Word 是否要根据单元格的内容自动调整表格单元格的大小。
	 * 			如果设置为禁止自动调整，则表格不显示边框；否则显示。
	 * 			通过常量类TableBehavior来设置该参数。
	 * @param autoFitBehavior 用于设置 Word 调整表格大小的“自动调整”规则。通过常量类AutoFitBehavior来设置该参数
	 * @return 新插入的表格对象Table
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
			throw new WordException("插入表格失败，原因：" + ex.getMessage());
		}
		
		return table;
	}
	
	/**
	 * <p>根据序号获取文档中的表格对象</p>
	 * <p>当对Word文档中的表格进行了删除、剪切操作后文档中实际的表格数量已发生变化，
	 * 索引号将不再确定，使用时需要注意。</p>
	 * @param index 表格对象的序号，从1开始计数。
	 * @return 表格对象
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
			throw new WordException("获取表格对象失败，原因：" + ex.getMessage());
		}
		return table;
	}

	@Override
	public int getCount() throws WordException{
		int num = 0;
		try {
			num = Dispatch.get(dispatch, "Count").getInt();
		} catch (Exception ex) {
			throw new WordException("获取对象数量失败，原因：" + ex.getMessage());
		}
		return num;
	}
}
