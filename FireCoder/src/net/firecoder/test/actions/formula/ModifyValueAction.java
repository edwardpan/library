/* FormulaAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula;

import net.firecoder.test.actions.model.JQueryDataTablesEdit;
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
public class ModifyValueAction extends ActionSupport implements ModelDriven<JQueryDataTablesEdit> {
	
	private FormulaManager formulaManager;
	
	private JQueryDataTablesEdit editor = new JQueryDataTablesEdit();
    
    @Action(value="modifyValue",
    	results={
    		@Result(name="success", type="json")
    	}
    )
    public String modifyValue() {
    	
    	
    	return SUCCESS;
    }
	
	public void setFormulaManager(FormulaManager formulaManager) {
		this.formulaManager = formulaManager;
	}

	@Override
	public JQueryDataTablesEdit getModel() {
		return editor;
	}
	
}
