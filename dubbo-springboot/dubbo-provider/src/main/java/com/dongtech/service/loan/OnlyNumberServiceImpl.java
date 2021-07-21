package com.dongtech.service.loan;

import com.alibaba.dubbo.config.annotation.Service;
import com.dongtech.constants.Constants;
import com.dongtech.service.loan.OnlyNumberService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 生成全局唯一数字
 * 
 * @author 东宝
 *
 */
@Service
public class OnlyNumberServiceImpl implements OnlyNumberService {

	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	
	/**
	 * 生成唯一数字
	 * 
	 * @return
	 */
	public Long getOnlyNumber() {
		Long onlyNumber = redisTemplate.opsForValue().increment(Constants.ONLY_NUMBER, 1L);
		return onlyNumber;
	}
}
