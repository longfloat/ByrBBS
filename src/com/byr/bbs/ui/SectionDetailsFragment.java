package com.byr.bbs.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.byr.bbs.R;
import com.byr.bbs.meta.Board;
import com.byr.bbs.meta.Section;
import com.byr.bbs.meta.SectionDetails;
import com.byr.bbs.net.BaseApi;
import com.byr.bbs.net.IHttpHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Liutong on 13-8-4.
 */
public class SectionDetailsFragment extends AbstractListFragment {

	private static final String TAG = SectionDetailsFragment.class
			.getSimpleName();
	private static final String RESTORE_SECTION_DETAILS = "restore_section_details";
	private static final String RESTORE_SUB_SECTIONS = "restore_subsections";
	private static final String RESTORE_BOARDS = "restore_boards";
	private static final String RESTORE_DESCRIPTION = "restore_description";
	private static final String RESTORE_SECTION_NAME = "restore_section_name";

	private String mSectionName = "0";
	private String mDescription = "";

	private SectionDetails mSectionDetails;
	private ArrayList<Board> mBoards;
	private ArrayList<String> mSubSections;
	private SectionDetailsAdapter mAdapter;

	private GetSectionDetailsTask mTask = null;
	private LayoutInflater mInflater;

	private SectionDetailsItemClickListener mListenter;

	public interface SectionDetailsItemClickListener {
		public void onItemClick(Object item);
	}

