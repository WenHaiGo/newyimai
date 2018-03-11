package com.easybuy.model;

import java.sql.Date;

public class EUser {

	private String EUId;
	private String EUName;
	private String EUPwd;
	private String EUSex;
	// 应该用sql的date还是应该使用util的date？
	private Date EUBirthday;
	private String EUIdentyCode;
	private String EUEmail;
	private String EUMoible;
	private String EUPhoto;
	private String EUAddress;
	private int EUStatus;
	private int id;

	public String getEUPhoto() {
		return EUPhoto;
	}

	public void setEUPhoto(String eUPhoto) {
		EUPhoto = eUPhoto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEUId() {
		return EUId;
	}

	public void setEUId(String eUId) {
		EUId = eUId;
	}

	public String getEUName() {
		return EUName;
	}

	public void setEUName(String eUName) {
		EUName = eUName;
	}

	public String getEUPwd() {
		return EUPwd;
	}

	public void setEUPwd(String eUPwd) {
		EUPwd = eUPwd;
	}

	public String getEUSex() {
		return EUSex;
	}

	public void setEUSex(String eUSex) {
		EUSex = eUSex;
	}

	public Date getEUBirthday() {
		return EUBirthday;
	}

	public void setEUBirthday(Date eUBirthday) {
		EUBirthday = eUBirthday;
	}

	public String getEUIdentyCode() {
		return EUIdentyCode;
	}

	public void setEUIdentyCode(String eUIdentyCode) {
		EUIdentyCode = eUIdentyCode;
	}

	public String getEUEmail() {
		return EUEmail;
	}

	public void setEUEmail(String eUEmail) {
		EUEmail = eUEmail;
	}

	public String getEUMoible() {
		return EUMoible;
	}

	public void setEUMoible(String eUMoible) {
		EUMoible = eUMoible;
	}

	public String getEUAddress() {
		return EUAddress;
	}

	public void setEUAddress(String eUAddress) {
		EUAddress = eUAddress;
	}

	public int getEUStatus() {
		return EUStatus;
	}

	public void setEUStatus(int eUStatus) {
		EUStatus = eUStatus;
	}

}
