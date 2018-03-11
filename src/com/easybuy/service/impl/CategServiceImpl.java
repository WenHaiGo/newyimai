package com.easybuy.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.easybuy.dao.CategDao;
import com.easybuy.dao.impl.CategDaoImpl;
import com.easybuy.dbutil.CategUtils;
import com.easybuy.model.EPCateg;
import com.easybuy.service.CategService;

public class CategServiceImpl implements CategService {

	@Override
	public List<EPCateg> getByPid(int pid) throws SQLException {
		// TODO Auto-generated method stub
		CategDao cd = new CategDaoImpl();
		return cd.getByPid(pid);
	}

	@Override
	public List<CategUtils> getCategoryOfTree() throws SQLException {
		// TODO Auto-generated method stub
		// 一个大类对应于一个工具类实例 ，所以size=大类个数
		List<CategUtils> CUList = new ArrayList<>();
		CategUtils cus = null;
		// 大类 集合
		List<EPCateg> pList = getByPid(0);
		for (int i = 0; i < pList.size(); i++) {
			CategUtils temp = new CategUtils();
			// 得到一个大类下所有的子类保存到工具类的list里面
			temp.setList(getByPid(pList.get(i).getEPCId()));
			temp.setName(pList.get(i).getEPCName());
			temp.setId(pList.get(i).getEPCId());

			CUList.add(temp);
		}
		return CUList;
	}

	@Override
	public boolean delCateg(int id) throws SQLException {
		// TODO Auto-generated method stub
		return new CategDaoImpl().delCateg(id);
	}

	@Override
	public EPCateg getByCid(int cid) throws SQLException {
		// TODO Auto-generated method stub
		return new CategDaoImpl().getByCid(cid);
	}

	@Override
	public boolean updateById(int cid, EPCateg categ) throws SQLException {
		// TODO Auto-generated method stub
		return new CategDaoImpl().updateById(cid, categ);
	}

	@Override
	public int getIdByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return new CategDaoImpl().getIdByName(name);
	}

	@Override
	public boolean delByCid(int cid) throws SQLException {
		// TODO Auto-generated method stub
		return new CategDaoImpl().delByCid(cid);
	}

}
