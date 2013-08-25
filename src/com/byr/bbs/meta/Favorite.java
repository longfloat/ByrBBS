package com.byr.bbs.meta;

import java.io.Serializable;

public class Favorite implements Serializable {

	private static final String TAG = Favorite.class.getSimpleName();

	public static final String LEVEL = "level";
	public static final String DESCRIPTION = "description";
	public static final String POSITION = "position";

	/**
	 * 收藏夹级数, 顶层收藏夹level为0
	 */
	private int level;

	/**
	 * 收藏夹目录
	 */
	private String description;

	/**
	 * 收藏夹目录位置, 该值用于删除收藏夹目录
	 */
	private int position;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
