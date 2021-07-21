package com.dongtech.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * UUID工具类生成id用
 */
public class UUIDUtil {
    private static final Logger logger = LoggerFactory.getLogger(UUIDUtil.class);


    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        logger.info("格式前的UUID ： " + UUID.randomUUID().toString());
        logger.info("格式化后的UUID ：" + getUUID());
    }
}
