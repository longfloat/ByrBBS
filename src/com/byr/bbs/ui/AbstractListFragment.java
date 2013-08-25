package com.byr.bbs.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.byr.bbs.GlobalApplication;
import com.byr.bbs.R;

/**
 * Created by Liutong on 13-8-4.
 */
public abstract class AbstractListFragment extends Fragment {
	private static final String TAG = AbstractListFragment.class
			.getSimpleName();

	protected View mLoadingStatusView;
	protected TextView mEmptyView;
	protected ListView mListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list_base, container,
				false);
		mLoadingStatusView = view.findViewById(R.id.loading_status_view);
		mListView = (ListView) view.findViewById(R.id.list);
		mEmptyView = (TextView) view.findViewById(R.id.empty_text);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_abstract_list, menu);
		String userName = GlobalApplication.getInstance().getUserName();
		if (!TextUtils.isEmpty(userName)) {
			menu.findItem(R.id.menu_user_name).setTitle(userName);
		}
	}

	protected void showViewForTaskPre() {
		mLoadingStatusView.setVisibility(View.VISIBLE);
		mEmptyView.setVisibility(View.GONE);
		mListView.setVisibility(View.GONE);
	}

	protected void showViewForTaskCancel() {
		mLoadingStatusView.setVisibility(View.GONE);
		mEmptyView.setVisibility(View.VISIBLE);
		mListView.setVisibility(View.GONE);
	}
}
