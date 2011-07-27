/* RunAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.firecoder.expressionhelper.ExpressionHelper;
import net.firecoder.expressionhelper.Variable;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.pojo.FormulaPojo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author �˳�
 * create: 2011-7-26
 */
@ParentPackage("json-default")
public class RunAction extends ActionSupport {
	
	private FormulaManager formulaManager;
	
	private String runFormulaId;
	private String[] runElNames;
	private String[] runElValues;
	private String[] runElTypes;
	private String runResult;
	
	@Action(value="runFormula",
		results= {
			@Result(name="success", type="json")
		}
	)
	public String run() {
		FormulaPojo pojo = new FormulaPojo();
		try {
			pojo.setId(Integer.parseInt(runFormulaId));
			pojo = formulaManager.getFormulaById(pojo);
			
			List<Variable> varList = new ArrayList<Variable>();
			for (int i = 0; i < runElNames.length; i++) {
				String value = runElValues[i];
				String type = runElTypes[i];
				Object valueObj = null;
				
				// ���ݴ���Ԫ�ص��������ͣ�����ת��Ϊ��Ӧ����
				BigDecimal d = new BigDecimal(value);
				if ("int".equals(type)) {
					valueObj = d.intValue();
				} else if ("double".equals(type)) {
					valueObj = d.doubleValue();
				}
				
				// ��������
				Variable var = new Variable(runElNames[i], valueObj);
				varList.add(var);
			}
			
			String exp = pojo.getExpression();
			if ("Y".equals(pojo.getIsSetScale()) && pojo.getScale() >= 0) {// ȡ����
				exp = "$mathToRoundUp(" + exp + ", " + pojo.getScale() + ")";
			}
			exp = "[EXP]" + exp + "[/EXP]";// �����ɱ�ʶ��ı��ʽ�ַ���
			
			// ��ʼ����
			ExpressionHelper helper = new ExpressionHelper();
			helper.setFullExpStr(exp);
			helper.setVariables(varList);
			runResult = helper.executeFullStr();
			
			runResult += pojo.getResultUnit();// �������λ�Ľ��
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public void setFormulaManager(FormulaManager formulaManager) {
		this.formulaManager = formulaManager;
	}

	public void setRunFormulaId(String runFormulaId) {
		this.runFormulaId = runFormulaId;
	}

	public void setRunElNames(String[] runElNames) {
		this.runElNames = runElNames;
	}

	public void setRunElValues(String[] runElValues) {
		this.runElValues = runElValues;
	}

	public String getRunResult() {
		return runResult;
	}

	public void setRunElTypes(String[] runElTypes) {
		this.runElTypes = runElTypes;
	}
	
}
