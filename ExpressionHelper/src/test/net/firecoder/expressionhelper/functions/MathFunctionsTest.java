package net.firecoder.expressionhelper.functions;

import java.math.BigDecimal;

import net.firecoder.expressionhelper.ExpressionHelper;

import org.junit.Test;



public class MathFunctionsTest {
	
	@Test
	public void test() {
//		BigDecimal d = new BigDecimal("0.1248601835");
//		System.out.println(d.setScale(4, BigDecimal.ROUND_UP));
		
		String exp = "[EXP]$mathToRoundUp(0.1278601835, 4)[/EXP]";
		ExpressionHelper helper = ExpressionHelper.getInstance();
		helper.setFullExpStr(exp);
		System.out.println(helper.executeFullStr());
	}
}
