package com.dongtech.service.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.dongtech.constants.Constants;
import com.dongtech.mapper.loan.RechargeRecordMapper;
import com.dongtech.mapper.user.UserMapper;
import com.dongtech.model.loan.RechargeRecord;
import com.dongtech.model.user.User;
import com.dongtech.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 用户相关处理Service实现
 * 
 * @author 东宝
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper uUserMapper;

	@Autowired
	private RechargeRecordMapper rechargeRecordMapper;

	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	
	/**
	 * 根据手机号查询用户是否存在
	 * 
	 * @param phone
	 * @return
	 */
	public int getUserByPhone (String phone) {
		return uUserMapper.selectByPhone(phone);
	}
	
	/**
	 * 用户注册
	 */
	public int register (User user) {
		return uUserMapper.insertSelective(user);
	}
	
	/**
	 * 用户登录
	 * 
	 * @param phone
	 * @param loginPassword
	 * 
	 * @return
	 */
	public User login (String phone, String loginPassword) {
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("phone", phone);
		paramMap.put("loginPassword", loginPassword);
		
		//根据手机号和登录密码查询用户(用户登录)
		User user = uUserMapper.selectByPhoneAndLoginPassword(paramMap);
		//更新用户登录时间
		if (null != user) {
			User u = new User();
			u.setId(user.getId());
			u.setLastLoginTime(new Date());
			uUserMapper.updateByPrimaryKeySelective(u);
		}
		return user;
	}
	
	/**
	 * 平台总注册用户数
	 * 
	 * @return
	 */
	public Long getAllUserCount () {
		//先去redis缓存，若缓存不存在，再取数据库，减少对数据库的访问，提升性能
		BoundValueOperations<String, Serializable> boundValueOperations = redisTemplate.boundValueOps(Constants.ALL_USER_COUNT);
		Long allUserCount = (Long)boundValueOperations.get();
		if (null == allUserCount) {
			allUserCount = uUserMapper.selectByUserCount();
			boundValueOperations.set(allUserCount);
			boundValueOperations.expire(15, TimeUnit.MINUTES);
		}
		return allUserCount;
	}
	
	/**
	 * 更新用户头像文件路径
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserById (User user) {
		return uUserMapper.updateByPrimaryKeySelective(user);
	}
	
	/**
	 * 根据用户ID获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById (Integer id) {
		return uUserMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据用户身份证号码获取用户信息条数
	 * 
	 * @param id
	 * @return
	 */
	public int getUserByIdCard (String idCard) {
		return uUserMapper.selectUserByIdCard(idCard);
	}

	@Override
	public int getUserIdByRechargeRecordNo(String rechargeNo) {
		//先要查一下用户的id
		RechargeRecord rechargeRecord = rechargeRecordMapper.selectByRechargeNo(rechargeNo);
		if(null!=rechargeRecord){
			return rechargeRecord.getUid();
		}else{
			return -1;
		}
	}
}
