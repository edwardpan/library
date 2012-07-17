/* WordApplication.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word应用程序对象
 * 
 * ==========================================================
 * 创建: [2010-4-28 下午04:35:06] by 潘超 
 * ============================================================ 
 */

package com.woobsoft.jacobword;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.woobsoft.jacobword.document.Document;
import com.woobsoft.jacobword.document.Documents;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>Word应用程序对象，提供对Word程序进程的管理</p>
 * <p>Word应用程序在被使用后必须调用quit()方法退出</p>
 *
 * @author 潘超
 * @date 2010-4-28 下午04:35:06
 */
public class WordApplication {
	/** Word运行程序 */
	private ActiveXComponent wordDispatch;
	private Documents documents;
	
	/**
	 * 初始化Word应用程序对象。
	 * 且设置Word应用程序在处理时不可见
	 * @throws WordException
	 */
	public WordApplication() throws WordException{
		if (wordDispatch == null){
			try{
				wordDispatch = new ActiveXComponent("Word.Application");
			} catch (Exception ex){
				throw new WordException("不能加载Word应用程序组件对象，原因：" + ex.getMessage());
			}
		}
		setVisible(false);
		documents = getDocuments();
	}
	
	/**
	 * <p>设置Word应用程序在处理时是否可见</p>
	 * @param visible true为可见，false为不可见
	 * @throws WordException
	 */
	public void setVisible(boolean visible) throws WordException{
		if (wordDispatch == null){
			return;
		}
		try{
			wordDispatch.setProperty("Visible", new Variant(visible));
		} catch (Exception ex){
			throw new WordException("不能为Word应用程序设置可见属性。原因：" + ex.getMessage());
		}
	}
	
	/**
	 * <p>从Word应用程序对象中获取文档集管理对象</p>
	 * @return 已初始化的文档集管理对象
	 * @throws WordException
	 */
	public Documents getDocuments() throws WordException{
		if (documents != null){
			return documents;
		}
		if (wordDispatch == null){
			throw new WordException("没有获取到ActiveXComponent对象，不能创建Documents");
		}
		Dispatch dis = null;
		try {
			dis = wordDispatch.getProperty("Documents").toDispatch();
		} catch (Exception ex) {
			throw new WordException("获取Word文档集管理对象失败，原因：" + ex.getMessage());
		}
		return new Documents(dis);
	}
	
	/**
	 * <p>打开一个已存在的Word文档</p>
	 * @param docPath Word文档所在的绝对路径
	 * @return 已初始化的Document对象
	 * @throws WordException
	 */
	public Document openDocument(String docPath) throws WordException{
		return documents.openDocument(docPath);
	}
	
	/**
	 * <p>创建一个新的Word文档，文档数据暂时在内存中</p>
	 * @return 已创建的Word文档
	 * @throws WordException
	 */
	public Document createDocument() throws WordException{
		return documents.createDocument();
	}
	
	/**
	 * <p>从Word应用程序对象中获取插入对象，该插入对象标识着光标所在位置</p>
	 * 如果获取该对象之前Word应用程序中没有文档元素Document对象，将会抛出异常。
	 * @return 已初始化的文档插入对象
	 * @throws WordException
	 */
	public Selection getSelection() throws WordException{
		if (wordDispatch == null){
			throw new WordException("没有获取到ActiveXComponent对象，不能创建Selection");
		}
		Dispatch dis = null;
		try {
			dis = Dispatch.get(wordDispatch, "Selection").toDispatch();
		} catch (Exception ex) {
			throw new WordException("获取Word应用程序的光标插入对象失败，原因：" + ex.getMessage());
		}
		return new Selection(dis);
	}
	
	/**
	 * <p>退出Word应用程序，结束进程</p>
	 * @throws WordException
	 */
	public void quit() throws WordException{
		try{
			Dispatch.call(wordDispatch, "Quit");
		} catch (Exception ex){
			throw new WordException("退出Word应用程序失败，原因：" + ex.getMessage());
		}
	}
}
