package com.dongtech.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongtech.bean.UserInfo;
import com.dongtech.mapper.UserInfoMapper;
import com.dongtech.resultbean.BaseResult;
import com.dongtech.util.DateUtil;
import com.dongtech.util.StringUtil;
import com.dongtech.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    private JSONObject js=null;
    /**
     * 根据用户Id改变用户状态
     * @param userid
     */
    @ResponseBody
    @RequestMapping("/changeWorkStatus")
    public String changeWorkStatus(String userid){
        //先判断userid
        if(null==userid||""==userid||!StringUtil.isInteger(userid)){
            //修改失败
            js = BaseResult.jsonInit("1","null","请选择一个用户");
        }else{
            int id = Integer.parseInt(userid);
            //先判断目前状态
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
            String workstatus = userInfo.getWorkstatus();
            if("1".equals(workstatus)){
                UserInfo newUserInfo = new UserInfo();
                newUserInfo.setId(userInfo.getId());
                newUserInfo.setPassword(userInfo.getPassword());
                newUserInfo.setEmail(userInfo.getEmail());
                newUserInfo.setUsername(userInfo.getUsername());
                newUserInfo.setWorktime(userInfo.getWorktime());
                newUserInfo.setWorkstatus("0");
                int update = userInfoMapper.updateByPrimaryKey(newUserInfo);
                if(update==1){
                    //修改成功
                    js = BaseResult.jsonInit("0","null","修改成功");
                }else{
                    //修改失败
                    js = BaseResult.jsonInit("1","null","修改状态失败,请重试");
                }
            }else{
                UserInfo newUserInfo = new UserInfo();
                newUserInfo.setId(userInfo.getId());
                newUserInfo.setPassword(userInfo.getPassword());
                newUserInfo.setEmail(userInfo.getEmail());
                newUserInfo.setUsername(userInfo.getUsername());
                newUserInfo.setWorktime(userInfo.getWorktime());
                newUserInfo.setWorkstatus("1");
                int update = userInfoMapper.updateByPrimaryKey(newUserInfo);
                if(update==1){
                    //修改成功
                    js = BaseResult.jsonInit("0","null","修改成功");
                }else{
                    //修改失败
                    js = BaseResult.jsonInit("1","null","修改状态失败,请重试");
                }
            }
        }
        logger.info(String.valueOf(js));
        return js.toString();
    }

    @ResponseBody
    @RequestMapping("/addUserInfo")
    public String addUserInfo(String username, String password,String email){
        //后台判断逻辑

        //用户名和密码校验
        if (null==username||""==username||null==password||""==password||null==email||""==email){
            js = BaseResult.jsonInit("1", "null", "用户信息不全,请重新填写");

        }else{
        //两次密码一致
            Map<String,Object> map = new HashMap<>();
            map.put("username",username);
            //查询数据库
            UserInfo user = userInfoMapper.selectByUsername(map);
            //如果存在则返回用户名已存在,请重新输入用户名
            if (null==user){
                //不存在插入一条用户
                UserInfo userInfo = new UserInfo();
                //userInfo.setId(UUIDUtil.getUUID());
                userInfo.setEmail(email);
                userInfo.setUsername(username);
                userInfo.setPassword(password);
                userInfo.setWorkstatus("1");
                userInfo.setWorktime(DateUtil.getnyr());
                int insert = userInfoMapper.insert(userInfo);
                if(insert==1){
                    //插入成功
                    js = BaseResult.jsonInit("1", "null", "添加成功");
                }else{
                    //插入失败
                    js = BaseResult.jsonInit("1", "null", "添加失败,请稍后重试");
                }
            }else{
                js = BaseResult.jsonInit("1", "null", "用户名已存在,需重新选择");
            }
        }
        logger.info(String.valueOf(js));
        return js.toString();
    }

    @ResponseBody
    @RequestMapping("/editUserInfo")
    public String editUserInfo(String username, String password,String email,String userid){
        //后台逻辑判断
        if (null==userid||""==userid||null==username||""==username||null==password||""==password||null==email||""==email){
            js = BaseResult.jsonInit("1", "null", "用户信息不全,请重新填写");
        }else{
            boolean isInt = StringUtil.isInteger(userid);
            if(isInt){
                //获取userId
                int id = StringUtil.StringToInteger(userid);
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
                if(null==userInfo){
                    js = BaseResult.jsonInit("1", "null", "用户信息有误,请重新填写");
                }else{
                    UserInfo newUserInfo = new UserInfo();
                    newUserInfo.setUsername(username);
                    newUserInfo.setPassword(password);
                    newUserInfo.setId(id);
                    newUserInfo.setEmail(email);
                    newUserInfo.setWorktime(userInfo.getWorktime());
                    newUserInfo.setWorkstatus(userInfo.getWorkstatus());
                    int update = userInfoMapper.updateByPrimaryKey(newUserInfo);
                    if(update==1){
                        js = BaseResult.jsonInit("0", "null", "修改成功");
                    }else{
                        js = BaseResult.jsonInit("1", "null", "服务器繁忙,请稍后重试");
                    }
                }
            }else{
                js = BaseResult.jsonInit("1", "null", "用户信息有误,请重新填写");
            }
        }
        logger.info(String.valueOf(js));
        return js.toString();
    }

    @ResponseBody
    @RequestMapping("/deleteUserInfo")
    public String deleteUserInfo(String userid){
        if(null==userid||""==userid){
            js = BaseResult.jsonInit("1", "null", "请选择一个需要删除的用户");
        }else{
            boolean isInt = StringUtil.isInteger(userid);
            if(isInt){
                int id = StringUtil.StringToInteger(userid);
                int delete = userInfoMapper.deleteByPrimaryKey(id);
                if(delete==1){
                    js = BaseResult.jsonInit("0", "null", "修改成功");
                }else{
                    js = BaseResult.jsonInit("1", "null", "删除失败,请稍后重试");
                }
            }else{
                js = BaseResult.jsonInit("1", "null", "服务器繁忙,请稍后重试");
            }
        }
        logger.info(String.valueOf(js));
        return js.toString();
    }

}
