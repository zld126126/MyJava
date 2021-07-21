package com.dongtech.service.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dongtech.model.loan.RechargeRecord;
import com.dongtech.model.user.User;
import com.dongtech.service.user.UserService;
import org.springframework.stereotype.Component;

/**
 * 用户操作接口
 *
 */
@Component
public class UserServiceConsumer {

	@Reference
	UserService userService;
	
	/**
	 * 根据手机号查询用户是否存在
	 * 
	 * @param phone
	 * @return
	 */
	public int getUserByPhone(String phone){
		return userService.getUserByPhone(phone);
	}
	
	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public int register(User user){
		return userService.register(user);
	}
	
	/**
	 * 用户登录
	 * 
	 * @param phone
	 * @param loginPassword
	 * 
	 * @return
	 */
	public User login(String phone, String loginPassword){
		return userService.login(phone,loginPassword);
	}
	
	/**
	 * 平台总注册用户数
	 * 
	 * @return
	 */
	public Long getAllUserCount(){
		return userService.getAllUserCount();
	}
	
	/**
	 * 更新用户头像文件路径
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserById(User user){
		return userService.updateUserById(user);
	}
	
	/**
	 * 根据用户ID获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(Integer id){
		return userService.getUserById(id);
	}

    /**
     * 根据用户身份证号码获取用户信息条数
	 * @param idCard
     * @return
     */
	public int getUserByIdCard(String idCard){
		return userService.getUserByIdCard(idCard);
	}

	/**
	 * 根据充值记录号查询用户名
	 * @param rechargeNo
	 * @return
	 */
	public int getUserIdByRechargeRecordNo(String rechargeNo) {
		return userService.getUserIdByRechargeRecordNo(rechargeNo);
	}
}
