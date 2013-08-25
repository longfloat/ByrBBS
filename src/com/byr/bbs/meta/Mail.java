package com.byr.bbs.meta;

import java.io.Serializable;

public class Mail implements Serializable {

	private static final String TAG = Mailbox.class.getSimpleName();

	public static final String INDEX = "index";
	public static final String IS_M = "is_m";
	public static final String IS_READ = "is_read";
	public static final String IS_REPLY = "is_reply";
	public static final String HAS_ATTACHMENT = "has_attachment";
	public static final String TITLE = "title";
	public static final String USER = "user";
	public static final String POST_TIME = "post_time";
	public static final String BOX_NAME = "box_name";
	public static final String CONTENT = "content";
	public static final String ATTACHMENT = "attachment";

	private int index;
	private boolean is_m;
	private boolean is_read;
	private boolean is_reply;
	private boolean has_attachment;
	private String title;

	/**
	 * 发信人, 如果发信人不存在则为发信人的id, 即user_id
	 */
	private User user;
	private String user_id;
	private int post_time;
	private String box_name;
	private String content;
	private Attachment attachment;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isIs_m() {
		return is_m;
	}

	public void setIs_m(boolean is_m) {
		this.is_m = is_m;
	}

	public boolean isIs_read() {
		return is_read;
	}

	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}

	public boolean isIs_reply() {
		return is_reply;
	}

	public void setIs_reply(boolean is_reply) {
		this.is_reply = is_reply;
	}

	public boolean isHas_attachment() {
		return has_attachment;
	}

	public void setHas_attachment(boolean has_attachment) {
		this.has_attachment = has_attachment;
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getPost_time() {
		return post_time;
	}

	public void setPost_time(int post_time) {
		this.post_time = post_time;
	}

	public String getBox_name() {
		return box_name;
	}

	public void setBox_name(String box_name) {
		this.box_name = box_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

}
