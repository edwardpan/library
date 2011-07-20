/* Pagination.java
 * project: FireCoder
 */
package net.firecoder.test.dao;

import java.util.List;

/**
 * ·ÖÒ³½Ó¿Ú
 * create: 2011-7-16
 */
public class Pagination<T> {
	public final static int DEFAULT_PAGESIZE = 20;

	private int pageSize = DEFAULT_PAGESIZE;

	private List<T> items;

	private long totalCount;

	private int[] indexes = new int[0];

	private int startIndex = 0;

	public Pagination(List<T> items, long totalCount) {
		setPageSize(DEFAULT_PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(0);
	}

	public Pagination(List<T> items, long totalCount, int startIndex) {
		setPageSize(DEFAULT_PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
	}

	public Pagination(List<T> items, long totalCount, int pageSize, int startIndex) {
		setPageSize(DEFAULT_PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			int count = (int) totalCount / DEFAULT_PAGESIZE;
			if (totalCount % DEFAULT_PAGESIZE > 0)
				count++;
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = DEFAULT_PAGESIZE * i;
			}
		} else {
			this.totalCount = 0;
		}
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int getStartIndex() {
		return startIndex;
	}
	
	public int getStartIndex(int index) {
		return indexes[index];
	}

	public void setStartIndex(int startIndex) {
		if (totalCount <= 0)
			this.startIndex = 0;
		else if (startIndex >= totalCount)
			this.startIndex = indexes[indexes.length - 1];
		else if (startIndex < 0)
			this.startIndex = 0;
		else {
			this.startIndex = indexes[startIndex / DEFAULT_PAGESIZE];
		}
	}

	public int getNextIndex() {
		int nextIndex = getStartIndex() + DEFAULT_PAGESIZE;
		if (nextIndex >= totalCount)
			return getStartIndex();
		else
			return nextIndex;
	}

	public int getPreviousIndex() {
		int previousIndex = getStartIndex() - DEFAULT_PAGESIZE;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}

}
