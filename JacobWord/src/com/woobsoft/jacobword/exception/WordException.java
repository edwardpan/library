/* WordVBAException.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word�ĵ�VBA�����Բ����쳣
 * 
 * ==========================================================
 * ����: [2010-4-28 ����04:48:41] by �˳� 
 * ============================================================ 
 */

package com.woobsoft.jacobword.exception;

/**
 * <p>Word�ĵ�VBA�����Բ����쳣</p>
 *
 * @author �˳�
 * @date 2010-4-28 ����04:48:41
 */
public class WordException extends Exception {
	public WordException(){
		super();
	}
	
	public WordException(String message){
		super(message);
	}
}
