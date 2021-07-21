package com.dongtech.service.user;

import com.dongtech.model.loan.RechargeRecord;
import com.dongtech.model.user.User;

/**
 * 用户操作接口
 *
 */
public interface UserService {
	
	/**
	 * 根据手机号查询用户是否存在
	 * 
	 * @param phone
	 * @return
	 */
	public int getUserByPhone(String phone);
	
	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public int register(User user);
	
	/**
	 * 用户登录
	 * 
	 * @param phone
	 * @param loginPassword
	 * 
	 * @return
	 */
	public User login(String phone, String loginPassword);
	
	/**
	 * 平台总注册用户数
	 * 
	 * @return
	 */
	public Long getAllUserCount();
	
	/**
	 * 更新用户头像文件路径
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserById(User user);
	
	/**
	 * 根据用户ID获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(Integer id);
	
	/**
	 * 根据用户身份证号码获取用户信息条数
	 * 
	 * @param id
	 * @return
	 */
	public int getUserByIdCard(String idCard);

	/**
	 * 根据充值记录号查询用户id
	 * @param rechargeNo
	 * @return
	 */
	public int getUserIdByRechargeRecordNo(String rechargeNo);
}
