package com.easybuy.service.impl;

import java.util.List;

import com.easybuy.dao.impl.ENDaoImpl;
import com.easybuy.dao.impl.EUDaoImpl;
import com.easybuy.model.ENews;
import com.easybuy.service.ENewsService;

public class ENewsServiceImpl implements ENewsService {

	@Override
	public List<ENews> newsTitle() {
		// TODO Auto-generated method stub
		return new ENDaoImpl().newsTitle();
	}

	
	public ENews newsContent(String newsTitle) {
		// TODO Auto-generated method stub
		return new ENDaoImpl().newsContent(newsTitle);
	}


	@Override
	public boolean delNewsById(int id) {
		// TODO Auto-generated method stub
		return new ENDaoImpl().delNewsById(id);
	}


	@Override
	public ENews findNewsById(int id) {
		// TODO Auto-generated method stub
		return new ENDaoImpl().findNewsById(id);
	}


	@Override
	public boolean updateNewsById(ENews news,int id) {
		// TODO Auto-generated method stub
		return new ENDaoImpl().updateNewsById(news, id) ;
	}

}
