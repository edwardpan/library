
package example;
import java.util.ArrayList;    
import java.util.List;     
   
import com.jacob.activeX.ActiveXComponent;     
import com.jacob.com.ComThread;     
import com.jacob.com.Dispatch;     
import com.jacob.com.Variant;    
   
   
/**   
 *    
 *���ã�����jacob�������word �ļ���    
 *    
 *
 */   
public class CreateWord {    
   
    /** ��־��¼�� */   
   
    /** word�ĵ�   
     *    
     * �ڱ����������ַ�ʽ���Խ����ĵ��Ĵ���,<br>   
     * ��һ�ֵ��� createNewDocument   
     * �ڶ��ֵ��� openDocument    
     */     
    private Dispatch document = null;     
   
    /** word���г������ */     
    private ActiveXComponent word = null;     
   
    /** ����word�ĵ� */     
    private Dispatch documents = null;     
   
    /**   
     *  Selection ���� �����ڻ򴰸��еĵ�ǰ��ѡ���ݡ� ��ѡ���ݴ����ĵ���ѡ������ͻ����ʾ������������ĵ���û��ѡ���κ����ݣ���������㡣   
     *  ÿ���ĵ�����ֻ����һ��Selection ���󣬲���������Ӧ�ó�����ֻ����һ����� Selection ����   
     */   
    private Dispatch selection = null;     
   
    /**   
     *    
     * Range ���� �����ĵ��е�һ���������� ÿ�� Range ������һ����ʼ�ַ�λ�ú�һ����ֹ�ַ�λ�ö��塣   
     * ˵��������ǩ���ĵ��е�ʹ�÷������ƣ�Range ������ Visual Basic ������������ʶ�ĵ����ض����֡�   
     * ������ǩ��ͬ���ǣ�Range����ֻ�ڶ���ö���Ĺ�������ʱ�Ŵ��ڡ�   
     * Range�����������ѡ���ݡ�Ҳ����˵�������Զ���ʹ���һ����Χ�����������ѡ���ݡ����������ĵ��ж�������Χ����ÿ��������ֻ����һ����ѡ���ݡ�   
     */   
    private Dispatch range = null;    
   
    /**   
     * PageSetup ���� �ö�������ĵ�������ҳ���������ԣ�����߾ࡢ�±߾��ֽ�Ŵ�С����   
     */   
    private Dispatch pageSetup = null;    
   
    /** �ĵ��е����б����� */   
    private Dispatch tables = null;    
   
    /** һ�������� */   
    private Dispatch table = null;    
   
    /** ��������ж��� */   
    private Dispatch rows = null;    
   
    /** ��������ж��� */   
    private Dispatch cols = null;    
   
    /** ���ָ���ж��� */   
    private Dispatch row = null;    
   
    /** ���ָ���ж��� */   
    private Dispatch col = null;    
   
    /** �����ָ���ĵ�Ԫ�� */   
    private Dispatch cell = null;    
        
    /** ���� */   
    private Dispatch font = null;    
        
    /** ���뷽ʽ */   
    private Dispatch alignment = null;    
   
    /**   
     * ���췽��   
     */   
    public CreateWord() {     
   
        if(this.word == null){    
            /* ��ʼ��Ӧ����Ҫ�õ��Ķ���ʵ�� */   
            this.word = new ActiveXComponent("Word.Application");     
            /* ����Word�ĵ��Ƿ�ɼ���true-�ɼ�false-���ɼ� */   
            this.word.setProperty("Visible",new Variant(true));    
            /* ���ú� */   
            this.word.setProperty("AutomationSecurity", new Variant(3));    
        }    
        if(this.documents == null){    
            this.documents = word.getProperty("Documents").toDispatch();    
        }    
    }    
   