	@Override
	public void onAttach(Activity activity) {
		Log.i(TAG, "onAttach");
		super.onAttach(activity);
		try {
			mListenter = (SectionDetailsItemClickListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ "must implement SectionDetailsItemClickListener!");
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState != null) {
			Log.i(TAG, "savedInstanceState not null");
			mSectionDetails = (SectionDetails) savedInstanceState
					.getSerializable(RESTORE_SECTION_DETAILS);
			mBoards = (ArrayList<Board>) savedInstanceState
					.getSerializable(RESTORE_BOARDS);
			mSubSections = (ArrayList<String>) savedInstanceState
					.getSerializable(RESTORE_SUB_SECTIONS);
			mDescription = savedInstanceState.getString(RESTORE_DESCRIPTION);
			mSectionName = savedInstanceState.getString(RESTORE_SECTION_NAME);
		} else {
			Log.i(TAG, "savedInstanceState is null");
			Bundle bundle = getArguments();
			mInflater = LayoutInflater.from(getActivity());
			if (bundle != null) {
				mSectionName = bundle.getString(Section.NAME,
						Section.SECTION_NAME_DEFAULT);
				mDescription = bundle.getString(Section.DESCRIPTION);
			}

			mBoards = new ArrayList<Board>();
			mSubSections = new ArrayList<String>();

		}

		getActivity().getActionBar().setTitle(mDescription);

		mAdapter = new SectionDetailsAdapter();
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int i, long l) {
				mListenter.onItemClick(mAdapter.getItem(i));
			}
		});

		if (savedInstanceState == null) {
			mTask = new GetSectionDetailsTask();
			String[] params = new String[] { mSectionName };
			mTask.execute(params);
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_base, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			if (null == mTask) {
				mTask = new GetSectionDetailsTask();
				String[] params = new String[] { mSectionName };
				mTask.execute(params);
			}

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onResume() {
		Log.i(TAG, "onResume");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.i(TAG, "onPause");
		super.onPause();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.i(TAG, "onSaveInstanceState");
		super.onSaveInstanceState(outState);
		outState.putSerializable(RESTORE_SECTION_DETAILS, mSectionDetails);
		outState.putSerializable(RESTORE_BOARDS, mBoards);
		outState.putString(RESTORE_DESCRIPTION, mDescription);
		outState.putSerializable(RESTORE_SUB_SECTIONS, mSubSections);
		outState.putString(RESTORE_SECTION_NAME, mSectionName);
	}

	private class SectionDetailsAdapter extends BaseAdapter {
		private static final int SUB_SECTIONS = 0;
		private static final int BOARDS = 1;

		@Override
		public int getCount() {
			return mSubSections.size() + mBoards.size();
		}

		@Override
		public Object getItem(int i) {
			if (i < mSubSections.size()) {
				return mSubSections.get(i);
			} else {
				return mBoards.get(i - mSubSections.size());
			}
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public int getItemViewType(int position) {
			if (position < mSubSections.size()) {
				return SUB_SECTIONS;
			} else {
				return BOARDS;
			}
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public boolean isEmpty() {
			return mSubSections.isEmpty() && mBoards.isEmpty();
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			ViewHolder holder;
			if (null == view) {
				view = mInflater.inflate(R.layout.list_item_section, viewGroup,
						false);
				holder = new ViewHolder(view);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			if (getItemViewType(i) == SUB_SECTIONS) {
				holder.linear.setVisibility(View.GONE);
				holder.title.setTextColor(getResources().getColor(
						R.color.holo_blue_dark));
				holder.title.setText(mSubSections.get(i));
			} else {
				Board board = mBoards.get(i - mSubSections.size());
				holder.linear.setVisibility(View.VISIBLE);
				holder.title.setTextColor(getResources().getColor(
						R.color.text_default));
				holder.title.setText(board.getDescription());
				holder.todayCount.setText(Integer.toString(board
						.getPost_today_count()));
				holder.threadsCount.setText(Integer.toString(board
						.getPost_threads_count()));
				holder.userOnlineCount.setText(Integer.toString(board
						.getUser_online_count()));
				holder.manager.setText(board.getManager());
			}

			return view;
		}
	}

	public static class ViewHolder {
		public TextView title;
		public LinearLayout linear;
		public TextView todayCount;
		public TextView threadsCount;
		public TextView userOnlineCount;
		public TextView manager;

		public ViewHolder(View view) {
			title = (TextView) view.findViewById(R.id.item_section_title);
			linear = (LinearLayout) view.findViewById(R.id.item_section_board);
			todayCount = (TextView) view
					.findViewById(R.id.item_board_post_today_count);
			threadsCount = (TextView) view
					.findViewById(R.id.item_board_post_threads_count);
			userOnlineCount = (TextView) view
					.findViewById(R.id.item_board_user_online_count);
			manager = (TextView) view.findViewById(R.id.item_board_manager);
		}
	}

	private class GetSectionDetailsTask extends
			AsyncTask<String, Void, SectionDetails> {
		@Override
		protected SectionDetails doInBackground(String... strings) {
			Log.d(TAG, "GetSectionDetailsTask doInBackground");
			try {
				SectionDetails details = BaseApi.getSectionDetails(strings[0]);
				return details;
			} catch (IHttpHelper.HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showViewForTaskPre();
		}

		@Override
		protected void onPostExecute(SectionDetails sectionDetails) {
			Log.d(TAG, "GetSectionDetailsTask onPostExecute");
			super.onPostExecute(sectionDetails);
			mTask = null;
			mLoadingStatusView.setVisibility(View.GONE);
			if (null == sectionDetails) {
				mEmptyView.setVisibility(View.VISIBLE);
				mListView.setVisibility(View.GONE);

				mSubSections.clear();
				mBoards.clear();
				mSectionDetails = null;
			} else {
				mEmptyView.setVisibility(View.GONE);
				mListView.setVisibility(View.VISIBLE);

				mSectionDetails = sectionDetails;
				mBoards = mSectionDetails.getBoards();
				mSubSections = mSectionDetails.getSubsections();

				mDescription = mSectionDetails.getDescription();
				getActivity().getActionBar().setTitle(mDescription);
			}

			mAdapter.notifyDataSetChanged();
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
			mTask = null;
			showViewForTaskCancel();
		}
	}
}
