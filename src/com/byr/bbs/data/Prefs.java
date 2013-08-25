package com.byr.bbs.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import com.byr.bbs.GlobalApplication;

public class Prefs {

	private static final String TAG = Prefs.class.getSimpleName();
	public static final String USER = "user_info";
	public static final String USER_NAME = "user_name";
	public static final String USER_PASSWORD = "user_password";

	public static void saveUserInfos(String userName, String password) {
		Log.i(TAG, "saveUserInfos");
		String base64Name = Base64.encodeToString(userName.getBytes(),
				Base64.DEFAULT);
		String base64Password = Base64.encodeToString(password.getBytes(),
				Base64.DEFAULT);
		SharedPreferences sp = GlobalApplication.getInstance()
				.getSharedPreferences(USER, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(USER_NAME, base64Name);
		editor.putString(USER_PASSWORD, base64Password);
		editor.commit();
	}

	public static String[] getUserInfos() {
		Log.i(TAG, "getUserInfos");
		SharedPreferences sp = GlobalApplication.getInstance()
				.getSharedPreferences(USER, Context.MODE_PRIVATE);

		if (sp.contains(USER_NAME) && sp.contains(USER_PASSWORD)) {
			String[] authInfos = new String[] { "", "" };
			String name = sp.getString(USER_NAME, "");
			if (!name.equals("")) {
				authInfos[0] = new String(Base64.decode(name, Base64.DEFAULT));
			}

			String pwd = sp.getString(USER_PASSWORD, "");
			if (!pwd.equals("")) {
				authInfos[1] = new String(Base64.decode(pwd, Base64.DEFAULT));
			} else {
				return null;
			}
			return authInfos;
		}

		return null;

	}

}
