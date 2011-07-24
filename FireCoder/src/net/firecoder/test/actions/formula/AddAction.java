/* FormulaAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula;

import java.util.List;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.pojo.FormulaElementPojo;
import net.firecoder.test.pojo.FormulaPojo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author 潘超
 * create: 2011-7-19
 */
@ParentPackage("json-default")
public class AddAction extends ActionSupport implements ModelDriven<FormulaPojo> {
	
	private FormulaManager formulaManager;
	
	private FormulaPojo formula = new FormulaPojo();
	private List<FormulaElementPojo> formulaElList;
	
	@Action(value="add",
		results={
			@Result(name="success", type="json")
		}
	)
	public String execute() {
		// TODO 接收计算元素参数
		try {
			formulaManager.addFormula(formula);
		} catch (BeanException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public void setFormulaManager(FormulaManager formulaManager) {
		this.formulaManager = formulaManager;
	}

	@Override
	public FormulaPojo getModel() {
		return formula;
	}

	public List<FormulaElementPojo> getFormulaElList() {
		return formulaElList;
	}

	public void setFormulaElList(List<FormulaElementPojo> formulaElList) {
		this.formulaElList = formulaElList;
	}
	
}
