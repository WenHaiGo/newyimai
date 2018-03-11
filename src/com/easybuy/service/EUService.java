package com.easybuy.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.catalina.User;

import com.easybuy.model.EUser;

public interface EUService {

	// 用于注册使用
	boolean save(String EUId, String pwd) throws SQLException;

	// 用于注册查询
	public Boolean checkEUId(String EUId);

	// 用于登陆使用
	public Boolean checkEUId(String EUId, String EUPwd);
	//后台管理展示所有用户
	List<EUser> showAllUser();
	
	boolean saveUserFromManage(EUser user);
	
	public boolean updateUserById(EUser user, int id);
	
	public boolean delUserById(int id); 
	
	EUser findUserById(int id);
	
}
