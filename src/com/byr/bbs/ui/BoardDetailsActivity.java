package com.byr.bbs.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.byr.bbs.R;

public class BoardDetailsActivity extends FragmentActivity {
	private static final String TAG = BoardDetailsActivity.class
			.getSimpleName();

	public static final String BOARD_DETAIL_ARGS = "board_detail_args";
	private static final String FRAGMENT_TAG = "board_details_fragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_board_details);

		BoardDetailsFragment fragment = new BoardDetailsFragment();
		fragment.setArguments(getIntent().getBundleExtra(BOARD_DETAIL_ARGS));

		getActionBar().setDisplayHomeAsUpEnabled(true);

		getSupportFragmentManager().beginTransaction()
				.add(R.id.board_details, fragment, FRAGMENT_TAG).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.board_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
