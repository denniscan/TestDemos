package dennis.utils;

import android.util.Log;
/**
 * 简单工具类
 * Created by Dennis Can on 2017/2/9.
 */
public class A {
	private static final String TAG = "Dennis";

	protected static boolean isDebug() {return true;}

	private A() {}

	public static void log(String string) { log(TAG, string); }

	public static void log(String tag, String string) {
		if (isDebug())
			Log.i(tag, string);
	}
}