    /**   
     * ����ҳ�淽���ҳ�߾�   
     *   
     * @param orientation   
     *            ��ȡֵ0��1���ֱ������������   
     * @param leftMargin   
     *            ��߾��ֵ   
     * @param rightMargin   
     *            �ұ߾��ֵ   
     * @param topMargin   
     *            �ϱ߾��ֵ   
     * @param buttomMargin   
     *            �±߾��ֵ   
     */   
    public void setPageSetup(int orientation, int leftMargin,int rightMargin, int topMargin, int buttomMargin) {    
            
           System.out.println("����ҳ�淽���ҳ�߾�...");    
        if(this.pageSetup == null){    
            this.getPageSetup();    
        }    
        Dispatch.put(pageSetup, "Orientation", orientation);    
        Dispatch.put(pageSetup, "LeftMargin", leftMargin);    
        Dispatch.put(pageSetup, "RightMargin", rightMargin);    
        Dispatch.put(pageSetup, "TopMargin", topMargin);    
        Dispatch.put(pageSetup, "BottomMargin", buttomMargin);    
    }    
   
    /**    
     * ���ļ�    
     *    
     * @param inputDoc    
     *            Ҫ�򿪵��ļ���ȫ·��    
     * @return Dispatch    
     *            �򿪵��ļ�    
     */     
    public Dispatch openDocument(String inputDoc) {     
   
           System.out.println("��Word�ĵ�...");    
        this.document = Dispatch.call(documents,"Open",inputDoc).toDispatch();    
        this.getSelection();    
        this.getRange();    
        this.getAlignment();    
        this.getFont();    
        this.getPageSetup();    
        return this.document;     
    }     
   
    /**   
     * �����µ��ļ�   
     *    
     * @return Dispache �����½��ļ�   
     */   
    public Dispatch createNewDocument(){    
            
           System.out.println("�����µ��ļ�...");    
        this.document = Dispatch.call(documents,"Add").toDispatch();    
        this.getSelection();    
        this.getRange();    
        this.getPageSetup();    
        this.getAlignment();    
        this.getFont();    
        return this.document;    
    }    
   
    /**    
     * ѡ������    
     * @return Dispatch ѡ���ķ�Χ������    
     */     
    public Dispatch getSelection() {     
   
           System.out.println("��ȡѡ����Χ�Ĳ����...");    
        this.selection = word.getProperty("Selection").toDispatch();    
        return this.selection;     
    }     
   
    /**   
     * ��ȡ��ǰDocument�ڿ����޸ĵĲ���<p><br>   
     * ǰ��������ѡ�����ݱ������   
     *    
     * @param selectedContent ѡ������   
     * @return ���޸ĵĶ���   
     */   
    public Dispatch getRange() {    
   
           System.out.println("��ȡ��ǰDocument�ڿ����޸ĵĲ���...");    
        this.range = Dispatch.get(this.selection, "Range").toDispatch();    
        return this.range;    
    }    
   
    /**   
     * ��õ�ǰ�ĵ����ĵ�ҳ������   
     */   
    public Dispatch getPageSetup() {    
            
           System.out.println("��õ�ǰ�ĵ����ĵ�ҳ������...");    
        if(this.document == null){    
               System.out.println("document����Ϊ��...");    
            return this.pageSetup;    
        }    
        this.pageSetup = Dispatch.get(this.document, "PageSetup").toDispatch();    
        return this.pageSetup;    
    }    
   
    /**    
     * ��ѡ�����ݻ����������ƶ�    
     * @param count �ƶ��ľ���    
     */     
    public void moveUp(int count) {     
   
           System.out.println("��ѡ�����ݻ����������ƶ�...");    
        for(int i = 0;i < count;i++) {    
            Dispatch.call(this.selection,"MoveUp");    
        }    
    }     
   
    /**    
     * ��ѡ�����ݻ����������ƶ�    
     * @param count �ƶ��ľ���    
     */     
    public void moveDown(int count) {     
   
           System.out.println("��ѡ�����ݻ����������ƶ�...");    
        for(int i = 0;i < count;i++) {    
            Dispatch.call(this.selection,"MoveDown");    
        }    
    }     
   
    /**    
     * ��ѡ�����ݻ����������ƶ�    
     * @param count �ƶ��ľ���    
     */     
    public void moveLeft(int count) {     
   
           System.out.println("��ѡ�����ݻ����������ƶ�...");    
        for(int i = 0;i < count;i++) {    
            Dispatch.call(this.selection,"MoveLeft");    
        }    
    }     
   
