package com.dongtech.resultbean;

import com.alibaba.fastjson.JSONObject;

/**
 * 与前台交互对象
 */
public class BaseResult{
    /**
     * 与前台交互对象
     * @param status
     * @param data
     * @param msg
     * @return
     */
    public static JSONObject jsonInit(String status, String data, String msg){
        JSONObject json = new JSONObject();
        json.put("status",status);
        json.put("data",data);
        json.put("msg",msg);
        return json;
    }
}
