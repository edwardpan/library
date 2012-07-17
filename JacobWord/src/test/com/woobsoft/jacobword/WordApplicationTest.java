/* WordApplicationTest.java
 * ============================================================ 
 * �ɶ��岩����������޹�˾
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * �������ļ�
 * 
 * ==========================================================
 * ����: [2011-2-18 ����09:12:15] by �˳�
 * �޸ģ�[2010-3-1 21:01] by other
 *    �޸����ݣ�(�޸��������������Զ���)
 * ============================================================ 
 */

package test.com.woobsoft.jacobword;

import org.junit.Test;

import com.woobsoft.jacobword.Selection;
import com.woobsoft.jacobword.WordApplication;
import com.woobsoft.jacobword.document.Document;
import com.woobsoft.jacobword.exception.WordException;
import com.woobsoft.jacobword.range.Range;
import com.woobsoft.jacobword.table.AutoFitBehavior;
import com.woobsoft.jacobword.table.Table;
import com.woobsoft.jacobword.table.TableBehavior;
import com.woobsoft.jacobword.table.Tables;


/**
 * <p>�����ü�����</p>
 *
 * <p>������ù�����ϸ����</p>
 *
 * @author �˳�
 * @date 2011-2-18 ����09:12:15
 */
public class WordApplicationTest {
	
	@Test
	public void addTable() {
		WordApplication word = null;
		Document doc = null;
		Selection selection = null;
		
		try {
			word = new WordApplication();// ����WordӦ�ó���
			word.setVisible(false);
			doc = word.createDocument();
			selection = word.getSelection();
			
			Tables tables = doc.getTables();
			tables.add(selection.getRange(), 4, 4, 
					TableBehavior.wdWord9TableBehavior, 
					AutoFitBehavior.wdAutoFitWindow);
			Table table = tables.getTable(1);
			
			table.getCell(1, 1).getRange().setText("����");
			table.getCell(1, 2).getRange().setText("�˳�");
			table.getCell(1, 3).getRange().setText("�Ա�");
			table.getCell(1, 4).getRange().setText("��");
			
			table.getCell(2, 1).getRange().setText("��������");
			table.getCell(2, 2).getRange().setText("1986-6-1");
			table.getCell(2, 3).getRange().setText("�����ڵ�");
			table.getCell(2, 4).getRange().setText("�ɶ���");
			
			table.getCell(3, 1).getRange().getInLineShapes().addPicture("E:\\work\\nbproject\\logo.png");
			
			doc.saveAs("E:\\work\\nbproject\\pc.doc");
		} catch (WordException ex) {
			ex.printStackTrace();
		} finally {
			if (doc != null){
				try {
					doc.close(true);
				} catch (WordException e) {
					System.err.println(e.getMessage());
				}
			}
			
			if (word != null){
				try {
					word.quit();
				} catch (WordException e) {
					System.err.println(e.getMessage());
				}
			}
			
			// ���ߣ���������еķ�����ûִ�����
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {}
		}
	}
}
