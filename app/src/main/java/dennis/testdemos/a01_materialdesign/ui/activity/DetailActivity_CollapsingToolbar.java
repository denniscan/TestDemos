package dennis.testdemos.a01_materialdesign.ui.activity;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import dennis.testdemos.R;
import dennis.testdemos.a01_materialdesign.bean.ItemBean;
import dennis.testdemos.utils.constant.Constant;
import dennis.utils.base.ui.activity.BaseActivity;
/**
 * 详情页面
 * Created by Dennis Can on 2017/2/8.
 */
public class DetailActivity_CollapsingToolbar extends BaseActivity {
	private Toolbar toolbar;
	private ImageView imageView;

	@Override protected int getLayoutId() { return R.layout.a01_activity_detail_collapsing_toolbar; }

	@Override protected void assignViews() {
		toolbar = (Toolbar) findViewById(R.id.toolBar);
		imageView = (ImageView) findViewById(R.id.imageIV);
	}

	@Override protected void onViewsReady() {
		ItemBean itemBean = (ItemBean) mIntent.getSerializableExtra(Constant.TAG_DATA_BEAN);
		toolbar.setTitle(itemBean.getTitle());
		setSupportActionBar(toolbar);
		imageView.setImageResource(itemBean.getImageId());
	}

	@Override protected void loadRawData() {}
}