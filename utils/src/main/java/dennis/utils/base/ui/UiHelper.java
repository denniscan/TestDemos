package dennis.utils.base.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Stack;

import dennis.utils.base.ui.activity.BaseActivity;
/**
 * Activity跳转工具类
 * Created by Dennis Can on 2017/2/8.
 */
public class UiHelper {
	private static UiHelper instance;
	private Stack<Activity> activityStack;

	public static UiHelper getInstance() {
		if (instance == null) {
			synchronized (UiHelper.class) {
				if (instance == null)
					instance = new UiHelper();
			}
		}
		return instance;
	}

	private UiHelper() { }

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null)
			synchronized (UiHelper.class) {
				if (activityStack == null)
					activityStack = new Stack<>();
			}
		activityStack.add(activity);
	}

	/**
	 * 从栈中移除Activity
	 */
	public void removeActivity(BaseActivity activity) {
		if (activityStack != null)
			activityStack.remove(activity);
	}

	/**
	 * 退出栈中所有Activity
	 */
	public void finishAllActivity() {
		if (activityStack == null)
			return;

		for (int i = 0; i < activityStack.size(); i++) {
			finishActivity(activityStack.get(i));
		}
	}

	/**
	 * 退出指定Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null)
			activity.finish();
	}

	public static void jumpTo(Context context, Class clazz) {
		try {
			jumpTo(context, clazz, null);
		} finally {
			context = null;
		}
	}

	public static void jumpTo(Context context, Class clazz, Bundle bundle) {
		try {
			Intent intent = new Intent(context, clazz);
			if (bundle != null)
				intent.putExtras(bundle);
			context.startActivity(intent);
		} finally {
			context = null;
			bundle = null;
		}
	}

	public static void jumpToForResult(Activity activity, Class clazz, int requestCode) {
		try {
			jumpToForResult(activity, clazz, null, requestCode);
		} finally {
			activity = null;
		}
	}

	public static void jumpToForResult(Activity activity, Class clazz, Bundle bundle, int requestCode) {
		try {
			Intent intent = new Intent(activity, clazz);
			if (bundle != null)
				intent.putExtras(bundle);
			activity.startActivityForResult(intent, requestCode);
		} finally {
			activity = null;
			bundle = null;
		}
	}
}
