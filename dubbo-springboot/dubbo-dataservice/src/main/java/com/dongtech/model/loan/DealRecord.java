package com.dongtech.model.loan;

import java.io.Serializable;
import java.util.Date;

public class DealRecord implements Serializable {
	
	private static final long serialVersionUID = -6945101099298298970L;

	private Integer id;

    private Integer uid;

    private Long dealNo;

    private Integer dealStatus;

    private Double dealMoney;

    private Date dealTime;

    private Integer dealType;

    private String dealDesc;

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

    public Long getDealNo() {
        return dealNo;
    }

    public void setDealNo(Long dealNo) {
        this.dealNo = dealNo;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Double getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(Double dealMoney) {
        this.dealMoney = dealMoney;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Integer getDealType() {
        return dealType;
    }

    public void setDealType(Integer dealType) {
        this.dealType = dealType;
    }

    public String getDealDesc() {
        return dealDesc;
    }

    public void setDealDesc(String dealDesc) {
        this.dealDesc = dealDesc;
    }
}