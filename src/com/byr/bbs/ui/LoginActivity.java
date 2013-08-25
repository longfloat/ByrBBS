package com.byr.bbs.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.byr.bbs.R;
import com.byr.bbs.data.Prefs;
import com.byr.bbs.net.BaseApi;
import com.byr.bbs.net.NetHttpHelper;
import com.byr.bbs.utils.UIUtils;

import java.io.IOException;

/**
 * Created by Liutong on 13-7-30.
 */
public class LoginActivity extends Activity {
	private static final String TAG = LoginActivity.class.getSimpleName();

	private EditText mUsernameEdit;
	private EditText mPasswordEdit;
	private CheckBox mPasswordVisibility;
	private Button mLogin;
	private ProgressDialog mProgress;

	private UserLoginTask mLoginTask = null;
	private String mUsername;
	private String mPassword;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Prefs.getUserInfos() != null) {
			UIUtils.toMainActivity(LoginActivity.this);
			finish();
		}
		setContentView(R.layout.activity_login);

		mUsernameEdit = (EditText) findViewById(R.id.login_username);
		mPasswordEdit = (EditText) findViewById(R.id.login_password);
		mPasswordVisibility = (CheckBox) findViewById(R.id.login_password_visibility);
		mLogin = (Button) findViewById(R.id.login_button);

		mProgress = UIUtils.createProgressDialog(LoginActivity.this, null,
				getString(R.string.prompt_signing_in));

		mLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				doLogin();
			}
		});

		mPasswordVisibility
				.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton compoundButton,
							boolean isChecked) {
						if (isChecked) {
							mPasswordEdit
									.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
						} else {
							mPasswordEdit
									.setInputType(InputType.TYPE_CLASS_TEXT
											| InputType.TYPE_TEXT_VARIATION_PASSWORD);
						}

					}
				});
	}

	private boolean checkLoginInfo() {
		boolean isUsernameValid = false;
		boolean isPasswordValid = false;

		if (TextUtils.isEmpty(mUsernameEdit.getText().toString())) {
			mUsernameEdit.setError(getString(R.string.prompt_username_empty));
			isUsernameValid = false;
		} else {
			mUsername = mUsernameEdit.getText().toString();
			isUsernameValid = true;
		}

		if (TextUtils.isEmpty(mPasswordEdit.getText().toString())) {
			mPasswordEdit.setError(getString(R.string.prompt_password_empty));
			isPasswordValid = false;
		} else {
			mPassword = mPasswordEdit.getText().toString();
			isPasswordValid = true;
		}
		return isUsernameValid && isPasswordValid;
	}

	private void doLogin() {
		Log.i(TAG, "doLogin");

		if (mLoginTask != null) {
			return;
		}

		if (checkLoginInfo()) {
			mLoginTask = new UserLoginTask();
			mLoginTask.execute();
		}
	}

	private enum LoginStatus {
		NOT_MATCH, NET_ERROR, SUCCESS;
	}

	private class UserLoginTask extends AsyncTask<Void, Void, LoginStatus> {
		@Override
		protected LoginStatus doInBackground(Void... voids) {
			final String url = BaseApi.USER_LOGIN_API + BaseApi.FORMAT_JSON
					+ BaseApi.API_KEY;
			try {
				Log.i(TAG, "url=" + url);
				String json = new NetHttpHelper(mUsername, mPassword).get(url,
						NetHttpHelper.generateHeaders());
				Log.i(TAG, "login result: " + json);
				if (json.startsWith(BaseApi.REQUEST_NOT_FOUND)) {
					return LoginStatus.NOT_MATCH;
				}
			} catch (IOException e) {
				e.printStackTrace();
				return LoginStatus.NET_ERROR;
			}
			Prefs.saveUserInfos(mUsername, mPassword);
			return LoginStatus.SUCCESS;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgress.show();
			mLogin.setEnabled(false);
		}

		@Override
		protected void onPostExecute(LoginStatus result) {
			super.onPostExecute(result);
			mProgress.dismiss();
			int messageId = R.string.prompt_login_success;
			switch (result) {
			case SUCCESS:
				messageId = R.string.prompt_login_success;
				Toast.makeText(LoginActivity.this, messageId,
						Toast.LENGTH_SHORT).show();
				UIUtils.toMainActivity(LoginActivity.this);
				finish();
				return;
			case NOT_MATCH:
				messageId = R.string.prompt_username_password_not_match;
				mLogin.setEnabled(true);
				break;
			case NET_ERROR:
				messageId = R.string.prompt_net_error;
				mLogin.setEnabled(true);
				break;
			}

			Toast.makeText(LoginActivity.this, messageId, Toast.LENGTH_SHORT)
					.show();
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
			mLoginTask = null;
			mLogin.setEnabled(true);
		}
	}
}