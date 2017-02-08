package dennis.testdemos.a01_materialdesign.ui.fragment;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import dennis.testdemos.R;
import dennis.testdemos.a01_materialdesign.adapter.RecyclerAdapter;
import dennis.testdemos.a01_materialdesign.bean.ItemBean;
import dennis.testdemos.utils.app.MyApp;
import dennis.utils.base.ui.fragment.BaseFragment;
/**
 * ItemFragment
 * Created by Dennis Can on 2017/2/8.
 */
public class ItemFragment extends BaseFragment {
	public static final int TYPE_LIST_VIEW = 1;
	public static final int TYPE_GRID_VIEW = 2;
	public static final int TYPE_STAGGERED = 3;
	public static final String TAG_TYPE = "type";

	private SwipeRefreshLayout refreshLayout;
	private RecyclerView recyclerView;
	private RecyclerAdapter mAdapter;
	private List<ItemBean> dataList;
	private int type;

	@Override protected int getLayoutResourceId() {return R.layout.a01_fragment;}

	@Override protected void assignViews(View rootView) {
		refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
		recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
	}

	@Override protected void onViewsReady(View rootView) {
		initSwipeRefreshLayout();
		initRecyclerView();
	}

	@Override protected void loadRawData(View rootView) {
		createDataList();
		refreshLayout.setRefreshing(true);
		loadData();
	}

	private void initSwipeRefreshLayout() {
		refreshLayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_red_light,
				android.R.color.holo_blue_light);

		// 参数1： 下拉时是否缩放
		// 参数2： 下拉时开始显示的位置
		// 参数2： 下拉手势结束后，下拉刷新控件显示的位置
		refreshLayout.setProgressViewOffset(false, 10, 50);

		refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override public void onRefresh() { loadData(); }
		});
	}

	private void initRecyclerView() {
		type = mBundle.getInt(TAG_TYPE, TYPE_LIST_VIEW);
		recyclerView.setLayoutManager(getLayoutManager());
		mAdapter = new RecyclerAdapter(communicator.getActivity(),type);
		recyclerView.setAdapter(mAdapter);
	}

	@NonNull private RecyclerView.LayoutManager getLayoutManager() {
		RecyclerView.LayoutManager layoutManager;
		switch (type) {
		case TYPE_LIST_VIEW:
			layoutManager = new LinearLayoutManager(communicator.getActivity());
			break;
		case TYPE_GRID_VIEW:
			layoutManager = new GridLayoutManager(communicator.getActivity(), 3);
			break;
		case TYPE_STAGGERED:
		default:
			layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
			break;
		}
		return layoutManager;
	}

	private void createDataList() {
		dataList = new ArrayList<>();
		for (int i = 0; i < 40; i++) {
			ItemBean bean = new ItemBean(R.mipmap.p1 + i, "选项 " + (i + 1));
			dataList.add(bean);
		}
	}

	private void loadData() {
		new Thread() {
			@Override public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				MyApp.getHandler().post(new Runnable() {
					@Override public void run() {
						mAdapter.replaceData(dataList);
						refreshLayout.setRefreshing(false);
					}
				});
			}
		}.start();
	}

	// <-------- Communicator -------->
	private ICommunicator communicator;

	public void setCommunicator(ICommunicator communicator) { this.communicator = communicator; }

	public interface ICommunicator {
		Activity getActivity();
	}
}