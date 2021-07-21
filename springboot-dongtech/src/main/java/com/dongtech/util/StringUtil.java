package com.dongtech.util;

import java.util.UUID;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str!=null){
            str = str.trim();
        }
        return str == null || str.length() <= 0;
    }
    /**
     * 判断字符串是否非空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * 将前台传过来的，Unicode编码转换成汉字
     * @param ssg
     * @return
     */
    public static String unicode2String(String unicode){
        if(unicode==null||unicode.trim().length()<=0)return null;
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while((i=unicode.indexOf("\\u", pos)) != -1){
            sb.append(unicode.substring(pos, i));
            if(i+5 < unicode.length()){
                pos = i+6;
                sb.append((char)Integer.parseInt(unicode.substring(i+2, i+6), 16));
            }
        }
        return format(sb.toString());
    }

    /**
     * @Description: 根据传入的gridCode判断级别
     * @author: zhangrui
     * @time: 2017-11-29 下午2:27:14
     *
     */
    public static String judgeLevel(String gridCode){
        if(gridCode != null && gridCode.length() == 9){
            //片区
            String area1 = gridCode.substring(0,2);
            String area2 = gridCode.substring(2);

            //楼宇
            String building1 = gridCode.substring(0,4);
            String building2 = gridCode.substring(4);
            //楼层
            String floor1 = gridCode.substring(0,6);
            String floor2 = gridCode.substring(6);
            if(!"00".equals(area1) && "0000000".equals(area2)){  //片区标识
                //片区
                return "AREA";
            }else if(!"0000".equals(building1) && "00000".equals(building2)){  //楼宇标识
                //楼宇
                return "BUILDING";
            }else if(!"000000".equals(floor1) && "000".equals(floor2)){  // 楼层标识
                //楼层
                return "FLOOR";
            }else{ //教室标识
                //教室
                return "CLASSROOM";
            }
        }else {
            return " ";
        }
    }
    /**
     * 可以替换所有空格默认在接收参数中使用
     * @param str
     * @return
     */
    public static String format(String str){
        String result = null;
        if(isNotEmpty(str)){
            result = str;
            result = result.trim();
            result = result.replaceAll("\\s*",""); //可以替换大部分空白字符， 不限于空格 . 说明:\s 可以匹配空格、制表符、换页符等空白字符的其中任意一个
        }
        return result;
    }
    /**
     * 获取一个唯一编码
     * @return
     */
    public static String getUUID(){
        UUID id = UUID.randomUUID();
        String s = id.toString();
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
    }
    /**
     * @Description:将字符串末尾去零后用9补齐
     * @param:str 输入的字符
     * @param: strLength 补齐9后字符串的长度
     * @return
     * @author: zhangrui
     */
    public static String getEndCodeByStartCode(String str){
        String string = str.replaceAll("0*$","");
        int strLen = string.length();
        if(strLen<str.length()){
            while(strLen<str.length()){
                StringBuffer sb = new StringBuffer();
                sb.append(string).append("9");
                string = sb.toString();
                strLen = string.length();
            }
        }
        return string;
    }

    /**
     * 判断是否是int
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * String转换为int
     * @param str
     * @return
     */
    public static int StringToInteger(String str){
        int id = Integer.parseInt(str);
        return id;
    }

    public static String NullToString(String str){
        if(null==str){
            str="没有数据";
        }
        return str;
    }

    public static void main(String[] args) {

    }
}
