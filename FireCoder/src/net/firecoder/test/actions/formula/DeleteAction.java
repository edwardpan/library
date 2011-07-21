/* FormulaAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.pojo.FormulaPojo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author ≈À≥¨
 * create: 2011-7-19
 */
@ParentPackage("json-default")
public class DeleteAction extends ActionSupport implements ModelDriven<FormulaPojo> {
	
	private FormulaManager formulaManager;
	
	private FormulaPojo formula = new FormulaPojo();
	
	@Action(value="delete",
		results={
			@Result(name="success", type="json")
		}
	)
	public String execute() {
		try {
			formulaManager.deleteFormulaById(formula);
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
	
}
