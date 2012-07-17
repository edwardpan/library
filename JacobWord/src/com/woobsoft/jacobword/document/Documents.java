/* Documents.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word�ĵ���������
 * 
 * ==========================================================
 * ����: [2010-4-28 ����04:41:44] by �˳� 
 * ============================================================ 
 */

package com.woobsoft.jacobword.document;

import java.io.File;

import com.jacob.com.Dispatch;
import com.woobsoft.jacobword.HasMore;
import com.woobsoft.jacobword.WordElement;
import com.woobsoft.jacobword.exception.WordException;

/**
 * <p>Word�ĵ��������࣬�ṩ�˵����ĵ��Ĳ��ҡ����ء���������</p>
 *
 * @author �˳�
 * @date 2010-4-28 ����04:41:44
 */
public class Documents extends WordElement implements HasMore{
	/**
	 * <p>��ʼ��Word�ĵ��������ࡣ</p>
	 *
	 * @param dis WordӦ�ó������
	 */
	public Documents(Dispatch dis) throws WordException{
		super(dis);
	}
	
	/**
	 * <p>��һ���Ѵ��ڵ�Word�ĵ�</p>
	 * @param docPath Word�ĵ����ڵľ���·��
	 * @return �ѳ�ʼ����Document����
	 * @throws WordException
	 */
	public Document openDocument(String docPath) throws WordException{
		if (docPath == null || docPath.equals("") || 
				(!docPath.endsWith("doc") && !docPath.endsWith("docx")) ){
			throw new WordException("���ǺϷ���Word�ĵ������ܴ򿪣�");
		}
		File file = new File(docPath);
		if (!file.exists()){
			throw new WordException("Word�ĵ������ڣ�");
		}
		Dispatch document = null;
		try{
			document = Dispatch.call(dispatch, "Open", docPath).toDispatch();
		} catch (Exception ex){
			throw new WordException("��Word�ĵ�����ԭ��" + ex.getMessage());
		}
		return new Document(document);
	}
	
	/**
	 * <p>����һ���µ�Word�ĵ����ĵ�������ʱ���ڴ���</p>
	 * @return �Ѵ�����Word�ĵ�
	 * @throws WordException
	 */
	public Document createDocument() throws WordException{
		Dispatch document = null;
		try{
			document = Dispatch.call(dispatch, "Add").toDispatch();
		} catch (Exception ex){
			throw new WordException("����Word�ĵ�����ԭ��" + ex.getMessage());
		}
		return new Document(document);
	}

	@Override
	public int getCount() throws WordException {
		int num = 0;
		try {
			num = Dispatch.get(dispatch, "Count").getInt();
		} catch (Exception ex) {
			throw new WordException("��ȡ�ĵ���������ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return num;
	}
}