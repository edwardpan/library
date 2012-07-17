/* Rows.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 描述该文件
 * 
 * ==========================================================
 * 创建: [2011-2-17 下午04:31:37] by 潘超
 * 修改：[2010-3-1 21:01] by other
 *    修改内容：(修改内容描述，可以多行)
 * ============================================================ 
 */

package com.woobsoft.jacobword.table;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.HasMore;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>表格列的集合对象</p>
 *
 * @author 潘超
 * @date 2011-2-17 下午04:31:37
 */
public class Columns extends WordElement implements HasMore {

	/**
	 * <p>初始化表格列集合对象</p>
	 * @param dispatch
	 * @throws WordException
	 */
	public Columns(Dispatch dispatch) throws WordException {
		super(dispatch);
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
