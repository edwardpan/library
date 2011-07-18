package net.firecoder.expressionhelper;

import java.util.ArrayList;
import java.util.List;

import net.firecoder.expressionhelper.ExpressionHelper;
import net.firecoder.expressionhelper.Variable;

import org.junit.Test;


public class ExpressionHelperTest {

	@Test
	public void testComputeDouble() {
		String exp = "今天是：[EXP]$SYSDATE() + \"，这天啊，热的！\"[/EXP]，明天是：[EXP]\"星期五\"[/EXP]，对头。";
//		String exp = "{0.00002 * 100 / 3}, {\"今天是：\" + $SYSDATE() + Va}";
		List<Variable> varList = new ArrayList<Variable>();
		varList.add(new Variable("Va", 11));
		ExpressionHelper helper = ExpressionHelper.getInstance();
		helper.setFullExpStr(exp);
		helper.setVariables(varList);
		String result = helper.executeFullStr();
		System.out.println(result);
	}

}
