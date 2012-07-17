/* WordSaveFormat.java
 * ============================================================ 
 * 成都五博软件技术有限公司
 * CHENGDU 5PSOFT TECHNOLOGY CO.,LTD.
 * ============================================================
 *
 * Word文档保存格式枚举
 * 
 * ==========================================================
 * 创建: [2010-4-28 下午05:52:18] by 潘超 
 * ============================================================ 
 */

package com.woobsoft.jacobword;

/**
 * <p>Word文档保存格式枚举</p>
 *
 * @author 潘超
 * @date 2010-4-28 下午05:52:18
 */
public class WordSaveFormat {
	/** Microsoft Office Word 格式 */
	public static final int WORD_FORMAT_DOCUMENT = 0;
	/** Microsoft DOS 文本格式 */
	public static final int WORD_FORMAT_DOS_TEXT = 4;
	/** 标准 HTML 格式 */
	public static final int WORD_FORMAT_HTML = 8;
	/** Word 默认文档文件格式。对于 Microsoft Office Word 2007，这是 DOCX 格式 */
	public static final int WORD_FORMAT_DOCUMENT_DEFALT = 16; 
}
