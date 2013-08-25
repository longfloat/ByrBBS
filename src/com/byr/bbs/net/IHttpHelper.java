package com.byr.bbs.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liutong on 13-7-24.
 */
public interface IHttpHelper {

	public String get(GetRequest request) throws IOException, HttpException;

	public String get(String url, Map<String, String> requestHeaders)
			throws IOException, HttpException;

	public String post(PostRequest request) throws IOException, HttpException;

	public String post(String url, Map<String, String> requestHeaders,
			String content) throws IOException, HttpException;

	// public void setConnectTimeout(int timeoutMillis);
	//
	// public void setReadTimeout(int timeoutMillis);

	public static class GetRequest {
		private String mUrl;
		private Map<String, String> mHeaders;

		public GetRequest(String url) {
			mUrl = url;
		}

		public String getUrl() {
			return mUrl;
		}

		public void setUrl(String url) {
			mUrl = url;
		}

		public Map<String, String> getHeaders() {
			return mHeaders;
		}

		public void setHeaders(String name, String value) {
			if (mHeaders == null) {
				mHeaders = new HashMap<String, String>();
			}

			mHeaders.put(name, value);
		}
	}

	public static class PostRequest extends GetRequest {
		private String mContent;

		public PostRequest(String url) {
			super(url);
		}

		public String getContent() {
			return mContent;
		}

		public void setContent(String content) {
			mContent = content;
		}
	}

	public static class HttpException extends IOException {
		private final int mStatusCode;
		private final String mReasonPhrase;

		public HttpException(int statusCode, String reasonPhrase) {
			super(statusCode + " " + reasonPhrase);
			mStatusCode = statusCode;
			mReasonPhrase = reasonPhrase;
		}

		/**
		 * Gets the HTTP response status code.
		 */
		public int getStatusCode() {
			return mStatusCode;
		}

		/**
		 * Gets the HTTP response reason phrase.
		 */
		public String getReasonPhrase() {
			return mReasonPhrase;
		}
	}
}
