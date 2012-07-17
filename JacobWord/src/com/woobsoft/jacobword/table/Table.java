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
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.range.HasRange;
import com.woobsoft.jacobword.range.Range;

/**
 * <p>Word文档的表格对象</p>
 *
 * @author 潘超
 * @date 2010-4-29 上午11:00:04
 */
public class Table extends WordElement implements HasRange{
	/**
	 * <p>初始化Word文档表格对象。</p>
	 * @param table Table的Dispatch对象
	 * @throws WordException
	 */
	public Table(Dispatch table) throws WordException{
		super(table);
	}
	
	/**
	 * <p>获取表格中的一个单元格</p>
	 * @param rowIndex 单元格所在行序号，从1开始
	 * @param columnIndex 单元格所在列序号，从1开始
	 * @return 单元格对象
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
			throw new WordException("获取单元格对象失败，原因：" + ex.getMessage());
		}
		
		return cell;
	}

	@Override
	public Range getRange() throws WordException {
		Dispatch dis = null;
		try{
			dis = Dispatch.get(dispatch, "Range").toDispatch();
		} catch (Exception ex) {
			throw new WordException("获取表格对象的选择范围失败，原因：" + ex.getMessage());
		}
		Range range = new Range(dis);
		return range;
	}
	
	/**
	 * <p>获取表格行集合对象</p>
	 * @return
	 * @throws WordException
	 */
	public Rows getRows() throws WordException{
		Dispatch dis = null;
		try{
			dis = Dispatch.get(dispatch, "Rows").toDispatch();
		} catch (Exception ex) {
			throw new WordException("获取表格行集合对象失败，原因：" + ex.getMessage());
		}
		Rows rows = new Rows(dis);
		return rows;
	}
	
	/**
	 * <p>获取表格列集合对象</p>
	 * @return
	 * @throws WordException
	 */
	public Columns getColumns() throws WordException{
		Dispatch dis = null;
		try{
			dis = Dispatch.get(dispatch, "Columns").toDispatch();
		} catch (Exception ex) {
			throw new WordException("获取表格列集合对象失败，原因：" + ex.getMessage());
		}
		Columns columns = new Columns(dis);
		return columns;
	}
}
