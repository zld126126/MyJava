package com.dongtech.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: dongbao
 * @Date: 2018/9/10 15:10
 * @Description:
 */
/*
 *代码很简单，就是判断常用的字符串是不是满足一些条件
 *
 *里面有判断字符串是不是url，还有构建url的代码等等。
 *
 *还有很多更使用的判断，自己看代码，老司机我就不多说了
 */
public class StringUtils {
    public StringUtils() {
    }

    public static String trim(String source) {
        return source != null?source.trim():source;
    }

    public static boolean isEmpty(String source) {
        return source == null || source.length() == 0;
    }

    public static boolean isNotEmpty(String source) {
        return !isEmpty(source);
    }

    public static boolean isBlank(String source) {
        return source == null || source.length() <= 0 || source.trim().length() <= 0;
    }

    public static boolean isNotBlank(String source) {
        return !isBlank(source);
    }

    public static boolean isNumeric(String str) {
        return !isBlank(str) && str.trim().matches("\\d+");
    }

    public static boolean isBool(String str) {
        return !isBlank(str) && (str.trim().equals("true") || str.trim().equals("false"));
    }

    public static boolean isEquals(String src, String dest) {
        return src == null && dest == null?true:(src != null && dest != null?src.trim().equals(dest.trim()):false);
    }

    public static boolean isNotEquals(String src, String dest) {
        return !isEquals(src, dest);
    }

    public static boolean isRightUrl(String s) {
        if (isEmpty(s))
            return false;
        else {
            if (s.startsWith("http://"))
                return true;
            else
                return false;
        }
    }

    public static boolean isInteger(String str) {
        byte begin = 0;
        if(isBlank(str)) {
            return false;
        } else {
            str = str.trim();
            if(str.startsWith("+") || str.startsWith("-")) {
                if(str.length() == 1) {
                    return false;
                }

                begin = 1;
            }

            for(int i = begin; i < str.length(); ++i) {
                if(!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String buildUrl(String url, HashMap<String, String> params){
        if(params != null){
            StringBuffer buffer = new StringBuffer(url);
            if(buffer.indexOf("?") == -1){
                buffer.append("?");
            }

            buffer.append(buildParam(params));
            return buffer.toString();
        }else {
            return url;
        }
    }


    private static String buildParam(HashMap<String, String> params){
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        buffer.deleteCharAt(buffer.length()-1);
        return buffer.toString();
    }
}