    /**    
     * ��ѡ�����ݻ����������ƶ�    
     * @param count �ƶ��ľ���    
     */     
    public void moveRight(int count) {     
   
           System.out.println("��ѡ�����ݻ����������ƶ�...");    
        for(int i = 0;i < count;i++) {    
            Dispatch.call(this.selection,"MoveRight");    
        }    
    }    
        
    /**   
     * �س���   
     */   
    public void enterDown(int count){    
            
           System.out.println("���س���...");    
        for(int i = 0;i < count;i++) {    
            Dispatch.call(this.selection, "TypeParagraph");    
        }    
    }    
   
    /**    
     * �Ѳ�����ƶ����ļ���λ��    
     */     
    public void moveStart() {     
   
           System.out.println("�Ѳ�����ƶ����ļ���λ��...");    
        Dispatch.call(this.selection,"HomeKey",new Variant(6));     
    }     
   
    /**    
     * ��ѡ�����ݻ����㿪ʼ�����ı�    
     * @param selection ѡ������    
     * @param toFindText Ҫ���ҵ��ı�    
     * @return boolean true-���ҵ���ѡ�и��ı���false-δ���ҵ��ı�    
     */     
    public boolean find(String toFindText) {     
   
           System.out.println("��ѡ�����ݻ����㿪ʼ�����ı�"+" Ҫ�������ݣ�  "+toFindText);    
        /* ��selection����λ�ÿ�ʼ��ѯ */   
        Dispatch find = Dispatch.call(this.selection,"Find").toDispatch();     
        /* ����Ҫ���ҵ����� */   
        Dispatch.put(find,"Text",toFindText);     
        /* ��ǰ���� */   
        Dispatch.put(find,"Forward","True");     
        /* ���ø�ʽ */   
        Dispatch.put(find,"Format","True");     
        /* ��Сдƥ�� */   
        Dispatch.put(find,"MatchCase","True");     
        /* ȫ��ƥ�� */   
        Dispatch.put(find,"MatchWholeWord","True");     
        /* ���Ҳ�ѡ�� */   
        return Dispatch.call(find,"Execute").getBoolean();     
    }     
   
    /**    
     * ��ѡ�������滻Ϊ�趨�ı�    
     * @param selection ѡ������    
     * @param newText �滻Ϊ�ı�    
     */     
    public void replace(String newText) {     
   
           System.out.println("��ѡ�������滻Ϊ�趨�ı�...");    
        /* �����滻�ı� */   
        Dispatch.put(this.selection,"Text",newText);     
    }     
   
    /**    
     * ȫ���滻    
     * @param selection ѡ�����ݻ���ʼ�����    
     * @param oldText Ҫ�滻���ı�    
     * @param replaceObj �滻Ϊ�ı�   
     */     
    public void replaceAll(String oldText,Object replaceObj) {     
   
           System.out.println("ȫ���滻...");    
        /* �ƶ����ļ���ͷ */   
        moveStart();     
        /* ����滻��ʽ */   
        String newText = (String) replaceObj;    
        /* ͼƬ�滻��ʽ */   
        if(oldText.indexOf("image") != -1 || newText.lastIndexOf(".bmp") != -1 || newText.lastIndexOf(".jpg") != -1 || newText.lastIndexOf(".gif") != -1){     
            while (find(oldText)) {     
                insertImage(newText);     
                Dispatch.call(this.selection,"MoveRight");     
            }     
            /* �����滻��ʽ */   
        } else {    
            while (find(oldText)) {     
                replace(newText);     
                Dispatch.call(this.selection,"MoveRight");     
            }     
        }    
    }     
   
    /**    
     * ����ͼƬ    
     * @param selection ͼƬ�Ĳ����    
     * @param imagePath ͼƬ�ļ���ȫ·����    
     */     
    public void insertImage(String imagePath) {     
   
           System.out.println("����ͼƬ...");    
        Dispatch.call(this.selection, "TypeParagraph");    
        Dispatch.call(Dispatch.get(this.selection,"InLineShapes").toDispatch(),"AddPicture",imagePath);     
    }     
   
