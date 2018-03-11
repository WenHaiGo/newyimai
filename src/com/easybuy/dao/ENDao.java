package com.easybuy.dao;

import java.util.List;

import com.easybuy.model.ENews;

public interface ENDao {
	// 获取所有新闻
	public List<ENews> newsTitle();

	// 根据标题获取内容
	public ENews newsContent(String newsTitle);

	public boolean delNewsById(int id);

	ENews findNewsById(int id);

	boolean updateNewsById(ENews news,int id);

}
