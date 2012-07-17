/* Document.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * һ��Word�ĵ��������
 * 
 * ==========================================================
 * ����: [2010-4-28 ����05:07:24] by �˳� 
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
 * <p>һ��Word�ĵ��������</p>
 *
 * @author �˳�
 * @date 2010-4-28 ����05:07:24
 */
public class Document extends WordElement implements HasTables, HasInLineShapes{
	
	/**
	 * <p>��ʼ��Word�ĵ�����</p>
	 * @param doc Word�ĵ���Dispatch����
	 * @throws WordException
	 */
	public Document(Dispatch doc) throws WordException{
		super(doc);
	}
	
	/**
	 * <p>ʹ��Ĭ�ϵ�Word�ļ������ʽ������ĵ���</p>
	 * <p>Ĭ�ϵ�Word�ļ������ʽΪ������{@link com.woobsoft.jacobword.WordSaveFormat}.WORD_FORMAT_DOCUMENT_DEFAULT</P>
	 * @param savePath ��Ҫ�����ĵ��ľ���·��������Ҫָ��������ļ����ƺͺ�׺��
	 */
	public void saveAs(String savePath) throws WordException{
		saveAs(savePath, WordSaveFormat.WORD_FORMAT_DOCUMENT_DEFALT);
	}
	
	/**
	 * <p>ʹ��ָ����Word�ļ������ʽ������ĵ���</p>
	 * <p>Word�ļ������ʽ������ɳ�����{@link WordSaveFormat}����</p>
	 * @param savePath ��Ҫ�����ĵ��ľ���·��������Ҫָ��������ļ����ƺͺ�׺��
	 * @param fileFormat Word�ļ������ʽ��
	 */
	public void saveAs(String savePath, int fileFormat) throws WordException{
		try{
			Dispatch.call(dispatch, "SaveAs", new Variant(savePath), new Variant(fileFormat));
		} catch (Exception ex){
			throw new WordException("�����ĵ�ʧ�ܣ�ԭ��" + ex.getMessage());
		}
	}
	
	/**
	 * <p>�رո��ĵ���ָ���ر�ʱ�Ƿ���Ҫ������ĵ�</p>
	 * @param isSave trueΪ�ر�ʱ������ĵ���falseΪ������
	 */
	public void close(boolean isSave) throws WordException{
		try {
			Dispatch.call(dispatch, "Close", new Variant(isSave));
		} catch (Exception ex) {
			throw new WordException("�ر��ĵ�ʧ�ܣ�ԭ��" + ex.getMessage());
		}
	}
	
	@Override
	public InLineShapes getInLineShapes() throws WordException{
		InLineShapes shapes = null;
		try {
			Dispatch shapesDis = Dispatch.get(dispatch, "InLineShapes").toDispatch();
			shapes = new InLineShapes(shapesDis);
		} catch (Exception ex){
			throw new WordException("��ȡ��Ƕͼ�ζ���ʧ�ܣ�ԭ��" + ex.getMessage());
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
			throw new WordException("��ȡ��񼯶���ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return tables;
	}
	
	/**   
     * ����ĵ���ҳ�����Զ���   
	 * @throws WordException 
     */
    public PageSetup getPageSetup() throws WordException {    
    	PageSetup pageSetup = null;
		try {
			Dispatch tablesDis = Dispatch.get(dispatch, "PageSetup").toDispatch();
			pageSetup = new PageSetup(tablesDis);
		} catch (Exception ex){
			throw new WordException("��ȡҳ�����Զ���ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return pageSetup;    
    }    
}
