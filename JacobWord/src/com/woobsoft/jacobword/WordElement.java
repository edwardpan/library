/* WordElement.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word元素抽象类
 * 
 * ==========================================================
 * 创建: [2011-2-17 下午03:42:05] by 潘超
 * ============================================================ 
 */

package com.woobsoft.jacobword;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>Word元素抽象类</p>
 *
 * @author 潘超
 * @date 2011-2-17 下午03:42:05
 */
public abstract class WordElement {
	protected Dispatch dispatch;
	
	/**
	 * <p>初始化Word元素对象。传入操作Word元素对象必须的Dispatch对象</p>
	 * @param dispatch 原始Dispatch对象
	 * @throws WordException
	 */
	public WordElement(Dispatch dispatch) throws WordException{
		if (dispatch == null){
			throw new WordException("初始化Word元素对象失败，原因：没有获得Dispatch对象");
		}
		this.dispatch = dispatch;
	}
	
	/**
	 * <p>获取Word元素对象的原始Dispatch对象</p>
	 * @return
	 */
	public Dispatch getDispatch() {
		return dispatch;
	}
}
