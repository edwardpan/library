/* PageSetup.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 描述该文件
 * 
 * ==========================================================
 * 创建: [2011-2-17 下午03:56:05] by 潘超
 * ============================================================ 
 */

package com.woobsoft.jacobword.document;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>文档页面属性对象</p>
 *
 * @author 潘超
 * @date 2011-2-17 下午03:56:05
 */
public class PageSetup extends WordElement {
	/**
	 * <p>初始化Word文档对象</p>
	 * @param dis Word文档的Dispatch对象
	 * @throws WordException
	 */
	public PageSetup(Dispatch dis) throws WordException{
		super(dis);
	}
	
	/**
	 * <p>设置页面布局方向，使用常量类Orientation</p>
	 * @param orientation 使用常量类Orientation
	 * @throws WordException 
	 */
	public void setOrientation(int orientation) {
        try {
			Dispatch.put(dispatch, "Orientation", orientation);
		} catch (Exception e) {
			System.err.println("设置页面布局方向失败，原因：" + e.getMessage());
		}
	}
	
	/**
	 * <p>设置页面左边缘与正文左边界之间的距离（以磅为单位）。</p>
	 * @param leftMargin
	 */
	public void setLeftMargin(int leftMargin) {
        try {
			Dispatch.put(dispatch, "LeftMargin", leftMargin);
		} catch (Exception e) {
			System.err.println("设置页面左边缘与正文左边界之间的距离失败，原因：" + e.getMessage());
		}
	}

	/**
	 * <p>设置页面右边距与正文右边界之间的距离（以磅为单位）。</p>
	 * @param rightMargin
	 */
	public void setRightMargin(int rightMargin) {
        try {
			Dispatch.put(dispatch, "RightMargin", rightMargin);
		} catch (Exception e) {
			System.err.println("设置页面右边距与正文右边界之间的距离失败，原因：" + e.getMessage());
		}
	}

	/**
	 * <p>设置页面的上边缘与正文文本的上边界之间的距离（以磅为单位）。</p>
	 * @param topMargin
	 */
	public void setTopMargin(int topMargin) {
        try {
			Dispatch.put(dispatch, "TopMargin", topMargin);
		} catch (Exception e) {
			System.err.println("设置页面的上边缘与正文文本的上边界之间的距离失败，原因：" + e.getMessage());
		}
	}

	/**
	 * <p>设置页面底边与正文文本边界之间的距离（以磅为单位）。</p>
	 * @param bottomMargin
	 */
	public void setBottomMargin(int bottomMargin) {
		try {
			Dispatch.put(dispatch, "BottomMargin", bottomMargin);
		} catch (Exception e) {
			System.err.println("设置页面底边与正文文本边界之间的距离失败，原因：" + e.getMessage());
		}
	}
}
