package com.byr.bbs.net.params;

import com.byr.bbs.net.BaseApi;

/**
 * Created by Liutong on 13-8-22.
 */
public class BoardParams {

	public static final String NAME = "name";
	public static final String MODE = "mode";
	public static final String COUNT = "count";
	public static final String PAGE = "page";

	private static final int MODE_DEFAULT = 2;
	private static final int COUNT_DEFAULT = 30;
	private static final int PAGE_DEFAULT = 1;

	/**
	 * 合法的版面名称
	 * */
	private String name;

	/**
	 * 版面文章列表模式
	 * <ul>
	 * <li>0: 以id为顺序列表</li>
	 * <li>1: 文摘区列表</li>
	 * <li>2: 同主题(web顺序)列表</li>
	 * <li>3: 精华区列表</li>
	 * <li>4: 回收站列表</li>
	 * <li>5: 废纸篓列表</li>
	 * <li>6: 同主题(发表顺序)列表</li>
	 * </ul>
	 * */
	private int mode;

	/**
	 * 每页文章数量
	 * */
	private int count;

	/**
	 * 文章的页数
	 * */
	private int page;

	public BoardParams(String name, int mode, int count, int page) {
		this.name = name;
		this.mode = mode;
		this.count = count;
		this.page = page;
	}

	public BoardParams(String name) {
		this.name = name;
		this.mode = MODE_DEFAULT;
		this.count = COUNT_DEFAULT;
		this.page = PAGE_DEFAULT;
	}

	public BoardParams(String name, int page) {
		this.name = name;
		this.mode = MODE_DEFAULT;
		this.count = COUNT_DEFAULT;
		this.page = page;
	}

	public String getBoardParams() {
		StringBuilder builder = new StringBuilder();
		builder.append(name).append(BaseApi.FORMAT_JSON).append("?");
		builder.append(MODE).append("=").append(mode).append("&");
		builder.append(COUNT).append("=").append(count).append("&");
		builder.append(PAGE).append("=").append(page);
		return builder.toString();
	}
}
