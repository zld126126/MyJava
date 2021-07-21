package com.dongtech.Utils;

import java.util.List;

public class CommonUtil {
    public static boolean isEmptyList(List<?> list) {
        if(list.size()==0||"".equals(list)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isNotEmptyList(List<?> list) {
        if(list.size()==0||"".equals(list)){
            return false;
        }else{
            return true;
        }
    }
}
