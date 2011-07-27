package net.firecoder.test.actions;

import net.firecoder.expressionhelper.ExpressionHelper;

import org.junit.Test;


public class ExpTest {
	@Test
	public void test() {
//		double r = 0.00055d + 0.0001d;
////		BigDecimal d = new BigDecimal("0.0002").add(new BigDecimal("0.0001"));
//		System.out.println(r);
//		BigDecimal d = new BigDecimal(r);
//		BigDecimal result = d.setScale(4, BigDecimal.ROUND_HALF_UP);
//		r = Double.valueOf(result.toString());
//		System.out.println(r);
		
		String exp = "[EXP]$mathToRoundUp(0.00055d + 0.0001d, 4)[/EXP]";
		ExpressionHelper helper = ExpressionHelper.getInstance();
		helper.setFullExpStr(exp);
		System.out.println(helper.executeFullStr());
	}
}
