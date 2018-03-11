package com.easybuy.dao;

import java.sql.SQLException;
import java.util.List;

import com.easybuy.model.EPCateg;

public interface CategDao {
	// 通过pid获取所有分类
	List<EPCateg> getByPid(int pid) throws SQLException;

	boolean delCateg(int delId) throws SQLException;

	EPCateg getByCid(int cid) throws SQLException;

	boolean updateById(int cid, EPCateg categ) throws SQLException;

	int getIdByName(String name) throws SQLException;
	boolean delByCid(int cid) throws SQLException;
}
