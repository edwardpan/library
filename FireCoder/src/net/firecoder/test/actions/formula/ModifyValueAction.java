/* FormulaAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula;

import java.lang.reflect.Field;

import net.firecoder.test.actions.model.JQueryDataTablesEdit;
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
public class ModifyValueAction extends ActionSupport implements ModelDriven<JQueryDataTablesEdit> {
	
	private FormulaManager formulaManager;
	
	private JQueryDataTablesEdit editor = new JQueryDataTablesEdit();
    
    @Action(value="modifyValue",
    	results={
    		@Result(name="success", type="json")
    	}
    )
    public String modifyValue() {
    	FormulaPojo pojo = new FormulaPojo();
    	pojo.setId(Integer.parseInt(editor.getId()));
    	try {
			pojo = formulaManager.getFormulaById(pojo);
			Field field = pojo.getClass().getDeclaredField(editor.getColumnName());
			field.setAccessible(true);
			field.set(pojo, editor.getValue());
			formulaManager.modifyFormulaById(pojo);
		} catch (BeanException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    	
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
