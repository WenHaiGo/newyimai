package com.easybuy.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.easybuy.dao.impl.EProductDaoImpl;
import com.easybuy.dbutil.PageUtil;
import com.easybuy.model.ECartProduct;
import com.easybuy.model.EPCateg;
import com.easybuy.model.EProduct;
import com.easybuy.service.EProductService;

public class EProductServiceImpl implements EProductService {

	@Override
	public List<EProduct> getSpecialSaleProduct(int isSpecialSale) {
		// TODO Auto-generated method stub
		return new EProductDaoImpl().getSpecialSaleProduct(isSpecialSale);
	}

	public EProduct getDetailProduct(int EPId) {
		return new EProductDaoImpl().getDetailProduct(EPId);
	}

	@Override
	public List<EPCateg> getCateg() {
		// TODO Auto-generated method stub
		return new EProductDaoImpl().getCateg();
	}

	public List<EProduct> getHotProduct(int saleNum) {
		return new EProductDaoImpl().getHotProduct(saleNum);
	}

	// 获取某一个分类下的所有商品 有分页功能
	@Override
	public PageUtil<EProduct> getCategProduct(int EPCId, int pageNo, int pageSize) {
		// TODO Auto-generated method stub

		PageUtil<EProduct> pageUtil = new PageUtil<>();

		EProductDaoImpl epDao = new EProductDaoImpl();
		// 为了获取总页数 需要先获取总的条数还需要在dao里面把这个搞定

		int totalNumData = epDao.getProductNumByCategId(EPCId);
		// 这里不要使用math.ceil这个方法，这个方法处理分数会出错。
		int totalPages = totalNumData % pageSize == 0 ? totalNumData / pageSize : totalNumData / pageSize + 1;
		if (pageNo <= 1) {
			pageNo = 1;
		}
		if (pageNo >= totalPages) {
			pageNo = totalPages;
		}

		pageUtil.setList(epDao.getCategProduct(EPCId, pageNo, pageSize));
		pageUtil.setPageNo(pageNo);
		pageUtil.setPageSize(pageSize);
		pageUtil.setTotalPage(totalPages);
		return pageUtil;
	}

	// 得到购物车所有商品
	@Override
	public List<EProduct> getAllCartProduct(String EPUId) {
		// TODO Auto-generated method stub
		return new EProductDaoImpl().getAllCartProduct(EPUId);
	}

	@Override
	public List<ECartProduct> getCartProductInfo(String EPUId) {
		// TODO Auto-generated method stub
		return new EProductDaoImpl().getCartProductInfo(EPUId);
	}

	// 通过id把购物车商品删除掉
	@Override
	public Boolean deleCartProductByPid(int EPId) {
		// TODO Auto-generated method stub
		return new EProductDaoImpl().deleCartProductByPid(EPId);
	}

	// 添加到购物车
	@Override
	public Boolean cartSave(String EUId, int EPId, int productNum) {
		// TODO Auto-generated method stub
		return new EProductDaoImpl().cartSave(EUId, EPId, productNum);
	}

	@Override
	public List<EProduct> getAllProduct() throws SQLException {
		// TODO Auto-generated method stub

		return new EProductDaoImpl().getAllProduct();
	}

	@Override
	public boolean save(EProduct e) throws SQLException {
		// TODO Auto-generated method stub
		
		return new EProductDaoImpl().save(e);
	}

}
