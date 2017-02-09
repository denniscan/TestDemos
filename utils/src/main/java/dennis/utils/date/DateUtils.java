package dennis.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * 日期时间工具类
 * Created by Dennis Can on 2017/2/9.
 */
public class DateUtils {
	private DateUtils() {}

	private static SimpleDateFormat fullDateFormat;

	/**
	 * 获取时间格式器，yyyy-MM-dd HH:mm:ss
	 */
	public static SimpleDateFormat getFullDateFormat() {
		if (fullDateFormat == null)
			fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fullDateFormat;
	}

	private static SimpleDateFormat dateFormat;

	/**
	 * 获取时间格式器，yyyy-MM-dd
	 */
	public static SimpleDateFormat getDateFormat() {
		if (dateFormat == null)
			dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat;
	}

	private static SimpleDateFormat timeFormat;

	/**
	 * 获取时间格式器，HH:mm:ss
	 */
	public static SimpleDateFormat getTimeFormat() {
		if (timeFormat == null)
			timeFormat = new SimpleDateFormat("HH:mm:ss");
		return timeFormat;
	}

	/**
	 * 根据指定格式读取Calendar日期
	 *
	 * @param format
	 * 		为空则默认为"yyyy-MM-dd HH:mm:ss"
	 */
	public static String getDisplayDate(Calendar calendar, String format) {
		if (format == null || format.length() == 0) {

			return getFullDateFormat().format(calendar.getTime());
		} else {
			return new SimpleDateFormat(format).format(calendar.getTime());
		}
	}
}
