/* InLineShape.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word文档内嵌图形
 * 
 * ==========================================================
 * 创建: [2010-4-29 上午09:20:25] by 潘超 
 * ============================================================ 
 */

package com.woobsoft.jacobword.inlineshape;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.range.HasRange;
import com.woobsoft.jacobword.range.Range;

/**
 * <p>Word文档中单个内嵌图形</p>
 *
 * @author 潘超
 * @date 2010-4-29 上午09:20:25
 */
public class InLineShape extends WordElement implements HasRange {
	/**
	 * <p>初始化Word文档内嵌图形对象</p>
	 * @param shape Word文档内嵌图形的Dispatch对象
	 * @throws WordException
	 */
	public InLineShape(Dispatch shape) throws WordException{
		super(shape);
	}

	@Override
	public Range getRange() throws WordException {
		Dispatch dis = null;
		try{
			dis = Dispatch.get(dispatch, "Range").toDispatch();
		} catch (Exception ex) {
			throw new WordException("获取图形对象的选择范围失败，原因：" + ex.getMessage());
		}
		Range range = new Range(dis);
		return range;
	}
}
