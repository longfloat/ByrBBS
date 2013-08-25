package com.byr.bbs.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;

public class BaseUtils {
	private static final String TAG = BaseUtils.class.getSimpleName();

	public static String encodeUrl(List<NameValuePair> pairs) {
		if (null == pairs) {
			return Constants.EMPTY_STRING;
		}

		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (NameValuePair nameValuePair : pairs) {

			try {
				if (first) {
					first = false;
				} else {
					builder.append(Constants.URL_AND);
				}

				builder.append(URLEncoder.encode(nameValuePair.getName(),
						Constants.ENCODING_UTF8));
				builder.append(Constants.URL_EQUAL);
				builder.append(URLEncoder.encode(nameValuePair.getValue(),
						Constants.ENCODING_UTF8));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		return builder.toString();
	}

	public static String getFriendlyTime(long milliseconds) {
		final long DAY_MILLS = 24 * 3600 * 1000;
		final String FORMAT_TODAY = "HH:mm";
		final String FORMAT_OTHER = "yyyy-MM-dd";
		long current = System.currentTimeMillis();
		SimpleDateFormat sdf = null;
		if (current - milliseconds < DAY_MILLS) {
			sdf = new SimpleDateFormat(FORMAT_TODAY, Locale.getDefault());
		} else {
			sdf = new SimpleDateFormat(FORMAT_OTHER, Locale.getDefault());
		}

		return sdf.format(new Date(milliseconds));
	}

}
