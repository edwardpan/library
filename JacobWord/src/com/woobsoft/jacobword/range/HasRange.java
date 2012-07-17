/* HasRange.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 描述该文件
 * 
 * ==========================================================
 * 创建: [2010-4-29 上午09:45:30] by 潘超 
 * 修改：[2010-3-1 21:01] by other
 *    修改内容：(修改内容描述，可以多行)
 * ============================================================ 
 */

package com.woobsoft.jacobword.range;

import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>对象拥有选择范围</p>
 *
 * @author 潘超
 * @date 2010-4-29 上午09:45:30
 */
public interface HasRange {
	/**
	 * <p>获取选择范围对象</p>
	 * @return
	 * @throws WordException
	 */
	public Range getRange() throws WordException;
}
