package net.firecoder.expressionhelper.functions;

import net.firecoder.expressionhelper.ExpressionHelper;

import org.junit.Test;



public class StringFunctionsTest {
	
	@Test
	public void testReplace() {
//		String exp = "我的英文名称是：{$strToReplaceFirst(\" Edward Pan，Bruce · Lee \", \" \", \"\")}";
//		String exp = "我的英文名称是：{$strToTrim(\" Edward Pan  \")}";
		String exp = "我的英文名称是：{$strToLowerCase(\" Edward Pan  \")}";
		ExpressionHelper helper = ExpressionHelper.getInstance();
		helper.setFullExpStr(exp);
		String result = helper.executeFullStr();
		System.out.println(result);
	}
}