    /**   
     * �ϲ����   
     *   
     * @param selection ��������   
     * @param tableIndex �����ʼ��   
     * @param fstCellRowIdx ��ʼ��   
     * @param fstCellColIdx ��ʼ��   
     * @param secCellRowIdx ������   
     * @param secCellColIdx ������   
     */   
    public void mergeCell(int tableIndex, int fstCellRowIdx, int fstCellColIdx, int secCellRowIdx, int secCellColIdx){    
   
           System.out.println("�ϲ���Ԫ��...");    
        if(this.table == null){    
               System.out.println("table����Ϊ��...");    
            return;    
        }    
        Dispatch fstCell = Dispatch.call(table, "Cell",new Variant(fstCellRowIdx), new Variant(fstCellColIdx)).toDispatch();    
        Dispatch secCell = Dispatch.call(table, "Cell",new Variant(secCellRowIdx), new Variant(secCellColIdx)).toDispatch();    
        Dispatch.call(fstCell, "Merge", secCell);    
    }    
   
    /**   
     * ��Table�����в�����ֵ<p>   
     *     ������ʽ��ArrayList<Object[]>List.size()Ϊ����������<br>   
     *     Object[]��length����ֵӦ�����������ı��������ͬ   
     *    
     * @param selection �����   
     * @param tableIndex �����ʼ��   
     * @param list ��������   
     */   
    public void insertToTable(List<Object[]> list){    
   
        System.out.println("��Table�����в�������...");    
           System.out.println("��Table�����в�������...");    
        if(list == null || list.size() <= 0){    
               System.out.println("д�����ݼ�Ϊ��...");    
            return;    
        }    
        if(this.table == null){    
               System.out.println("table����Ϊ��...");    
            return;    
        }    
        for(int i = 0; i < list.size(); i++){    
        	Object[]  strs = list.get(i);    
        	
            for(int j = 0; j<strs.length; j++){    
            	
            	
                /* ���������ÿһ����Ԫ�񣬱�����������Ҫ���������������ͬ */   
                Dispatch cell = this.getCell(i+1, j+1);    
                /* ѡ�д˵�Ԫ�� */   
                Dispatch.call(cell, "Select");    
                
                /* д�����ݵ��˵�Ԫ���� */   
                Dispatch.put(this.selection, "Text", strs[j]);    
                /* �ƶ��α굽��һ��λ�� */   
            
            
            }    
            
            this.moveDown(1);    
        }    
        this.enterDown(1);    
    }    
   
    /**   
     * ���ĵ�������������������   
     *    
     * @param selection �����   
     * @param list ��������   
     */   
    public void insertToDocument(List<String> list){    
   
           System.out.println("��Document�����в�������...");    
        if(list == null || list.size() <= 0){    
               System.out.println("д�����ݼ�Ϊ��...");    
            return;    
        }    
        if(this.document == null){    
               System.out.println("document����Ϊ��...");    
            return;    
        }    
        for(String str : list){    
            /* д����word�� */   
            this.applyListTemplate(3, 2);    
            Dispatch.put(this.selection, "Text", str);    
            this.moveDown(1);    
            this.enterDown(1);    
        }    
    }    
   
    /**   
     * �����µı��   
     *    
     * @param selection �����   
     * @param document �ĵ�����   
     * @param rowCount ����   
     * @param colCount ����   
     * @param width �߿���ֵ 0ǳɫ1��ɫ   
     * @return �´����ı�����   
     */   
    public Dispatch createNewTable(int rowCount, int colCount, int width){    
   
           System.out.println("�����µı��...");    
        if(this.tables == null){    
            this.getTables();    
        }    
        this.getRange();    
        if(rowCount > 0 && colCount > 0){    
            this.table = Dispatch.call(this.tables,"Add",this.range,new Variant(rowCount),new Variant(colCount),new Variant(width)).toDispatch();    
        }    
        /* �����´������ */   
        return this.table;    
    }    
   
    /**   
     * ��ȡDocument�����е�����Table����   
     *    
     * @return ����Table����   
     */   
    public Dispatch getTables(){    
   
   
           System.out.println("��ȡ���б�����...");    
        if(this.document == null){    
               System.out.println("document����Ϊ��...");    
            return this.tables;    
        }    
        this.tables = Dispatch.get(this.document, "Tables").toDispatch();    
        return this.tables;    
    }    
        
