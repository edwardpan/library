/* WordApplicationTest.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * 描述该文件
 * 
 * ==========================================================
 * 创建: [2011-2-18 上午09:12:15] by 潘超
 * 修改：[2010-3-1 21:01] by other
 *    修改内容：(修改内容描述，可以多行)
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
 * <p>类作用简单描述</p>
 *
 * <p>类的作用功能详细描述</p>
 *
 * @author 潘超
 * @date 2011-2-18 上午09:12:15
 */
public class WordApplicationTest {
	
	@Test
	public void addTable() {
		WordApplication word = null;
		Document doc = null;
		Selection selection = null;
		
		try {
			word = new WordApplication();// 启动Word应用程序
			word.setVisible(false);
			doc = word.createDocument();
			selection = word.getSelection();
			
			Tables tables = doc.getTables();
			tables.add(selection.getRange(), 4, 4, 
					TableBehavior.wdWord9TableBehavior, 
					AutoFitBehavior.wdAutoFitWindow);
			Table table = tables.getTable(1);
			
			table.getCell(1, 1).getRange().setText("姓名");
			table.getCell(1, 2).getRange().setText("潘超");
			table.getCell(1, 3).getRange().setText("性别");
			table.getCell(1, 4).getRange().setText("男");
			
			table.getCell(2, 1).getRange().setText("出生日期");
			table.getCell(2, 2).getRange().setText("1986-6-1");
			table.getCell(2, 3).getRange().setText("现所在地");
			table.getCell(2, 4).getRange().setText("成都市");
			
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
			
			// 休眠，避免对象中的方法还没执行完成
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {}
		}
	}
}
