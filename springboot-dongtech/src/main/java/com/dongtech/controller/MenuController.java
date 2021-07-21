package com.dongtech.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongtech.bean.PermissionInfo;
import com.dongtech.bean.PermissionPath;
import com.dongtech.bean.UserInfo;
import com.dongtech.bean.UserRole;
import com.dongtech.mapper.PermissionInfoMapper;
import com.dongtech.mapper.PermissionPathMapper;
import com.dongtech.mapper.UserInfoMapper;
import com.dongtech.mapper.UserRoleMapper;
import com.dongtech.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.relation.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单控制
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionPathMapper permissionPathMapper;

    @Autowired
    private PermissionInfoMapper permissionInfoMapper;

    @RequestMapping("/adduserrole")
    public String adduserrole(Model model){
        //查询所有用户信息
        List<UserInfo> userInfos = userInfoMapper.selectAllUser();
        model.addAttribute("userInfos",userInfos);
        //查询权限信息
        List<PermissionInfo> permissionInfos = permissionInfoMapper.selectAllPermission();
        model.addAttribute("permissionInfos",permissionInfos);
        return "menu/adduserrole";
    }

    /**
     * 权限管理
     * @return
     */
    @RequestMapping("/permissiondocument")
    public String permissiondocument(Model model){
        JSONArray ja = new JSONArray();
        //查询所有权限角色
        List<PermissionInfo> permissionInfos = permissionInfoMapper.selectAllPermission();
        if(null!=permissionInfos){
            for (PermissionInfo permissionInfo:permissionInfos) {
                Integer pid = permissionInfo.getId();
                Integer num = permissionInfo.getPermissionnum();
                String permissionName = permissionInfo.getPermissionexplain();
                List<PermissionPath> permissionPaths = permissionPathMapper.selectPathsByPermissionInfoNum(num);
                JSONObject js = new JSONObject();
                js.put("pid",pid);
                js.put("num",num);
                js.put("permissionname",permissionName);
                js.put("permissionpaths",permissionPaths);
                ja.add(js);
            }
        }
        //JSONObject json = new JSONObject();
        //json.put("data",ja);
        logger.info(String.valueOf(ja));
        model.addAttribute("permissiondocuments",ja);
        //List<Map<String,Object>> permissionLists = permissionInfoMapper.selectAllPermissionAndAllPermissionPath();
        return "menu/permissiondocument";
    }

    @RequestMapping("/editpermission")
    public String editpermission(String pid,Model model){
        JSONObject json = new JSONObject();
        if(StringUtil.isNotEmpty(pid)){
            int id = StringUtil.StringToInteger(pid);
            PermissionInfo permissionInfo = permissionInfoMapper.selectByPrimaryKey(id);
            if(null!=permissionInfo){
                Integer permissionnum = permissionInfo.getPermissionnum();
                List<PermissionPath> permissionPaths = permissionPathMapper.selectPathsByPermissionInfoNum(permissionnum);
                if(null!=permissionPaths){
                    json.put("pid",permissionInfo.getId());
                    json.put("permissionpaths",permissionPaths);
                    json.put("pname",permissionInfo.getPermissionexplain());
                    model.addAttribute("editpermission",json);
                    return "menu/editpermission";
                }else{
                    return "menu/permissiondocument";
                }
            }else{
                return "menu/permissiondocument";
            }
        }else{
            return "menu/permissiondocument";
        }

    }

    /**
     * 角色管理
     * @return
     */
    @RequestMapping("/roledocument")
    public String roledocument(Model model){
        /*Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("sys")) {
            //有权限
            List<Map<String,Object>> userRoles = userRoleMapper.selectAllRole();
            model.addAttribute("userRoles",userRoles);
            return "menu/roledocument";
        } else {
            //无权限
            return "menu/index";
        }*/

        List<Map<String,Object>> userRoles = userRoleMapper.selectAllRole();
        model.addAttribute("userRoles",userRoles);
        return "menu/roledocument";

    }

    /**
     * 用户管理
     * @return
     */
    @RequestMapping("/userdocument")
    public String userdocument(Model model){
        List<UserInfo> userInfos = userInfoMapper.selectAllUser();
        model.addAttribute("userInfos",userInfos);
        return "menu/userdocument";
    }

    /**
     * 用户编辑
     * @return
     */
    @RequestMapping("/edituserinfo")
    public String edituserinfo(Model model, String userid){
        //判断是不是数字
        boolean isInt = StringUtil.isInteger(userid);
        if(isInt){
            int id = StringUtil.StringToInteger(userid);
            logger.info(String.valueOf(id));
            //userid="1";
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
            if(null==userInfo){
                return "menu/index";
            }else{
                model.addAttribute("userInfo",userInfo);
                return "menu/edituserinfo";
            }
        }else{
            return "menu/index";
        }
    }

    /**
     * 用户添加
     * @return
     */
    @RequestMapping("/adduserinfo")
    public String adduserinfo(Model model){
        return "menu/adduserinfo";
    }


    @RequestMapping("/edituserprofile")
    public String edituserprofile(Model model){
        Subject subject = SecurityUtils.getSubject();
        UserInfo login = (UserInfo)subject.getPrincipal();
        if(null==login){
            return "menu/index";
        }else {
            logger.info("当前登陆用户:"+login);
            String username = login.getUsername();
            //根据用户名查询信息
            Map<String, Object> userprofile = userRoleMapper.selectUserProfileByUserName(username);
            //model.addAttribute("userprofile",userprofile);
            Integer id = (Integer) userprofile.get("id")==null?-1:(Integer) userprofile.get("id");
            String name = (String) userprofile.get("username")==null?"没有数据":(String) userprofile.get("username");
            String useraddress = (String) userprofile.get("useraddress")==null?"没有数据":(String) userprofile.get("useraddress");
            Integer age = (Integer) userprofile.get("age")==null?-1:(Integer) userprofile.get("age");
            String worktime = (String) userprofile.get("worktime")==null?"没有数据":(String) userprofile.get("worktime");
            String aboutuser = (String) userprofile.get("aboutuser")==null?"没有数据":(String) userprofile.get("aboutuser");
            Map<String,Object> newMap = new HashMap<>();
            newMap.put("id",id);
            newMap.put("username",name);
            newMap.put("useraddress",useraddress);
            newMap.put("age",age);
            newMap.put("worktime",worktime);
            newMap.put("aboutuser",aboutuser);
            model.addAttribute("userprofile",newMap);
            logger.info("当前用户信息:"+newMap);
            return "menu/edituserprofile";
        }
    }


    /**
     * 用户信息
     * @return
     */
    @RequestMapping("/profiledocument")
    public String profiledocument(Model model){
        Subject subject = SecurityUtils.getSubject();
        UserInfo login = (UserInfo)subject.getPrincipal();
        if(null==login){
            return "menu/index";
        }else{
            logger.info("当前登陆用户:"+login);
            String username = login.getUsername();
            //根据用户名查询信息
            Map<String, Object> userprofile = userRoleMapper.selectUserProfileByUserName(username);
            //model.addAttribute("userprofile",userprofile);
            Integer id = (Integer) userprofile.get("id")==null?-1:(Integer) userprofile.get("id");
            String name = (String) userprofile.get("username")==null?"没有数据":(String) userprofile.get("username");
            String useraddress = (String) userprofile.get("useraddress")==null?"没有数据":(String) userprofile.get("useraddress");
            Integer age = (Integer) userprofile.get("age")==null?-1:(Integer) userprofile.get("age");
            String worktime = (String) userprofile.get("worktime")==null?"没有数据":(String) userprofile.get("worktime");
            String aboutuser = (String) userprofile.get("aboutuser")==null?"没有数据":(String) userprofile.get("aboutuser");
            Map<String,Object> newMap = new HashMap<>();
            newMap.put("id",id);
            newMap.put("username",name);
            newMap.put("useraddress",useraddress);
            newMap.put("age",age);
            newMap.put("worktime",worktime);
            newMap.put("aboutuser",aboutuser);
            model.addAttribute("userprofile",newMap);
            logger.info("当前用户信息:"+newMap);
            return "menu/profiledocument";
        }

    }

    /**
     * 登录后主页
     * @return
     */
    @RequestMapping("/indexdocument")
    public String indexdocument(){
        return "menu/indexdocument";
    }

    /**
     * 控制台
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "menu/index";
    }

    /**
     * 404
     * @return
     */
    @RequestMapping("/error-404")
    public String error404(){
        return "menu/error-404";
    }

    /**
     * 500
     * @return
     */
    @RequestMapping("/error-500")
    public String error500(){
        return "menu/error-500";
    }

    @RequestMapping("/edituserrole")
    public String edituserrole(Model model, String roleid){
        if(StringUtil.isNotEmpty(roleid)){
            Integer id = StringUtil.StringToInteger(roleid);
            Map<String,Object> userRole = userRoleMapper.selectRoleByRoleid(id);
            if(null==userRole){
                return "menu/roledocument";
            }else{
                model.addAttribute("userRole",userRole);
                //查询权限信息
                List<PermissionInfo> permissionInfos = permissionInfoMapper.selectAllPermission();
                if(null==permissionInfos){
                    return "menu/roledocument";
                }else{
                    model.addAttribute("permissionInfos",permissionInfos);
                    return "menu/edituserrole";
                }
            }
        }else {
            return "menu/roledocument";
        }
    }
}
