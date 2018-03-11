package com.easybuy.model;

public class EProduct {
	private int EPId;
	private String EPName;
	private String EPDesc;
	private int EPPrice;
	private int EPStock;
	private int EPCId;
	private int EPCChildId;
	private String EPFile;
	private int isSpecialPrice;
	private int EPSaleNum;

	public int getEPSaleNum() {
		return EPSaleNum;
	}

	public void setEPSaleNum(int ePSaleNum) {
		EPSaleNum = ePSaleNum;
	}

	public int getEPId() {
		return EPId;
	}

	public void setEPId(int ePId) {
		EPId = ePId;
	}

	public String getEPName() {
		return EPName;
	}

	public void setEPName(String ePName) {
		EPName = ePName;
	}

	public String getEPDesc() {
		return EPDesc;
	}

	public void setEPDesc(String ePDesc) {
		EPDesc = ePDesc;
	}

	public int getEPPrice() {
		return EPPrice;
	}

	public void setEPPrice(int ePPrice) {
		EPPrice = ePPrice;
	}

	public int getEPStock() {
		return EPStock;
	}

	public void setEPStock(int ePStock) {
		EPStock = ePStock;
	}

	public int getEPCId() {
		return EPCId;
	}

	public void setEPCId(int ePCId) {
		EPCId = ePCId;
	}

	public int getEPCChildId() {
		return EPCChildId;
	}

	public void setEPCChildId(int ePCChildId) {
		EPCChildId = ePCChildId;
	}

	public String getEPFile() {
		return EPFile;
	}

	public void setEPFile(String ePFile) {
		EPFile = ePFile;
	}

	public int getIsSpecialPrice() {
		return isSpecialPrice;
	}

	public void setIsSpecialPrice(int isSpecialPrice) {
		this.isSpecialPrice = isSpecialPrice;
	}

}
