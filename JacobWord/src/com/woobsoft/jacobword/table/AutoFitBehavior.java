package com.woobsoft.jacobword.table;

/**
 * <p>指定 Microsoft Word 如何使用自动调整功能来调整表格的大小。</p>
 * @author 潘超
 * @date:2011下午02:33:04
 */
public class AutoFitBehavior {
	/** 根据表格中包含的内容自动调整表格的大小。 */
	public static final int wdAutoFitContent = 1;
	/** 将表格设置为固定大小而与内容无关，因此不会自动调整表格大小。 */
	public static final int wdAutoFitFixed = 0;
	/** 根据活动窗口的宽度自动调整表格大小。 */
	public static final int wdAutoFitWindow = 2;
}
