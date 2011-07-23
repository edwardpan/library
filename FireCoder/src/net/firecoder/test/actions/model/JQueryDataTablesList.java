/* JQueryDataTablesList.java
 * project: FireCoder
 */
package net.firecoder.test.actions.model;

import net.firecoder.test.dao.Pagination;

/**
 * ��jquery_datatables���ʹ�õı�����ԭ��
 * ���е�����get������get�ؼ��ֺ�ĵ�һ����ĸ��ΪСд��
 * �Ա㷵�ص�JSON�����ܱ�jquery_datatablesʶ���磺iDisplayStart�����ı�����
 * (�Զ�ת�������JSON������get��������Ϊ��������)
 * @author �˳�
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
