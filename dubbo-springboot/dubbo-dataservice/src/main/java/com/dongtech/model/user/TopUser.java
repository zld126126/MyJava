package com.dongtech.model.user;

import java.io.Serializable;

/**
 * 投资用户排行榜实体对象
 *
 */
public class TopUser implements Serializable {

	private static final long serialVersionUID = -8641829924916413676L;

	private String phone;
	
	private Double score;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
}
