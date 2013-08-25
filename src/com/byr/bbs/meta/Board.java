package com.byr.bbs.meta;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.Serializable;

public class Board implements Serializable {

	private static final String TAG = Board.class.getSimpleName();
	private static final boolean LOCAL_LOG_V = false;

	public static final String NAME = "name";
	public static final String MANAGER = "manager";
	public static final String DESCRIPTION = "description";
	public static final String CLASS = "class";
	public static final String SECTION = "section";
	public static final String POST_TODAY_COUNT = "post_today_count";
	public static final String POST_THREADS_COUNT = "post_threads_count";
	public static final String POST_ALL_COUNT = "post_all_count";
	public static final String IS_READ_ONLY = "is_read_only";
	public static final String IS_NO_REPLY = "is_no_reply";
	public static final String ALLOW_ATTACHMENT = "allow_attachment";
	public static final String ALLOW_ANONYMOUS = "allow_anonymous";
	public static final String ALLOW_OUTGO = "allow_post";
	public static final String ALLOW_POST = "allow_post";
	public static final String USER_ONLINE_COUNT = "user_online_count";

	private String name;

	/**
	 * 版主列表, 以空格分隔各id
	 */
	private String manager;

	private String description;

	/**
	 * 版面所述类别
	 */
	private String board_class;

	/**
	 * 版面所属分区号
	 */
	private String section;

	/**
	 * 今日发文总数
	 */
	private int post_today_count;

	/**
	 * 版面主题总数
	 */
	private int post_threads_count;

	/**
	 * 版面文章总数
	 */
	private int post_all_count;
	private boolean is_read_only;
	private boolean is_no_only;
	private boolean allow_attachment;
	private boolean allow_anonymous;

	/**
	 * 版面是否允许转信
	 */
	private boolean allow_outgo;

	/**
	 * 当前登录用户是否有发文/回复权限
	 */
	private boolean allow_post;
	private int user_online_count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBoard_class() {
		return board_class;
	}

	public void setBoard_class(String board_class) {
		this.board_class = board_class;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getPost_today_count() {
		return post_today_count;
	}

	public void setPost_today_count(int post_today_count) {
		this.post_today_count = post_today_count;
	}

	public int getPost_threads_count() {
		return post_threads_count;
	}

	public void setPost_threads_count(int post_threads_count) {
		this.post_threads_count = post_threads_count;
	}

	public int getPost_all_count() {
		return post_all_count;
	}

	public void setPost_all_count(int post_all_count) {
		this.post_all_count = post_all_count;
	}

	public boolean isIs_read_only() {
		return is_read_only;
	}

	public void setIs_read_only(boolean is_read_only) {
		this.is_read_only = is_read_only;
	}

	public boolean isIs_no_only() {
		return is_no_only;
	}

	public void setIs_no_only(boolean is_no_only) {
		this.is_no_only = is_no_only;
	}

	public boolean isAllow_attachment() {
		return allow_attachment;
	}

	public void setAllow_attachment(boolean allow_attachment) {
		this.allow_attachment = allow_attachment;
	}

	public boolean isAllow_anonymous() {
		return allow_anonymous;
	}

	public void setAllow_anonymous(boolean allow_anonymous) {
		this.allow_anonymous = allow_anonymous;
	}

	public boolean isAllow_outgo() {
		return allow_outgo;
	}

	public void setAllow_outgo(boolean allow_outgo) {
		this.allow_outgo = allow_outgo;
	}

	public boolean isAllow_post() {
		return allow_post;
	}

	public void setAllow_post(boolean allow_post) {
		this.allow_post = allow_post;
	}

	public int getUser_online_count() {
		return user_online_count;
	}

	public void setUser_online_count(int user_online_count) {
		this.user_online_count = user_online_count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(NAME).append(":").append(name).append(", ");
		builder.append(DESCRIPTION).append(":").append(description)
				.append(", ");
		builder.append(SECTION).append(":").append(section).append(", ");
		builder.append(USER_ONLINE_COUNT).append(":").append(user_online_count);
		return builder.toString();
	}

	public static Board parse(String json) {
		try {
			Gson gson = new Gson();
			Board board = gson.fromJson(json, Board.class);
			if (LOCAL_LOG_V) {
				Log.i(TAG, board.toString());
			}

			return board;
		} catch (JsonSyntaxException e) {
			Log.e(TAG, e.getMessage());
			return null;
		}
	}

}
