/* ListFormulaElementAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.pojo.FormulaElementPojo;

/**
 * @author ≈À≥¨
 * create: 2011-7-26
 */
@ParentPackage("json-default")
public class ListFormulaElementAction extends ActionSupport  {
	private FormulaManager formulaManager;
	
	private FormulaElementPojo elementTerm;
	private List<FormulaElementPojo> elementList;
	
	@Action(value="listFormulaEl",
		results= {
			@Result(name="success", type="json")
		}
	)
	public String listFormulaElement() {
		try {
			elementList = formulaManager.listElementByFormulaId(elementTerm).getItems();
		} catch (BeanException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public void setFormulaManager(FormulaManager formulaManager) {
		this.formulaManager = formulaManager;
	}
	
	public void setElementTerm(FormulaElementPojo elementTerm) {
		this.elementTerm = elementTerm;
	}

	public List<FormulaElementPojo> getElementList() {
		return elementList;
	}

}
