/* PinyinHelper.java
 * ============================================================ 
 */

package net.firecoder.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author 潘超
 * @date 2010-7-19 下午02:45:59
 */
public class PinyinConverter {
	/**
	 * 
	 * @param name 需要转换的汉字字符串
	 * @param isShort 是否生成拼音简写，每个拼音的首字母，并转换为大写
	 * @return
	 */
	public static String toPinyin(String name, boolean isShort){
		if (name == null) return "";
		String pinyin = "";
		
		char[] nameChar = name.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = 
                                           new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                	String onePinYin = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
                	if (isShort) {
                		onePinYin = onePinYin.substring(0, 1).toUpperCase();
                	}
                	pinyin += onePinYin;
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
            	pinyin += nameChar[i];
            }
        }
        return pinyin;
	}
	
	public static void main(String[] args) {
        System.out.println(PinyinConverter.toPinyin("绿", true));
    }
}
