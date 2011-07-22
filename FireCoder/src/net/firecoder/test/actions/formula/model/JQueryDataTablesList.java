/* JQueryDataTablesList.java
 * project: FireCoder
 */
package net.firecoder.test.actions.formula.model;

import net.firecoder.test.dao.Pagination;
import net.firecoder.test.pojo.FormulaPojo;

/**
 * @author ≈À≥¨
 * create: 2011-7-22
 */
public class JQueryDataTablesList {
	private Pagination<FormulaPojo> page;
	private int	iDisplayStart;
	private int	iDisplayLength;
	private String sSearch;
	private int	iTotalRecords;
	private int	iTotalDisplayRecords;
	private String sEcho;
	
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

	public int getIDisplayStart() {
		return iDisplayStart;
	}

	public int getIDisplayLength() {
		return iDisplayLength;
	}

	public String getSSearch() {
		return sSearch;
	}

	public void setITotalRecords(int totalRecords) {
		iTotalRecords = totalRecords;
	}

	public void setITotalDisplayRecords(int totalDisplayRecords) {
		iTotalDisplayRecords = totalDisplayRecords;
	}
}
