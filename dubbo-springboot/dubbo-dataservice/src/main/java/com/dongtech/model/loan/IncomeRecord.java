package com.dongtech.model.loan;

import java.io.Serializable;
import java.util.Date;

public class IncomeRecord implements Serializable {
	
	private static final long serialVersionUID = -1444119298076487766L;

	private Integer id;

    private Integer uid;

    private Integer loanId;

    private Integer bidId;

    private Date incomeDate;
    
    private Double bidMoney;

    private Double incomeMoney;
    
    private Integer incomeStatus;
    
    private LoanInfo loanInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getBidId() {
		return bidId;
	}

	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}

	public Date getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	public Double getBidMoney() {
		return bidMoney;
	}

	public void setBidMoney(Double bidMoney) {
		this.bidMoney = bidMoney;
	}

	public Double getIncomeMoney() {
		return incomeMoney;
	}

	public void setIncomeMoney(Double incomeMoney) {
		this.incomeMoney = incomeMoney;
	}

	public Integer getIncomeStatus() {
		return incomeStatus;
	}

	public void setIncomeStatus(Integer incomeStatus) {
		this.incomeStatus = incomeStatus;
	}

	public LoanInfo getLoanInfo() {
		return loanInfo;
	}

	public void setLoanInfo(LoanInfo loanInfo) {
		this.loanInfo = loanInfo;
	}
}