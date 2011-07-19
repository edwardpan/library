/* ListAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.pojo.FormulaPojo;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ≈À≥¨
 * create: 2011-7-19
 */
public class ListAction extends ActionSupport {
	
	private FormulaManager formulaManager;
	private List<FormulaPojo> list;
	
	@Action(value="list",
		results={
			@Result(name="success", location="/formula/list.jsp")
		}
	)
	public String listFormula() {
		try {
			list = formulaManager.listFormulas();
		} catch (BeanException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	public void setFormulaManager(FormulaManager formulaManager) {
		this.formulaManager = formulaManager;
	}
	
	public List<FormulaPojo> getList() {
		return list;
	}
}
