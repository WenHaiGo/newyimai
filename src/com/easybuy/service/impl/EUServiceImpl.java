package com.easybuy.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.easybuy.dao.impl.EUDaoImpl;
import com.easybuy.dbutil.DBUtil;
import com.easybuy.model.EUser;
import com.easybuy.service.EUService;

public class EUServiceImpl implements EUService {

	@Override
	public boolean save(String EUId, String pwd) throws SQLException {
		// TODO Auto-generated method stub

		// 注册添加用户到数据库
		return new EUDaoImpl().save(EUId, pwd);

	}

	public Boolean checkEUId(String EUId) {
		// TODO Auto-generated method stub
		EUDaoImpl dao = new EUDaoImpl();
		return dao.CheckEUId(EUId);
	}

	@Override
	public Boolean checkEUId(String EUId, String EUPwd) {
		// TODO Auto-generated method stub
		return new EUDaoImpl().checkEUId(EUId, EUPwd);
	}

	@Override
	public List<EUser> showAllUser() {
		// TODO Auto-generated method stub
		return new EUDaoImpl().showAllUser();
	}

	@Override
	public boolean saveUserFromManage(EUser user) {
		// TODO Auto-generated method stub
		return new EUDaoImpl().saveUserFromManage(user);
	}

	@Override
	public boolean updateUserById(EUser user, int id) {
		// TODO Auto-generated method stub
		return new EUDaoImpl().updateUserById(user, id);
	}

	@Override
	public boolean delUserById(int id) {
		// TODO Auto-generated method stub
		return new EUDaoImpl().delUserById(id);
	}

	@Override
	public EUser findUserById(int id) {
		// TODO Auto-generated method stub
		return new EUDaoImpl().findUserById(id);
	}
	
	

}
