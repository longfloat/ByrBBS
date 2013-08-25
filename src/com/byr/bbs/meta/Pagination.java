package com.byr.bbs.meta;

public class Pagination {

	private static final String TAG = Pagination.class.getSimpleName();

	public static final String PAGE_ALL_COUNT = "page_all_count";
	public static final String PAGE_CURRENT_COUNT = "page_current_count";
	public static final String ITEM_PAGE_COUNT = "item_page_count";
	public static final String ITEM_ALL_COUNT = "item_all_count";

	/**
	 * 总页数
	 */
	private int page_all_count;

	/**
	 * 当前页数
	 */
	private int page_current_count;

	/**
	 * 每页元素个数
	 */
	private int item_page_count;

	/**
	 * 所有元素个数
	 */
	private int item_all_count;

	public int getPage_all_count() {
		return page_all_count;
	}

	public void setPage_all_count(int page_all_count) {
		this.page_all_count = page_all_count;
	}

	public int getPage_current_count() {
		return page_current_count;
	}

	public void setPage_current_count(int page_current_count) {
		this.page_current_count = page_current_count;
	}

	public int getItem_page_count() {
		return item_page_count;
	}

	public void setItem_page_count(int item_page_count) {
		this.item_page_count = item_page_count;
	}

	public int getItem_all_count() {
		return item_all_count;
	}

	public void setItem_all_count(int item_all_count) {
		this.item_all_count = item_all_count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(PAGE_CURRENT_COUNT).append("=")
				.append(page_current_count).append(", ");
		builder.append(PAGE_ALL_COUNT).append("=").append(page_all_count)
				.append(", ");
		builder.append(ITEM_ALL_COUNT).append("=").append(item_all_count)
				.append(", ");
		builder.append(ITEM_PAGE_COUNT).append("=").append(item_page_count);
		return builder.toString();
	}

}
