package com.byr.bbs;

import android.app.Application;
import android.util.Log;

import com.byr.bbs.data.Prefs;

public class GlobalApplication extends Application {

	private static final String TAG = GlobalApplication.class.getSimpleName();
	private static GlobalApplication mApplication;
	private String mUserName = "";

	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		mApplication = this;
		String[] userInfos = Prefs.getUserInfos();
		if (userInfos != null) {
			mUserName = userInfos[0];
		}
	}

	public static GlobalApplication getInstance() {
		return mApplication;
	}

	public String getUserName() {
		return mUserName;
	}

}