    /**   
     * ��ȡDocument��Table������   
     *    
     * @return �������   
     */   
    public int getTablesCount(){    
            
           System.out.println("��ȡ�ĵ��б������...");    
        if(this.tables == null){    
            this.getTables();    
        }    
        return Dispatch.get(tables, "Count").getInt();    
            
    }    
   
    /**   
     * ��ȡָ����ŵ�Table����   
     *    
     * @param tableIndex Table����   
     * @return   
     */   
    public Dispatch getTable(int tableIndex){    
   
           System.out.println("��ȡָ��������...");    
        if(this.tables == null){    
            this.getTables();    
        }    
        if(tableIndex >= 0){    
            this.table = Dispatch.call(this.tables, "Item", new Variant(tableIndex)).toDispatch();    
        }    
        return this.table;    
    }    
   
    /**   
     * ��ȡ����������   
     *    
     * @return ������   
     */   
    public int getTableColumnsCount() {    
   
           System.out.println("��ȡ���������...");    
        if(this.table == null){    
               System.out.println("table����Ϊ��...");    
            return 0;    
        }    
        return Dispatch.get(this.cols,"Count").getInt();    
    }    
   
    /**   
     * ��ȡ����������   
     *    
     * @return ������   
     */   
    public int getTableRowsCount(){    
   
           System.out.println("��ȡ���������...");    
        if(this.table == null){    
               System.out.println("table����Ϊ��...");    
            return 0;    
        }    
        return Dispatch.get(this.rows,"Count").getInt();    
    }    
    /**   
     * ��ȡ����ж���   
     *    
     * @return �ж���   
     */   
    public Dispatch getTableColumns() {    
   
           System.out.println("��ȡ����ж���...");    
        if(this.table == null){    
               System.out.println("table����Ϊ��...");    
            return this.cols;    
        }    
        this.cols = Dispatch.get(this.table,"Columns").toDispatch();    
        return this.cols;    
    }    
   
   
    /**   
     * ��ȡ�����ж���   
     *    
     * @return ������   
     */   
    public Dispatch getTableRows(){    
   
           System.out.println("��ȡ���������...");    
        if(this.table == null){    
               System.out.println("table����Ϊ��...");    
            return this.rows;    
        }    
        this.rows = Dispatch.get(this.table,"Rows").toDispatch();    
        return this.rows;    
    }    
   
    /**   
     * ��ȡָ������ж���   
     *    
     * @return �ж���   
     */   
    public Dispatch getTableColumn(int columnIndex) {    
   
           System.out.println("��ȡָ������ж���...");    
        if(this.cols == null){    
            this.getTableColumns();    
        }    
        if(columnIndex >= 0){    
            this.col = Dispatch.call(this.cols, "Item", new Variant(columnIndex)).toDispatch();    
        }    
        return this.col;    
    }    
   
    /**   
     * ��ȡ�����ָ�����ж���   
     *    
     * @param rowIndex �����   
     * @return �ж���   
     */   
    public Dispatch getTableRow(int rowIndex){    
   
           System.out.println("��ȡָ�����������...");    
        if(this.rows == null){    
            this.getTableRows();    
        }    
        if(rowIndex >= 0){    
            this.row = Dispatch.call(this.rows, "Item", new Variant(rowIndex)).toDispatch();    
        }    
        return this.row;    
    }    
        
    /**   
     * �Զ��������   
     */   
    public void autoFitTable() {    
            
           System.out.println("�Զ��������...");    
        int count = this.getTablesCount();    
        for (int i = 0; i < count; i++) {    
            Dispatch table = Dispatch.call(tables, "Item", new Variant(i + 1)).toDispatch();    
            Dispatch cols = Dispatch.get(table, "Columns").toDispatch();    
            Dispatch.call(cols, "AutoFit");    
        }    
    }    
   
    /**   
     * ��ȡ��ǰ�ĵ��У�����е�ָ����Ԫ��   
     *   
     * @param CellRowIdx  ��Ԫ��������   
     * @param CellColIdx ��Ԫ��������   
     * @return ָ����Ԫ�����   
     */   
    public Dispatch getCell(int cellRowIdx, int cellColIdx) {    
   
            
           System.out.println("��ȡ��ǰ�ĵ��У�����е�ָ����Ԫ��...");    
        if(this.table == null){    
               System.out.println("table����Ϊ��...");    
            return this.cell;    
        }    
        if(cellRowIdx >= 0 && cellColIdx >=0){    
            this.cell = Dispatch.call(this.table, "Cell", new Variant(cellRowIdx),new Variant(cellColIdx)).toDispatch();    
        }    
        return this.cell;    
    }    
   
