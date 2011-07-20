/* ListAction.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula;

import net.firecoder.test.beans.BeanException;
import net.firecoder.test.beans.formula.FormulaManager;
import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaPojo;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author ≈À≥¨
 * create: 2011-7-20
 */
@ParentPackage("json-default")
public class ListAction extends ActionSupport {
	private FormulaManager formulaManager;

	private Pagination<FormulaPojo> page;
	private int	iDisplayStart;
	private int	iDisplayLength;
	private String sSearch;
	private int	iTotalRecords;
	private int	iTotalDisplayRecords;
	private String sEcho;

	@Action(value="list",
		results={
			@Result(name="success", type="json")
		}
	)
	public String listFormula() {
		try {
			page = formulaManager.listFormulas(iDisplayStart, iDisplayLength);
			iTotalRecords = (int)page.getTotalCount();
			iTotalDisplayRecords = (int)page.getTotalCount();
		} catch (BeanException e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public void setFormulaManager(FormulaManager formulaManager) {
		this.formulaManager = formulaManager;
	}

	public Pagination<FormulaPojo> getPage() {
		return page;
	}

	public void setPage(Pagination<FormulaPojo> page) {
		this.page = page;
	}

	public void setIDisplayStart(int displayStart) {
		iDisplayStart = displayStart;
	}

	public void setIDisplayLength(int displayLength) {
		iDisplayLength = displayLength;
	}

	public void setSSearch(String search) {
		sSearch = search;
	}

	public int getITotalRecords() {
		return iTotalRecords;
	}

	public int getITotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public String getSEcho() {
		return sEcho;
	}

	public void setSEcho(String echo) {
		sEcho = echo;
	}
	
}
