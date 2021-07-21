package com.dongtech.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongtech.bean.UserInfo;
import com.dongtech.bean.UserProfile;
import com.dongtech.mapper.UserInfoMapper;
import com.dongtech.mapper.UserProfileMapper;
import com.dongtech.mapper.UserRoleMapper;
import com.dongtech.resultbean.BaseResult;
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

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userprofile")
public class UserProfileController {
    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    private JSONObject js;

    @ResponseBody
    @RequestMapping("/editUserProfile")
    public String editUserProfile(String userid, String useraddress, String worktime, String aboutuser, String age, String username){
        Subject subject = SecurityUtils.getSubject();
        UserInfo login = (UserInfo)subject.getPrincipal();
        if(null==login){
            js = BaseResult.jsonInit("1","null","请登录用户");
        }else if (null == username || "" == username || null == userid || "" == userid || null == useraddress || "" == useraddress || null == worktime || "" == worktime || null == aboutuser || "" == aboutuser || null == age || "" == age) {
            js = BaseResult.jsonInit("1","null","信息有误,请重新填写");
        } else {
            //准备参数
            Integer id = userid == null || userid == "没有数据" ? -1 : StringUtil.StringToInteger(userid);
            String name = username == null || username == "没有数据" ? "" : username;
            String address = useraddress == null || useraddress == "没有数据" ? "" : useraddress;
            Integer userage = age == null || age == "没有数据" ? -1 : StringUtil.StringToInteger(age);
            String time = worktime == null || worktime == "没有数据" ? "" : worktime;
            String about = aboutuser == null || aboutuser == "没有数据" ? "" : aboutuser;
            //判断
            if (name != null && name != "" && time != null && time != "") {
                //更新用户表信息
                UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
                if (null != userInfo) {
                    UserInfo newUserInfo = new UserInfo();
                    newUserInfo.setUsername(name);
                    newUserInfo.setPassword(userInfo.getPassword());
                    newUserInfo.setId(id);
                    newUserInfo.setEmail(userInfo.getEmail());
                    newUserInfo.setWorktime(time);
                    newUserInfo.setWorkstatus(userInfo.getWorkstatus());
                    int updateUserInfo = userInfoMapper.updateByPrimaryKey(newUserInfo);
                    if (updateUserInfo == 1) {
                        if (address != null && address != "" && about != null && about != "" && age != null) {
                            UserProfile userProfile = userProfileMapper.selectUserProfileByUserInfoId(id);
                            if (userProfile != null) {
                                UserProfile newUserProfile = new UserProfile();
                                newUserProfile.setAboutuser(about);
                                newUserProfile.setId(userProfile.getId());
                                newUserProfile.setAge(userage);
                                newUserProfile.setUseraddress(address);
                                newUserProfile.setUserid(userProfile.getUserid());
                                int updateUserProfile = userProfileMapper.updateByPrimaryKey(newUserProfile);
                                if (updateUserProfile == 1) {
                                    js = BaseResult.jsonInit("0","null","修改成功");
                                } else {
                                    js = BaseResult.jsonInit("1","null","修改失败,请稍后重试");
                                }
                            }else{
                                js = BaseResult.jsonInit("1","null","修改失败,请稍后重试");
                            }
                        }else{
                            js = BaseResult.jsonInit("1","null","修改失败,请稍后重试");
                        }

                    } else {
                        js = BaseResult.jsonInit("1","null","修改失败,请稍后重试");
                    }
                }else{
                    js = BaseResult.jsonInit("1","null","信息有误,请稍后重试");
                }
            }else{
                js = BaseResult.jsonInit("1","null","修改失败,请稍后重试");
            }
        }
        logger.info(String.valueOf(js));
        return js.toString();
    }
}
