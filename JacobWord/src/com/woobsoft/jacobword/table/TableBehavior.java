package com.woobsoft.jacobword.table;

/**
 * 指定 Microsoft Word 是否要根据表格单元格的内容自动调整其大小（“自动调整”功能）。
 * @author 潘超
 * @date:2011下午02:29:10
 */
public class TableBehavior {
	/** 禁用“自动调整”功能，默认设置。设置此选项后表格将不显示边框 */
	public static final int wdWord8TableBehavior = 0;
	/** 启用“自动调整”功能。设置此选后表格将显示边框 */
	public static final int wdWord9TableBehavior = 1;
}
