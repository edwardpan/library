/* PageSetup.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * �������ļ�
 * 
 * ==========================================================
 * ����: [2011-2-17 ����03:56:05] by �˳�
 * ============================================================ 
 */

package com.woobsoft.jacobword.document;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>�ĵ�ҳ�����Զ���</p>
 *
 * @author �˳�
 * @date 2011-2-17 ����03:56:05
 */
public class PageSetup extends WordElement {
	/**
	 * <p>��ʼ��Word�ĵ�����</p>
	 * @param dis Word�ĵ���Dispatch����
	 * @throws WordException
	 */
	public PageSetup(Dispatch dis) throws WordException{
		super(dis);
	}
	
	/**
	 * <p>����ҳ�沼�ַ���ʹ�ó�����Orientation</p>
	 * @param orientation ʹ�ó�����Orientation
	 * @throws WordException 
	 */
	public void setOrientation(int orientation) {
        try {
			Dispatch.put(dispatch, "Orientation", orientation);
		} catch (Exception e) {
			System.err.println("����ҳ�沼�ַ���ʧ�ܣ�ԭ��" + e.getMessage());
		}
	}
	
	/**
	 * <p>����ҳ�����Ե��������߽�֮��ľ��루�԰�Ϊ��λ����</p>
	 * @param leftMargin
	 */
	public void setLeftMargin(int leftMargin) {
        try {
			Dispatch.put(dispatch, "LeftMargin", leftMargin);
		} catch (Exception e) {
			System.err.println("����ҳ�����Ե��������߽�֮��ľ���ʧ�ܣ�ԭ��" + e.getMessage());
		}
	}

	/**
	 * <p>����ҳ���ұ߾��������ұ߽�֮��ľ��루�԰�Ϊ��λ����</p>
	 * @param rightMargin
	 */
	public void setRightMargin(int rightMargin) {
        try {
			Dispatch.put(dispatch, "RightMargin", rightMargin);
		} catch (Exception e) {
			System.err.println("����ҳ���ұ߾��������ұ߽�֮��ľ���ʧ�ܣ�ԭ��" + e.getMessage());
		}
	}

	/**
	 * <p>����ҳ����ϱ�Ե�������ı����ϱ߽�֮��ľ��루�԰�Ϊ��λ����</p>
	 * @param topMargin
	 */
	public void setTopMargin(int topMargin) {
        try {
			Dispatch.put(dispatch, "TopMargin", topMargin);
		} catch (Exception e) {
			System.err.println("����ҳ����ϱ�Ե�������ı����ϱ߽�֮��ľ���ʧ�ܣ�ԭ��" + e.getMessage());
		}
	}

	/**
	 * <p>����ҳ��ױ��������ı��߽�֮��ľ��루�԰�Ϊ��λ����</p>
	 * @param bottomMargin
	 */
	public void setBottomMargin(int bottomMargin) {
		try {
			Dispatch.put(dispatch, "BottomMargin", bottomMargin);
		} catch (Exception e) {
			System.err.println("����ҳ��ױ��������ı��߽�֮��ľ���ʧ�ܣ�ԭ��" + e.getMessage());
		}
	}
}