    /**   
     * �����ĵ�����   
     *    
     * @param title ��������   
     */   
    public void setTitle(String title){    
            
           System.out.println("�����ĵ�����...");    
        if(title == null || "".equals(title)){    
               System.out.println("�ĵ�����Ϊ��...");    
            return;    
        }    
        Dispatch.call(this.selection, "TypeText", title);     
    }    
        
    /**   
     * ���õ�ǰ����ߵĴ�ϸ   
     *   
     * @param width   
     *        width��Χ��1<w<13,�����0���ʹ���û�п�   
     */   
    public void setTableBorderWidth(int width) {    
   
           System.out.println("���õ�ǰ����ߵĴ�ϸ...");    
        if(this.table == null){    
               System.out.println("table����Ϊ��...");    
            return;    
        }    
        /*   
         * ���ñ���ߵĴ�ϸ 1���������ϱ�һ���� 2�����������һ���� 3�����±�һ���� 4�����ұ�һ���� 5�������ϱ����±�֮������к���   
         * 6������������ұ�֮����������� 7�������Ͻǵ����½ǵ�б�� 8�������½ǵ����Ͻǵ�б��   
         */   
        Dispatch borders = Dispatch.get(table, "Borders").toDispatch();    
        Dispatch border = null;    
        for (int i = 1; i < 7; i++) {    
            border = Dispatch.call(borders, "Item", new Variant(i)).toDispatch();    
            if (width != 0) {    
                Dispatch.put(border, "LineWidth", new Variant(width));    
                Dispatch.put(border, "Visible", new Variant(true));    
            } else if (width == 0) {    
                Dispatch.put(border, "Visible", new Variant(false));    
            }    
        }    
    }    
        
    /**   
     * �Ե�ǰselection������Ŀ���źͱ��   
     * @param tabIndex   
     *     1: ��Ŀ���   
     *     2: ���   
     *     3: �༶���   
     *     4: �б���ʽ   
     * @param index   
     *     0:��ʾû�� ,�������ִ�����Ǹ�Tabҳ�еĵڼ�������   
     */   
    public void applyListTemplate(int tabIndex,int index){    
   
           System.out.println("�Ե�ǰselection������Ŀ���źͱ��...");    
        /* ȡ��ListGalleries�����б� */   
        Dispatch listGalleries = Dispatch.get(this.word, "ListGalleries").toDispatch();    
        /* ȡ���б���һ������ */   
        Dispatch listGallery = Dispatch.call(listGalleries, "Item", new Variant(tabIndex)).toDispatch();    
        Dispatch listTemplates = Dispatch.get(listGallery, "ListTemplates").toDispatch();    
        if(this.range == null){    
            this.getRange();    
        }    
        Dispatch listFormat = Dispatch.get(this.range, "ListFormat").toDispatch();    
        Dispatch.call(listFormat,"ApplyListTemplate",Dispatch.call(listTemplates, "Item", new Variant(index)), new Variant(true),new Variant(1),new Variant(0));    
    }    
        
    /**   
     * �����ĵ�Ŀ¼   
     *   
     * Ŀǰ���ù̶�������ʽ���Ժ���Զ�̬���е���   
     */   
    public void addTablesOfContents()    
    {    
      /* ȡ��ActiveDocument��TablesOfContents��range���� */   
      Dispatch ActiveDocument = word.getProperty("ActiveDocument").toDispatch();    
      Dispatch TablesOfContents = Dispatch.get(ActiveDocument,"TablesOfContents").toDispatch();    
      Dispatch range = Dispatch.get(this.selection, "Range").toDispatch();    
      /* ����Ŀ¼ */     
      Dispatch.call(TablesOfContents,"Add",range,new Variant(true),new Variant(1),new Variant(3),new Variant(true),new Variant(""),new Variant(true),new Variant(true));    
        
    }    
   
        
    /**   
     * ���õ�ǰSelection λ�÷�ʽ   
     * @param selectedContent 0������1�����У�2�����ҡ�   
     */   
    public void setAlignment(int alignmentType) {    
            
           System.out.println("���õ�ǰSelection λ�÷�ʽ...");    
        if(this.alignment == null){    
            this.getAlignment();    
        }    
        Dispatch.put(this.alignment, "Alignment", alignmentType);    
    }    
        
