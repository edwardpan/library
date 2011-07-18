package net.firecoder.expressionhelper;

import java.util.ArrayList;
import java.util.List;

import net.firecoder.expressionhelper.ExpressionHelper;
import net.firecoder.expressionhelper.Variable;

import org.junit.Test;


public class ExpressionHelperTest {

	@Test
	public void testComputeDouble() {
		String exp = "�����ǣ�[EXP]$SYSDATE() + \"�����찡���ȵģ�\"[/EXP]�������ǣ�[EXP]\"������\"[/EXP]����ͷ��";
//		String exp = "{0.00002 * 100 / 3}, {\"�����ǣ�\" + $SYSDATE() + Va}";
		List<Variable> varList = new ArrayList<Variable>();
		varList.add(new Variable("Va", 11));
		ExpressionHelper helper = ExpressionHelper.getInstance();
		helper.setFullExpStr(exp);
		helper.setVariables(varList);
		String result = helper.executeFullStr();
		System.out.println(result);
	}

}
