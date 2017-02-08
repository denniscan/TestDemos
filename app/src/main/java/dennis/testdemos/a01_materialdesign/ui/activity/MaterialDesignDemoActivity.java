package dennis.testdemos.a01_materialdesign.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import dennis.testdemos.R;
import dennis.testdemos.a01_materialdesign.adapter.ViewPagerAdapter;
import dennis.testdemos.a01_materialdesign.ui.fragment.ItemFragment;
import dennis.testdemos.utils.app.MyApp;
import dennis.utils.base.ui.activity.BaseActivity;
/**
 * 材料设计Demo
 * Created by Dennis Can on 2017/2/8.
 */
public class MaterialDesignDemoActivity extends BaseActivity {
	private Toolbar toolbar;
	private DrawerLayout drawerLayout;
	private NavigationView navigationView;
	private TabLayout tabLayout;
	private ViewPager viewPager;
	private FloatingActionButton floatingButton;
	private ViewPagerAdapter mAdapter;

	@Override protected int getLayoutId() { return R.layout.a01_activity_main; }

	@Override protected void assignViews() {
		toolbar = findView(R.id.toolBar);
		drawerLayout = findView(R.id.drawerLayout);
		navigationView = findView(R.id.navigationView);
		tabLayout = findView(R.id.tabLayout);
		viewPager = findView(R.id.viewPager);
		floatingButton = findView(R.id.floatingButton);
	}

	@Override protected void onViewsReady() {
		setSupportActionBar(toolbar);

		ActionBarDrawerToggle toggle =
				new ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.navigationDrawerOpen,
						R.string.navigationDrawerClose);
		drawerLayout.addDrawerListener(toggle);
		toggle.syncState();

		navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);

		initViewPager();

		floatingButton.setOnClickListener(this);
	}

	@Override protected void loadRawData() {}

	private void initViewPager() {
		List<Fragment> list = new ArrayList<>();
		String[] titleArray = {"列表", "网格", "瀑布流",};
		int[] typeArray = {ItemFragment.TYPE_LIST_VIEW, ItemFragment.TYPE_GRID_VIEW, ItemFragment.TYPE_STAGGERED,};
		Bundle bundle;
		for (int i = 0; i < titleArray.length; i++) {
			ItemFragment fragment = new ItemFragment();
			bundle = new Bundle();
			bundle.putInt(ItemFragment.TAG_TYPE, typeArray[i]);
			fragment.setArguments(bundle);
			fragment.setCommunicator(fragmentCommunicator);
			list.add(fragment);
		}
		mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), titleArray, list);
		viewPager.setAdapter(mAdapter);
		tabLayout.setupWithViewPager(viewPager);
	}

	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.a01_navigation_menu, menu);
		return true;
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.nav_camera:
		case R.id.nav_gallery:
		case R.id.nav_manage:
		case R.id.nav_send:
		case R.id.nav_share:
		case R.id.nav_slideshow:
			if (isDrawerOpen())
				closeDrawer();
			onItemClick(id);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.floatingButton:
			Snackbar.make(v, "Do something here!", Snackbar.LENGTH_SHORT).show();
			break;
		}
	}

	private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
			new NavigationView.OnNavigationItemSelectedListener() {
				@Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
					int id = item.getItemId();
					onItemClick(id);
					drawerLayout.closeDrawer(GravityCompat.START);
					return true;
				}
			};

	private void onItemClick(int id) {
		String string = "";
		switch (id) {
		case R.id.nav_camera:
			string = "camera";
			break;
		case R.id.nav_gallery:
			string = "gallery";
			break;
		case R.id.nav_manage:
			string = "manage";
			break;
		case R.id.nav_send:
			string = "send";
			break;
		case R.id.nav_share:
			string = "share";
			break;
		case R.id.nav_slideshow:
			string = "slideshow";
			break;
		}
		MyApp.showToast(string + " clicked.");
	}

	private ItemFragment.ICommunicator fragmentCommunicator = new ItemFragment.ICommunicator() {
		@Override public Activity getActivity() { return activity; }
	};

	@Override public void onBackPressed() {
		if (isDrawerOpen())
			closeDrawer();
		else
			super.onBackPressed();
	}

	private boolean isDrawerOpen() {return drawerLayout.isDrawerOpen(GravityCompat.START);}

	private void closeDrawer() {drawerLayout.closeDrawer(GravityCompat.START);}

	private void openDrawer() {drawerLayout.openDrawer(GravityCompat.START);}
}
