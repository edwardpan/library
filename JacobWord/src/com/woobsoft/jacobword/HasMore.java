/* HasMore.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 描述该文件
 * 
 * ==========================================================
 * 创建: [2011-2-17 下午03:12:56] by 潘超
 * ============================================================ 
 */

package com.woobsoft.jacobword;

import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>实现该接口的对象表示其为一种集合对象，内有多个元素。</p>
 *
 * @author 潘超
 * @date 2011-2-17 下午03:12:56
 */
public interface HasMore {
	
	/**
	 * <p>获取该集合对象中元素的个数</p>
	 * @return
	 */
	public int getCount() throws WordException;
}
