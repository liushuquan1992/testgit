package com.lsq.util;

public class StringUtil {

    /**
     * 字符串判空
     * @param string
     * @return
     */
    public static boolean isNull(String string) {
        return string == null || string.length() == 0;
    }
    
    /**
     * 对指定对象进行 toString 操作；如果该对象为 null ，则返回空串（""）
     * 
     * @param obj
     *            指定的对象
     * @return 对指定对象进行 toString 操作；如果该对象为 null ，则返回空串（""）
     */
    public static String sBlank(Object obj) {
        return sBlank(obj, "");
    }
    
    /**
     * 对指定对象进行 toString 操作；如果该对象为 null 或者 toString 方法为空串（""），则返回默认值
     * 
     * @param obj
     *            指定的对象
     * @param def
     *            默认值
     * @return 对指定对象进行 toString 操作；如果该对象为 null 或者 toString 方法为空串（""），则返回默认值
     */
    public static String sBlank(Object obj, String def) {
        if (null == obj) {
			return def;
		}
        String s = obj.toString();
        return StringUtil.isBlank(s) ? def : s;
    }
    
    /**
     *  空null false; 
     * @param cs
     * @return
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
    
    public static boolean isBlank(CharSequence cs) {
        if (null == cs) {
			return true;
		}
        int length = cs.length();
        for (int i = 0; i < length; i++) {
            if (!(Character.isWhitespace(cs.charAt(i)))) {
				return false;
			}
        }
        return true;
    }
}
