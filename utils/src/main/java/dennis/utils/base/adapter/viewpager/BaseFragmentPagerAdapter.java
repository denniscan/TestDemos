package dennis.utils.base.adapter.viewpager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.LinkedList;
import java.util.List;
/**
 * ViewPagerAdapter基类
 * Created by Dennis Can on 2017/2/8.
 */
public abstract class BaseFragmentPagerAdapter<T> extends FragmentStatePagerAdapter {
	protected List<T> list;

	public BaseFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		list = new LinkedList<>();
	}

	public void setDataList(List<T> list) { this.list = list; }

	public void addData(T data) {addData(data, true);}

	public void addData(T data, boolean refresh) {
		list.add(data);
		if (refresh)
			notifyDataSetChanged();
	}

	public void addData(List<? extends T> list) {addData(list, true);}

	public void addData(List<? extends T> list, boolean refresh) {
		if (list != null)
			this.list.addAll(list);
		if (refresh)
			notifyDataSetChanged();
	}

	public int replaceData(List<? extends T> list) { return replaceData(list, true); }

	public int replaceData(List<? extends T> list, boolean refresh) {
		if (list == null)
			return 0;
		this.list.clear();
		this.list.addAll(list);
		if (refresh)
			notifyDataSetChanged();
		return list.size();
	}

	public void removeData(int position) {
		if (position >= 0 && position < list.size())
			list.remove(position);
	}

	public void clearData() {this.list.clear();}

	public void setData(int position, T data) {
		if (position >= 0 && position < list.size())
			list.set(position, data);
	}

	public T getData(int position) {
		if (position >= 0 && position < list.size())
			return list.get(position);
		return null;
	}

	public List<T> getData() {return list;}

	@Override public int getCount() { return list.size(); }
}