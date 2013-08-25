package com.byr.bbs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import com.byr.bbs.R;
import com.byr.bbs.meta.Board;
import com.byr.bbs.meta.Section;

/**
 * Created by Liutong on 13-8-4.
 */
public class SectionDetailsActivity extends FragmentActivity implements
		SectionDetailsFragment.SectionDetailsItemClickListener {

	private static final String TAG = SectionDetailsActivity.class
			.getSimpleName();
	public static final String SECTION_DETAIL_ARGS = "section_detail_args";
	private static final String FRAGMENT_TAG = "section_details_fragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_section_details);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		Bundle args = getIntent().getBundleExtra(SECTION_DETAIL_ARGS);

		SectionDetailsFragment fragment = new SectionDetailsFragment();
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.section_details, fragment, FRAGMENT_TAG).commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onItemClick(Object item) {
		if (item instanceof String) {
			Log.i(TAG, "Subsection item click!");
			SectionDetailsFragment fragment = new SectionDetailsFragment();

			Bundle args = new Bundle();
			args.putString(Section.NAME, (String) item);
			args.putString(Section.DESCRIPTION, (String) item);
			fragment.setArguments(args);
			FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();
			ft.addToBackStack(null);
			ft.replace(R.id.section_details, fragment, FRAGMENT_TAG);
			ft.commit();
		} else if (item instanceof Board) {
			Log.i(TAG, "Board item click!");
			Bundle args = new Bundle();
			args.putString(Board.NAME, ((Board) item).getName());
			args.putString(Board.DESCRIPTION, ((Board) item).getDescription());
			Intent intent = new Intent(SectionDetailsActivity.this,
					BoardDetailsActivity.class);
			intent.putExtra(BoardDetailsActivity.BOARD_DETAIL_ARGS, args);
			startActivity(intent);
		}
	}
}
