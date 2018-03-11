package com.easybuy.dao;

import java.sql.SQLException;
import java.util.List;

import com.easybuy.model.ENews;
import com.easybuy.model.EUser;

public interface EUDao {
	/**
	 * 传入一个User对象，成功返回true，失败返回false
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	boolean save(String EUId, String pwd) throws SQLException;

	public Boolean CheckEUId(String eUId);

	public Boolean checkEUId(String EUId, String EUPwd);

	List<EUser> showAllUser();

	public boolean saveUserFromManage(EUser user);

	// 删除用户
	boolean delUserById(int id);

	// 修改用户
	boolean updateUserById(EUser user,int id);
	//根据id得到用户
	EUser findUserById(int id);
	
}
