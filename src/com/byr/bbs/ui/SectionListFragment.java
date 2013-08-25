package com.byr.bbs.ui;

import android.content.Intent;
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
import android.widget.TextView;

import com.byr.bbs.R;
import com.byr.bbs.meta.Section;
import com.byr.bbs.meta.SectionList;
import com.byr.bbs.net.BaseApi;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Liutong on 13-8-2.
 */
public class SectionListFragment extends AbstractListFragment {
	private static final String TAG = SectionListFragment.class.getSimpleName();
	private static final String RESTORE_SECTION_LIST = "restore_section_list";
	private static final String RESTORE_SECTIONS = "restore_sections";
	private SectionList mSectionList;
	private ArrayList<Section> mSections;
	private SectionListAdapter mAdapter;

	private GetSectionListTask mTask = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "onActivityCreated");
		View view = super.onCreateView(inflater, container, savedInstanceState);
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				String name = mSections.get(position).getName();
				String description = mSections.get(position).getDescription();
				Bundle bundle = new Bundle();
				bundle.putString(Section.NAME, name);
				bundle.putString(Section.DESCRIPTION, description);

				Intent intent = new Intent(getActivity(),
						SectionDetailsActivity.class);
				intent.putExtra(SectionDetailsActivity.SECTION_DETAIL_ARGS,
						bundle);

				startActivity(intent);
			}
		});
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.i(TAG, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);

		if (savedInstanceState != null) {
			mSectionList = (SectionList) savedInstanceState
					.getSerializable(RESTORE_SECTION_LIST);
			mSections = (ArrayList<Section>) savedInstanceState
					.getSerializable(RESTORE_SECTIONS);
		} else {
			mSectionList = new SectionList();
		}

		mAdapter = new SectionListAdapter();
		mListView.setAdapter(mAdapter);

		if (savedInstanceState == null) {
			mTask = new GetSectionListTask();
			mTask.execute();
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_base, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			if (mTask != null)
				return true;
			else {
				mTask = new GetSectionListTask();
				mTask.execute();
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.i(TAG, "onSaveInstanceState");
		super.onSaveInstanceState(outState);
		outState.putSerializable(RESTORE_SECTION_LIST, mSectionList);
		outState.putSerializable(RESTORE_SECTIONS, mSections);
	}

	private class SectionListAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public SectionListAdapter() {
			mInflater = LayoutInflater.from(getActivity());
		}

		@Override
		public int getCount() {
			return mSectionList.getSection_count();
		}

		@Override
		public Object getItem(int i) {
			return mSections.get(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			if (null == view) {
				view = mInflater.inflate(R.layout.list_item_topten, null);
			}

			TextView text = (TextView) view
					.findViewById(R.id.list_item_topten_text);
			text.setText(mSections.get(i).getDescription());

			return view;
		}
	}

	private class GetSectionListTask extends AsyncTask<Void, Void, SectionList> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showViewForTaskPre();
		}

		@Override
		protected SectionList doInBackground(Void... voids) {
			try {
				return BaseApi.getSectionList();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(SectionList sectionList) {
			Log.i(TAG, "onPostExecute");
			super.onPostExecute(sectionList);
			mTask = null;
			mLoadingStatusView.setVisibility(View.GONE);
			if (null == sectionList) {
				mSectionList = null;
				mSections.clear();
				mEmptyView.setVisibility(View.VISIBLE);
				mListView.setVisibility(View.GONE);
			} else {
				Log.d(TAG, "data not null");
				mEmptyView.setVisibility(View.GONE);
				mListView.setVisibility(View.VISIBLE);
				mSectionList = sectionList;
				mSections = mSectionList.getSections();
				mAdapter.notifyDataSetChanged();
			}
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
			showViewForTaskCancel();
		}
	}
}
