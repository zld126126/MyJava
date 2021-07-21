package com.dongtech.config;

/**
 * @Auther: dongbao
 * @Date: 2018/9/6 13:37
 * @Description:
 */
public class AlipayConfig {
    //如下配置账户基本信息

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static final String APP_ID = "2016091700532826";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static final String MERCHANT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCK1FePssuoFWzbs7Z38bWkdX4OrQnIJIU5/qyoCEwAkcmMgMBH8t9bywF/Zz1LK7rm3k7NCTewF8FZOxl6iB6+x0nd/a4OKpLmoZ5CPxtxkjvKBxbMU8eqwMqVSFAttXLEIvGBjh2Nyhid5jiRgCvry8UqKnsvlb4YH2gjHgXX0HBHkOgHCaW0j/1NC9VYhc2gfVhPAPuu9vc28Cr7Ung6+rZZ3ss+XuvbCtgEllgzu7sUidLB1gJNd5ZBV+JPrrsA/1nzlkz/hnsYB9/4MRUhT+1BAKiMwWnH4cVz9z928oLsZa5l/B9QmvmcG4vdeLMeVpXU0QwrYksYA33Xw3LXAgMBAAECggEAat0e9ZcMmzq6y7dfOp7NUmW6ffOGtxAdmVHHd6TDVCDkgwnMeNWkRxVR9s7yaH2l5fazs7Jnsl3m0Bfk+ePzvqjK3F1ccL0mzO8VUisfpeGJsm6YhvnqlS7IAfdi+HxWdlHrku1hJHavUkQXwTBnHp+BCn8eybc7V3g9geKnXsKEbWC9FPvC7qnR8M97tXWuQ1JTaqDF4zqfDWnqOwNhcgXootDGoLtlMMxcWziyqua57YGfofN80Q6t/xoAwJE8Jx9zHhphnUMcmkWFSbNlN17IGXS+g8OvFESnRlxRn9xKXLhbxmzKbbs4ZJpKBoCVuAZ9gQO46xGtUcfEOqtegQKBgQDntV8hdb/mtK3n8DURFCT0YWjPDzqOP35hSd0N8Wfav4xj/rWC1cbDBD1eTIRI+3i2Xky70uZWGl6Tpql/Au0rtc0isfYXAAO8//ysYr9WyySaQTBpU6TKoQSD1TxtLGHc9ErAcA1mtcRGspIAs1FllLj76pSahG1ITC/U1HeLFwKBgQCZYkTNnht9wgZNmLZeGY0v5U/iokm51YJZClr/VleoE3CU5rJD5lQUIjLgQQzBzFKFs+lh/91ViEH2UdRsSgf02wnkP25ZWYvWRHyck6jeofv4blwsz0tn0Cz53qbjbB0mBwf2juY8sJcfjQdBGfj10HoLsNX2S0KxAYqV380uQQKBgFWvCWXLMFK1H9Y0SrttEplYaDShsgqmSirZ8KfWTIxk5xqXNzIRsbWuHK+BZi3C6Imv53pRbCINEEpTiDxSrbfLO/eHjbRFqhw7IYnx3kXPS1LLpoZciwTvdEb4FKdekTXvFLGC/kGsF8/4+urNZN1GmP13asqVDAB/Q/TVOPuzAoGAPSvk1sA8pI3x55aE/Mhh930TvAYPMy8EmjA4FGdB9EU0fB35cHz2gUUWVzt6M/wh2TLkW/IoOolqDSubdUidHbkRm9b0UmwVCfIsZGG7Unb1v6F7318Y6qEqnsh+dGrUwSrCsUFSb1faHXooL32FPbvUQL9Yxa5EPcWomJc2BEECgYEAx5bgnMoFan2pLuI9o2KzXdL8FPhXpK+eArVKedru+okYtRaTryQTk2XL7RwqOOOXkJ4mJSbmUK7BIzIQCRRcpdCHiXabq9ndbI4QfI7KrVHDBpKGMgG+hAsIoK6k/1exksQrOJ1YGpqOxIPFgcL5EJVhzkAfoDfMTJAHBKUH7O0=";

    // 支付宝公钥，对应APPID下的应用公钥。
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwc7QTsG4gfH8PLIeu+HYSJUmWfHbd0Nn5BnsdLLHYA8AGYvquVwz1FPag3F0WWe4ostEkmSrx4TQMGicwNqkRSu4xhxx5H63IZbWCweug0TlelTo32QAml9gxHWQeY3labmBSl75xKE17BvGLWNKHwL5KEDjiSVPGppq68+BV1zXVq5W4xsXPdfZApTcmCv2VAmQvUPURVUK3vTQpyabTyvokAy53sVKyaxESQDlTjwlTHptmmVY1VxiH2ldQnvZh4fKglt2YzRzhFScjV4AJFgIEfPbNTNg+xH+had+DcmfY3TjNGEWsuCaPwlkPFt9skwB0JO+JpqJ4AGInrN4IQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String NOTIFY_URL = "http://dev.p2p.com:9300/pay/api/alipayNotify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数
    public static final String RETURN_URL = "http://localhost:8083/api/alipayBack";

    // 签名方式
    public static final String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static final String CHARSET = "utf-8";

    // 数据格式
    public static final String JSON = "json";

    // 支付宝网关（沙箱环境）
    public static final String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    //以上配置账户基本信息
}
