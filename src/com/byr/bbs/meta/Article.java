package com.byr.bbs.meta;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Article extends BaseArticle implements Serializable {

	private static final String TAG = Article.class.getSimpleName();

	public static final String CONTENT = "content";
	public static final String ATTACHMENT = "attachment";
	public static final String PREVIOUS_ID = "previous_id";
	public static final String NEXT_ID = "next_id";
	public static final String THREADS_PREVIOUS_ID = "threads_previous_id";
	public static final String THREADS_NEXT_ID = "threads_next_id";

	private String content;
	private Attachment attachment;
	private int previous_id;
	private int next_id;
	private int threads_previous_id;
	private int threads_next_id;

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

	public int getPrevious_id() {
		return previous_id;
	}

	public void setPrevious_id(int previous_id) {
		this.previous_id = previous_id;
	}

	public int getNext_id() {
		return next_id;
	}

	public void setNext_id(int next_id) {
		this.next_id = next_id;
	}

	public int getThreads_previous_id() {
		return threads_previous_id;
	}

	public void setThreads_previous_id(int threads_previous_id) {
		this.threads_previous_id = threads_previous_id;
	}

	public int getThreads_next_id() {
		return threads_next_id;
	}

	public void setThreads_next_id(int threads_next_id) {
		this.threads_next_id = threads_next_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(ATTACHMENT).append(":{").append(attachment.toString())
				.append("}, ");
		builder.append(PREVIOUS_ID).append(":").append(previous_id)
				.append(", ");
		builder.append(next_id).append(":").append(next_id).append(", ");
		builder.append(THREADS_PREVIOUS_ID).append(":").append(", ");
		builder.append(THREADS_NEXT_ID).append(":").append(threads_next_id);
		return builder.toString();
	}

	public static Article parse(String json) {
		try {
			Article article = new Gson().fromJson(json, Article.class);
			JSONObject jo = new JSONObject(json);
			String attachJson = jo.getString(Article.ATTACHMENT);

			article.setAttachment(Attachment.parse(attachJson));

			Log.i(TAG, "article==null? " + Boolean.toString(article == null));
			return article;
		} catch (JsonSyntaxException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
