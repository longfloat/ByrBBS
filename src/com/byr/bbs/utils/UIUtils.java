package com.byr.bbs.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.byr.bbs.ui.MainActivity;

/**
 * Created by Liutong on 13-7-30.
 */
public class UIUtils {
	private static final String TAG = UIUtils.class.getSimpleName();

	public static ProgressDialog createProgressDialog(Context context,
			String title, CharSequence message) {
		ProgressDialog dialog = new ProgressDialog(context);
		if (title != null) {
			dialog.setTitle(title);
		}

		if (message != null) {
			dialog.setMessage(message);
		}

		return dialog;
	}

	public static void toMainActivity(Context context) {
		Intent intent = new Intent(context, MainActivity.class);
		context.startActivity(intent);

	}

}
