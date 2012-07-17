
package example;
import java.util.ArrayList;    
import java.util.List;     
   
import com.jacob.activeX.ActiveXComponent;     
import com.jacob.com.ComThread;     
import com.jacob.com.Dispatch;     
import com.jacob.com.Variant;    
   
   
/**   
 *    
 *作用：利用jacob插件生成word 文件！    
 *    
 *
 */   
public class CreateWord {    
   
    /** 日志记录器 */   
   
    /** word文档   
     *    
     * 在本类中有两种方式可以进行文档的创建,<br>   
     * 第一种调用 createNewDocument   
     * 第二种调用 openDocument    
     */     
    private Dispatch document = null;     
   
    /** word运行程序对象 */     
    private ActiveXComponent word = null;     
   
    /** 所有word文档 */     
    private Dispatch documents = null;     
   
    /**   
     *  Selection 对象 代表窗口或窗格中的当前所选内容。 所选内容代表文档中选定（或突出显示）的区域，如果文档中没有选定任何内容，则代表插入点。   
     *  每个文档窗格只能有一个Selection 对象，并且在整个应用程序中只能有一个活动的 Selection 对象。   
     */   
    private Dispatch selection = null;     
   
    /**   
     *    
     * Range 对象 代表文档中的一个连续区域。 每个 Range 对象由一个起始字符位置和一个终止字符位置定义。   
     * 说明：与书签在文档中的使用方法类似，Range 对象在 Visual Basic 过程中用来标识文档的特定部分。   
     * 但与书签不同的是，Range对象只在定义该对象的过程运行时才存在。   
     * Range对象独立于所选内容。也就是说，您可以定义和处理一个范围而无需更改所选内容。还可以在文档中定义多个范围，但每个窗格中只能有一个所选内容。   
     */   
    private Dispatch range = null;    
   
    /**   
     * PageSetup 对象 该对象包含文档的所有页面设置属性（如左边距、下边距和纸张大小）。   
     */   
    private Dispatch pageSetup = null;    
   
    /** 文档中的所有表格对象 */   
    private Dispatch tables = null;    
   
    /** 一个表格对象 */   
    private Dispatch table = null;    
   
    /** 表格所有行对象 */   
    private Dispatch rows = null;    
   
    /** 表格所有列对象 */   
    private Dispatch cols = null;    
   
    /** 表格指定行对象 */   
    private Dispatch row = null;    
   
    /** 表格指定列对象 */   
    private Dispatch col = null;    
   
    /** 表格中指定的单元格 */   
    private Dispatch cell = null;    
        
    /** 字体 */   
    private Dispatch font = null;    
        
    /** 对齐方式 */   
    private Dispatch alignment = null;    
   
    /**   
     * 构造方法   
     */   
    public CreateWord() {     
   
        if(this.word == null){    
            /* 初始化应用所要用到的对象实例 */   
            this.word = new ActiveXComponent("Word.Application");     
            /* 设置Word文档是否可见，true-可见false-不可见 */   
            this.word.setProperty("Visible",new Variant(true));    
            /* 禁用宏 */   
            this.word.setProperty("AutomationSecurity", new Variant(3));    
        }    
        if(this.documents == null){    
            this.documents = word.getProperty("Documents").toDispatch();    
        }    
    }    
   
