package com.byr.bbs.meta;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by Liutong on 13-7-27.
 */
public class BaseArticle {
	private static final String TAG = BaseArticle.class.getSimpleName();

	public static final String ID = "id";
	public static final String GROUP_ID = "group_id";
	public static final String REPLY_ID = "reply_id";
	public static final String FLAG = "flag";
	public static final String POSITION = "position";
	public static final String IS_TOP = "is_top";
	public static final String IS_SUBJECT = "is_subject";
	public static final String HAS_ATTACHMENT = "has_attachment";
	public static final String IS_ADMIN = "is_admin";
	public static final String TITLE = "title";
	public static final String USER = "user";
	public static final String POST_TIME = "post_time";
	public static final String BOARD_NAME = "board_name";

	private int id;
	private int group_id;
	private int reply_id;
	private String flag;
	private int position;
	private boolean is_top;
	private boolean is_subject;
	private boolean has_attachment;
	private boolean is_admin;
	private String title;
	private User user;
	private int post_time;
	private String board_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isIs_top() {
		return is_top;
	}

	public void setIs_top(boolean is_top) {
		this.is_top = is_top;
	}

	public boolean isIs_subject() {
		return is_subject;
	}

	public void setIs_subject(boolean is_subject) {
		this.is_subject = is_subject;
	}

	public boolean isHas_attachment() {
		return has_attachment;
	}

	public void setHas_attachment(boolean has_attachment) {
		this.has_attachment = has_attachment;
	}

	public boolean isIs_admin() {
		return is_admin;
	}

	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPost_time() {
		return post_time;
	}

	public void setPost_time(int post_time) {
		this.post_time = post_time;
	}

	public String getBoard_name() {
		return board_name;
	}

	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(TITLE).append(":").append(title).append(", ");
		builder.append(ID).append(":").append(id).append(", ");
		builder.append(GROUP_ID).append(":").append(group_id).append(", ");
		builder.append(HAS_ATTACHMENT).append(":").append(has_attachment)
				.append(", ");
		builder.append(USER).append(":").append(user).append(", ");
		builder.append(BOARD_NAME).append(":").append(board_name).append(", ");
		return builder.toString();
	}

	public static BaseArticle parse(String json) {
		try {
			return new Gson().fromJson(json, BaseArticle.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
}
