package com.dongtech.shiro;

import com.dongtech.bean.PermissionInfo;
import com.dongtech.bean.PermissionPath;
import com.dongtech.bean.UserInfo;
import com.dongtech.bean.UserRole;
import com.dongtech.mapper.PermissionInfoMapper;
import com.dongtech.mapper.PermissionPathMapper;
import com.dongtech.mapper.UserInfoMapper;
import com.dongtech.mapper.UserRoleMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import javax.annotation.Resource;
import java.util.*;


public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionInfoMapper permissionInfoMapper;

    @Autowired
    private PermissionPathMapper permissionPathMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("------------------开始身份认证------------------");
        //获取用户
        UserInfo user = (UserInfo)SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();

        //查询用户信息
        if (null!=user){
            String username = user.getUsername();
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
            String pname = permissionInfo.getPermissionexplain();
            //用户角色赋予
            Set<String> roleSet = new HashSet<String>();
            roleSet.add(pname);
            info.setRoles(roleSet);
            //查询权限路径
            Integer pnum = permissionInfo.getPermissionnum();
            List<PermissionPath> permissionPaths = permissionPathMapper.selectPathsByPermissionInfoNum(pnum);
            //权限路径赋予
            Set<String> permissionSet = new HashSet<String>();
            for (PermissionPath permissionPath:permissionPaths){
                permissionSet.add(permissionPath.getPermissionpath());
            }
            info.setStringPermissions(permissionSet);
        }
        /*//用户角色赋予
        Set<String> roleSet = new HashSet<String>();
        roleSet.add(user.getUsername());
        info.setRoles(roleSet);
        //权限路径赋予
        Set<String> permissionSet = new HashSet<String>();
        info.setStringPermissions(permissionSet);*/
        logger.info(String.valueOf(info));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("------------------开始登陆验证------------------");
        String username = (String) token.getPrincipal();
        //Map<String, Object> map = new HashMap<>(16);
        //map.put("username", username);
        String password = new String((char[]) token.getCredentials());
        UserInfo user = new UserInfo(username,password);

        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        // 密码错误
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        // 账号锁定
        /*if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }*/
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
