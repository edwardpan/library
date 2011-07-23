/* JQueryDataTablesList.java
 * project: FireCoder
 */
package net.firecoder.test.actions.model;

import net.firecoder.test.dao.Pagination;

/**
 * 因jquery_datatables插件使用的变量名原因，
 * 类中的所有get方法中get关键字后的第一个字母都为小写，
 * 以便返回的JSON数据能被jquery_datatables识别，如：iDisplayStart这样的变量。
 * (自动转换输出的JSON数据以get方法名作为变量名。)
 * @author 潘超
 * create: 2011-7-22
 */
public class JQueryDataTablesList<T> {
	private Pagination<T> page;
	private int	iDisplayStart;
	private int	iDisplayLength;
	private String sSearch;
	private int	iTotalRecords;
	private int	iTotalDisplayRecords;
	private String sEcho;
	
	public Pagination<T> getPage() {
		return page;
	}
	public void setPage(Pagination<T> page) {
		this.page = page;
	}
	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setIDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setIDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setSSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setITotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setITotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public String getsEcho() {
		return sEcho;
	}
	public void setSEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	
}
