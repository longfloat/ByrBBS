package com.byr.bbs.net;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.byr.bbs.data.Prefs;
import com.byr.bbs.utils.Constants;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;

public class NetHttpHelper implements IHttpHelper {

	private static final String TAG = NetHttpHelper.class.getSimpleName();

	private static final int CONNECT_TIMEOUT = 10 * 1000;
	private static final int READ_TIMEOUT = 10 * 1000;

	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String METHOD_GET = "GET";
	public static final String METHOD_POST = "POST";

	public static final String HEADER_KEY_CONNECTION = "Connection";
	public static final String HEADER_VALUE_CONNECTION = "Keep-Alive";
	public static final String HEADER_KEY_CHARSET = "Charset";
	public static final String HEADER_VALUE_CHARSET = "UTF-8";
	public static final String HEADER_KEY_ACCEPT_ENCODING = "Accept-Encoding";
	public static final String HEADER_VALUE_ACCEPT_ENCODING = "gzip, deflate";
	public static final String HEADER_KEY_CONTENT_LENGTH = "Content-Length";

	private MyAuthenticator mAuthenticator;

	public NetHttpHelper() {
		mAuthenticator = new MyAuthenticator();
	}

	public NetHttpHelper(String username, String password) {
		mAuthenticator = new MyAuthenticator(username, password);
	}

	private static class MyAuthenticator extends Authenticator {
		private String mUsername;
		private String mPassword;

		public MyAuthenticator(String username, String password) {
			mUsername = username;
			mPassword = password;
		}

		public MyAuthenticator() {
			String[] infos = Prefs.getUserInfos();
			mUsername = infos[0];
			mPassword = infos[1];
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(mUsername,
					mPassword.toCharArray());
		}
	}

	public static Map<String, String> generateHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(HEADER_KEY_CONNECTION, HEADER_VALUE_CONNECTION);
		headers.put(HEADER_KEY_CHARSET, HEADER_VALUE_CHARSET);
		headers.put(HEADER_KEY_ACCEPT_ENCODING, HEADER_VALUE_ACCEPT_ENCODING);
		return headers;
	}

	@Override
	public String get(GetRequest request) throws IOException, HttpException {
		return get(request.getUrl(), request.getHeaders());
	}

	@Override
	public String get(String url, Map<String, String> requestHeaders)
			throws IOException, HttpException {
		HttpURLConnection connection = null;
		try {
			connection = createConnection(url, requestHeaders);
			connection.setRequestMethod(METHOD_GET);
			connection.setDoOutput(false);
			connection.connect();
			return handleResponse(connection);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	@Override
	public String post(PostRequest request) throws HttpException, IOException {
		return post(request.getUrl(), request.getHeaders(),
				request.getContent());
	}

	@Override
	public String post(String url, Map<String, String> requestHeaders,
			String content) throws HttpException, IOException {
		HttpURLConnection connection = null;
		try {
			if (requestHeaders == null) {
				requestHeaders = new HashMap<String, String>();
			}
			requestHeaders.put(HEADER_KEY_CONTENT_LENGTH,
					Integer.toString((content == null) ? 0 : content.length()));
			connection = createConnection(url, requestHeaders);
			connection.setRequestMethod(METHOD_POST);
			connection.setDoInput(connection != null);
			connection.setDoOutput(connection != null);
			connection.setUseCaches(false);
			connection.connect();

			if (content != null) {
				DataOutputStream dos = new DataOutputStream(
						connection.getOutputStream());
				dos.write(content.getBytes());
				dos.flush();
				dos.close();
			}

			return handleResponse(connection);
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	private HttpURLConnection createConnection(String url,
			Map<String, String> headers) throws IOException {
		Authenticator.setDefault(mAuthenticator);
		URL u = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) u.openConnection();
		connection.setConnectTimeout(CONNECT_TIMEOUT);
		connection.setReadTimeout(READ_TIMEOUT);
		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				connection.addRequestProperty(key, value);
			}
		}
		return connection;
	}

	private String handleResponse(HttpURLConnection connection)
			throws IOException, HttpException {
		int statusCode = connection.getResponseCode();

		if (statusCode != HttpURLConnection.HTTP_OK) {
			throw new HttpException(statusCode, connection.getResponseMessage());
		}

		return readStream(connection);
	}

	private String readStream(HttpURLConnection connection) throws IOException,
			HttpException {
		InputStream is = null;
		BufferedReader reader = null;
		try {
			is = connection.getInputStream();
			String contentEncode = connection.getContentEncoding();
			if (connection != null
					&& !Constants.EMPTY_STRING.equals(contentEncode)
					&& Constants.ENCODING_GZIP.equals(contentEncode)) {
				is = new GZIPInputStream(is);
			}
			reader = new BufferedReader(new InputStreamReader(is,
					DEFAULT_CHARSET));
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

			return builder.toString();

		} finally {
			try {
				if (is != null)
					is.close();

				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Bitmap readStreamForImage(HttpURLConnection connection)
			throws IOException, HttpException {
		InputStream is = null;
		Bitmap image = null;
		try {
			is = connection.getInputStream();
			String contentEncode = connection.getContentEncoding();
			if (connection != null
					&& !Constants.EMPTY_STRING.equals(contentEncode)
					&& Constants.ENCODING_GZIP.equals(contentEncode)) {
				is = new GZIPInputStream(is);
			}
			image = BitmapFactory.decodeStream(is);

			return image;
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String getCookieString(HttpURLConnection connection) {
		StringBuilder builder = new StringBuilder();
		String headName = null;
		for (int i = 0; (headName = connection.getHeaderField(i)) != null; ++i) {
			StringTokenizer tokenizer = new StringTokenizer(headName,
					Constants.COOKIE_DELIMITER);
			while (tokenizer.hasMoreTokens()) {
				builder.append(tokenizer.nextToken()).append(
						Constants.COOKIE_DELIMITER);
			}
		}

		return builder.toString();
	}

}
