package com.byr.bbs.meta;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Widget implements Serializable {

	private static final String TAG = Widget.class.getSimpleName();

	/**
	 * 十大热门话题
	 */
	public static final String TOPTEN = "topten";

	/**
	 * 推荐文章
	 */
	public static final String RECOMMEND = "recommend";

	/**
	 * 分区热门话题
	 */
	public static final String SECTION_NAME = "section-name";

	public static final String NAME = "name";
	public static final String TITLE = "title";
	public static final String TIME = "time";

	/**
	 * widget标识
	 */
	private String name;
	private String title;

	/**
	 * 上次修改时间
	 */
	private int time;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public static ArrayList<Threads> parseTopten(String json) {
		try {
			Gson gson = new Gson();
			JSONObject jo = new JSONObject(json);
			Type type = new TypeToken<ArrayList<Threads>>() {
			}.getType();
			return gson.fromJson(jo.getString("article"), type);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

}
