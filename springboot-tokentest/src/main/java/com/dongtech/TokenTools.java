package com.dongtech;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: dongbao
 * @Date: 2018/9/3 11:33
 * @Description:
 */
public class TokenTools {
    /**
     * 生成token放入session
     * @param request
     * @param tokenServerkey
     */
    public static boolean createToken(HttpServletRequest request,String tokenServerkey){
        String token = TokenProccessor.getInstance().makeToken();
        if(StringUtils.isNotEmpty(token)){
            request.getSession().setAttribute(tokenServerkey, token);
            System.out.println(tokenServerkey+":"+token);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 移除token
     * @param request
     * @param tokenServerkey
     */
    public static void removeToken(HttpServletRequest request,String tokenServerkey){
        request.getSession().removeAttribute(tokenServerkey);
    }

    /**
     * 判断请求参数中的token是否和session中一致
     * @param request
     * @param tokenClientkey
     * @param tokenServerkey
     * @return
     */
    public static boolean judgeTokenIsEqual(HttpServletRequest request, String tokenClientkey, String tokenServerkey){
        //String token_client = request.getParameter(tokenClientkey);
        String token_client = (String) request.getSession().getAttribute(tokenClientkey);
        if(StringUtils.isEmpty(token_client)){
            return false;
        }
        System.out.println(tokenClientkey+":"+token_client);
        String token_server = (String) request.getSession().getAttribute(tokenServerkey);
        if(StringUtils.isEmpty(token_server)){
            return false;
        }
        System.out.println(tokenServerkey+":"+token_server);
        if(!token_server.equals(token_client)){
            return false;
        }

        return true;
    }
}
