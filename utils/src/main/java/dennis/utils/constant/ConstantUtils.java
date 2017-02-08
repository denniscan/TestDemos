package dennis.utils.constant;

import android.util.SparseArray;
/**
 * 常量工具类
 * Created by Dennis Can on 2017/2/8.
 */
public class ConstantUtils {
	// <-------- API Map -------->
	private static SparseArray<String> aipInfoArray;

	public static SparseArray<String> getAipInfoArray() {
		if (aipInfoArray == null) {
			aipInfoArray = new SparseArray<>();
			aipInfoArray.put(24, "7.0");
			aipInfoArray.put(23, "6.0, Android M");
			aipInfoArray.put(22, "MNC, 5.1.1, Android LOLLIPOP_MR1");
			aipInfoArray.put(21, "5.0.1, Android LOLLIPOP");
			aipInfoArray.put(20, "4.4W.2, Android KITKAT_WATCH");
			aipInfoArray.put(19, "4.4.2, Android KITKAT");
			aipInfoArray.put(18, "4.3.1, Android JELLY_BEAN_MR2");
			aipInfoArray.put(17, "4.2.2, Android JELLY_BEAN_MR1");
			aipInfoArray.put(16, "4.1.2, Android JELLY_BEAN");
			aipInfoArray.put(15, "4.0.3, Android ICE_CREAM_SANDWICH_MR1");
			aipInfoArray.put(14, "4.0, Android ICE_CREAM_SANDWICH");
			aipInfoArray.put(13, "3.2, Android HONEYCOMB_MR2");
			aipInfoArray.put(12, "3.1, Android HONEYCOMB_MR1");
			aipInfoArray.put(11, "3.0, Android HONEYCOMB");
			aipInfoArray.put(10, "2.3.3, Android GINGERBREAD_MR1");
			aipInfoArray.put(9, "2.3.1, Android GINGERBREAD");
			aipInfoArray.put(8, "2.2, Android FROYO");
			aipInfoArray.put(7, "2.1, Android ECLAIR_MR1");
			aipInfoArray.put(6, "2.0.1, Android ECLAIR_0_1");
			aipInfoArray.put(5, "2.0, Android ECLAIR");
			aipInfoArray.put(4, "1.6, Android DONUT");
			aipInfoArray.put(3, "1.5, Android CUPCAKE");
			aipInfoArray.put(2, "1.1, Android BASE_1_1");
			aipInfoArray.put(1, "original, Android BASE");
		}
		return aipInfoArray;
	}
}