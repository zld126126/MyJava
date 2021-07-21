package com.dongtech.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongtech.bean.UserRole;
import com.dongtech.mapper.UserRoleMapper;
import com.dongtech.resultbean.BaseResult;
import com.dongtech.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userrole")
public class UserRoleController {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    private UserRoleMapper userRoleMapper;

    private JSONObject js = null;

    @ResponseBody
    @RequestMapping("/addUserRole")
    public String addUserRole(String userid,String permissionid){
        if(StringUtil.isNotEmpty(userid)&&StringUtil.isNotEmpty(permissionid)){
            int uid = StringUtil.StringToInteger(userid);
            int pid = StringUtil.StringToInteger(permissionid);
            Map<String,Object> map = new HashMap<>();
            map.put("userid",uid);
            map.put("permissionid",pid);
            //先查重
            UserRole oldUserRole = userRoleMapper.selectUserRoleByUseridAndPermissionid(map);
            if(null==oldUserRole){
                UserRole userRole = new UserRole();
                userRole.setUserid(StringUtil.StringToInteger(userid));
                userRole.setPermissionid(StringUtil.StringToInteger(permissionid));
                int insert = userRoleMapper.insert(userRole);
                if(insert==1){
                    js = BaseResult.jsonInit("0", "null", "插入角色成功");
                }else{
                    js = BaseResult.jsonInit("1", "null", "插入失败,请稍后重试");

                }
            }else{
                js = BaseResult.jsonInit("1", "null", "该用户已有所选权限");
            }
        }else{
            js = BaseResult.jsonInit("1", "null", "用户信息有误,请重新选择");
        }
        logger.info(String.valueOf(js));
        return js.toString();
    }

    @ResponseBody
    @RequestMapping("/editUserRole")
    public String editUserRole(String roleid,String permissionid){
        if(StringUtil.isNotEmpty(roleid)&&StringUtil.isNotEmpty(permissionid)){
            Integer rid = StringUtil.StringToInteger(roleid);
            UserRole oldUserRole = userRoleMapper.selectByPrimaryKey(rid);
            if(null!=oldUserRole){
                Integer pid = StringUtil.StringToInteger(permissionid);
                UserRole userRole = new UserRole();
                userRole.setId(rid);
                userRole.setPermissionid(pid);
                userRole.setUserid(oldUserRole.getUserid());
                int update = userRoleMapper.updateByPrimaryKey(userRole);
                if(update==1){
                    js = BaseResult.jsonInit("0", "null", "角色更新成功");
                }else{
                    js = BaseResult.jsonInit("1", "null", "角色更新失败");
                }
            }else{
                js = BaseResult.jsonInit("1", "null", "角色信息有误");
            }
        }else{
            js = BaseResult.jsonInit("1", "null", "请至少选择一个角色");
        }
        logger.info(String.valueOf(js));
        return js.toString();
    }

    @RequestMapping("/deleteUserRole")
    @ResponseBody
    public String deleteUserRole(String roleid){
        if(StringUtil.isNotEmpty(roleid)){
            int rid = StringUtil.StringToInteger(roleid);
            int delete = userRoleMapper.deleteByPrimaryKey(rid);
            if(delete==1){
                js = BaseResult.jsonInit("0", "null", "角色删除成功");
            }else{
                js = BaseResult.jsonInit("1", "null", "角色删除失败");
            }
        }else{
            js = BaseResult.jsonInit("1", "null", "请至少选择一个角色");
        }
        logger.info(String.valueOf(js));
        return js.toString();
    }

}
