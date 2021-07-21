package com.dongtech.timer;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.dongtech.model.loan.BidInfo;
import com.dongtech.model.loan.IncomeRecord;
import com.dongtech.model.loan.LoanInfo;
import com.dongtech.service.loan.*;
import com.dongtech.service.user.FinanceAccountService;
import com.dongtech.service.user.FinanceAccountServiceConsumer;
import com.dongtech.util.DateUtils;
import com.dongtech.util.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度
 * 
 * @author 东宝
 *
 */
@Component("taskScheduling")
public class TaskScheduling {
	
	private static Logger logger = Logger.getLogger(TaskScheduling.class);
	
	@Autowired
	private LoanInfoServiceConsumer loanInfoService;
	
	@Autowired
	private BidInfoServiceConsumer bidInfoService;

	@Autowired
	private IncomeRecordServiceConsumer incomeRecordService;
	
	@Autowired
	private FinanceAccountServiceConsumer financeAccountService;
	
	//@Scheduler注解来修饰一个要调度的方法
	@Scheduled(cron = "0/5 * * * * *")
	public void runGenerateIncomePlan () {
		logger.info("----满标后定时生成收益计划任务start......");
		this.generateIncomePlan();
		logger.info("----满标后定时生成收益计划任务end......");
	}
	
	@Scheduled(cron = "0/5 * * * * *")
	public void runIncomeBack () {
		logger.info("----定时执行回款任务start......");
		this.incomeBack();
		logger.info("----定时执行回款任务end......");
	}
	
	/**
	 * 产品投资满标后，为用户的投资生成收益计划
	 * 收益计划是用户预计的收益，用户可以查看，
	 * 待产品到期后，收益及本金返回到用户账户中
	 * 
	 */
	public void generateIncomePlan () {
		
		//查询满标状态的产品，productStatus=1，满标状态为1
		List<LoanInfo> loanInfoList = loanInfoService.getLoanInfoByProductStatus(1);
		
		for (LoanInfo loanInfo : loanInfoList) {
			
			Double rate = loanInfo.getRate();//产品利率
			int cycle = loanInfo.getCycle();//产品期限
			Date productFullTime = loanInfo.getProductFullTime();//满标时间
			Date incomeDate = DateUtils.getDateAddMonths(productFullTime, cycle);//收益时间
			
			//获取该产品下的投资记录，每笔投资都生成一个收益计划
			List<BidInfo> bidInfoList = bidInfoService.getBidInfoByLoanId(loanInfo.getId());
			
			for (BidInfo bidInfo : bidInfoList) {
				Double bidMoney = bidInfo.getBidMoney();//投资金额
				Double incomeMoney = bidMoney * rate / 100 / 365 * cycle * 30;//计算收益金额
				
				//封装收益计划数据
				IncomeRecord incomeRecord = new IncomeRecord();
				incomeRecord.setBidId(bidInfo.getId());
				incomeRecord.setIncomeDate(incomeDate);
				incomeRecord.setBidMoney(bidMoney);
				incomeRecord.setIncomeMoney(NumberUtils.number2Two(incomeMoney));
				incomeRecord.setLoanId(bidInfo.getLoanId());
				incomeRecord.setUid(bidInfo.getUid());
				incomeRecord.setIncomeStatus(0);//收益状态（0未返，1已返）
				
				//将收益计划数据插入数据库
				int in = incomeRecordService.addIncomeRecord(incomeRecord);
				
				if (in <= 0) {
					logger.info("生成收益计划失败-->bidId=" + bidInfo.getId() + "-->uid=" + bidInfo.getUid());
				} else {
					logger.info("生成收益计划成功-->bidId=" + bidInfo.getId() + "-->uid=" + bidInfo.getUid());
				}
			}
			
			//已生成收益计划，将产品状态改为2，产品状态（0未满标，1已满标，2满标已生成收益计划）
			LoanInfo loan = new LoanInfo();
			loan.setId(loanInfo.getId());
			loan.setProductStatus(2);
			int updateLoan = loanInfoService.updateLoanInfoById(loan);
			if (updateLoan <= 0) {
				logger.info("投资到期回款，更新产品状态为2失败了，-->bidId=" + loanInfo.getId());
			}
		}
	}
	
	/**
	 * 投资到期为用户回款
	 * 
	 */
	public void incomeBack () {
		
		//查询当天应该回款的收益计划
		List<IncomeRecord> incomeRecordList = incomeRecordService.getIncomeRecordByIncomeDateIsCurrentDate();
		
		for (IncomeRecord incomeRecord : incomeRecordList) {
			
			//将资金返回用户资金账户表
			Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
			paramMap.put("uid", incomeRecord.getUid());
			paramMap.put("bidMoney", incomeRecord.getBidMoney());//投资金额
			paramMap.put("incomeMoney", incomeRecord.getIncomeMoney());//收益金额
			
			int updateFinance = financeAccountService.updateFinanceAccountByBidAndIncomeMoneyAdd(paramMap);
			if (updateFinance <= 0) {
				logger.info("投资到期为用户回款失败-->bidId=" + incomeRecord.getBidId() + "-->uid=" + incomeRecord.getUid());
			} else {
				logger.info("投资到期为用户回款成功-->bidId=" + incomeRecord.getBidId() + "-->uid=" + incomeRecord.getUid());
			}
			
			//更新收益计划表incomeStatus=1，收益状态（0未返，1已返）
			IncomeRecord updateIncomeRecord = new IncomeRecord();
			updateIncomeRecord.setId(incomeRecord.getId());
			updateIncomeRecord.setIncomeStatus(1);
			int updateRows = incomeRecordService.updateIncomeRecordById(updateIncomeRecord);
			if (updateRows <= 0) {
				logger.info("更新收益计划表收益状态为1已返失败-->id=" + incomeRecord.getBidId() + "-->uid=" + incomeRecord.getUid());
			}
		}
	}
}