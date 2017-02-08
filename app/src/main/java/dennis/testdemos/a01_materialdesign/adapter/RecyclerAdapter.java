package dennis.testdemos.a01_materialdesign.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dennis.testdemos.R;
import dennis.testdemos.a01_materialdesign.bean.ItemBean;
import dennis.testdemos.a01_materialdesign.ui.fragment.ItemFragment;
import dennis.testdemos.a01_materialdesign.ui.activity.DetailActivity_CollapsingToolbar;
import dennis.testdemos.utils.constant.Constant;
import dennis.utils.base.adapter.recyclerview.BaseRecyclerViewAdapter;
/**
 * Created by Dennis Can on 2017/2/8.
 */
public class RecyclerAdapter extends BaseRecyclerViewAdapter<ItemBean> {
	private final Activity activity;
	private final int type;

	public RecyclerAdapter(Activity activity, int type) {
		this.activity = activity;
		this.type = type;
	}

	@Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ViewHolder(parent);
	}

	@Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		final ViewHolder viewHolder = (ViewHolder) holder;
		ItemBean data = getData(position);
		viewHolder.imageView.setImageResource(data.getImageId());
		viewHolder.titleTV.setText(data.getTitle());
		viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				onItemClick(viewHolder, getData(viewHolder.getAdapterPosition()));
			}
		});
	}

	private void onItemClick(ViewHolder holder, ItemBean bean) {
		Intent intent = new Intent(activity, DetailActivity_CollapsingToolbar.class);
		intent.putExtra(Constant.TAG_DATA_BEAN, bean);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Android 5.0 API21 转场动画
			activity.startActivity(intent,
					ActivityOptionsCompat.makeSceneTransitionAnimation(activity, holder.imageView,
							activity.getString(R.string.tran_01)).toBundle());
		} else {
			activity.startActivity(intent);
		}
	}

	private int getItemLayoutResourceId() {
		int id;
		switch (type) {
		case ItemFragment.TYPE_LIST_VIEW:
			id = R.layout.a01_item_list;
			break;
		case ItemFragment.TYPE_GRID_VIEW:
			id = R.layout.a01_item_grid;
			break;
		case ItemFragment.TYPE_STAGGERED:
		default:
			id = R.layout.a01_item_staggered;
			break;
		}
		return id;
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		final ImageView imageView;
		final TextView titleTV;

		ViewHolder(ViewGroup parent) {
			super(LayoutInflater.from(parent.getContext()).inflate(getItemLayoutResourceId(), parent, false));
			imageView = (ImageView) itemView.findViewById(R.id.imageIV);
			titleTV = (TextView) itemView.findViewById(R.id.titleTV);
		}
	}
}