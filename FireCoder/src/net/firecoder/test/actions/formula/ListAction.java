/* ListAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula;

import net.firecoder.test.actions.model.JQueryDataTablesList;
import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaPojo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author ≈À≥¨
 * create: 2011-7-20
 */
@ParentPackage("json-default")
public class ListAction extends ActionSupport implements ModelDriven<JQueryDataTablesList> {
	private FormulaManager formulaManager;

	private JQueryDataTablesList<FormulaPojo> list = new JQueryDataTablesList<FormulaPojo>();

	@Action(value="list",
		results={
			@Result(name="success", type="json")
		}
	)
	public String listFormula() {
		try {
			list.setPage(
					formulaManager.listFormulas(null, list.getiDisplayStart(), list.getiDisplayLength()));
			list.setITotalRecords((int)list.getPage().getTotalCount());
			list.setITotalDisplayRecords((int)list.getPage().getTotalCount());
		} catch (BeanException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public void setFormulaManager(FormulaManager formulaManager) {
		this.formulaManager = formulaManager;
	}

	@Override
	public JQueryDataTablesList getModel() {
		return list;
	}

}
