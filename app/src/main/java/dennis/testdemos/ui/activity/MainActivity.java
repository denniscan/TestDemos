package dennis.testdemos.ui.activity;

import android.view.View;

import dennis.testdemos.R;
import dennis.testdemos.a01_materialdesign.ui.activity.MaterialDesignDemoActivity;
import dennis.utils.base.ui.UiHelper;
import dennis.utils.base.ui.activity.BaseActivity;

public class MainActivity extends BaseActivity {

	@Override protected int getLayoutId() { return R.layout.activity_main; }

	@Override protected void assignViews() {}

	@Override protected void onViewsReady() {findViewById(R.id.materialDesignBtn).setOnClickListener(this);}

	@Override protected void loadRawData() {}

	@Override public void onClick(View v) {
		switch (v.getId()){
		case R.id.materialDesignBtn:
			UiHelper.jumpTo(activity, MaterialDesignDemoActivity.class);
			break;
		}
	}
}

