package dennis.utils.network;

import okhttp3.Call;
/**
 * 网络工具类
 * Created by Dennis Can on 2017/2/8.
 */
public class NetworkUtils {
	/**
	 * 停止网络访问
	 */
	public static void cancel(Call call) {
		if (call == null)
			return;
		try {
			call.cancel();
		} finally {
			call = null;
		}
	}
}
