package com.easybuy.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Service;

import com.easybuy.model.ENews;
import com.easybuy.service.impl.ENewsServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class ShowNews
 */
@WebServlet("/ShowNewsServlet")
public class ShowNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowNewsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 需要返回一个新闻列表
		String param = request.getParameter("param");
		if (param != null && param.equals("newsListPage")) {
			newsList(request, response);
		}

		if (param != null && param.equals("newsDetailPage")) {
			newsDetail(request, response);
		}
	}

	void newsList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<ENews> newsList = new ENewsServiceImpl().newsTitle();
		Gson gson = new Gson();
		String newsTitleJson = gson.toJson(newsList);
		System.out.println(newsTitleJson);
		response.getWriter().write(newsTitleJson);
	}

	void newsDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String newsTitle = request.getParameter("newsTitle");
		System.out.println("cdcdcddc"+newsTitle);
		if (newsTitle != null) {
			ENews newsContent = new ENewsServiceImpl().newsContent(newsTitle);
			request.setAttribute("newsContent", newsContent);
			request.getRequestDispatcher("news-view.jsp").forward(request, response);
		}

		/*
		 * 本来想使用ajax 使用HTML显示，遗留下的代码。 List<ENews> newsContent = new
		 * ENewsServiceImpl().newsContent("newsTitle"); Gson gson = new Gson(); String
		 * newsContentJson = gson.toJson(newsContent); System.out.println("nihao" +
		 * newsContentJson); response.getWriter().write(newsContentJson);
		 */
		// 接下来就是根据newsTitle来查询content

		// 重定向到新闻展示页面：在没有的世界里
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
