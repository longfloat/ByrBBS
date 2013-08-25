package com.byr.bbs.meta;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * Created by Liutong on 13-7-27.
 */
public class BoardDetails extends Board {
	private static final String TAG = BoardDetails.class.getSimpleName();

	public static final String PAGINATION = "pagination";
	public static final String ARTICLE = "article";

	private ArrayList<Threads> articles;
	private Pagination pagination;

	public ArrayList<Threads> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Threads> articles) {
		this.articles = articles;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append(PAGINATION).append("=").append(pagination.toString())
				.append(", ");
		builder.append(ARTICLE).append("=").append(articles.size());

		return builder.toString();
	}

	public static BoardDetails parse(String json) {
		try {
			Gson gson = new Gson();
			BoardDetails details = gson.fromJson(json, BoardDetails.class);
			JSONObject jo = new JSONObject(json);
			Pagination pagination = gson.fromJson(
					jo.getString(BoardDetails.PAGINATION), Pagination.class);
			details.setPagination(pagination);

			JSONArray ja = jo.getJSONArray(BoardDetails.ARTICLE);
			ArrayList<Threads> articles = new ArrayList<Threads>();
			for (int i = 0; i < ja.length(); ++i) {
				String str = ja.getString(i);
				String user = new JSONObject(str).getString(Article.USER);
				if (user.startsWith("{")) {
					Threads threads = Threads.parse(str);
					if (threads != null) {
						articles.add(Threads.parse(str));
					}
				}
			}

			details.setArticles(articles);

			return details;

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
