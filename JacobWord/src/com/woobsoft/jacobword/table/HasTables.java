/* HasTables.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 描述该文件
 * 
 * ==========================================================
 * 创建: [2011-2-17 下午03:01:55] by 潘超
 * ============================================================ 
 */

package com.woobsoft.jacobword.table;

import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>拥有表格集对象</p>
 *
 * @author 潘超
 * @date 2011-2-17 下午03:01:55
 */
public interface HasTables {
	
	/**
	 * <p>获取表格集对象。内可能包含多个表格</p>
	 * @return 
	 * @throws WordException
	 */
	public Tables getTables() throws WordException;
}
