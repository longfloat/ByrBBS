package com.byr.bbs.meta;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Liutong on 13-7-27.
 */
public class ThreadsDetails extends Threads implements Serializable {

	private static final String TAG = Threads.class.getSimpleName();

	public static final String PAGINATION = "pagination";
	public static final String ARTICLE = "article";

	private Pagination pagination;
	private ArrayList<Article> articles;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}
}
