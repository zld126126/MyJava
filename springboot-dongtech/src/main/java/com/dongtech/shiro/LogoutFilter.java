package com.dongtech.shiro;


import com.dongtech.bean.UserInfo;
import com.dongtech.util.DateUtil;
import com.dongtech.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 退出过滤
 */

public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {
    private static final Logger logger = LoggerFactory.getLogger(LogoutFilter.class);

    /**
     * 退出后重定向的地址
     */
    private String loginUrl = "/login";

    public String getLoginUrl()
    {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        try
        {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);
            try
            {
                UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
                if (null!=user)
                {
                    String loginName = user.getUsername();
                    logger.info(loginName+":退出了");
                }
                // 退出登录
                subject.logout();
            }
            catch (SessionException ise)
            {
                logger.info(String.valueOf(ise));
            }
            issueRedirect(request, response, redirectUrl);
        }
        catch (Exception e)
        {
            logger.info(String.valueOf(e));
        }
        return false;
    }

    /**
     * 退出跳转URL
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject)
    {
        String url = getLoginUrl();
        if (StringUtil.isNotEmpty(url))
        {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }

}
