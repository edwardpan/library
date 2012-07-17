/* Document.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 一个Word文档对象的类
 * 
 * ==========================================================
 * 创建: [2010-4-28 下午05:07:24] by 潘超 
 * ============================================================ 
 */

package com.woobsoft.jacobword.document;

import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.WordSaveFormat;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.inlineshape.HasInLineShapes;
import com.woobsoft.jacobword.inlineshape.InLineShapes;
import com.woobsoft.jacobword.table.HasTables;
import com.woobsoft.jacobword.table.Tables;

/**
 * <p>一个Word文档对象的类</p>
 *
 * @author 潘超
 * @date 2010-4-28 下午05:07:24
 */
public class Document extends WordElement implements HasTables, HasInLineShapes{
	
	/**
	 * <p>初始化Word文档对象</p>
	 * @param doc Word文档的Dispatch对象
	 * @throws WordException
	 */
	public Document(Dispatch doc) throws WordException{
		super(doc);
	}
	
	/**
	 * <p>使用默认的Word文件保存格式保存该文档。</p>
	 * <p>默认的Word文件保存格式为常量：{@link com.woobsoft.jacobword.WordSaveFormat}.WORD_FORMAT_DOCUMENT_DEFAULT</P>
	 * @param savePath 需要保存文档的绝对路径，并需要指定保存的文件名称和后缀后。
	 */
	public void saveAs(String savePath) throws WordException{
		saveAs(savePath, WordSaveFormat.WORD_FORMAT_DOCUMENT_DEFALT);
	}
	
	/**
	 * <p>使用指定的Word文件保存格式保存该文档。</p>
	 * <p>Word文件保存格式的类别由常量类{@link WordSaveFormat}定义</p>
	 * @param savePath 需要保存文档的绝对路径，并需要指定保存的文件名称和后缀后。
	 * @param fileFormat Word文件保存格式。
	 */
	public void saveAs(String savePath, int fileFormat) throws WordException{
		try{
			Dispatch.call(dispatch, "SaveAs", new Variant(savePath), new Variant(fileFormat));
		} catch (Exception ex){
			throw new WordException("保存文档失败，原因：" + ex.getMessage());
		}
	}
	
	/**
	 * <p>关闭该文档。指定关闭时是否需要保存该文档</p>
	 * @param isSave true为关闭时保存该文档，false为不保存
	 */
	public void close(boolean isSave) throws WordException{
		try {
			Dispatch.call(dispatch, "Close", new Variant(isSave));
		} catch (Exception ex) {
			throw new WordException("关闭文档失败，原因：" + ex.getMessage());
		}
	}
	
	@Override
	public InLineShapes getInLineShapes() throws WordException{
		InLineShapes shapes = null;
		try {
			Dispatch shapesDis = Dispatch.get(dispatch, "InLineShapes").toDispatch();
			shapes = new InLineShapes(shapesDis);
		} catch (Exception ex){
			throw new WordException("获取内嵌图形对象失败，原因：" + ex.getMessage());
		}
		return shapes;
	}
	
	@Override
	public Tables getTables() throws WordException{
		Tables tables = null;
		try {
			Dispatch tablesDis = Dispatch.get(dispatch, "Tables").toDispatch();
			tables = new Tables(tablesDis);
		} catch (Exception ex){
			throw new WordException("获取表格集对象失败，原因：" + ex.getMessage());
		}
		return tables;
	}
	
	/**   
     * 获得文档的页面属性对象   
	 * @throws WordException 
     */
    public PageSetup getPageSetup() throws WordException {    
    	PageSetup pageSetup = null;
		try {
			Dispatch tablesDis = Dispatch.get(dispatch, "PageSetup").toDispatch();
			pageSetup = new PageSetup(tablesDis);
		} catch (Exception ex){
			throw new WordException("获取页面属性对象失败，原因：" + ex.getMessage());
		}
		return pageSetup;    
    }    
}
