package com.easybuy.service;

import java.util.List;

import com.easybuy.model.ENews;

public interface ENewsService {
	// 查询所有的新闻标题列表
	List<ENews> newsTitle();

	// 根据标题来查询对应的内容
	ENews newsContent(String newsTitle);

	boolean delNewsById(int id);
	
	ENews findNewsById(int id);
	boolean updateNewsById(ENews news,int id);
}
