package net.firecoder.expressionhelper.functions;

import java.util.ArrayList;
import java.util.List;

import net.firecoder.expressionhelper.ExpressionHelper;
import net.firecoder.expressionhelper.Variable;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateFunctionsTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void testConvertDateFormat() {
		
//		String exp = "今天的日期为：{$dateToNewFormat(\"2011-3-23\", \"yyyy-M-d\", \"yyyy/MM/dd\")}";
//		String exp = "今天的日期为：{$dateToString([2011-03-23], \"yyyyMMdd\") + \"  \" + VALUE}";
		String exp = "今天的日期为：[EXP]$dateToString($SYSDATE(), \"yyyyMMdd\")[/EXP]，315的日期为：[EXP]$dateToString([2011-03-15], \"yyyyMMdd\")[/EXP]";
//		String exp = "今天的日期为：{$stringToDate(\"2011-3-23\")}";
		List<Variable> variables = new ArrayList<Variable>();
		variables.add(new Variable("VALUE", "123"));
		logger.info("日志输出测试");
		
		ExpressionHelper helper = ExpressionHelper.getInstance();
		helper.setFullExpStr(exp);
		helper.setVariables(variables);
		String result = helper.executeFullStr();
		System.out.println(result);
	}

}
