package work.douzi.report.util;

/**
 * @author Ryan
 */
public class StringUtil {
    public static boolean isNullOrEmpty(String str) {
        return null != str && !"".equals(str) && !"null".equals(str);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }
}
