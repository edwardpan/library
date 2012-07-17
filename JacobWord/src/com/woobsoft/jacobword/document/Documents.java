/* Documents.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word文档集管理类
 * 
 * ==========================================================
 * 创建: [2010-4-28 下午04:41:44] by 潘超 
 * ============================================================ 
 */

package com.woobsoft.jacobword.document;

import java.io.File;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.HasMore;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>Word文档集管理类，提供了单个文档的查找、加载、创建方法</p>
 *
 * @author 潘超
 * @date 2010-4-28 下午04:41:44
 */
public class Documents extends WordElement implements HasMore{
	/**
	 * <p>初始化Word文档集管理类。</p>
	 *
	 * @param dis Word应用程序对象
	 */
	public Documents(Dispatch dis) throws WordException{
		super(dis);
	}
	
	/**
	 * <p>打开一个已存在的Word文档</p>
	 * @param docPath Word文档所在的绝对路径
	 * @return 已初始化的Document对象
	 * @throws WordException
	 */
	public Document openDocument(String docPath) throws WordException{
		if (docPath == null || docPath.equals("") || 
				(!docPath.endsWith("doc") && !docPath.endsWith("docx")) ){
			throw new WordException("不是合法的Word文档，不能打开！");
		}
		File file = new File(docPath);
		if (!file.exists()){
			throw new WordException("Word文档不存在！");
		}
		Dispatch document = null;
		try{
			document = Dispatch.call(dispatch, "Open", docPath).toDispatch();
		} catch (Exception ex){
			throw new WordException("打开Word文档出错，原因：" + ex.getMessage());
		}
		return new Document(document);
	}
	
	/**
	 * <p>创建一个新的Word文档，文档数据暂时在内存中</p>
	 * @return 已创建的Word文档
	 * @throws WordException
	 */
	public Document createDocument() throws WordException{
		Dispatch document = null;
		try{
			document = Dispatch.call(dispatch, "Add").toDispatch();
		} catch (Exception ex){
			throw new WordException("创建Word文档出错，原因：" + ex.getMessage());
		}
		return new Document(document);
	}

	@Override
	public int getCount() throws WordException {
		int num = 0;
		try {
			num = Dispatch.get(dispatch, "Count").getInt();
		} catch (Exception ex) {
			throw new WordException("获取文档对象总数失败，原因：" + ex.getMessage());
		}
		return num;
	}
}