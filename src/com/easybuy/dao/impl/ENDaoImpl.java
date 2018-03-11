package com.easybuy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.sql.Date;
import com.easybuy.dao.ENDao;
import com.easybuy.dbutil.DBUtil;
import com.easybuy.model.ENews;

public class ENDaoImpl implements ENDao {

	Connection conn = DBUtil.getConn();
	PreparedStatement pstm = null;
	ResultSet rs = null;
	String sql = null;

	// 获取所有新闻
	@Override
	public List<ENews> newsTitle() {
		// TODO Auto-generated method stub
		List<ENews> newsTitle = null;
		sql = "select * from en_news";
		try {
			newsTitle = new LinkedList<ENews>();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ENews e = new ENews();
				e.setENId(rs.getInt("en_id"));
				e.setENTitle(rs.getString("en_title"));
				e.setENContent(rs.getString("en_content"));
				e.setENTime(rs.getDate("en_create_time"));
				newsTitle.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				DBUtil.DBclose(conn, pstm, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return newsTitle;

	}

	public ENews newsContent(String newsTitle) {
		sql = "select * from en_news where en_title = ?";
		ENews e = new ENews();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, newsTitle);
			rs = pstm.executeQuery();
			while (rs.next()) {

				e.setENId(rs.getInt("en_id"));
				e.setENTitle(rs.getString("en_title"));
				e.setENContent(rs.getString("en_content"));
				e.setENTime(rs.getDate("en_create_time"));

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		finally {
			try {
				DBUtil.DBclose(conn, pstm, rs);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return e;
	}

	@Override
	public boolean delNewsById(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from en_news where en_id =?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		boolean isDel = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			int a = pstm.executeUpdate();

			if (a > 0) {
				isDel = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isDel;
	}

	@Override
	public ENews findNewsById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from en_news where en_id=?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ENews en = new ENews();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			while (rs.next()) {
				en.setENContent(rs.getString("en_content"));
				en.setENId(rs.getInt("en_id"));
				en.setENTime(rs.getDate("en_create_time"));
				en.setENTitle(rs.getString("en_title"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return en;
	}

	@Override
	public boolean updateNewsById(ENews news, int id) {
		// TODO Auto-generated method stub
		String sql = "update en_news set en_title=?,en_content=?,en_create_time=? where en_id = ?";
		Connection conn = DBUtil.getConn();
		PreparedStatement pstm = null;
		boolean isUpdate = false;
		try {
			pstm = conn.prepareStatement(sql);

			pstm.setString(1, news.getENTitle());
			pstm.setString(2, news.getENContent());
			pstm.setDate(3, news.getENTime());
			pstm.setInt(4, news.getENId());

			int a = pstm.executeUpdate();
			if (a > 0) {
				isUpdate = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUpdate;
	}
}
