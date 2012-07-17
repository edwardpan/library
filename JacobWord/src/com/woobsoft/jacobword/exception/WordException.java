/* WordVBAException.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word文档VBA宏语言操作异常
 * 
 * ==========================================================
 * 创建: [2010-4-28 下午04:48:41] by 潘超 
 * ============================================================ 
 */

package com.woobsoft.jacobword.exception;

/**
 * <p>Word文档VBA宏语言操作异常</p>
 *
 * @author 潘超
 * @date 2010-4-28 下午04:48:41
 */
public class WordException extends Exception {
	public WordException(){
		super();
	}
	
	public WordException(String message){
		super(message);
	}
}
