package com.woobsoft.htmlpump.exception;

/**
 * <p>HTML���ݳ�ȡ���쳣</p>
 * @author �˳�
 * @date 2011-3-16 ����01:30:56
 */
public class PumpRunException extends Exception {
	public PumpRunException(Throwable t) {
		super(t);
	}
	
	public PumpRunException(String message) {
		super(message);
	}
	
	public PumpRunException(String message, Throwable cause) {
		super(message, cause);
	}
}
