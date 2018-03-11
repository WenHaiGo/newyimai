package com.easybuy.model;

import java.sql.Date;

public class ECartProduct {
	private String EUId;
	private Date createTime;
	private int PNum;
	private int PId;
	private int c_id;

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getEUId() {
		return EUId;
	}

	public void setEUId(String eUId) {
		EUId = eUId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getPNum() {
		return PNum;
	}

	public void setPNum(int pNum) {
		PNum = pNum;
	}

	public int getPId() {
		return PId;
	}

	public void setPId(int pId) {
		PId = pId;
	}

}
