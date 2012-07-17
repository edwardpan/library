/* HasInLineShapes.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 描述该文件
 * 
 * ==========================================================
 * 创建: [2011-2-17 下午03:05:23] by 潘超
 * 修改：[2010-3-1 21:01] by other
 *    修改内容：(修改内容描述，可以多行)
 * ============================================================ 
 */

package com.woobsoft.jacobword.inlineshape;

import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>拥有图形集对象</p>
 *
 * @author 潘超
 * @date 2011-2-17 下午03:05:23
 */
public interface HasInLineShapes {
	
	/**
	 * <p>获取图形集对象，内可能有多个图形</p>
	 * @return
	 * @throws WordException
	 */
	public InLineShapes getInLineShapes() throws WordException;
}
