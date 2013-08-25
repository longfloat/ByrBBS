package com.byr.bbs.ui;

import java.io.IOException;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.byr.bbs.R;
import com.byr.bbs.meta.BoardDetails;
import com.byr.bbs.meta.Threads;
import com.byr.bbs.net.BaseApi;
import com.byr.bbs.net.params.BoardParams;
import com.byr.bbs.utils.BaseUtils;

/**
 * Created by Liutong on 13-8-4.
 */
public class BoardDetailsFragment extends AbstractListFragment {
	private BoardDetails mBoardDetails;
	private ArrayList<Threads> mThreads;
	private BoardDetailsAdapter mAdapter;
	private GetBoardDetailsTask mTask;
	private String mBoardName;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Bundle args = getArguments();
		if (args != null) {
			mBoardName = args.getString(BoardDetails.NAME);
			getActivity().getActionBar().setTitle(
					args.getString(BoardDetails.DESCRIPTION));
		}

		mThreads = new ArrayList<Threads>();
		mAdapter = new BoardDetailsAdapter();
		mListView.setAdapter(mAdapter);

		mTask = new GetBoardDetailsTask();
		BoardParams param = new BoardParams(mBoardName);
		BoardParams[] params = new BoardParams[] { param };
		mTask.execute(params);
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
				mTask = new GetBoardDetailsTask();
				BoardParams param = new BoardParams(mBoardName);
				BoardParams[] params = new BoardParams[] { param };
				mTask.execute(params);
			}

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	private class BoardDetailsAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public BoardDetailsAdapter() {
			mInflater = LayoutInflater.from(getActivity());
		}

		@Override
		public int getViewTypeCount() {
			return super.getViewTypeCount();
		}

		@Override
		public int getItemViewType(int position) {
			return super.getItemViewType(position);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public Object getItem(int i) {
			return mThreads.get(i);
		}

		@Override
		public int getCount() {
			return mThreads.size();
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			ViewHolder holder;
			Threads threads = mThreads.get(i);

			if (null == view) {
				view = mInflater.inflate(R.layout.list_item_board_details,
						viewGroup, false);
				holder = new ViewHolder(view);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			holder.title.setText(threads.getTitle());
			holder.time.setText(BaseUtils.getFriendlyTime(threads
					.getPost_time() * 1000L));
			holder.author.setText(threads.getUser().getId());
			holder.replyCount.setText(String.valueOf(threads.getReply_count()));
			holder.lastReplyUser.setText(threads.getLast_reply_user_id());
			holder.lastReplyTime.setText(BaseUtils.getFriendlyTime(threads
					.getLast_reply_time() * 1000L));

			return view;
		}
	}

	private static class ViewHolder {
		public TextView title, author, time, replyCount, lastReplyUser,
				lastReplyTime;

		public ViewHolder(View view) {
			title = (TextView) view
					.findViewById(R.id.item_board_details_article_title);
			author = (TextView) view
					.findViewById(R.id.item_board_details_author);
			time = (TextView) view
					.findViewById(R.id.item_board_details_post_time);
			replyCount = (TextView) view
					.findViewById(R.id.item_board_details_reply_count);
			lastReplyUser = (TextView) view
					.findViewById(R.id.item_board_details_last_user);
			lastReplyTime = (TextView) view
					.findViewById(R.id.item_board_details_last_reply_time);
		}
	}

	private class GetBoardDetailsTask extends
			AsyncTask<BoardParams, Void, BoardDetails> {
		@Override
		protected BoardDetails doInBackground(BoardParams... params) {
			try {
				BoardDetails details = BaseApi.getBoardDetails(params[0]);
				return details;
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
		protected void onPostExecute(BoardDetails boardDetails) {
			super.onPostExecute(boardDetails);
			mTask = null;
			mLoadingStatusView.setVisibility(View.GONE);
			if (null == boardDetails) {
				mEmptyView.setVisibility(View.VISIBLE);
				mListView.setVisibility(View.GONE);

				mThreads.clear();
				mBoardDetails = null;

			} else {
				mEmptyView.setVisibility(View.GONE);
				mListView.setVisibility(View.VISIBLE);

				mBoardDetails = boardDetails;
				mThreads = mBoardDetails.getArticles();
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
