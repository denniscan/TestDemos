package dennis.utils.base.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dennis.utils.R;
import dennis.utils.network.NetworkUtils;
import dennis.utils.base.ui.UiHelper;
import okhttp3.Call;

/**
 * Activity基类
 * Created by Dennis Can on 2017/2/8.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
	public BaseActivity activity;
	protected Intent mIntent;
	protected Bundle mBundle;
	protected Call call;
	protected AlertDialog msgDialog;

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		activity = this;
		UiHelper.getInstance().addActivity(activity);
		msgDialog = new AlertDialog.Builder(this, R.style.MyAlertDialog).setTitle(R.string.message).create();

		mIntent = getIntent();
		if (mIntent != null)
			mBundle = mIntent.getExtras();

		int layoutId = getLayoutId();
		if (layoutId != 0)
			setContentView(layoutId);

		assignViews();
		onViewsReady();
		loadRawData();
	}

	protected abstract int getLayoutId();

	protected abstract void assignViews();

	protected abstract void onViewsReady();

	protected abstract void loadRawData();

	@Override public void onClick(View v) {}

	@Override protected void onDestroy() {
		super.onDestroy();
		UiHelper.getInstance().removeActivity(activity);
		cancelNetworkHandler();
	}

	protected <T> T findView(int resId) { return (T) findViewById(resId); }

	protected void cancelNetworkHandler() { NetworkUtils.cancel(call); }

	protected void showMessage(String message) {
		msgDialog.setMessage(message);
		msgDialog.show();
	}
}
