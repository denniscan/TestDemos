package dennis.utils.base.adapter.recyclerview;

import android.support.v7.widget.RecyclerView;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter {
	protected static final int ITEM_VIEW_TYPE_NORMAL = 0;

	protected List<T> list;

	public BaseRecyclerViewAdapter() {list = new LinkedList<>();}

	public BaseRecyclerViewAdapter(List<T> list) { this.list = list; }

	// <-------- DataList -------->
	@Deprecated public void setData(List<T> data) { setData(data, true);}

	@Deprecated public void setData(List<T> data, boolean flash) {
		this.list = data;
		if (flash)
			notifyDataSetChanged();
	}

	public boolean addData(T data) { return addData(data, true); }

	public boolean addData(T data, boolean flash) {
		if (data == null)
			return false;
		list.add(data);
		if (flash)
			notifyDataSetChanged();
		return true;
	}

	public int addData(List<? extends T> list) { return addData(list, true); }

	public int addData(List<? extends T> list, boolean flash) {
		if (list == null || !(list.size() > 0))
			return 0;
		int skip = 0;
		for (T data : list) {
			if (data == null)
				skip++;
			else
				addData(data, false);
		}
		if (flash)
			notifyDataSetChanged();
		return list.size() - skip;
	}

	public boolean set(int index, T item) { return set(index, item, true); }

	public boolean set(int index, T item, boolean flash) {
		if (outBorder(index))
			return false;

		this.list.set(index, item);
		if (flash)
			notifyDataSetChanged();
		return true;
	}

	public int replaceData(List<? extends T> list) { return replaceData(list, true); }

	public int replaceData(List<? extends T> list, boolean flash) {
		if (list == null)
			return 0;
		this.list.clear();
		this.list.addAll(list);
		if (flash)
			notifyDataSetChanged();
		return list.size();
	}

	public T removeData(int index) { return removeData(index, true); }

	public T removeData(int index, boolean flash) {
		if (outBorder(index))
			return null;
		T data = list.remove(index);
		if (flash)
			notifyDataSetChanged();
		return data;
	}

	public <T extends Serializable> boolean removeData(T data) {return removeData(data, true);}

	public <T extends Serializable> boolean removeData(T data, boolean refresh) {
		boolean removed = list.remove(data);
		if (removed && refresh)
			notifyDataSetChanged();
		return removed;
	}

	public void clearData() { clearData(true); }

	public void clearData(boolean flash) {
		list.clear();
		if (flash)
			notifyDataSetChanged();
	}

	public boolean setData(int index, T data) { return setData(index, data, true); }

	public boolean setData(int index, T data, boolean flash) {
		if (outBorder(index))
			return false;
		list.set(index, data);
		if (flash)
			notifyDataSetChanged();
		return true;
	}

	public T getData(int index) {
		if (outBorder(index))
			return null;
		return list.get(index);
	}

	public List<T> getData() { return list; }

	protected boolean outBorder(int index) {return index < 0 || index >= list.size();}

	// <-------- Implements -------->
	@Override public int getItemCount() { return list.size(); }

	@Override public int getItemViewType(int position) { return ITEM_VIEW_TYPE_NORMAL; }

	// <-------- IListener -------->
	protected OnItemClickListener onItemClickListener;

	public <T extends Serializable> void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public interface OnItemClickListener {
		void onClick(int position);
	}

	protected OnItemLongClickListener onItemLongClickListener;

	public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
		this.onItemLongClickListener = onItemLongClickListener;
	}

	public interface OnItemLongClickListener {
		boolean onLongClick(int position);
	}
}
