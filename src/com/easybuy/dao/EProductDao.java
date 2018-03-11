package com.easybuy.dao;

import java.sql.SQLException;
import java.util.List;

import com.easybuy.model.ECartProduct;
import com.easybuy.model.EPCateg;
import com.easybuy.model.EProduct;

public interface EProductDao {

	List<EProduct> getSpecialSaleProduct(int isSpecialSale);

	EProduct getDetailProduct(int EPId);

	List<EPCateg> getCateg();

	List<EProduct> getHotProduct(int saleNum);

	List<EProduct> getCategProduct(int EPCId, int pageNo, int pageSize);

	List<EProduct> getAllCartProduct(String EPUId);

	EProduct getProductById(int productId);

	List<ECartProduct> getCartProductInfo(String EPUId);

	Boolean deleCartProductByPid(int EPId);

	// 得到所有的商品
	List<EProduct> getAllProduct() throws SQLException;

	/**
	 * 
	 * @EUId 传入当前登录的用户名
	 * @EPId 传入要保存的商品id
	 * @return 是否保存成功
	 */
	Boolean cartSave(String EUId, int EPId, int productNum);

	// 获取某一分类下的商品数量
	int getProductNumByCategId(int EPCId);

	// 新添加商品
	boolean save(EProduct e) throws SQLException;

}
