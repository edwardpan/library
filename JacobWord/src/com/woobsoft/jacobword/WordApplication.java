/* WordApplication.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * WordӦ�ó������
 * 
 * ==========================================================
 * ����: [2010-4-28 ����04:35:06] by �˳� 
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
 * <p>WordӦ�ó�������ṩ��Word������̵Ĺ���</p>
 * <p>WordӦ�ó����ڱ�ʹ�ú�������quit()�����˳�</p>
 *
 * @author �˳�
 * @date 2010-4-28 ����04:35:06
 */
public class WordApplication {
	/** Word���г��� */
	private ActiveXComponent wordDispatch;
	private Documents documents;
	
	/**
	 * ��ʼ��WordӦ�ó������
	 * ������WordӦ�ó����ڴ���ʱ���ɼ�
	 * @throws WordException
	 */
	public WordApplication() throws WordException{
		if (wordDispatch == null){
			try{
				wordDispatch = new ActiveXComponent("Word.Application");
			} catch (Exception ex){
				throw new WordException("���ܼ���WordӦ�ó����������ԭ��" + ex.getMessage());
			}
		}
		setVisible(false);
		documents = getDocuments();
	}
	
	/**
	 * <p>����WordӦ�ó����ڴ���ʱ�Ƿ�ɼ�</p>
	 * @param visible trueΪ�ɼ���falseΪ���ɼ�
	 * @throws WordException
	 */
	public void setVisible(boolean visible) throws WordException{
		if (wordDispatch == null){
			return;
		}
		try{
			wordDispatch.setProperty("Visible", new Variant(visible));
		} catch (Exception ex){
			throw new WordException("����ΪWordӦ�ó������ÿɼ����ԡ�ԭ��" + ex.getMessage());
		}
	}
	
	/**
	 * <p>��WordӦ�ó�������л�ȡ�ĵ����������</p>
	 * @return �ѳ�ʼ�����ĵ����������
	 * @throws WordException
	 */
	public Documents getDocuments() throws WordException{
		if (documents != null){
			return documents;
		}
		if (wordDispatch == null){
			throw new WordException("û�л�ȡ��ActiveXComponent���󣬲��ܴ���Documents");
		}
		Dispatch dis = null;
		try {
			dis = wordDispatch.getProperty("Documents").toDispatch();
		} catch (Exception ex) {
			throw new WordException("��ȡWord�ĵ����������ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return new Documents(dis);
	}
	
	/**
	 * <p>��һ���Ѵ��ڵ�Word�ĵ�</p>
	 * @param docPath Word�ĵ����ڵľ���·��
	 * @return �ѳ�ʼ����Document����
	 * @throws WordException
	 */
	public Document openDocument(String docPath) throws WordException{
		return documents.openDocument(docPath);
	}
	
	/**
	 * <p>����һ���µ�Word�ĵ����ĵ�������ʱ���ڴ���</p>
	 * @return �Ѵ�����Word�ĵ�
	 * @throws WordException
	 */
	public Document createDocument() throws WordException{
		return documents.createDocument();
	}
	
	/**
	 * <p>��WordӦ�ó�������л�ȡ������󣬸ò�������ʶ�Ź������λ��</p>
	 * �����ȡ�ö���֮ǰWordӦ�ó�����û���ĵ�Ԫ��Document���󣬽����׳��쳣��
	 * @return �ѳ�ʼ�����ĵ��������
	 * @throws WordException
	 */
	public Selection getSelection() throws WordException{
		if (wordDispatch == null){
			throw new WordException("û�л�ȡ��ActiveXComponent���󣬲��ܴ���Selection");
		}
		Dispatch dis = null;
		try {
			dis = Dispatch.get(wordDispatch, "Selection").toDispatch();
		} catch (Exception ex) {
			throw new WordException("��ȡWordӦ�ó���Ĺ��������ʧ�ܣ�ԭ��" + ex.getMessage());
		}
		return new Selection(dis);
	}
	
	/**
	 * <p>�˳�WordӦ�ó��򣬽�������</p>
	 * @throws WordException
	 */
	public void quit() throws WordException{
		try{
			Dispatch.call(wordDispatch, "Quit");
		} catch (Exception ex){
			throw new WordException("�˳�WordӦ�ó���ʧ�ܣ�ԭ��" + ex.getMessage());
		}
	}
}
