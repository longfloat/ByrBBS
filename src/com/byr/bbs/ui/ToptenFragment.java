package com.byr.bbs.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.byr.bbs.R;
import com.byr.bbs.meta.Threads;
import com.byr.bbs.net.BaseApi;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Liutong on 13-7-31.
 */
public class ToptenFragment extends AbstractListFragment {

	private static final String TAG = ToptenFragment.class.getSimpleName();
	private ArrayList<Threads> mArticles = null;
	private ToptenAdapter mAdapter = null;
	private GetToptenTask mTask = null;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mArticles = new ArrayList<Threads>();
		mAdapter = new ToptenAdapter();
		mListView.setAdapter(mAdapter);
		mTask = new GetToptenTask();
		mTask.execute();
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
				mTask = new GetToptenTask();
				mTask.execute();
				return true;
			}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	private class ToptenAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public ToptenAdapter() {
			mInflater = LayoutInflater.from(getActivity());
		}

		@Override
		public int getCount() {
			return mArticles.size();
		}

		@Override
		public Object getItem(int i) {
			return mArticles.get(i);
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
			text.setText(mArticles.get(i).getTitle());

			return view;
		}
	}

	private class GetToptenTask extends
			AsyncTask<Void, Void, ArrayList<Threads>> {
		@Override
		protected ArrayList<Threads> doInBackground(Void... voids) {
			Log.i(TAG, "doInBackground");
			try {
				return BaseApi.getTopten();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showViewForTaskPre();
		}

		@Override
		protected void onPostExecute(ArrayList<Threads> threadses) {
			Log.i(TAG, "onPostExecute");
			super.onPostExecute(threadses);
			mTask = null;
			mLoadingStatusView.setVisibility(View.GONE);
			if (null == threadses) {
				mArticles.clear();
				mEmptyView.setVisibility(View.VISIBLE);
				mListView.setVisibility(View.GONE);
			} else {
				Log.d(TAG, "data not null");
				mEmptyView.setVisibility(View.GONE);
				mListView.setVisibility(View.VISIBLE);
				mArticles = threadses;
				mAdapter.notifyDataSetChanged();
			}
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
			showViewForTaskCancel();
			mTask = null;
		}
	}
}
