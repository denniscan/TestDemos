package dennis.utils.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
/**
 * Application基类
 * Created by Dennis Can on 2017/2/8.
 */
public class BaseApplication extends Application {
	public static final String DIR_IMAGE = "image";
	public static final String DIR_FILE = "file";
	public static final String DIR_TEMP = "temp";
	public static final String FILE_SEPARATOR = File.separator;
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private static Context context;
	private static Handler handler;

	/**
	 * 临时目录路径
	 */
	public static String CACHE_PATH;

	// <-------- 分类目录路径 -------->
	public static String CACHE_IMAGE_PATH;
	public static String CACHE_FILE_PATH;
	public static String CACHE_TEMP;

	private static float density;
	private static int mainThreadId;

	@Override public void onCreate() {
		super.onCreate();
		context = this;
		handler = new Handler();

		initFilePath();
		initDeviceInfo();
		initThread();
	}

	private void initFilePath() {
		File externalCacheDir = getExternalCacheDir();
		if (externalCacheDir == null)
			CACHE_PATH = "";
		else
			CACHE_PATH = externalCacheDir.getAbsolutePath();
		if (TextUtils.isEmpty(CACHE_PATH))
			CACHE_PATH = getCacheDir().getAbsolutePath();

		CACHE_IMAGE_PATH = CACHE_PATH + FILE_SEPARATOR + DIR_IMAGE;
		File file = new File(CACHE_IMAGE_PATH);
		if (!file.exists())
			file.mkdirs();

		CACHE_FILE_PATH = CACHE_PATH + FILE_SEPARATOR + DIR_FILE;
		file = new File(CACHE_FILE_PATH);
		if (!file.exists())
			file.mkdirs();

		CACHE_TEMP = CACHE_PATH + FILE_SEPARATOR + DIR_TEMP;
		file = new File(CACHE_TEMP);
		if (!file.exists())
			file.mkdirs();
	}

	private void initDeviceInfo() { density = getResources().getDisplayMetrics().density; }

	private void initThread() { mainThreadId = android.os.Process.myTid(); }

	public static Context getContext() { return context; }

	public static Handler getHandler() { return handler; }

	public static float getDensity() { return density; }

	public static int getMainThreadId() { return mainThreadId; }

	public static float getDimension(int resId) { return getContext().getResources().getDimension(resId); }

	public static float getDimensionValue(int resId) { return getDimension(resId) / getDensity(); }

	// <-------- Toast -------->
	private static Toast toast;

	public static void showToast(int resId) { showToast(getContext().getResources().getString(resId)); }

	public static void showToast(String string) {
		if (toast == null) {
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			toast.setText(string);
		}
		toast.show();
	}
}
