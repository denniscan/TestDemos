package dennis.testdemos.a01_materialdesign.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import dennis.utils.base.adapter.viewpager.BaseFragmentPagerAdapter;
/**
 * ViewPagerAdapter
 * Created by Dennis Can on 2017/2/8.
 */
public class ViewPagerAdapter extends BaseFragmentPagerAdapter<Fragment> {
	private final String[] titleArray;

	public ViewPagerAdapter(FragmentManager fm, String[] titleArray, List<Fragment> list) {
		super(fm);
		this.titleArray = titleArray;
		addData(list,false);
	}

	@Override public Fragment getItem(int position) { return list.get(position); }

	@Override public CharSequence getPageTitle(int position) { return titleArray[position]; }
}