    /**   
     * 设置页面方向和页边距   
     *   
     * @param orientation   
     *            可取值0或1，分别代表横向和纵向   
     * @param leftMargin   
     *            左边距的值   
     * @param rightMargin   
     *            右边距的值   
     * @param topMargin   
     *            上边距的值   
     * @param buttomMargin   
     *            下边距的值   
     */   
    public void setPageSetup(int orientation, int leftMargin,int rightMargin, int topMargin, int buttomMargin) {    
            
           System.out.println("设置页面方向和页边距...");    
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
     * 打开文件    
     *    
     * @param inputDoc    
     *            要打开的文件，全路径    
     * @return Dispatch    
     *            打开的文件    
     */     
    public Dispatch openDocument(String inputDoc) {     
   
           System.out.println("打开Word文档...");    
        this.document = Dispatch.call(documents,"Open",inputDoc).toDispatch();    
        this.getSelection();    
        this.getRange();    
        this.getAlignment();    
        this.getFont();    
        this.getPageSetup();    
        return this.document;     
    }     
   
    /**   
     * 创建新的文件   
     *    
     * @return Dispache 返回新建文件   
     */   
    public Dispatch createNewDocument(){    
            
           System.out.println("创建新的文件...");    
        this.document = Dispatch.call(documents,"Add").toDispatch();    
        this.getSelection();    
        this.getRange();    
        this.getPageSetup();    
        this.getAlignment();    
        this.getFont();    
        return this.document;    
    }    
   
    /**    
     * 选定内容    
     * @return Dispatch 选定的范围或插入点    
     */     
    public Dispatch getSelection() {     
   
           System.out.println("获取选定范围的插入点...");    
        this.selection = word.getProperty("Selection").toDispatch();    
        return this.selection;     
    }     
   
    /**   
     * 获取当前Document内可以修改的部分<p><br>   
     * 前提条件：选定内容必须存在   
     *    
     * @param selectedContent 选定区域   
     * @return 可修改的对象   
     */   
    public Dispatch getRange() {    
   
           System.out.println("获取当前Document内可以修改的部分...");    
        this.range = Dispatch.get(this.selection, "Range").toDispatch();    
        return this.range;    
    }    
   
    /**   
     * 获得当前文档的文档页面属性   
     */   
    public Dispatch getPageSetup() {    
            
           System.out.println("获得当前文档的文档页面属性...");    
        if(this.document == null){    
               System.out.println("document对象为空...");    
            return this.pageSetup;    
        }    
        this.pageSetup = Dispatch.get(this.document, "PageSetup").toDispatch();    
        return this.pageSetup;    
    }    
   
    /**    
     * 把选定内容或插入点向上移动    
     * @param count 移动的距离    
     */     
    public void moveUp(int count) {     
   
           System.out.println("把选定内容或插入点向上移动...");    
        for(int i = 0;i < count;i++) {    
            Dispatch.call(this.selection,"MoveUp");    
        }    
    }     
   
    /**    
     * 把选定内容或插入点向下移动    
     * @param count 移动的距离    
     */     
    public void moveDown(int count) {     
   
           System.out.println("把选定内容或插入点向下移动...");    
        for(int i = 0;i < count;i++) {    
            Dispatch.call(this.selection,"MoveDown");    
        }    
    }     
   
    /**    
     * 把选定内容或插入点向左移动    
     * @param count 移动的距离    
     */     
    public void moveLeft(int count) {     
   
           System.out.println("把选定内容或插入点向左移动...");    
        for(int i = 0;i < count;i++) {    
            Dispatch.call(this.selection,"MoveLeft");    
        }    
    }     
   
    /**    
     * 把选定内容或插入点向右移动    
     * @param count 移动的距离    
     */     
    public void moveRight(int count) {     
   
           System.out.println("把选定内容或插入点向右移动...");    
        for(int i = 0;i < count;i++) {    
            Dispatch.call(this.selection,"MoveRight");    
        }    
    }    
        
    /**   
     * 回车键   
     */   
    public void enterDown(int count){    
            
           System.out.println("按回车键...");    
        for(int i = 0;i < count;i++) {    
            Dispatch.call(this.selection, "TypeParagraph");    
        }    
    }    
   
    /**    
     * 把插入点移动到文件首位置    
     */     
    public void moveStart() {     
   
           System.out.println("把插入点移动到文件首位置...");    
        Dispatch.call(this.selection,"HomeKey",new Variant(6));     
    }     
   
    /**    
     * 从选定内容或插入点开始查找文本    
     * @param selection 选定内容    
     * @param toFindText 要查找的文本    
     * @return boolean true-查找到并选中该文本，false-未查找到文本    
     */     
    public boolean find(String toFindText) {     
   
           System.out.println("从选定内容或插入点开始查找文本"+" 要查找内容：  "+toFindText);    
        /* 从selection所在位置开始查询 */   
        Dispatch find = Dispatch.call(this.selection,"Find").toDispatch();     
        /* 设置要查找的内容 */   
        Dispatch.put(find,"Text",toFindText);     
        /* 向前查找 */   
        Dispatch.put(find,"Forward","True");     
        /* 设置格式 */   
        Dispatch.put(find,"Format","True");     
        /* 大小写匹配 */   
        Dispatch.put(find,"MatchCase","True");     
        /* 全字匹配 */   
        Dispatch.put(find,"MatchWholeWord","True");     
        /* 查找并选中 */   
        return Dispatch.call(find,"Execute").getBoolean();     
    }     
   
    /**    
     * 把选定内容替换为设定文本    
     * @param selection 选定内容    
     * @param newText 替换为文本    
     */     
    public void replace(String newText) {     
   
           System.out.println("把选定内容替换为设定文本...");    
        /* 设置替换文本 */   
        Dispatch.put(this.selection,"Text",newText);     
    }     
   
    /**    
     * 全局替换    
     * @param selection 选定内容或起始插入点    
     * @param oldText 要替换的文本    
     * @param replaceObj 替换为文本   
     */     
    public void replaceAll(String oldText,Object replaceObj) {     
   
           System.out.println("全局替换...");    
        /* 移动到文件开头 */   
        moveStart();     
        /* 表格替换方式 */   
        String newText = (String) replaceObj;    
        /* 图片替换方式 */   
        if(oldText.indexOf("image") != -1 || newText.lastIndexOf(".bmp") != -1 || newText.lastIndexOf(".jpg") != -1 || newText.lastIndexOf(".gif") != -1){     
            while (find(oldText)) {     
                insertImage(newText);     
                Dispatch.call(this.selection,"MoveRight");     
            }     
            /* 正常替换方式 */   
        } else {    
            while (find(oldText)) {     
                replace(newText);     
                Dispatch.call(this.selection,"MoveRight");     
            }     
        }    
    }     
   
    /**    
     * 插入图片    
     * @param selection 图片的插入点    
     * @param imagePath 图片文件（全路径）    
     */     
    public void insertImage(String imagePath) {     
   
           System.out.println("插入图片...");    
        Dispatch.call(this.selection, "TypeParagraph");    
        Dispatch.call(Dispatch.get(this.selection,"InLineShapes").toDispatch(),"AddPicture",imagePath);     
    }     
   
    /**   
     * 合并表格   
     *   
     * @param selection 操作对象   
     * @param tableIndex 表格起始点   
     * @param fstCellRowIdx 开始行   
     * @param fstCellColIdx 开始列   
     * @param secCellRowIdx 结束行   
     * @param secCellColIdx 结束列   
     */   
    public void mergeCell(int tableIndex, int fstCellRowIdx, int fstCellColIdx, int secCellRowIdx, int secCellColIdx){    
   
           System.out.println("合并单元格...");    
        if(this.table == null){    
               System.out.println("table对象为空...");    
            return;    
        }    
        Dispatch fstCell = Dispatch.call(table, "Cell",new Variant(fstCellRowIdx), new Variant(fstCellColIdx)).toDispatch();    
        Dispatch secCell = Dispatch.call(table, "Cell",new Variant(secCellRowIdx), new Variant(secCellColIdx)).toDispatch();    
        Dispatch.call(fstCell, "Merge", secCell);    
    }    
   
    /**   
     * 想Table对象中插入数值<p>   
     *     参数形式：ArrayList<Object[]>List.size()为表格的总行数<br>   
     *     Object[]的length属性值应该与所创建的表格列数相同   
     *    
     * @param selection 插入点   
     * @param tableIndex 表格起始点   
     * @param list 数据内容   
     */   
    public void insertToTable(List<Object[]> list){    
   
        System.out.println("向Table对象中插入数据...");    
           System.out.println("向Table对象中插入数据...");    
        if(list == null || list.size() <= 0){    
               System.out.println("写出数据集为空...");    
            return;    
        }    
        if(this.table == null){    
               System.out.println("table对象为空...");    
            return;    
        }    
        for(int i = 0; i < list.size(); i++){    
        	Object[]  strs = list.get(i);    
        	
            for(int j = 0; j<strs.length; j++){    
            	
            	
                /* 遍历表格中每一个单元格，遍历次数与所要填入的内容数量相同 */   
                Dispatch cell = this.getCell(i+1, j+1);    
                /* 选中此单元格 */   
                Dispatch.call(cell, "Select");    
                
                /* 写出内容到此单元格中 */   
                Dispatch.put(this.selection, "Text", strs[j]);    
                /* 移动游标到下一个位置 */   
            
            
            }    
            
            this.moveDown(1);    
        }    
        this.enterDown(1);    
    }    
   
    /**   
     * 在文档中正常插入文字内容   
     *    
     * @param selection 插入点   
     * @param list 数据内容   
     */   
    public void insertToDocument(List<String> list){    
   
           System.out.println("向Document对象中插入数据...");    
        if(list == null || list.size() <= 0){    
               System.out.println("写出数据集为空...");    
            return;    
        }    
        if(this.document == null){    
               System.out.println("document对象为空...");    
            return;    
        }    
        for(String str : list){    
            /* 写出至word中 */   
            this.applyListTemplate(3, 2);    
            Dispatch.put(this.selection, "Text", str);    
            this.moveDown(1);    
            this.enterDown(1);    
        }    
    }    
   
    /**   
     * 创建新的表格   
     *    
     * @param selection 插入点   
     * @param document 文档对象   
     * @param rowCount 行数   
     * @param colCount 列数   
     * @param width 边框数值 0浅色1深色   
     * @return 新创建的表格对象   
     */   
    public Dispatch createNewTable(int rowCount, int colCount, int width){    
   
           System.out.println("创建新的表格...");    
        if(this.tables == null){    
            this.getTables();    
        }    
        this.getRange();    
        if(rowCount > 0 && colCount > 0){    
            this.table = Dispatch.call(this.tables,"Add",this.range,new Variant(rowCount),new Variant(colCount),new Variant(width)).toDispatch();    
        }    
        /* 返回新创建表格 */   
        return this.table;    
    }    
   
    /**   
     * 获取Document对象中的所有Table对象   
     *    
     * @return 所有Table对象   
     */   
    public Dispatch getTables(){    
   
   
           System.out.println("获取所有表格对象...");    
        if(this.document == null){    
               System.out.println("document对象为空...");    
            return this.tables;    
        }    
        this.tables = Dispatch.get(this.document, "Tables").toDispatch();    
        return this.tables;    
    }    
        
    /**   
     * 获取Document中Table的数量   
     *    
     * @return 表格数量   
     */   
    public int getTablesCount(){    
            
           System.out.println("获取文档中表格数量...");    
        if(this.tables == null){    
            this.getTables();    
        }    
        return Dispatch.get(tables, "Count").getInt();    
            
    }    
   
    /**   
     * 获取指定序号的Table对象   
     *    
     * @param tableIndex Table序列   
     * @return   
     */   
    public Dispatch getTable(int tableIndex){    
   
           System.out.println("获取指定表格对象...");    
        if(this.tables == null){    
            this.getTables();    
        }    
        if(tableIndex >= 0){    
            this.table = Dispatch.call(this.tables, "Item", new Variant(tableIndex)).toDispatch();    
        }    
        return this.table;    
    }    
   
    /**   
     * 获取表格的总列数   
     *    
     * @return 总列数   
     */   
    public int getTableColumnsCount() {    
   
           System.out.println("获取表格总行数...");    
        if(this.table == null){    
               System.out.println("table对象为空...");    
            return 0;    
        }    
        return Dispatch.get(this.cols,"Count").getInt();    
    }    
   
    /**   
     * 获取表格的总行数   
     *    
     * @return 总行数   
     */   
    public int getTableRowsCount(){    
   
           System.out.println("获取表格总行数...");    
        if(this.table == null){    
               System.out.println("table对象为空...");    
            return 0;    
        }    
        return Dispatch.get(this.rows,"Count").getInt();    
    }    
    /**   
     * 获取表格列对象   
     *    
     * @return 列对象   
     */   
    public Dispatch getTableColumns() {    
   
           System.out.println("获取表格行对象...");    
        if(this.table == null){    
               System.out.println("table对象为空...");    
            return this.cols;    
        }    
        this.cols = Dispatch.get(this.table,"Columns").toDispatch();    
        return this.cols;    
    }    
   
   
    /**   
     * 获取表格的行对象   
     *    
     * @return 总行数   
     */   
    public Dispatch getTableRows(){    
   
           System.out.println("获取表格总行数...");    
        if(this.table == null){    
               System.out.println("table对象为空...");    
            return this.rows;    
        }    
        this.rows = Dispatch.get(this.table,"Rows").toDispatch();    
        return this.rows;    
    }    
   
    /**   
     * 获取指定表格列对象   
     *    
     * @return 列对象   
     */   
    public Dispatch getTableColumn(int columnIndex) {    
   
           System.out.println("获取指定表格行对象...");    
        if(this.cols == null){    
            this.getTableColumns();    
        }    
        if(columnIndex >= 0){    
            this.col = Dispatch.call(this.cols, "Item", new Variant(columnIndex)).toDispatch();    
        }    
        return this.col;    
    }    
   
    /**   
     * 获取表格中指定的行对象   
     *    
     * @param rowIndex 行序号   
     * @return 行对象   
     */   
    public Dispatch getTableRow(int rowIndex){    
   
           System.out.println("获取指定表格总行数...");    
        if(this.rows == null){    
            this.getTableRows();    
        }    
        if(rowIndex >= 0){    
            this.row = Dispatch.call(this.rows, "Item", new Variant(rowIndex)).toDispatch();    
        }    
        return this.row;    
    }    
        
    /**   
     * 自动调整表格   
     */   
    public void autoFitTable() {    
            
           System.out.println("自动调整表格...");    
        int count = this.getTablesCount();    
        for (int i = 0; i < count; i++) {    
            Dispatch table = Dispatch.call(tables, "Item", new Variant(i + 1)).toDispatch();    
            Dispatch cols = Dispatch.get(table, "Columns").toDispatch();    
            Dispatch.call(cols, "AutoFit");    
        }    
    }    
   
    /**   
     * 获取当前文档中，表格中的指定单元格   
     *   
     * @param CellRowIdx  单元格所在行   
     * @param CellColIdx 单元格所在列   
     * @return 指定单元格对象   
     */   
    public Dispatch getCell(int cellRowIdx, int cellColIdx) {    
   
            
           System.out.println("获取当前文档中，表格中的指定单元格...");    
        if(this.table == null){    
               System.out.println("table对象为空...");    
            return this.cell;    
        }    
        if(cellRowIdx >= 0 && cellColIdx >=0){    
            this.cell = Dispatch.call(this.table, "Cell", new Variant(cellRowIdx),new Variant(cellColIdx)).toDispatch();    
        }    
        return this.cell;    
    }    
   
    /**   
     * 设置文档标题   
     *    
     * @param title 标题内容   
     */   
    public void setTitle(String title){    
            
           System.out.println("设置文档标题...");    
        if(title == null || "".equals(title)){    
               System.out.println("文档标题为空...");    
            return;    
        }    
        Dispatch.call(this.selection, "TypeText", title);     
    }    
        
    /**   
     * 设置当前表格线的粗细   
     *   
     * @param width   
     *        width范围：1<w<13,如果是0，就代表没有框   
     */   
    public void setTableBorderWidth(int width) {    
   
           System.out.println("设置当前表格线的粗细...");    
        if(this.table == null){    
               System.out.println("table对象为空...");    
            return;    
        }    
        /*   
         * 设置表格线的粗细 1：代表最上边一条线 2：代表最左边一条线 3：最下边一条线 4：最右边一条线 5：除最上边最下边之外的所有横线   
         * 6：除最左边最右边之外的所有竖线 7：从左上角到右下角的斜线 8：从左下角到右上角的斜线   
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
     * 对当前selection设置项目符号和编号   
     * @param tabIndex   
     *     1: 项目编号   
     *     2: 编号   
     *     3: 多级编号   
     *     4: 列表样式   
     * @param index   
     *     0:表示没有 ,其它数字代表的是该Tab页中的第几项内容   
     */   
    public void applyListTemplate(int tabIndex,int index){    
   
           System.out.println("对当前selection设置项目符号和编号...");    
        /* 取得ListGalleries对象列表 */   
        Dispatch listGalleries = Dispatch.get(this.word, "ListGalleries").toDispatch();    
        /* 取得列表中一个对象 */   
        Dispatch listGallery = Dispatch.call(listGalleries, "Item", new Variant(tabIndex)).toDispatch();    
        Dispatch listTemplates = Dispatch.get(listGallery, "ListTemplates").toDispatch();    
        if(this.range == null){    
            this.getRange();    
        }    
        Dispatch listFormat = Dispatch.get(this.range, "ListFormat").toDispatch();    
        Dispatch.call(listFormat,"ApplyListTemplate",Dispatch.call(listTemplates, "Item", new Variant(index)), new Variant(true),new Variant(1),new Variant(0));    
    }    
        
    /**   
     * 增加文档目录   
     *   
     * 目前采用固定参数方式，以后可以动态进行调整   
     */   
    public void addTablesOfContents()    
    {    
      /* 取得ActiveDocument、TablesOfContents、range对象 */   
      Dispatch ActiveDocument = word.getProperty("ActiveDocument").toDispatch();    
      Dispatch TablesOfContents = Dispatch.get(ActiveDocument,"TablesOfContents").toDispatch();    
      Dispatch range = Dispatch.get(this.selection, "Range").toDispatch();    
      /* 增加目录 */     
      Dispatch.call(TablesOfContents,"Add",range,new Variant(true),new Variant(1),new Variant(3),new Variant(true),new Variant(""),new Variant(true),new Variant(true));    
        
    }    
   
        
    /**   
     * 设置当前Selection 位置方式   
     * @param selectedContent 0－居左；1－居中；2－居右。   
     */   
    public void setAlignment(int alignmentType) {    
            
           System.out.println("设置当前Selection 位置方式...");    
        if(this.alignment == null){    
            this.getAlignment();    
        }    
        Dispatch.put(this.alignment, "Alignment", alignmentType);    
    }    
        
    /**   
     * 获取当前选择区域的对齐方式   
     *    
     * @return 对其方式对象   
     */   
    public Dispatch getAlignment(){    
            
           System.out.println("获取当前选择区域的对齐方式...");    
        if(this.selection == null){    
            this.getSelection();    
        }    
        this.alignment = Dispatch.get(this.selection, "ParagraphFormat").toDispatch();    
        return this.alignment;    
    }    
        
    /**   
     * 获取字体对象   
     *    
     * @return 字体对象   
     */   
    public Dispatch getFont(){    
            
           System.out.println("获取字体对象...");    
        if(this.selection == null){    
            this.getSelection();    
        }    
        this.font = Dispatch.get(this.selection, "Font").toDispatch();    
        return this.font;    
    }    
        
    /**   
     * 设置选定内容的字体 注：在调用此方法前，选定区域对象selection必须存在   
     *   
     * @param fontName   
     *            字体名称，例如 "宋体"   
     * @param isBold   
     *            粗体   
     * @param isItalic   
     *            斜体   
     * @param isUnderline   
     *            下划线   
     * @param rgbColor   
     *            颜色，例如"255,255,255"   
     * @param fontSize   
     *            字体大小   
     * @param Scale   
     *            字符间距，百分比值。例如 70代表缩放为70%   
     */   
    public void setFontScale(String fontName, boolean isBold, boolean isItalic, boolean isUnderline, String rgbColor, int Scale, int fontSize) {    
            
           System.out.println("设置字体...");    
        Dispatch.put(this.font, "Name", fontName);    
        Dispatch.put(this.font, "Bold", isBold);    
        Dispatch.put(this.font, "Italic", isItalic);    
        Dispatch.put(this.font, "Underline", isUnderline);    
        Dispatch.put(this.font, "Color", rgbColor);    
        Dispatch.put(this.font, "Scaling", Scale);    
        Dispatch.put(this.font, "Size", fontSize);    
    }    
        
    /**    
     * 保存文件    
     * @param outputPath 输出文件（包含路径）    
     */     
    public void saveAs(String outputPath) {     
   
           System.out.println("保存文件...");    
        if(this.document == null){    
               System.out.println("document对象为空...");    
            return;    
        }    
        if(outputPath ==null || "".equals(outputPath)){    
               System.out.println("文件保存路径为空...");    
            return;    
        }    
        Dispatch.call(this.document,"SaveAs",outputPath);     
    }    
        
    public void saveAsHtml(String htmlFile){    
        Dispatch.invoke(this.document,"SaveAs",Dispatch.Method, new Object[]{htmlFile,new Variant(8)}, new int[1]);    
    }    
   
    /**    
     * 关闭文件    
     * @param document 要关闭的文件    
     */     
    public void close() {    
   
           System.out.println("关闭文件...");    
        if(document == null){    
               System.out.println("document对象为空...");    
            return;    
        }    
        Dispatch.call(document,"Close",new Variant(0));     
    }    
   
    /**   
     * 列印word文件   
     *   
     */   
    public void printFile(){    
           System.out.println("打印文件...");    
        if(document == null){    
               System.out.println("document对象为空...");    
            return;    
        }    
        Dispatch.call(document,"PrintOut");    
    }    
   
    /**    
     * 退出程序    
     */     
    public void quit() {  
        System.out.println("退出程序");    
//      word.invoke("Quit",new Variant[0]);   
        Dispatch.call(word, "Quit");
        ComThread.Release();     
    }    
   
   
}  
