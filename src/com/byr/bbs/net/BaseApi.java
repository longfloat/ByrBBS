package com.byr.bbs.net;

import java.io.IOException;
import java.util.ArrayList;

import android.util.Log;

import com.byr.bbs.meta.Article;
import com.byr.bbs.meta.BaseArticle;
import com.byr.bbs.meta.BoardDetails;
import com.byr.bbs.meta.SectionDetails;
import com.byr.bbs.meta.SectionList;
import com.byr.bbs.meta.Threads;
import com.byr.bbs.meta.User;
import com.byr.bbs.meta.Widget;
import com.byr.bbs.net.params.BoardParams;

public class BaseApi {

	private static final String TAG = BaseApi.class.getSimpleName();

	public static final String BASE_BBS_URL = "http://bbs.byr.cn/";
	public static final String BASE_API_URL = "http://api.byr.cn";
	public static final String API_KEY = "?appkey=78e223c052793f0b&";
	public static final String API_KEY2 = "appkey=78e223c052793f0b&";
	public static final String FORMAT_JSON = ".json";
	public static final String FORMAT_XML = ".xml";
	public static final String SUFFIX = FORMAT_JSON + API_KEY;

	public static final String USER_API = BASE_API_URL + "/user";
	public static final String USER_LOGIN_API = BASE_API_URL + "/user/login";
	public static final String USER_LOGOUT_API = BASE_API_URL + "/user/logout";

	public static final String SECTION_API = BASE_API_URL + "/section";
	public static final String BOARD_API = BASE_API_URL + "/board";
	public static final String ARTICLE_API = BASE_API_URL + "/article";
	public static final String THREADS_API = BASE_API_URL + "/threads";
	public static final String ATTCHMENT_API = BASE_API_URL + "/attachment";
	public static final String MAIL_API = BASE_API_URL + "/mail";
	public static final String FAVORITE_API = BASE_API_URL + "/favorite";
	public static final String SEARCH_API = BASE_API_URL + "/search";
	public static final String REFER_API = BASE_API_URL + "/refer";
	public static final String WIDGET_TOPTEN_API = BASE_API_URL
			+ "/widget/topten";
	public static final String WIDGET_RECOMMEND_API = BASE_API_URL
			+ "/widget/recommend";
	public static final String WIDGET_SECTION_HOT_API = BASE_API_URL
			+ "/widget";

	public static final String REQUEST_NOT_FOUND = "{\"request\"";

	public static User getUserInfo() throws IHttpHelper.HttpException,
			IOException {
		final String url = String.format("%s%s%s", USER_LOGIN_API, FORMAT_JSON,
				API_KEY);

		String info = new NetHttpHelper().get(url,
				NetHttpHelper.generateHeaders());
		if (info.startsWith(REQUEST_NOT_FOUND)) {
			return null;
		} else {
			return User.parse(info);
		}
	}

	public static SectionList getSectionList() throws IOException,
			IHttpHelper.HttpException {
		final String url = String.format("%s%s%s", SECTION_API, FORMAT_JSON,
				API_KEY);
		Log.i(TAG, url);
		String json = new NetHttpHelper().get(url,
				NetHttpHelper.generateHeaders());
		SectionList sectionList = SectionList.parse(json);
		return sectionList;

	}

	public static SectionDetails getSectionDetails(String name)
			throws IOException, IHttpHelper.HttpException {
		final String url = String.format("%s/%s%s%s", SECTION_API, name,
				FORMAT_JSON, API_KEY);
		Log.i(TAG, url);
		String json = new NetHttpHelper().get(url,
				NetHttpHelper.generateHeaders());
		SectionDetails details = SectionDetails.parse(json);
		return details;
	}

	public static Article getArticle(String name, int id) throws IOException,
			IHttpHelper.HttpException {
		final String url = String.format("%s/%s/%s%s%s", ARTICLE_API, name, id,
				FORMAT_JSON, API_KEY);
		Log.i(TAG, url);

		String json = new NetHttpHelper().get(url,
				NetHttpHelper.generateHeaders());
		Log.i(TAG, "article result-->" + json);
		Article article = Article.parse(json);
		return article;
	}

	public static BaseArticle getBaseArticle(String name, int id)
			throws IOException, IHttpHelper.HttpException {
		final String url = String.format("%s/%s/%s%s%s", ARTICLE_API, name, id,
				FORMAT_JSON, API_KEY);
		Log.i(TAG, url);
		String json = new NetHttpHelper().get(url,
				NetHttpHelper.generateHeaders());
		Log.i(TAG, "article result-->" + json);
		BaseArticle article = BaseArticle.parse(json);
		return article;
	}

	public static BoardDetails getBoardDetails(String name) throws IOException,
			IHttpHelper.HttpException {
		final String url = String.format("%s/%s%s%s", BOARD_API, name,
				FORMAT_JSON, API_KEY);
		Log.i(TAG, url);
		String json = new NetHttpHelper().get(url,
				NetHttpHelper.generateHeaders());
		return BoardDetails.parse(json);
	}

	public static BoardDetails getBoardDetails(BoardParams params)
			throws IOException, IHttpHelper.HttpException {
		final String url = String.format("%s/%s&%s", BOARD_API,
				params.getBoardParams(), API_KEY2);
		Log.i(TAG, url);
		String json = new NetHttpHelper().get(url,
				NetHttpHelper.generateHeaders());
		Log.i(TAG, json);
		return BoardDetails.parse(json);
	}

	public static ArrayList<Threads> getTopten() throws IOException,
			IHttpHelper.HttpException {
		final String url = String.format("%s%s%s", WIDGET_TOPTEN_API,
				FORMAT_JSON, API_KEY);
		Log.i(TAG, url);
		String json = new NetHttpHelper().get(url,
				NetHttpHelper.generateHeaders());
		return Widget.parseTopten(json);
	}

}
