/* Selection.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word文档插入点类
 * 
 * ==========================================================
 * 创建: [2010-4-28 下午04:59:28] by 潘超 
 * 修改：[2010-3-1 21:01] by other
 *    修改内容：(修改内容描述，可以多行)
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
 * <p>Word文档插入点类</p>
 *
 * @author 潘超
 * @date 2010-4-28 下午04:59:28
 */
public class Selection extends WordElement implements HasRange, HasTables, HasInLineShapes {
	
	/**
	 * <p>初始化Word文档插入点类。</p>
	 * @param dis Selection的Dispatch对象
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
			throw new WordException("获取Word文档插入点所标识的选择范围失败，原因：" + ex.getMessage());
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
			throw new WordException("获取内嵌图形对象失败，原因：" + ex.getMessage());
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
			throw new WordException("获取表格集对象失败，原因：" + ex.getMessage());
		}
		return tables;
	}
}