    /**   
     * ��ȡ��ǰѡ������Ķ��뷽ʽ   
     *    
     * @return ���䷽ʽ����   
     */   
    public Dispatch getAlignment(){    
            
           System.out.println("��ȡ��ǰѡ������Ķ��뷽ʽ...");    
        if(this.selection == null){    
            this.getSelection();    
        }    
        this.alignment = Dispatch.get(this.selection, "ParagraphFormat").toDispatch();    
        return this.alignment;    
    }    
        
    /**   
     * ��ȡ�������   
     *    
     * @return �������   
     */   
    public Dispatch getFont(){    
            
           System.out.println("��ȡ�������...");    
        if(this.selection == null){    
            this.getSelection();    
        }    
        this.font = Dispatch.get(this.selection, "Font").toDispatch();    
        return this.font;    
    }    
        
    /**   
     * ����ѡ�����ݵ����� ע���ڵ��ô˷���ǰ��ѡ���������selection�������   
     *   
     * @param fontName   
     *            �������ƣ����� "����"   
     * @param isBold   
     *            ����   
     * @param isItalic   
     *            б��   
     * @param isUnderline   
     *            �»���   
     * @param rgbColor   
     *            ��ɫ������"255,255,255"   
     * @param fontSize   
     *            �����С   
     * @param Scale   
     *            �ַ���࣬�ٷֱ�ֵ������ 70��������Ϊ70%   
     */   
    public void setFontScale(String fontName, boolean isBold, boolean isItalic, boolean isUnderline, String rgbColor, int Scale, int fontSize) {    
            
           System.out.println("��������...");    
        Dispatch.put(this.font, "Name", fontName);    
        Dispatch.put(this.font, "Bold", isBold);    
        Dispatch.put(this.font, "Italic", isItalic);    
        Dispatch.put(this.font, "Underline", isUnderline);    
        Dispatch.put(this.font, "Color", rgbColor);    
        Dispatch.put(this.font, "Scaling", Scale);    
        Dispatch.put(this.font, "Size", fontSize);    
    }    
        
    /**    
     * �����ļ�    
     * @param outputPath ����ļ�������·����    
     */     
    public void saveAs(String outputPath) {     
   
           System.out.println("�����ļ�...");    
        if(this.document == null){    
               System.out.println("document����Ϊ��...");    
            return;    
        }    
        if(outputPath ==null || "".equals(outputPath)){    
               System.out.println("�ļ�����·��Ϊ��...");    
            return;    
        }    
        Dispatch.call(this.document,"SaveAs",outputPath);     
    }    
        
    public void saveAsHtml(String htmlFile){    
        Dispatch.invoke(this.document,"SaveAs",Dispatch.Method, new Object[]{htmlFile,new Variant(8)}, new int[1]);    
    }    
   
    /**    
     * �ر��ļ�    
     * @param document Ҫ�رյ��ļ�    
     */     
    public void close() {    
   
           System.out.println("�ر��ļ�...");    
        if(document == null){    
               System.out.println("document����Ϊ��...");    
            return;    
        }    
        Dispatch.call(document,"Close",new Variant(0));     
    }    
   
    /**   
     * ��ӡword�ļ�   
     *   
     */   
    public void printFile(){    
           System.out.println("��ӡ�ļ�...");    
        if(document == null){    
               System.out.println("document����Ϊ��...");    
            return;    
        }    
        Dispatch.call(document,"PrintOut");    
    }    
   
    /**    
     * �˳�����    
     */     
    public void quit() {  
        System.out.println("�˳�����");    
//      word.invoke("Quit",new Variant[0]);   
        Dispatch.call(word, "Quit");
        ComThread.Release();     
    }    
   
   
}  
