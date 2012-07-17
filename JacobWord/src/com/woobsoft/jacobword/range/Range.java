/* Range.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word文档元素范围类
 * 
 * ==========================================================
 * 创建: [2010-4-29 上午09:50:14] by 潘超 
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
 * <p>Word文档元素范围类</p>
 *
 * @author 潘超
 * @date 2010-4-29 上午09:50:14
 */
public class Range extends WordElement implements HasInLineShapes, HasTables{
	/**
	 * <p>初始化Word文档元素范围类</p>
	 *
	 * @param range Word文档元素范围类的Dispatch对象
	 * @throws WordException
	 */
	public Range(Dispatch range) throws WordException{
		super(range);
	}
	
	/**
	 * <p>将一个选取范围内的内容复制到另一个选取范围中</p>
	 * @param source 源选取范围
	 * @param target 目录选取范围
	 * @throws WordException
	 */
	public static void copyRange(HasRange source, HasRange target) throws WordException{
		source.getRange().copy();
		target.getRange().paste();
	}
	
	/**
	 * <p>将范围中包括的Word文档元素复制到系统粘帖板中</p>
	 * @throws WordException
	 */
	public void copy() throws WordException{
		try {
			Dispatch.call(dispatch, "Copy");
		} catch (Exception ex){
			throw new WordException("复制失败，原因：" + ex.getMessage());
		}
	}
	
	/**
	 * <p>从系统粘帖板中获取数据粘帖在当前选择范围内</p>
	 * @throws WordException
	 */
	public void paste() throws WordException{
		try {
			Dispatch.call(dispatch, "Paste");
		} catch (Exception ex){
			throw new WordException("粘帖失败，原因：" + ex.getMessage());
		}
	}
	
	/**
	 * <p>将范围中包括的Word文档元素剪切到系统粘帖板中</p>
	 * @throws WordException
	 */
	public void cut() throws WordException{
		try {
			Dispatch.call(dispatch, "Cut");
		} catch (Exception ex){
			throw new WordException("剪切失败，原因：" + ex.getMessage());
		}
	}
	
	/**
	 * <p>将范围中包括的Word文档元素删除</p>
	 * @throws WordException
	 */
	public void delete() throws WordException{
		try {
			Dispatch.call(dispatch, "Delete");
		} catch (Exception ex){
			throw new WordException("删除失败，原因：" + ex.getMessage());
		}
	}
	
	/**
	 * <p>为该范围中设置文字内容，该范围中原包含的文字将被替换</p>
	 * @throws WordException
	 */
	public void setText(String value) throws WordException{
		try {
			Dispatch.put(dispatch, "Text", value);
		} catch (Exception ex){
			throw new WordException("设置范围中的文字失败，原因：" + ex.getMessage());
		}
	}
	
	/**
	 * <p>获取该范围中的文字内容</p>
	 * @throws WordException
	 */
	public String getText() throws WordException{
		String value = "";
		try {
			value = Dispatch.get(dispatch, "Text").getString();
		} catch (Exception ex){
			throw new WordException("获取范围中的文字失败，原因：" + ex.getMessage());
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
