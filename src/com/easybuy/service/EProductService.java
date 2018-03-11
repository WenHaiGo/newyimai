package com.easybuy.service;

import java.sql.SQLException;
import java.util.List;

import com.easybuy.dbutil.PageUtil;
import com.easybuy.model.ECartProduct;
import com.easybuy.model.EPCateg;
import com.easybuy.model.EProduct;

public interface EProductService {
	List<EProduct> getSpecialSaleProduct(int isSpecialSale);

	EProduct getDetailProduct(int EPId);

	// 获取所有菜单
	List<EPCateg> getCateg();

	List<EProduct> getHotProduct(int saleNum);

	// 根据传入的EPCId获取某一个分类下所有的商品

	PageUtil<EProduct> getCategProduct(int EPCId, int pageNo, int pageSize);

	List<EProduct> getAllCartProduct(String EPUId);

	List<ECartProduct> getCartProductInfo(String EPUId);

	Boolean deleCartProductByPid(int EPId);

	/**
	 * 
	 * @EUId 传入当前登录的用户名
	 * @EPId 传入要保存的商品id
	 * @return 是否保存成功
	 */
	Boolean cartSave(String EUId, int EPId, int productNum);

	// 得到所有的商品
	List<EProduct> getAllProduct() throws SQLException;

	// 新增加商品
	boolean save(EProduct e) throws SQLException;
}
