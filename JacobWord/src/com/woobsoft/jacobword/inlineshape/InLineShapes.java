/* InLineShape.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word文档内嵌图形
 * 
 * ==========================================================
 * 创建: [2010-4-29 上午09:20:25] by 潘超 
 * ============================================================ 
 */

package com.woobsoft.jacobword.inlineshape;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.woobsoft.jacobword.HasMore;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>Word文档内嵌图形集</p>
 *
 * @author 潘超
 * @date 2010-4-29 上午09:20:25
 */
public class InLineShapes extends WordElement implements HasMore {
	/**
	 * <p>初始化Word文档内嵌图形集对象</p>
	 * @param shapes Word文档内嵌图形集的Dispatch对象
	 * @throws WordException
	 */
	public InLineShapes(Dispatch shapes) throws WordException{
		super(shapes);
	}

	/**
	 * 向图形集区域中插入一张新的图片
	 * @param picPath
	 * @return 插入的图片的InLineShape对象
	 * @throws WordException 
	 */
	public InLineShape addPicture(String picPath) throws WordException{
		Dispatch dis = null;
		InLineShape shape = null;
		try{
			dis = Dispatch.call(dispatch, "AddPicture", picPath).toDispatch();
			shape = new InLineShape(dis);
		} catch (Exception ex) {
			throw new WordException("插入图形失败，原因：" + ex.getMessage());
		}
		return shape;
	}
	
	/**
	 * <p>根据序号获取文档中的内嵌图形对象</p>
	 * <p>当对Word文档中的图形进行了删除、剪切、增加操作后文档中实际的图形数量已发生变化，
	 * 索引号将不再确定，使用时需要注意。</p>
	 * @param index 内嵌图形对象的序号，从1开始计数。
	 * @return 内嵌图形对象
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
			throw new WordException("获取内嵌图形对象失败，原因：" + ex.getMessage());
		}
		return shape;
	}
	
	@Override
	public int getCount() throws WordException{
		int num = 0;
		try {
			num = Dispatch.get(dispatch, "Count").getInt();
		} catch (Exception ex) {
			throw new WordException("获取内嵌图形对象数量失败，原因：" + ex.getMessage());
		}
		return num;
	}
}
