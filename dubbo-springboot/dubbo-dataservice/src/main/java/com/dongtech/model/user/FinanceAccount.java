package com.dongtech.model.user;

import java.io.Serializable;

/**
 * 用户财务资金账户信息
 * 
 */
public class FinanceAccount implements Serializable {
	
	private static final long serialVersionUID = -1402081863472096872L;

	private Long id;

    private Long uid;

    private Double availableMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Double getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(Double availableMoney) {
        this.availableMoney = availableMoney;
    }
}