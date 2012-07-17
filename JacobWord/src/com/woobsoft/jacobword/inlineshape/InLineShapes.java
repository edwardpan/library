/* InLineShape.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word�ĵ���Ƕͼ��
 * 
 * ==========================================================
 * ����: [2010-4-29 ����09:20:25] by �˳� 
 * ============================================================ 
 */

package com.woobsoft.jacobword.inlineshape;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.woobsoft.jacobword.HasMore;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>Word�ĵ���Ƕͼ�μ�</p>
 *
 * @author �˳�
 * @date 2010-4-29 ����09:20:25
 */
public class InLineShapes extends WordElement implements HasMore {
	/**
	 * <p>��ʼ��Word�ĵ���Ƕͼ�μ�����</p>
	 * @param shapes Word�ĵ���Ƕͼ�μ���Dispatch����
	 * @throws WordException
	 */
	public InLineShapes(Dispatch shapes) throws WordException{
		super(shapes);
	}

	/**
	 * ��ͼ�μ������в���һ���µ�ͼƬ
	 * @param picPath
	 * @return �����ͼƬ��InLineShape����
	 * @throws WordException 
	 */
	public InLineShape addPicture(String picPath) throws WordException{
		Dispatch dis = null;
		InLineShape shape = null;
		try{
			dis = Dispatch.call(dispatch, "AddPicture", picPath).toDispatch();
			shape = new InLineShape(dis);
		} catch (Exception ex) {
			throw new WordException("����ͼ��ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return shape;
	}
	
	/**
	 * <p>������Ż�ȡ�ĵ��е���Ƕͼ�ζ���</p>
	 * <p>����Word�ĵ��е�ͼ�ν�����ɾ�������С����Ӳ������ĵ���ʵ�ʵ�ͼ�������ѷ����仯��
	 * �����Ž�����ȷ����ʹ��ʱ��Ҫע�⡣</p>
	 * @param index ��Ƕͼ�ζ������ţ���1��ʼ������
	 * @return ��Ƕͼ�ζ���
	 * @throws WordException
	 */
	public InLineShape getInLineShape(int index) throws WordException{
		if (index < 1){
			return null;
		}
		InLineShape shape = null;
		try {
			Dispatch shapeItem = Dispatch.call(dispatch, "Item", new Variant(index)).toDispatch();
			shape = new InLineShape(shapeItem);
		} catch (Exception ex){
			throw new WordException("��ȡ��Ƕͼ�ζ���ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return shape;
	}
	
	@Override
	public int getCount() throws WordException{
		int num = 0;
		try {
			num = Dispatch.get(dispatch, "Count").getInt();
		} catch (Exception ex) {
			throw new WordException("��ȡ��Ƕͼ�ζ�������ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return num;
	}
}
