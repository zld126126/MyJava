package com.dongtech.mapper.user;

import java.util.Map;

import com.dongtech.model.user.User;

/**
 * 用户操作Mapper
 * 
 */
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User uUser);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 根据手机号和登录密码查询用户(用户登录)
     * 
     * @param
     * @return
     */
    User selectByPhoneAndLoginPassword(Map<String, Object> paramMap);
    
    /**
     * 平台总注册用户数
     * 
     * @return
     */
    Long selectByUserCount();
    
    /**
     * 根据用户手机号查询用户是否存在
     * 
     * @param phone
     * @return
     */
    int selectByPhone(String phone);
    
    int selectUserByIdCard(String idCard);
}