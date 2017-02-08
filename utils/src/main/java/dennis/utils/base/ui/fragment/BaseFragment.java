package dennis.utils.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by Dennis Can on 2017/2/8.
 */

public abstract class BaseFragment extends Fragment {
	protected View rootView;
	protected Bundle mBundle;

	@Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		if (rootView == null) {
			rootView = inflater.inflate(getLayoutResourceId(), container, false);
			mBundle = getArguments();
			assignViews(rootView);
			onViewsReady(rootView);
			loadRawData(rootView);
		} else {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null)
				parent.removeView(rootView);
		}
		return rootView;
	}

	protected abstract int getLayoutResourceId();

	protected abstract void assignViews(View rootView);

	protected abstract void onViewsReady(View rootView);

	protected abstract void loadRawData(View rootView);
}
