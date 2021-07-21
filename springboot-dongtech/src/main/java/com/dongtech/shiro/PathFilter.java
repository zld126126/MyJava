package com.dongtech.shiro;

import com.dongtech.bean.PermissionInfo;
import com.dongtech.bean.PermissionPath;
import com.dongtech.bean.UserInfo;
import com.dongtech.bean.UserRole;
import com.dongtech.controller.LoginController;
import com.dongtech.mapper.PermissionInfoMapper;
import com.dongtech.mapper.PermissionPathMapper;
import com.dongtech.mapper.UserInfoMapper;
import com.dongtech.mapper.UserRoleMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class PathFilter extends AccessControlFilter {

    private static final Logger logger = LoggerFactory.getLogger(PathFilter.class);


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionInfoMapper permissionInfoMapper;

    @Autowired
    private PermissionPathMapper permissionPathMapper;

    private String errorUrl;

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }
    /**
     * 表示是否允许访问 ，如果允许访问返回true，否则false；
     * @param servletRequest
     * @param servletResponse
     * @param o 表示写在拦截器中括号里面的字符串 mappedValue 就是 [urls] 配置中拦截器参数部分
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        logger.info("------------------自定义path验证------------------");
        boolean flag = false;
        Subject subject = getSubject(servletRequest,servletResponse);
        /*String url = getPathWithinApplication(servletRequest);
        logger.info("当前用户正在访问的 url => " + url);
        UserInfo user = (UserInfo)subject.getPrincipal();
        if(null==user){

        }else{
            String username = user.getUsername();
            logger.info("当前用户是: => " + username);
            Map<String,Object> usermap = new HashMap<>();
            usermap.put("username",username);
            //查询数据库
            UserInfo userInfo = userInfoMapper.selectByUsername(usermap);
            //查询用户角色
            Integer userid = userInfo.getId();
            Map<String,Object> rolemap = new HashMap<>();
            rolemap.put("userid",userid);
            UserRole userRole = userRoleMapper.selectRoleByUserid(rolemap);
            //查询权限信息
            Integer pid = userRole.getId();
            PermissionInfo permissionInfo = permissionInfoMapper.selectByPrimaryKey(pid);
            //查询权限路径
            //String pname = permissionInfo.getPermissionexplain();
            //查询权限路径
            Integer pnum = permissionInfo.getPermissionnum();
            List<PermissionPath> permissionPaths = permissionPathMapper.selectPathsByPermissionInfoNum(pnum);
            for (PermissionPath permissionPath:permissionPaths){
                if(url.equals(permissionPath.getPermissionpath())){
                    flag = true;
                    logger.info("当前用户是: => " + username+"有"+url+"访问权限");
                }
            }
        }*/
        if(subject.hasRole("sys")||subject.hasRole("manager")){
            flag = true;
        }

        return flag;
        //return subject.isPermitted(url);
    }
    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回 true 表示需要继续处理；如果返回 false 表示该拦截器实例已经处理了，将直接返回即可。
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        /*HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        response.sendRedirect(request.getContextPath() + this.errorUrl);

        // 返回 false 表示已经处理，例如页面跳转啥的，表示不在走以下的拦截器了（如果还有配置的话）
        return false;*/
        Subject subject = getSubject(servletRequest, servletResponse);
        if (subject != null)
        {
            subject.logout();
        }
        saveRequestAndRedirectToLogin(servletRequest, servletResponse);
        return true;
    }
}
