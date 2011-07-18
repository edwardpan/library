package net.firecoder.expressionhelper.functions;

import net.firecoder.expressionhelper.ExpressionHelper;

import org.junit.Test;



public class StringFunctionsTest {
	
	@Test
	public void testReplace() {
//		String exp = "�ҵ�Ӣ�������ǣ�{$strToReplaceFirst(\" Edward Pan��Bruce �� Lee \", \" \", \"\")}";
//		String exp = "�ҵ�Ӣ�������ǣ�{$strToTrim(\" Edward Pan  \")}";
		String exp = "�ҵ�Ӣ�������ǣ�{$strToLowerCase(\" Edward Pan  \")}";
		ExpressionHelper helper = ExpressionHelper.getInstance();
		helper.setFullExpStr(exp);
		String result = helper.executeFullStr();
		System.out.println(result);
	}
}
