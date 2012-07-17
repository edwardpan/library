package com.woobsoft.htmlpump.exception;

/**
 * <p>HTML数据抽取器异常</p>
 * @author 潘超
 * @date 2011-3-16 下午01:30:56
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
