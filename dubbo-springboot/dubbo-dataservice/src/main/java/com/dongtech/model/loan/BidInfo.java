package com.dongtech.model.loan;

import java.io.Serializable;
import java.util.Date;
import com.dongtech.model.user.User;

/**
 * 投标记录信息对象
 * 
 */
public class BidInfo implements Serializable {
	
	private static final long serialVersionUID = -6282697335755415355L;

	private Integer id;

    private Integer loanId;

    private Integer uid;

    private Double bidMoney;

    private Date bidTime;

    private int bidStatus;

    private User user;
    
    private LoanInfo loanInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Double getBidMoney() {
		return bidMoney;
	}

	public void setBidMoney(Double bidMoney) {
		this.bidMoney = bidMoney;
	}

	public Date getBidTime() {
		return bidTime;
	}

	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	public int getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(int bidStatus) {
		this.bidStatus = bidStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoanInfo getLoanInfo() {
		return loanInfo;
	}

	public void setLoanInfo(LoanInfo loanInfo) {
		this.loanInfo = loanInfo;
	}
}