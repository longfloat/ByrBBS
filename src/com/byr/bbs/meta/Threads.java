package com.byr.bbs.meta;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by Liutong on 13-7-27.
 */
public class Threads extends BaseArticle {

	public static final String REPLY_COUNT = "reply_count";
	public static final String LAST_REPLY_USER_ID = "last_reply_user_id";
	public static final String LAST_REPLY_TIME = "last_reply_time";

	private int reply_count;
	private String last_reply_user_id;
	private int last_reply_time;

	public int getReply_count() {
		return reply_count;
	}

	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}

	public String getLast_reply_user_id() {
		return last_reply_user_id;
	}

	public void setLast_reply_user_id(String last_reply_user_id) {
		this.last_reply_user_id = last_reply_user_id;
	}

	public int getLast_reply_time() {
		return last_reply_time;
	}

	public void setLast_reply_time(int last_reply_time) {
		this.last_reply_time = last_reply_time;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(REPLY_COUNT).append(":").append(reply_count)
				.append(", ");
		builder.append(LAST_REPLY_USER_ID).append(":")
				.append(last_reply_user_id).append(", ");
		builder.append(last_reply_time).append(":").append(last_reply_time);
		return builder.toString();
	}

	public static Threads parse(String json) {
		try {
			return new Gson().fromJson(json, Threads.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
}
