/* Cell.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word文档中表格的单元格对象
 * 
 * ==========================================================
 * 创建: [2010-4-29 上午11:10:49] by 潘超 
 * ============================================================ 
 */

package com.woobsoft.jacobword.table;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.range.HasRange;
import com.woobsoft.jacobword.range.Range;

/**
 * <p>Word文档中表格的单元格对象</p>
 *
 * @author 潘超
 * @date 2010-4-29 上午11:10:49
 */
public class Cell extends WordElement implements HasRange, HasTables{
	/**
	 * <p>初始化Word文档单元格对象。</p>
	 * @param dis 单元格的Dispatch对象
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
			throw new WordException("获取Word文档中单元格对象的选择范围失败，原因：" + ex.getMessage());
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
			throw new WordException("获取表格集对象失败，原因：" + ex.getMessage());
		}
		return tables;
	}
}
