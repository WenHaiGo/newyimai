package com.easybuy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.easybuy.dao.CategDao;
import com.easybuy.dbutil.DBUtil;
import com.easybuy.model.EPCateg;

public class CategDaoImpl implements CategDao {

	// 通过pid得到所有分类
	@Override
	public List<EPCateg> getByPid(int pid) throws SQLException {
		// TODO Auto-generated method stub
		List<EPCateg> list = new ArrayList<EPCateg>();
		String sql = "select epc_id,epc_name,epc_parent_id from e_category  where epc_parent_id = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, pid);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			EPCateg c = new EPCateg();
			c.setEPCId(rs.getInt("epc_id"));
			c.setEPCName(rs.getString("epc_name"));
			c.setEPCParentId(rs.getInt("epc_parent_id"));
			list.add(c);
		}
		try {

		} finally {
			DBUtil.DBclose(conn, pstm, rs);
		}
		return list;
	}

	@Override
	public boolean delCateg(int id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from e_category where epc_id=?";
		boolean isDel = false;
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		int a = pstm.executeUpdate();
		if (a > 0) {
			isDel = true;
		}
		try {

		} finally {
			DBUtil.DBclose(conn, pstm);
		}
		return isDel;
	}

	@Override
	public EPCateg getByCid(int cid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select epc_id,epc_name,epc_parent_id from e_category  where epc_id = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, cid);
		ResultSet rs = pstm.executeQuery();
		EPCateg c = null;
		while (rs.next()) {
			c = new EPCateg();
			c.setEPCId(rs.getInt("epc_id"));
			c.setEPCName(rs.getString("epc_name"));
			c.setEPCParentId(rs.getInt("epc_parent_id"));
		}
		try {

		} finally {
			DBUtil.DBclose(conn, pstm, rs);
		}
		return c;
	}

	@Override
	public boolean updateById(int cid, EPCateg categ) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update e_category set epc_name= ?,epc_parent_id =? where epc_id = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, categ.getEPCName());
		pstm.setInt(2, categ.getEPCParentId());
		pstm.setInt(3, cid);
		boolean isUpdate = false;
		int a = pstm.executeUpdate();
		if (a > 0) {
			isUpdate = true;
		}
		try {

		} finally {
			DBUtil.DBclose(conn, pstm);
		}
		return isUpdate;
	}

	@Override
	public int getIdByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select epc_id from  e_category  where epc_name = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, name);
		ResultSet rs = pstm.executeQuery();
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("epc_id");

		}
		try {

		} finally {
			DBUtil.DBclose(conn, pstm, rs);
		}
		return id;
	}

	@Override
	public boolean delByCid(int cid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from e_category where epc_id = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, cid);
		int temp = pstm.executeUpdate();
		System.out.println("数据库"+cid);
		System.out.println("数据库"+temp);
		try {

		} finally {
			DBUtil.DBclose(conn, pstm);
		}
		return temp > 0 ? true : false;

	}

}
