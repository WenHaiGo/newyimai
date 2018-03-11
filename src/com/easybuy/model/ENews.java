package com.easybuy.model;

import java.sql.Date;

public class ENews {
	private int ENId;
	private String ENTitle;
	private String ENContent;
	private Date ENTime;

	public int getENId() {
		return ENId;
	}

	public void setENId(int eNId) {
		ENId = eNId;
	}

	public String getENTitle() {
		return ENTitle;
	}

	public void setENTitle(String eNTitle) {
		ENTitle = eNTitle;
	}

	public Date getENTime() {
		return ENTime;
	}

	public void setENTime(Date eNTime) {
		ENTime = eNTime;
	}

	public String getENContent() {
		return ENContent;
	}

	public void setENContent(String eNContent) {
		ENContent = eNContent;
	}
}
